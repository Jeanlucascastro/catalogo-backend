package com.sociedade.catalogoback.controllers;

import com.sociedade.catalogoback.domain.company.Company;
import com.sociedade.catalogoback.domain.company.dto.CreateCompanyDTO;
import com.sociedade.catalogoback.domain.user.User;
import com.sociedade.catalogoback.exception.GenericException;
import com.sociedade.catalogoback.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping(value = "/company")
public class CompanyController {


    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping()
    @ResponseBody()
    public ResponseEntity<Company> saveCompany(@RequestBody CreateCompanyDTO createCompanyDTO,
                                               @AuthenticationPrincipal User user) {
        try {
            Company saveCompany = this.companyService.saveCompany(createCompanyDTO, user);
            return new ResponseEntity<>(saveCompany, HttpStatus.CREATED);
        } catch (GenericException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
