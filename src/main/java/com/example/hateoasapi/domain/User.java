package com.example.hateoasapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.ToString;

import javax.persistence.Id;
import java.util.Collection;

@Setter
@Document
@ToString
public class User implements UserDetails {

    @Id
    @Field("_id")
    private String objectId;

    private String email;
    private boolean enabled;
    private boolean locked;
    private String username;
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
}
