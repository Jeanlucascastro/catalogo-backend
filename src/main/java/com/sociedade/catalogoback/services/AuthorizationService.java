package com.sociedade.catalogoback.services;

import com.sociedade.catalogoback.domain.company.Company;
import com.sociedade.catalogoback.domain.user.User;
import com.sociedade.catalogoback.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username);
    }

    public List<Company> getCompaniesByUserId(String userId) {
        Optional<User> userOptional = repository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return user.getCompanies();
        } else {
            throw new EntityNotFoundException("Usuario não encontrado com o ID: " + userId);
        }
    }
}
