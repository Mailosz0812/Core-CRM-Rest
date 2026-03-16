package org.mailosz.crmrest.crmuser.auth;

import io.jsonwebtoken.*;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.security.SignatureException;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {
    private final JWTUtils jwtUtils;
    private final HandlerExceptionResolver handlerExceptionResolver;

    public JWTAuthFilter(JWTUtils jwtUtils, @Qualifier("handlerExceptionResolver") HandlerExceptionResolver handlerExceptionResolver) {
        this.jwtUtils = jwtUtils;
        this.handlerExceptionResolver = handlerExceptionResolver;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = extractToken(request);
        try{
            if(token != null) {
                Claims claims = jwtUtils.validateToken(token);

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        claims.getSubject(),
                        null,
                        jwtUtils.extractAuthorities(claims));
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }catch (ExpiredJwtException e){
            this.handlerExceptionResolver.resolveException(request,response,null,e);
            return;
        } catch(MalformedJwtException e){
            this.handlerExceptionResolver.resolveException(request,response,null,e);
            return;
        }catch(UnsupportedJwtException e){
            this.handlerExceptionResolver.resolveException(request,response,null,e);
            return;
        } catch(JwtException e){
            this.handlerExceptionResolver.resolveException(request,response,null,e);
            return;
        }
        filterChain.doFilter(request, response);
    }
    private String extractToken(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if(bearerToken != null && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }
        return null;
    }
}
