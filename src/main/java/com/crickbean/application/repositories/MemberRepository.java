package com.crickbean.application.repositories;

import com.crickbean.application.model.Member;
import com.crickbean.application.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findAll();
    List<Member> findAllByActiveTrueAndTeamAndMemberNameContaining(Team team, String memberName);
    List<Member> findAllByActiveTrueAndMemberNameContaining(String memberName);
    Optional<Member> findByMemberName(String memberName);
    Optional<Member> findByMemberUserName(String memberUserName);
}
