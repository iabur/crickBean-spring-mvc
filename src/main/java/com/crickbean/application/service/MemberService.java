package com.crickbean.application.service;

import com.crickbean.application.dto.MemberDto;
import com.crickbean.application.model.Member;
import com.crickbean.application.model.Role;
import com.crickbean.application.model.Team;
import com.crickbean.application.model.User;
import com.crickbean.application.repositories.MemberRepository;
import com.crickbean.application.repositories.TeamRepository;
import com.crickbean.application.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorityService authorityService;

    public void save(MemberDto memberDto, Authentication authentication) {
        Member member = new Member();
        BeanUtils.copyProperties(memberDto, member);
        var team = teamRepository.findByTeamName(authentication.getName()).get();
        member.setTeam(team);
        member.setActive(true);
        System.out.println(member);
        if (userRepository.findByUsername(memberDto.getMemberName()).isEmpty()) {
            User user = new User();
            user.setUsername(memberDto.getMemberName());
            user.setPassword(passwordEncoder.encode(memberDto.getMemberPassword()));
            Set<Role> roles = new HashSet<>();
            if (memberDto.getMemberType() == "Player") {
                roles.add(authorityService.findByRoleName("ROLE_PLAYER"));
            } else {
                roles.add(authorityService.findByRoleName("ROLE_STUFF"));
            }
            user.setRoles(roles);
            user.setLogo(memberDto.getMemberPhoto());
            userRepository.save(user);
        }
        memberRepository.save(member);
    }

    public List<Member> allMember() {
        return memberRepository.findAll();
    }

    public void updateMember(MemberDto memberDto, Authentication authentication) {
        Member member = memberRepository.findById(memberDto.getMemberId()).get();
        if (memberRepository.findByMemberUserName(authentication.getName()) != null) {
            if (member.getMemberId() != memberRepository.findByMemberUserName(authentication.getName()).get().getMemberId()) {
                throw new RuntimeException("Access denied");
            }
            uSave(memberDto, authentication, member);
        } else if (teamRepository.findByTeamName(authentication.getName()).get().getTeamId() == member.getTeam().getTeamId()) {
            uSave(memberDto, authentication, member);
        }
        else {
            throw new RuntimeException("Access denied");
        }
    }

    public void uSave(MemberDto memberDto, Authentication authentication, Member member) {
        User user = userRepository.findByUsername(member.getMemberName()).get();
        user.setUsername(memberDto.getMemberName());
        user.setPassword(passwordEncoder.encode(memberDto.getMemberPassword()));
        user.setLogo(memberDto.getMemberPhoto());
        userRepository.save(user);
        var team = member.getTeam();
        BeanUtils.copyProperties(memberDto, member);
        member.setTeam(team);
        memberRepository.save(member);
    }

    public void deleteMember(Long memberId) {
        Member member = memberRepository.findById(memberId).get();
        if (member.isActive()) {
            member.setActive(false);
        } else {
            member.setActive(true);
        }
        memberRepository.save(member);
    }

    public List<Member> activeMember(Long teamId, String memberName) {
        if (teamId == null) {
            return memberRepository.findAllByActiveTrueAndMemberNameContaining(memberName);
        } else {
            return memberRepository.findAllByActiveTrueAndTeamAndMemberNameContaining(teamRepository.findById(teamId).get(), memberName);
        }
    }
}
