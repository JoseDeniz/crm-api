package com.crm.api.infrastructure.security;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtTokenAuthFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;
    private final ApplicationUserDetailsService userDetailsService;

    public JwtTokenAuthFilter(JwtTokenUtil jwtTokenUtil, ApplicationUserDetailsService userDetailsService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        final String requestHeader = request.getHeader("Authorization");
        String bearer = "Bearer ";
        if (requestHeader == null || !requestHeader.startsWith(bearer)) {
            response.sendError(HttpStatus.BAD_REQUEST.value(), "The request does not contain Authorization");
            return;
        }

        String username;
        String authToken;
        try {
            authToken = requestHeader.substring(bearer.length());
            username = jwtTokenUtil.getUsernameFromToken(authToken);
        } catch (ExpiredJwtException e) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Token has expired");
            return;
        } catch (StringIndexOutOfBoundsException e) {
            response.sendError(HttpStatus.BAD_REQUEST.value(), "Token format is invalid");
            return;
        }

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
        if (jwtTokenUtil.validateToken(authToken, userDetails)) {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        request.setAttribute("user", userDetails);

        chain.doFilter(request, response);
    }
}
