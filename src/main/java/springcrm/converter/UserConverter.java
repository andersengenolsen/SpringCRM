package springcrm.converter;

import org.springframework.stereotype.Component;
import springcrm.entity.User;
import springcrm.model.AppUser;

/**
 * {@link Converter}, converting from {@link User} to {@link AppUser}
 */
@Component
public class UserConverter implements Converter<User, AppUser> {

    /**
     * @param user converting from
     * @return {@link AppUser} converted from {@link User}
     */
    @Override
    public AppUser convert(User user) {
        if (user == null)
            return null;

        AppUser appUser = new AppUser();

        appUser.setPassword(user.getPassword());
        appUser.setPasswordVerif(user.getPassword());
        appUser.setUsername(user.getUsername());

        if (user.getRole() != null)
            appUser.setFormRole(user.getRole().getName());

        if (user.getId() != null)
            appUser.setId(user.getId());

        return appUser;
    }
}
