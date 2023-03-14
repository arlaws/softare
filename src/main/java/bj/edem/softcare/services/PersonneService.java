package bj.edem.softcare.services;

import bj.edem.softcare.entities.Personne;
import bj.edem.softcare.repositories.PersonneRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PersonneService {

    private static final int PAGE_ELEMENT_SIZE_ADMIN = 12;

    @Autowired
    PersonneRepository repository;

//    @Autowired
//    UserService userService;

    @Transactional
    public Personne save(Personne personne) {

        if (repository.existsByNomPrenomsAndContact(personne.getNomPrenoms(), personne.getContact())) {
            return repository.findByNomPrenomsAndContact(personne.getNomPrenoms(), personne.getContact());
        }
        return repository.save(personne);
    }

    public Optional<Personne> delete(Long id) {
        return findById(id).map(personne -> {
            repository.delete(personne);
            return personne;
        });
    }

    @Transactional
    public Optional<Personne> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public List<Personne> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Page<Personne> findAllByPage(Integer pageNumber) {
        //PageRequest pageRequest = new PageRequest(pageNumber - 1, PAGE_ELEMENT_SIZE_ADMIN);
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, PAGE_ELEMENT_SIZE_ADMIN);
        return repository.findAll(pageRequest);
    }
}
