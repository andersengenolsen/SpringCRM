package springcrm.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

/**
 * Entity class representing a User of the application, used for registration through the admin panel.
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "is required")
    @Size(min = 3, message = "is required")
    @Column(name = "username")
    private String username;

    @NotNull(message = "is required")
    @Size(min = 6, message = "is required")
    @Column(name = "password", columnDefinition = "char(68)")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_has_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    /**
     * This field is NOT a column in the database table!
     */
    @NotNull(message = "is required")
    @Size(min = 6, message = "is required")
    @Transient
    private String passwordVerif;

    /* -- CONSTRUCTORS -- */

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, Collection<Role> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    /* -- GETTERS AND SETTERS -- */

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @NotNull
    public String getUsername() {
        return username;
    }

    public void setUsername(@NotNull String username) {
        this.username = username;
    }

    @NotNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NotNull String password) {
        this.password = password;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    @NotNull
    public String getPasswordVerif() {
        return passwordVerif;
    }

    public void setPasswordVerif(@NotNull String passwordVerif) {
        this.passwordVerif = passwordVerif;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
