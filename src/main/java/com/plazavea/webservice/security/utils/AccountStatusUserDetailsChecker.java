package com.plazavea.webservice.security.utils;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.context.support.MessageSourceAccessor;

public class AccountStatusUserDetailsChecker implements UserDetailsChecker {

	protected final MessageSourceAccessor messages = SpringSecurityMessageSource
			.getAccessor();

    @Override
    public void check(UserDetails toCheck) {
        if (!toCheck.isAccountNonLocked()) {
			throw new LockedException(messages.getMessage(
					"AccountStatusUserDetailsChecker.locked", "User account is locked"));
		}

		if (!toCheck.isEnabled()) {
			throw new DisabledException(messages.getMessage(
					"AccountStatusUserDetailsChecker.disabled", "User is disabled"));
		}

		if (!toCheck.isAccountNonExpired()) {
			throw new AccountExpiredException(
					messages.getMessage("AccountStatusUserDetailsChecker.expired",
							"User account has expired"));
		}

		if (!toCheck.isCredentialsNonExpired()) {
			throw new CredentialsExpiredException(messages.getMessage(
					"AccountStatusUserDetailsChecker.credentialsExpired",
					"User credentials have expired"));
		}
        
    }
}