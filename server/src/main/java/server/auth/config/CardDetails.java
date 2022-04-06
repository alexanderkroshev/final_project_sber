package server.auth.config;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import server.auth.Status;
import server.model.BasicModel;


import java.util.Collection;
import java.util.List;

@Data
public class CardDetails implements UserDetails {
    private final String cardNumber;
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
        return cardNumber;
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

    public static UserDetails fromCard(BasicModel card) {
        return new User(
                card.getLogin(),
                card.getPassword(),
                card.getStatus().equals(Status.ACTIVE),
                card.getStatus().equals(Status.ACTIVE),
                card.getStatus().equals(Status.ACTIVE),
                card.getStatus().equals(Status.ACTIVE),
                card.getRole().getAuthorities()
        );
    }
}
