package springcrm.converter;

import org.springframework.stereotype.Component;
import springcrm.entity.User;
import springcrm.model.AppUser;

/**
 * {@link Converter}, converting from {@link AppUser} to {@link User}
 */
@Component
public class AppUserConverter implements Converter<AppUser, User> {

    /**
     *
     * @param appUser Converting from
     * @return {@link User} converted from {@link AppUser}
     */
    @Override
    public User convert(AppUser appUser) {
        User user = new User();
        user.setPassword(appUser.getPassword());
        user.setUsername(appUser.getUsername());

        if (appUser.getId() != null)
            user.setId(appUser.getId());

        return user;
    }
}
