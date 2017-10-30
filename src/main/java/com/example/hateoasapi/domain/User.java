package com.example.hateoasapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.ToString;

import javax.persistence.Id;
import java.util.Collection;

@Setter
@Getter
@Document
@ToString
public class User implements UserDetails {

    @Id
    private String id;

    private String email;
    private boolean enabled;
    private boolean locked;
    private String username;
    private UserProfile userProfile;
    @JsonIgnore
    private String password;

    @JsonIgnore
    private Collection<? extends UserRole> authorities;

    @Override
    public Collection<? extends UserRole> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public enum UniqueUserFields {
        email, username
    }
}
