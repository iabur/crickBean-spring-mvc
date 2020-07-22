package com.crickbean.application.repositories;

import com.crickbean.application.model.Country;
import com.crickbean.application.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@Transactional
public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findAllByActiveTrueAndTeamNameContaining(String teamName);
    List<Team> findAllByActiveTrueAndCountryAndTeamNameContaining(Country country, String teamName);
    Optional<Team> findByTeamName(String teamName);
}
