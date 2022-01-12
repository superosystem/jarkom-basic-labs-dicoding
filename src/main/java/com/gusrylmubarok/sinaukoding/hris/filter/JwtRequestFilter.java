package com.gusrylmubarok.sinaukoding.hris.filter;

import com.gusrylmubarok.sinaukoding.hris.entity.User;
import com.gusrylmubarok.sinaukoding.hris.service.JwtTokenService;
import com.gusrylmubarok.sinaukoding.hris.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String requestHeaderToken = request.getHeader("Authorization");

        String username = null;
        String jwtToken = null;

        if (requestHeaderToken != null) {
            jwtToken = requestHeaderToken;

            try {
                username = jwtTokenService.getUsernameFromToken(jwtToken);
            }catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
            }catch (ExpiredJwtException e) {
                System.out.println("Token has expired");
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            User user = new User();
            user.setUsername(username);

            user = userService.findOne(user);

            if (jwtTokenService.validation(jwtToken, user)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
