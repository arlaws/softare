package bj.edem.softcare.services;

import bj.edem.softcare.entities.Authorities;
import bj.edem.softcare.repositories.AuthoritiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthoritiesService {

    @Autowired
    private AuthoritiesRepository authoritiesRepository;

    @Transactional
    public Authorities save(Authorities authorities) {
        return authoritiesRepository.save(authorities);
    }

    @org.springframework.transaction.annotation.Transactional()
    public void addAuthorities(Authorities authorities) {

        authoritiesRepository.save(authorities);
    }

    @Transactional()
    public Authorities findAuthoritiesByusername(String username) {

        return authoritiesRepository.findAuthoritiesByusername(username);
    }

}
