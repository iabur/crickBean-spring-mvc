package com.crickbean.application.controllers;

import com.crickbean.application.dto.CountryDto;
import com.crickbean.application.dto.TeamDto;
import com.crickbean.application.exceptions.ResourceNotFoundException;
import com.crickbean.application.model.Team;
import com.crickbean.application.service.CountryService;
import com.crickbean.application.service.TeamService;
import com.crickbean.application.util.Constants;
import javafx.scene.chart.ScatterChart;
import org.springframework.beans.factory.annotation.Autowired;
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
public class TeamController {
    @Autowired
    private TeamService teamService;

    @Autowired
    private CountryService countryService;

    @GetMapping("/team/add")
    public String addTeam(Model model) {
        model.addAttribute("allTeams", teamService.allTeams());
        model.addAttribute("countries", countryService.getAllCountry());
        model.addAttribute("team", new TeamDto());
        return "admin/team/teams";
    }

    @PostMapping("/team/add")
    public String addTeam(@ModelAttribute(name = "team") TeamDto teamDto, Model model, @RequestParam(name = "file") MultipartFile file) {
        System.out.println(teamDto);
        if (teamDto.getTeamName() == null || teamDto.getTeamName().trim().isEmpty()) {
            throw new ResourceNotFoundException("Team not found");
        }
        if (file.isEmpty()) {
            throw new ResourceNotFoundException("Team flag not found");
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
            teamDto.setTeamPhoto("/images/" + file.getOriginalFilename());
            teamService.saveTeam(teamDto);
            model.addAttribute("message", "Team added successfully");
            return "redirect:/team/add";
        } catch (IOException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @GetMapping("/team/update")
    public String updateTeam(@RequestParam(name = "teamId") Long id, Model model) {
        model.addAttribute("teamId", id);
        model.addAttribute("countries", countryService.getAllCountry());
        model.addAttribute("team", new TeamDto());
        return "admin/team/editTeams";
    }

    @PostMapping("/team/update")
    public String updataTeam(@ModelAttribute(name = "team") TeamDto teamDto, Model model, @RequestParam("file") MultipartFile file) {
        if (teamDto.getTeamName() == null || teamDto.getTeamName().trim().isEmpty()) {
            throw new ResourceNotFoundException("Team not found");
        }
        if (file.isEmpty()) {
            throw new ResourceNotFoundException("Team flag not found");
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
            teamDto.setTeamPhoto("/images/" + file.getOriginalFilename());
            teamService.saveUpdate(teamDto);
            model.addAttribute("message", "Team updated successfully");
            return "redirect:/team/add";
        } catch (IOException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @GetMapping("/team/delete")
    public String deleteCountry(@RequestParam(name = "teamId") Long id, Model model) {
        teamService.deleteTeamById(id);
        return "redirect:/team/add";
    }


    @GetMapping("/team/teams")
    public String specificTeam(Model model, @RequestParam(name = "countryId") Long countryId, @RequestParam(name = "queryText") String queryText) {

        if(countryId == null)
        {
            model.addAttribute("countryId", -1);
            model.addAttribute("teams", teamService.allActiveTeams(queryText));
        }
        else {
            model.addAttribute("countryId", countryId);
            model.addAttribute("teams",teamService.specificTeams(countryId, queryText));
        }
        return "team/allTeams";
    }
}
