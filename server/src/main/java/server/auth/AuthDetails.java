package server.auth;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import server.Status;
import server.model.AuthModel;
import java.util.Collection;
import java.util.List;

@Data
public class AuthDetails implements UserDetails {
    private final String login;
    private final String password;
    private final List<SimpleGrantedAuthority> authorities;
    private final boolean isActive;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    public static UserDetails fromBasicModel(AuthModel model) {
        return new User(
                model.getLogin(),
                model.getPassword(),
                model.getStatus().equals(Status.ACTIVE),
                model.getStatus().equals(Status.ACTIVE),
                model.getStatus().equals(Status.ACTIVE),
                model.getStatus().equals(Status.ACTIVE),
                model.getRole().getAuthorities()
        );
    }
}
