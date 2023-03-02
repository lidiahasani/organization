package com.lidia.organization.services.impl;

import com.lidia.organization.model.Punonjes;
import com.lidia.organization.repositories.PunonjesRepository;
import com.lidia.organization.security.model.UserPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final PunonjesRepository punonjesRepository;

    public UserDetailsServiceImpl(PunonjesRepository punonjesRepository) {
        this.punonjesRepository = punonjesRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) {
        Punonjes user = punonjesRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Punonjesi nuk u gjend me kete email: " + email));

        return UserPrincipal.build(user);
    }
}
