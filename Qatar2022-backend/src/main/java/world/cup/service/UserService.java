package world.cup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import world.cup.models.User;
import world.cup.repositories.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service(value = "userService")
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        User user = getUsers().stream().filter(t -> id.equals(t.getId()))
                .findFirst()
                .orElse(null);
        return user;

    }

    public User addNewUser(User user, BindingResult bindingResult) {
        Optional<User> userOptional = userRepository.findUserByEmail(user.getEmail());
        String password=user.getPassword();
        String pw_hash = BCrypt.hashpw(password, BCrypt.gensalt(10));
        user.setPassword(pw_hash);
        if (userOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }
        userOptional = userRepository.findByUsername(user.getUsername());
        if (userOptional.isPresent()){
            throw new IllegalStateException("username taken");
        }
        if(bindingResult.hasErrors()){
            throw new IllegalStateException(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        userRepository.save(user);
        return user;
    }



    public void DeleteUser(Long id){
        Optional<User> userId = userRepository.findById(id);
        if(!userId.isPresent()){
            throw new IllegalStateException("user does not exist");
        }
        userId.get().setRoles(null);
        userRepository.deleteById(id);
    }

    public void updateUser(Long userId, User userUpdate) {
        User user = userRepository.findById(userId).orElseThrow(()-> new IllegalStateException(
                "user with id " + userId + " does not exist"));
        if (userUpdate.getFirstName()!=null &&
                userUpdate.getFirstName().length() > 0 &&
                !Objects.equals(user.getFirstName(), userUpdate.getFirstName())){
            user.setFirstName(userUpdate.getFirstName());
        }
        if (userUpdate.getLastName()!=null &&
                userUpdate.getLastName().length() > 0 &&
                !Objects.equals(user.getLastName(), userUpdate.getLastName())){
            user.setLastName(userUpdate.getLastName());
        }
        if (userUpdate.getEmail()!=null &&
                userUpdate.getEmail().length()>0 &&
                !Objects.equals(user.getEmail(), userUpdate.getEmail())){
            Optional<User> userOptional = userRepository.findUserByEmail(userUpdate.getEmail());
            if (userOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            user.setEmail(userUpdate.getEmail());
        }

        if (userUpdate.getPassword()!=null &&
                userUpdate.getPassword().length() > 0 &&
                !Objects.equals(user.getPassword(), userUpdate.getPassword())){
            String pw_hash = BCrypt.hashpw(userUpdate.getPassword(), BCrypt.gensalt(10));
            user.setPassword(pw_hash);
        }

        if (userUpdate.getCountry()!=null &&
                userUpdate.getCountry().length() > 0 &&
                !Objects.equals(user.getCountry(), userUpdate.getCountry())){
            user.setCountry(userUpdate.getCountry());
        }

        if (userUpdate.getTel()!=null &&
                userUpdate.getTel().length() > 0 &&
                !Objects.equals(user.getTel(), userUpdate.getTel())){
            user.setTel(userUpdate.getTel());
        }

        if (userUpdate.getDob()!=null &&
                userUpdate.getDob().toString().length() > 0 &&
                !Objects.equals(user.getDob(), userUpdate.getDob())){
            user.setDob(userUpdate.getDob());
        }

        if (userUpdate.getUsername()!=null &&
                userUpdate.getUsername().length() > 0 &&
                !Objects.equals(user.getUsername(), userUpdate.getUsername())){
            user.setUsername(userUpdate.getUsername());
        }

        if (userUpdate.getImage()!=null &&
                userUpdate.getImage().length() > 0 &&
                !Objects.equals(user.getImage(), userUpdate.getImage())){
            user.setImage(userUpdate.getImage());
        }

        userRepository.save(user);

    }
}
