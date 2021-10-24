package world.cup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import world.cup.models.Role;
import world.cup.models.User;
import world.cup.repositories.RoleRepository;
import world.cup.repositories.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    @Autowired
    public RoleService(RoleRepository roleRepository, UserRepository userRepository){
        this.roleRepository=roleRepository;
        this.userRepository=userRepository;
    }

    public List<Role> getRoles(){
        return roleRepository.findAll();
    }

    public Role getRole(Long id) {
        Role role = getRoles().stream().filter(t -> id.equals(t.getId()))
                .findFirst()
                .orElse(null);
        return role;
    }

    public void addNewRole(Role role, BindingResult bindingResult) {
        Optional<Role> roleOptional = roleRepository.findRoleByName(role.getName());
        if (roleOptional.isPresent()){
            throw new IllegalStateException("role already exists");
        }
        if(bindingResult.hasErrors()){
            throw new IllegalStateException(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        roleRepository.save(role);
    }

    @Transactional
    public void DeleteRole(Long id){
        Optional<Role> roleId = roleRepository.findById(id);
        if(!roleId.isPresent()){
            throw new IllegalStateException("role does not exist");
        }
        roleRepository.deleteById(id);
    }

    public void updateRole(Long roleId, Role roleUpdate) {
        Role role = roleRepository.findById(roleId).orElseThrow(()-> new IllegalStateException(
                "role with id " + roleId + " does not exist"));
        if (roleUpdate.getName() !=null &&
                roleUpdate.getName().toString().length() > 0 &&
                !Objects.equals(role.getName(), roleUpdate.getName())){
            role.setName(roleUpdate.getName());
        }

        roleRepository.save(role);
    }
    @Transactional
    public void linkNewUserToRole(Long userId, Long roleId){
        User user = userRepository.findById(userId).orElseThrow(()-> new IllegalStateException(
                "user with id " + userId + " does not exist"));
        Role role = roleRepository.findById(roleId).orElseThrow(()-> new IllegalStateException(
                "role with id " + roleId + " does not exist"));
        Set<Role> userRoles = user.getRoles();
        //userRoles.clear();
        userRoles.add(role);
        user.setRoles(userRoles);
        userRepository.save(user);

    }

    @Transactional
    public void clearRoles(Long userId){
        User user = userRepository.findById(userId).orElseThrow(()-> new IllegalStateException(
                "user with id " + userId + " does not exist"));
        Set<Role> userRoles = user.getRoles();
        userRoles.clear();
        user.setRoles(userRoles);
        //userRepository.save(user);
    }
}