package world.cup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import world.cup.models.User;
import world.cup.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService=userService;
    }

    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping(path = "{userId}")
    User getUser(@PathVariable Long userId) {
        return userService.getUser(userId);
    }

    @PostMapping("/signup")
    public User registerNewUser(@Valid @RequestBody User user, BindingResult bindingResult){
        return userService.addNewUser(user,bindingResult);
    }

    @DeleteMapping("{id}")
    public void DeleteUserById(@PathVariable Long id) {
        userService.DeleteUser(id);
    }

    @PutMapping(path="{userId}")
    public void updateUser(
            @PathVariable("userId") Long userId,
            @RequestBody User userUpdate
    ){
        userService.updateUser(userId, userUpdate);

    }
}
