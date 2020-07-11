package com.crickbean.application.repositories;

import com.crickbean.application.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface CountryRepository extends JpaRepository<Country, Long> {
}
