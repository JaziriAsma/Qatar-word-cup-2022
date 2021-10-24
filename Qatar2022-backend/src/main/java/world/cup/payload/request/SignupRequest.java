package world.cup.payload.request;

import javax.persistence.Lob;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Set;
 
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @NotNull(message = "Firstname cannot be null")
    @Size(min = 2, max = 20, message = "Firstname between 2 and 20 characters")
    private String firstName;

    @NotBlank
    @NotNull(message = "Lastname cannot be null")
    @Size(min = 2, max = 20, message = "Lastname between 2 and 20 characters")
    private String lastName;

    @NotBlank
    @NotNull(message = "Email cannot be null")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank
    @NotNull(message = "Country cannot be null")
    private String country;

    @NotBlank
    @NotNull(message = "Phone number cannot be null")
    @Size(min = 8, max = 15, message = "Email between 8 and 15 characters")
    private String tel;

    @NotNull(message = "Dob cannot be null")
    @PastOrPresent
    private LocalDate dob;
    
    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    @Lob
    private String image;
  
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Set<String> getRole() {
      return this.role;
    }
    
    public void setRole(Set<String> role) {
      this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

