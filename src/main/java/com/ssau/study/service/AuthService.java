package com.ssau.study.service;

import com.ssau.study.dto.UserPojo;
import com.ssau.study.entity.User;
import com.ssau.study.repository.StudentRepository;
import com.ssau.study.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public UserPojo register(UserPojo pojo) {
        User user = UserPojo.toEntity(pojo);
        user.setPassword(passwordEncoder.encode(pojo.getPassword()));
        return UserPojo.fromEntity(userRepository.save(user));
    }

    public UserPojo login(UserPojo pojo) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        pojo.getUsername(),
                        pojo.getPassword())
        );
        User user = userRepository.findUserByUsername(
                pojo.getUsername()).orElseThrow(() -> {
                    throw new NoSuchElementException();
                }
        );
        return UserPojo.fromEntity(user);
    }

    public String getUsername() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return securityContext.getAuthentication().getName();
    }

    //------------------------------------------------------------------------------------------------------------------
    @PreAuthorize("#username == authentication.principal.username")
    @PostAuthorize("returnObject != authentication.principal.username")
    public ArrayList<String> getRoles(String username) {
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        ArrayList<String> arr = new ArrayList<>();
        for (GrantedAuthority grantedAuthority : authorities) {
            arr.add(grantedAuthority.getAuthority());
        }
        return arr;
    }
}
