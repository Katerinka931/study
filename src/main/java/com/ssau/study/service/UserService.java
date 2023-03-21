package com.ssau.study.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    public String getUsername() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return securityContext.getAuthentication().getName();
    }

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

    @PreFilter("filterObject != authentication.principal.username")
    public String joinUsernames(List<String> usernames) {
        return usernames.stream().collect(Collectors.joining(";"));
    }

    @PreFilter(value = "filterObject != authentication.principal.username", filterTarget = "usernames")
    public String joinUsernamesAndRoles(List<String> usernames, List<String> roles) {
        return usernames.stream().collect(Collectors.joining(";")) + roles.stream().collect(Collectors.joining(";"));
    }
}
