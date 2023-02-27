package com.codeup.codeupspringblog.services;

import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.models.UserWithRoles;
import com.codeup.codeupspringblog.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

    @Service
    public class UserDetailsLoader extends UserDaoService {
        private final UserRepository userDao;

        public UserDetailsLoader(UserRepository userDao) {
            this.userDao = userDao;
        }

        public UserDetailsLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
            super(userRepository, passwordEncoder);
        }

        @Override
        public UserDetails loadUserByUsername(String username) {
            User user = userDao.findByUsername(username);
            if(user == null) {
                throw new UsernameNotFoundException("No user found for " + username);
            }

            return new UserWithRoles(user);
        }
    }
