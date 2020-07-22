package com.crickbean.application.controllers;

import com.crickbean.application.dto.MemberDto;
import com.crickbean.application.exceptions.ResourceNotFoundException;
import com.crickbean.application.service.CountryService;
import com.crickbean.application.service.MemberService;
import com.crickbean.application.service.TeamService;
import com.crickbean.application.service.UserService;
import com.crickbean.application.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class MemberController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private UserService userService;

    @GetMapping("/member/add")
    public String addMember(Model model, Authentication authentication) {
        model.addAttribute("allMembers", memberService.allMember());
        model.addAttribute("member", new MemberDto());
        model.addAttribute("user", userService.getLoggedUser(authentication));
        return "admin/team-member/team-members";
    }

    @PostMapping("/member/add")
    public String addMember(@ModelAttribute(name = "member") MemberDto memberDto, Model model, @RequestParam(name = "file") MultipartFile file, Authentication authentication) {
        if (memberDto.getMemberName() == null || memberDto.getMemberName().trim().isEmpty()) {
            throw new ResourceNotFoundException("Member not found");
        }
        if (file.isEmpty()) {
            throw new ResourceNotFoundException("Member Photo not found");
        }
        try {
            byte[] bytes = file.getBytes();
            String getAbsoluteFilePath = Constants.UPLOADED_FOLDER;
            Path path = Paths.get(getAbsoluteFilePath + file.getOriginalFilename());
            File dir = Paths.get(getAbsoluteFilePath).toFile();
            if (!dir.exists()) {
                dir.mkdirs();
            }
            Files.write(path, bytes);
            memberDto.setMemberPhoto("/images/" + file.getOriginalFilename());
            memberService.save(memberDto, authentication);
            model.addAttribute("message", "New Member added successfully");
            return "redirect:/member/add";
        } catch (IOException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @GetMapping("member/update")
    public String updateMember(Model model, @RequestParam(name = "memberId") String memberId, Authentication authentication) {
        model.addAttribute("member", new MemberDto());
        model.addAttribute("memberId", memberId);
        model.addAttribute("user", userService.getLoggedUser(authentication));
        model.addAttribute("teams", teamService.allTeams());
        return "admin/team-member/editTeam-members";
    }

    @PostMapping("/member/update")
    public String updateMember(@ModelAttribute(name = "member") MemberDto memberDto, @RequestParam(name = "file") MultipartFile file, Authentication authentication){
        System.out.println(memberDto);
        if (memberDto.getMemberName() == null || memberDto.getMemberName().trim().isEmpty()) {
            throw new ResourceNotFoundException("Member not found");
        }
        if (file.isEmpty()) {
            throw new ResourceNotFoundException("Member Photo not found");
        }
        try {
            byte[] bytes = file.getBytes();
            String getAbsoluteFilePath = Constants.UPLOADED_FOLDER;
            Path path = Paths.get(getAbsoluteFilePath + file.getOriginalFilename());
            File dir = Paths.get(getAbsoluteFilePath).toFile();
            if (!dir.exists()) {
                dir.mkdirs();
            }
            Files.write(path, bytes);
            memberDto.setMemberPhoto("/images/" + file.getOriginalFilename());
            memberService.updateMember(memberDto, authentication);
            return "redirect:/member/add";
        } catch (IOException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }

    }

    @GetMapping("/member/delete")
    public String memberDelete(@RequestParam(name = "memberId") Long memberId, Model model) {
        memberService.deleteMember(memberId);
        return "redirect:/member/add";
    }

    @GetMapping("/member/members")
    public String showActiveMember(@RequestParam(name = "teamId") Long teamId, @RequestParam(name = "queryText") String queryText, Model model, Authentication authentication){
        if(teamId==null){
            model.addAttribute("teamId", -1);
        }
        else {
            model.addAttribute("teamId", teamId);
        }
        model.addAttribute("members",memberService.activeMember(teamId,queryText));
        model.addAttribute("user", userService.getLoggedUser(authentication));
        return "team-member/teamMembers";
    }
}

