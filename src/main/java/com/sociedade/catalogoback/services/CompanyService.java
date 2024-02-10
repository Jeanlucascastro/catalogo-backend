package com.sociedade.catalogoback.services;

import com.sociedade.catalogoback.domain.company.Company;
import com.sociedade.catalogoback.domain.company.dto.CreateCompanyDTO;
import com.sociedade.catalogoback.domain.user.User;
import com.sociedade.catalogoback.repositories.CompanyRepository;
import com.sociedade.catalogoback.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    UserRepository userRepository;

    @Transactional
    public Company saveCompany(@RequestBody @Valid CreateCompanyDTO companyDTO, User user) {

        List<User> users = new ArrayList<>();
        users.add(user);

        Company company = new Company();

        company.setName(companyDTO.name());
        company.setDescription(companyDTO.description());
//        company.setUsers(users);

        Company newCompany = this.companyRepository.save(company);

        User existingUser = (User) this.userRepository.findByLogin(user.getLogin());

        existingUser.setCompany(newCompany);

        this.userRepository.save(existingUser);

        return this.companyRepository.save(company);
    }

    public Company updateById(@PathVariable Long id, @RequestBody final CreateCompanyDTO companyDTO) {

        Company existingCompany = companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrado com o ID: " + id ));

        existingCompany.setName(companyDTO.name());
        existingCompany.setDescription(companyDTO.description());

        return companyRepository.save(existingCompany);
    }

    public Company delete(@PathVariable Long id) {
        Company existingCompany = companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrado com o ID: " + id ));
        existingCompany.setDeleted(true);
        return companyRepository.save(existingCompany);
    }

    public Page<Company> find(Pageable pageable, Boolean isPageable) {
        if (isPageable) {
            return this.companyRepository.findAll(pageable);
        } else {
            return new PageImpl<>(this.companyRepository.findAll());
        }
    }

    public Company findById(Long id) {
        return this.companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrado com o ID: " + id));
    }

}
