package com.example.hateoasapi.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.ToString;

import javax.persistence.Id;
import java.util.Collection;

@Document
@ToString
public class User implements UserDetails {

    @Id
    @Field("_id")
    private String objectId;

    private String username;
    private String password;
    private Collection<? extends UserRole> authorities;

    @Override
    public Collection<? extends UserRole> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends UserRole> authorities) {
        this.authorities = authorities;
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
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
