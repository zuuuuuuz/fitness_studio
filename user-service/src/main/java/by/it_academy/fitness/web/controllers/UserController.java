package by.it_academy.fitness.web.controllers;

import by.it_academy.fitness.core.dto.Page;
import by.it_academy.fitness.core.dto.User;
import by.it_academy.fitness.core.dto.UserCreate;
import by.it_academy.fitness.service.UserHolder;
import by.it_academy.fitness.service.api.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    private final IUserService userService;
    private final UserHolder holder;

    public UserController(IUserService userService, UserHolder holder) {
            this.userService = userService;
            this.holder = holder;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> add(@RequestBody UserCreate userCreate){
        userService.add(userCreate);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page> getPageUsers(@RequestParam (name = "page", defaultValue = "0") Integer page,
                                             @RequestParam (name = "size", defaultValue = "20") Integer size){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getPageUsers(page, size));
    }
    @RequestMapping(path = "/{uuid}", method = RequestMethod.GET)
    public ResponseEntity<User> getCard(@PathVariable("uuid") UUID uuid) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getCard(uuid));
    }

    @RequestMapping(path = "/{uuid}/dt_update/{dt_update}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@PathVariable ("uuid") UUID uuid,
                                        @PathVariable ("dt_update") LocalDateTime dtUpdate,//должно быть LocalDateTime
                                        @RequestBody UserCreate userCreate){
        userService.updateUser(uuid, dtUpdate, userCreate);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @RequestMapping(path = "/me", method = RequestMethod.GET)
    public ResponseEntity<User> getMyCard() {
        return ResponseEntity.status(HttpStatus.OK).body(holder.getUser());
    }
}
