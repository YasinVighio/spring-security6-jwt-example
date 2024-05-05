package com.jwtexample.springsecurity6jwtexample.configs;

import com.jwtexample.springsecurity6jwtexample.Utils.JwtUtil;
import com.jwtexample.springsecurity6jwtexample.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class JwtFilter extends OncePerRequestFilter {


    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Step 1: Check if rquest has header named authorization (you can change nane)

        final String authHeader = request.getHeader("AUTHORIZATION");

        String jwtToken = null;
        String username = null;

        if(authHeader!=null && authHeader.startsWith("Bearer")){

            //STEP 2: cut the bearer part and get token only

            jwtToken = authHeader.substring(7);
            try {

                //STEP 3: get the username from token

                username = jwtUtil.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                logger.warn("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                logger.warn("JWT Token has expired");
            }
        } else {
            logger.warn("JWT Token does not begin with Bearer String");
        }

        //STEP 4: Validate username and token


        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {


            //STEP 5: check if user exists in db
            UserDetails userDetails = this.userService.loadUserByUsername(username);

            if (jwtUtil.validateToken(jwtToken, userDetails)) {

                //STEP 6: if user exists in db and token is not expired then update security context

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        //STEP 7
        filterChain.doFilter(request, response);
    }
}
