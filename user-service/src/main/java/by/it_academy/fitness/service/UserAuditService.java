package by.it_academy.fitness.service;

import by.it_academy.fitness.core.audit.Audit;
import by.it_academy.fitness.core.dto.EssenceType;
import by.it_academy.fitness.core.dto.User;
import by.it_academy.fitness.core.dto.UserCreate;
import by.it_academy.fitness.core.dto.UserRegistration;
import by.it_academy.fitness.service.api.IAuditService;
import by.it_academy.fitness.service.api.IUserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Aspect
@Service
public class UserAuditService {
    private final IAuditService auditService;
    private final UserHolder userHolder;

    private final IUserService userService;

    public UserAuditService(IAuditService auditService, UserHolder userHolder,
                            IUserService userService) {
        this.auditService = auditService;
        this.userHolder = userHolder;
        this.userService = userService;
    }

    @After("execution(* by.it_academy.fitness.service.UserService.add(..))")
    public void add(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();

            UserCreate arg = (UserCreate) args[0];

            String uuid = userService.loadUserByUsername(arg.getMail()).getUuid().toString();
            User user = userHolder.getUser();

            Audit audit = new Audit();
            audit.setUser(user);
            audit.setText("Администратор создал User c UUID: " + uuid);
            audit.setType(EssenceType.USER);
            audit.setId(uuid);
            auditService.send(audit);

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @After("execution(* by.it_academy.fitness.service.UserService.updateUser(..))")
    public void AfterUpdateUser(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();

            UUID uuid = (UUID) args[0];

            Audit audit = new Audit();
            User user = userHolder.getUser();
            audit.setUser(user);
            audit.setText("Администратор обновил информации об User c UUID: " + uuid);
            audit.setType(EssenceType.USER);

            audit.setId(uuid.toString());
            auditService.send(audit);

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
    @After("execution(* by.it_academy.fitness.service.RegistrationService.addUser(..))")
    public void addUser(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();

            UserRegistration arg = (UserRegistration) args[0];

            String uuid = userService.loadUserByUsername(arg.getMail()).getUuid().toString();

            Audit audit = new Audit();
            audit.setText("Зарегистрировался User c UUID: " + uuid);
            audit.setType(EssenceType.USER);
            audit.setId(uuid);
            auditService.send(audit);

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
