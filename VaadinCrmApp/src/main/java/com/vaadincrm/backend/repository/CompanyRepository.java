package com.vaadincrm.backend.repository;

import com.vaadincrm.backend.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {

}
