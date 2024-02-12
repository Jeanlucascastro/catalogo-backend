package com.sociedade.catalogoback.repositories;

import com.sociedade.catalogoback.domain.company.Company;
import com.sociedade.catalogoback.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    List<User> findUsersById(Long companyId);
}
