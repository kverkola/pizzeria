package ua.opu.dl.pizzeria.util.constraints;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import ua.opu.dl.pizzeria.dao.UserDao;
import ua.opu.dl.pizzeria.model.Users;


public class UserDetailsImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException, DataAccessException {
        Users user = userDao.loadByLogin(username);
        List<Users> authList = new ArrayList<Users>();
        authList.add(user);
        return new User(user.getLogin(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                authList);
    }
}

