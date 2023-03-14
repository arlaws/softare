package bj.edem.softcare.services;

import bj.edem.softcare.entities.Authorities;
import bj.edem.softcare.entities.Users;
import bj.edem.softcare.repositories.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthoritiesService authorityService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Users save(Users user, String typeUser) {

        if (!userRepository.existsByUsername(user.getUsername())) {

            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user.setActivated(true);
            user.setVerified(true);

            Users users = userRepository.save(user);
            Authorities authority = new Authorities();

            authority.setAuthorityType(typeUser);
            authority.setUsername(users.getUsername());

            authorityService.save(authority);

            return users;
        }
        return null;
    }

    public Users edit(Users user, String typeUser) {

        System.out.println("New password --------- > " + user.getPassword());

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActivated(true);
        user.setVerified(true);

        Users users = userRepository.save(user);
        return users;
    }

    public Users findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    public Optional<Users> findById(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public List<Users> findAll() {
        return userRepository.findAll();
    }
}
