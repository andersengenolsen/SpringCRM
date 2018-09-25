package springcrm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import springcrm.dao.RoleDao;
import springcrm.dao.UserDao;
import springcrm.entity.Role;
import springcrm.entity.User;
import springcrm.user.AppUser;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of {@link Service}
 * Providing access through the use of {@link springcrm.dao.UserDaoImpl}
 * <p>
 * Implements custom methods from {@link Service}, and mandatory interface {@link UserService}
 */
@org.springframework.stereotype.Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * Returning user by username
     *
     * @param userName username
     * @return User object, null if not found
     */
    @Override
    @Transactional
    public User findByUserName(String userName) {
        return userDao.findByUserName(userName);
    }

    /**
     * Returning all users in the database
     *
     * @return all users
     */
    @Override
    @Transactional
    public List<User> getAll() {
        return userDao.getAll();
    }

    /**
     * Encrypting password and saving user to the database
     *
     * @param user to save
     */
    @Override
    @Transactional
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //TODO: Add roles!
        user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_user")));
        userDao.save(user);
    }

    /**
     * @param id to delete
     */
    @Override
    @Transactional
    public void delete(int id) {
        userDao.delete(id);
    }


    // TODO: Implement DB calls!
    @Override
    public User get(int id) {
        return null;
    }


    @Override
    public void delete(User T) {

    }

    /**
     * Used to check whether a given username already exists
     *
     * @throws UsernameNotFoundException
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = findByUserName(userName);

        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    /**
     * Mapping roles to authorities
     */
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}