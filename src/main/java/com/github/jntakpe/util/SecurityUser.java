package com.github.jntakpe.util;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Bean représentant l'utilisateur courant de l'application
 *
 * @author jntakpe
 */
public class SecurityUser extends User {

    private static final String DEFAULT_ROLE = "USER";

    private final String id;

    private final Date lastConnection;

    public SecurityUser(String username, String password, String id, Date lastConnection) {
        super(username, password, initAuthorities());
        this.id = id;
        this.lastConnection = lastConnection;
    }

    public String getId() {
        return id;
    }

    public Date getLastConnection() {
        return lastConnection;
    }

    private static Collection<GrantedAuthority> initAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>(1);
        authorities.add(new SimpleGrantedAuthority(DEFAULT_ROLE));
        return authorities;
    }

}
