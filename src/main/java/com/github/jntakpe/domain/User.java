package com.github.jntakpe.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

import javax.validation.constraints.Size;

/**
 * Entité représentant un utilisateur
 *
 * @author jntakpe
 */
public class User extends MongoEntity {

    @NotEmpty
    private String login;

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    @Size(min = 3, max = 20)
    private String password;

    @Transient
    private String confirmPassword;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return !(login != null ? !login.equals(user.login) : user.login != null);

    }

    @Override
    public int hashCode() {
        return login != null ? login.hashCode() : 0;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("login", login)
                .toString();
    }
}
