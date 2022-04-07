package server;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import server.auth.Permission;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@Getter
public enum Role {
    USER(Stream.of(Permission.DEVELOPERS_READ).collect(Collectors.toSet())),
    ADMIN(Stream.of(Permission.DEVELOPERS_READ, Permission.DEVELOPERS_WRITE)
            .collect(Collectors.toSet()));

    private Set<Permission> permissions;

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
