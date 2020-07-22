package com.crickbean.application.repositories;

import com.crickbean.application.model.Country;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface CountryRepository extends JpaRepository<Country, Long> {
    List<Country> findAllByActiveTrue();
    List<Country> findAllByActiveTrueAndCountryNameContains(String countryName);
    Optional<Country> findAllByActiveTrueAndCountryName(String countryName);
    Country findByCountryName(String countryName);
}
