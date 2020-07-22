package com.crickbean.application.service;

import com.crickbean.application.dto.CountryDto;
import com.crickbean.application.dto.TeamDto;
import com.crickbean.application.model.Country;
import com.crickbean.application.model.Role;
import com.crickbean.application.model.Team;
import com.crickbean.application.model.User;
import com.crickbean.application.repositories.CountryRepository;
import com.crickbean.application.repositories.TeamRepository;
import com.crickbean.application.repositories.UserRepository;
import com.google.protobuf.Option;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorityService authorityService;


    public void saveTeam(TeamDto teamDto, Authentication authentication) {
        Team team = new Team();
        BeanUtils.copyProperties(teamDto, team);
        var countryName = authentication.getName();
        Country country = countryRepository.findAllByActiveTrueAndCountryName(countryName).get();
        team.setCountry(country);
        team.setActive(true);
        if (userRepository.findByUsername(teamDto.getTeamName()).isEmpty()) {
            User user = new User();
            user.setUsername(teamDto.getTeamName());
            user.setPassword(passwordEncoder.encode("secret"));
            Set<Role> roles = new HashSet<>();
            roles.add(authorityService.findByRoleName("ROLE_TEAM_MANAGER"));
            user.setRoles(roles);
            user.setLogo(teamDto.getTeamPhoto());
            userRepository.save(user);
        }
        teamRepository.save(team);
    }

    public List<TeamDto> allTeams() {
        List<TeamDto> teamDtoList = new ArrayList<>();
        List<Team> t = teamRepository.findAll();
        teamRepository.findAll().forEach(team -> {
            TeamDto teamDto = new TeamDto();
            BeanUtils.copyProperties(team, teamDto);
            teamDto.setCountryName(team.getCountry().getCountryName());
            teamDtoList.add(teamDto);
        });
        return teamDtoList;
    }

    public List<TeamDto> allActiveTeams(String queryText) {
        List<TeamDto> teamDtoList = new ArrayList<>();
        teamRepository.findAllByActiveTrueAndTeamNameContaining(queryText).forEach(team -> {
            TeamDto teamDto = new TeamDto();
            BeanUtils.copyProperties(team, teamDto);
            teamDtoList.add(teamDto);
        });
        return teamDtoList;
    }

    public void saveUpdate(TeamDto teamDto, Authentication authentication) {
        Team team = teamRepository.findById(teamDto.getTeamId()).get();
        if (countryRepository.findByCountryName(authentication.getName()) != null) {
            if (team.getCountry().getCountry_id() != countryRepository.findByCountryName(authentication.getName()).getCountry_id()) {
                throw new RuntimeException("Access denied");
            }
            uSave(teamDto, authentication, team);
        } else if (teamRepository.findByTeamName(authentication.getName()).get().getTeamId() == team.getTeamId()) {
            uSave(teamDto, authentication, team);
        }
        else{
            throw new RuntimeException("Access denied");
        }
    }

    public void uSave(TeamDto teamDto, Authentication authentication, Team team) {
        User user = userRepository.findByUsername(team.getTeamName()).get();
        user.setUsername(teamDto.getTeamName());
        user.setLogo(teamDto.getTeamPhoto());
        userRepository.save(user);
        var country = team.getCountry();
        BeanUtils.copyProperties(teamDto, team);
        team.setCountry(country);
        teamRepository.save(team);
    }

    public void deleteTeamById(Long teamId) {
        Team team = teamRepository.findById(teamId).get();
        if (team.isActive() == true) {
            team.setActive(false);
        } else {
            team.setActive(true);
        }
        teamRepository.save(team);
    }

    public Optional<Team> findById(Long teamId) {
        return teamRepository.findById(teamId);
    }

    public List<Team> specificTeams(Long countryId, String queryText) {
        return teamRepository.findAllByActiveTrueAndCountryAndTeamNameContaining(countryRepository.findById(countryId).get(), queryText);
    }
}
