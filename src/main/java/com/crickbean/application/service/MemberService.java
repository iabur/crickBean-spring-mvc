package com.crickbean.application.service;

import com.crickbean.application.dto.MemberDto;
import com.crickbean.application.model.Member;
import com.crickbean.application.model.Team;
import com.crickbean.application.repositories.MemberRepository;
import com.crickbean.application.repositories.TeamRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService  {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TeamRepository teamRepository;

    public void save(MemberDto memberDto){
        Member member = new Member();
        BeanUtils.copyProperties(memberDto, member);
        member.setTeam(teamRepository.findById(memberDto.getTeamId()).get());
        member.setActive(true);
        System.out.println(member);
        memberRepository.save(member);
    }

    public List<Member> allMember(){
       return memberRepository.findAll();
    }
    public void updateMember(MemberDto memberDto){
        Member member = memberRepository.findById(memberDto.getMemberId()).get();
        BeanUtils.copyProperties(memberDto, member);
        member.setTeam(teamRepository.findById(memberDto.getTeamId()).get());
        memberRepository.save(member);
    }
    public void deleteMember(Long memberId){
        Member member = memberRepository.findById(memberId).get();
        if(member.isActive()){
            member.setActive(false);
        }
        else {
            member.setActive(true);
        }
        memberRepository.save(member);
    }

    public List<Member> activeMember(Long teamId, String memberName){
        if(teamId==null){
          return memberRepository.findAllByActiveTrueAndMemberNameContaining(memberName);
        }
        else {
          return memberRepository.findAllByActiveTrueAndTeamAndMemberNameContaining(teamRepository.findById(teamId).get(), memberName);
        }
    }
}
