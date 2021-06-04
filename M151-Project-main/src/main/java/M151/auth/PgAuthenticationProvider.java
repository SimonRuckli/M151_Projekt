package M151.auth;

import M151.model.UserRole;
import M151.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class PgAuthenticationProvider implements AuthenticationProvider {
    private final LoginService loginService;

    @Autowired
    public PgAuthenticationProvider(final LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        final String username = authentication.getName();
        final String userPassword = authentication.getCredentials().toString();

        return  getAuth(username, userPassword);
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    private Authentication getAuth(final String username, final String password) {
        if(loginService.login(username, password).isPresent()) {
            final UserRole userRole = loginService.login(username, password).get();
            return new UsernamePasswordAuthenticationToken(
                    username,
                    password,
                    Collections.singletonList(new SimpleGrantedAuthority(userRole.toString())));
        }
        return null;
    }
}
