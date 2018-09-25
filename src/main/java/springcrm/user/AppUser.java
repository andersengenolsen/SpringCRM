package springcrm.user;

import springcrm.validator.FieldMatch;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entity class representing a User of the application, used for registration through the admin panel.
 */
@FieldMatch.List({
        @FieldMatch(first = "password", second = "passwordVerif", message = "The password fields must match")
})
public class AppUser {

    @NotNull
    @Size(min = 1, message = "is required")
    private String userName;

    @NotNull
    @Size(min = 6, message = "is required")
    private String password;

    @NotNull
    @Size(min = 6, message = "is required")
    private String passwordVerif;

    @NotNull
    public String getUserName() {
        return userName;
    }

    public void setUserName(@NotNull String userName) {
        this.userName = userName;
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
}
