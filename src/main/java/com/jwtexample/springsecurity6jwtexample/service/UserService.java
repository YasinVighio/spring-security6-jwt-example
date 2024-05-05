package com.jwtexample.springsecurity6jwtexample.service;


import com.jwtexample.springsecurity6jwtexample.beans.JwtAuthResponse;
import com.jwtexample.springsecurity6jwtexample.Utils.JwtUtil;
import com.jwtexample.springsecurity6jwtexample.beans.LoginBean;
import com.jwtexample.springsecurity6jwtexample.dao.UserRepo;
import com.jwtexample.springsecurity6jwtexample.models.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserDTO> users = userRepo.findAll();
        UserDTO user = null;
        Optional<UserDTO> optionalUser = users.stream().filter(userDTO -> userDTO.getUsername().equals(username)).findFirst();

        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        UserDetails userDetails = User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getUserRoleTlByUserRoleId().getUserRoleName())
                .build();

        return userDetails;
    }

    public JwtAuthResponse logonUser(LoginBean requestBean) throws Exception{

        /**
         * Using authentication manager user is authenticated
         * If user is not authenticated it throws exception
         * if no exception is thrown that means user is authenticated
         * Now we get UserDetails from UserService and generate a token
         * We send the token to user
         */
        authenticate(requestBean.getUsername(), requestBean.getPassword());

        final UserDetails userDetails = loadUserByUsername(requestBean.getUsername());

        final String token = jwtUtil.generateToken(userDetails);

        JwtAuthResponse tokenResponse = new JwtAuthResponse();

        tokenResponse.setToken(token);
        tokenResponse.setSuccessful(true);

        return tokenResponse;
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
