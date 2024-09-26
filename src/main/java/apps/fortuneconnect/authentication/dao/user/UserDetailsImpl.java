package apps.fortuneconnect.authentication.dao.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@RequiredArgsConstructor
@Getter
public class UserDetailsImpl implements UserDetails {

	private final String password;
	private final Boolean isEnabled;
	private final Boolean isCredentialsExpired;
	private final Boolean isAccountLocked;
	private final Boolean isAccountExpired;
	private final Boolean firstLogin;

	public static UserDetailsImpl build(User user) {

		return new UserDetailsImpl(
				user.getPassword(),
				user.getIsEnabled(),
				user.getIsCredentialsExpired(),
				user.getIsAccountLocked(),
				user.getIsAccountExpired(),
				user.getFirstLogin());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return !isAccountExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !isAccountLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return !isCredentialsExpired;
	}

	@Override
	public boolean isEnabled() {
		return isEnabled;
	}
}
