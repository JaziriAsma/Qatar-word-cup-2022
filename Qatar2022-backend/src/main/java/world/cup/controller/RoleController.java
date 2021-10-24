package world.cup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import world.cup.models.Role;
import world.cup.service.RoleService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/role")
public class RoleController {
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService){
        this.roleService=roleService;
    }

    @GetMapping
    public List<Role> getRoles(){
        return roleService.getRoles();
    }

    @GetMapping(path = "{roleId}")
    Role getRole(@PathVariable Long roleId) {
        return roleService.getRole(roleId);
    }

    @PostMapping
    public void registerNewRole(@Valid @RequestBody Role role, BindingResult bindingResult){
        roleService.addNewRole(role,bindingResult);
    }

    @PutMapping(path="{roleId}")
    public void updateRole(
            @PathVariable("roleId") Long roleId,
            @RequestBody Role roleUpdate
    ){
        roleService.updateRole(roleId, roleUpdate);

    }

    @DeleteMapping("{id}")
    public void DeleteRoleById(@PathVariable Long id) {
        roleService.DeleteRole(id);
    }

    @GetMapping(path="/link/{userId}/{roleId}")
    public void linkUserToRole(@PathVariable("userId") Long userId, @PathVariable("roleId") Long roleId){
        roleService.linkNewUserToRole(userId, roleId);
    }

    @GetMapping(path="/clear/{userId}")
    public void clearRoles(@PathVariable("userId") Long userId){
        roleService.clearRoles(userId);
    }
}
