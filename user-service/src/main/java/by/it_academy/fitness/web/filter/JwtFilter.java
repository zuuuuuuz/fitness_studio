package by.it_academy.fitness.web.filter;

import by.it_academy.fitness.core.dto.User;
import by.it_academy.fitness.service.api.IUserService;
import by.it_academy.fitness.web.utils.JwtTokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final IUserService userService;
    private final JwtTokenUtil jwtTokenUtil;

    public JwtFilter(IUserService userService, JwtTokenUtil jwtTokenUtil) {
        this.userService = userService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {

        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (isEmpty(header) || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        // Get jwt token and validate
        final String token = header.split(" ")[1].trim();
        if (!jwtTokenUtil.validate(token)) {
            chain.doFilter(request, response);
            return;
        }

        User user = userService
                .loadUserByUsername(jwtTokenUtil.getUserMail(token));
        List<SimpleGrantedAuthority> roles = new ArrayList<>();
//        roles.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));

        roles.add(new SimpleGrantedAuthority("ROLE_" + jwtTokenUtil.getUserRole(token)));
        UsernamePasswordAuthenticationToken
                authentication = new UsernamePasswordAuthenticationToken(
                user, null,
                roles
        );

        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }
}