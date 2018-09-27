package springcrm.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AppUser {

    private Integer id;

    @NotNull(message = "is required")
    @Size(min = 3, message = "is required")
    private String username;

    @NotNull(message = "is required")
    @Size(min = 6, message = "is required")
    private String password;

    @NotNull(message = "is required")
    @Size(min = 6, message = "is required")
    private String passwordVerif;

    @NotNull
    private String formRole;

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

    @NotNull
    public String getPasswordVerif() {
        return passwordVerif;
    }

    public void setPasswordVerif(@NotNull String passwordVerif) {
        this.passwordVerif = passwordVerif;
    }

    @NotNull
    public String getFormRole() {
        return formRole;
    }

    public void setFormRole(@NotNull String formRole) {
        this.formRole = formRole;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
