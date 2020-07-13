package com.crickbean.application.service;

import com.crickbean.application.dto.CountryDto;
import com.crickbean.application.dto.TeamDto;
import com.crickbean.application.model.Country;
import com.crickbean.application.model.Team;
import com.crickbean.application.repositories.CountryRepository;
import com.crickbean.application.repositories.TeamRepository;
import com.google.protobuf.Option;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private CountryRepository countryRepository;

    public void saveTeam(TeamDto teamDto){
        Team team = new Team();
        BeanUtils.copyProperties(teamDto, team);
        Country country = countryRepository.findById(teamDto.getCountryId()).get();
        team.setCountry(country);
        team.setActive(true);
        teamRepository.save(team);
    }

    public List<TeamDto> allTeams(){
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

    public List<TeamDto> allActiveTeams(String queryText){
        List<TeamDto> teamDtoList = new ArrayList<>();
        teamRepository.findAllByActiveTrueAndTeamNameContaining(queryText).forEach(team -> {
            TeamDto teamDto = new TeamDto();
            BeanUtils.copyProperties(team, teamDto);
            teamDtoList.add(teamDto);
        });
        return teamDtoList;
    }

    public void saveUpdate(TeamDto teamDto){
        Team team = teamRepository.findById(teamDto.getTeamId()).get();
        BeanUtils.copyProperties(teamDto, team);
        Country country = countryRepository.findById(teamDto.getCountryId()).get();
        team.setCountry(country);
        teamRepository.save(team);
    }

    public void deleteTeamById(Long teamId) {
        Team team = teamRepository.findById(teamId).get();
        if (team.isActive() == true){
            team.setActive(false);
        }
        else {
            team.setActive(true);
        }
        teamRepository.save(team);
    }

    public Optional<Team> findById(Long teamId){
        return teamRepository.findById(teamId);
    }

    public List<Team> specificTeams(Long countryId, String queryText){
        return teamRepository.findAllByActiveTrueAndCountryAndTeamNameContaining(countryRepository.findById(countryId).get(), queryText);
    }

}
