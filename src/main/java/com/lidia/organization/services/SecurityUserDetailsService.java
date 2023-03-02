package com.lidia.organization.services;

import com.lidia.organization.model.Punonjes;
import com.lidia.organization.repositories.PunonjesRepository;
import com.lidia.organization.security.SecurityUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

    private final PunonjesRepository punonjesRepository;

    public SecurityUserDetailsService(PunonjesRepository punonjesRepository) {
        this.punonjesRepository = punonjesRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Punonjes user = punonjesRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));

        return SecurityUser.build(user);
    }
}
