package world.cup.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
@Table(	name = "users", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "username"),
			@UniqueConstraint(columnNames = "email") 
		})
public class User {
	@Id
	@SequenceGenerator(
			name = "user_sequence",
			sequenceName = "user_sequence",
			allocationSize = 1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "user_sequence"
	)
	private Long id;

	@NotBlank
	@Size(max = 20)
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

	@NotBlank
	@Size(max = 120)
	private String password;

	@Lob
	private String image;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	public User() {
	}

	public User (Long id, String firstName, String lastName, String email, String country, String tel, LocalDate dob, String password,String username, String image) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.country = country;
		this.tel = tel;
		this.dob = dob;
		this.password=password;
		this.username=username;
		this.image = image;
	}

	public User (String firstName, String lastName, String email, String country, String tel, LocalDate dob,String password,String username, String image) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.country = country;
		this.tel = tel;
		this.dob = dob;
		this.password=password;
		this.username=username;
		this.image = image;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
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

