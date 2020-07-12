package com.crickbean.application.repositories;

import com.crickbean.application.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Repository
@Transactional
public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findAllByActiveTrue();
}
