package ru.inno.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruav on 19.01.17.
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

        ru.inno.pojo.User userIn = userRepository.findByLogin(username).getUser();

        List<SimpleGrantedAuthority> authList = new ArrayList<>();
        authList.add(new SimpleGrantedAuthority("ROLE_USER"));
        if(userIn.isAdmin()){
            authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        UserDetails user = new User(username, userIn.getPassword(),
                true, true, true,
                true, authList);

        return user;
    }
}
