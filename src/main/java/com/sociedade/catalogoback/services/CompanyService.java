package com.sociedade.catalogoback.services;

import com.sociedade.catalogoback.domain.company.Company;
import com.sociedade.catalogoback.domain.company.dto.CreateCompanyDTO;
import com.sociedade.catalogoback.domain.user.User;
import com.sociedade.catalogoback.repositories.CompanyRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    public Company saveCompany(@RequestBody @Valid CreateCompanyDTO companyDTO, User user) {

        List<User> users = new ArrayList<>();
        users.add(user);

        Company company = new Company(
                companyDTO.name(), companyDTO.description(), users
        );

        return this.companyRepository.save(company);
    }
}
