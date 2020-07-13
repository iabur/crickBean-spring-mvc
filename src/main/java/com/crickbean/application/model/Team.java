package com.crickbean.application.model;

import javax.persistence.*;
import java.io.Serializable;

@Table
@Entity(name = "tbl_team")
public class Team implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "team_id", nullable = false)
    private Long teamId;

    @Column(name = "team_name", nullable = false)
    private String teamName;

    @Column(name = "team_type", nullable = false)
    private String teamType;

    @Column(name = "team_description", nullable = true)
    private String teamDescription;

    @Column(name = "team_photo")
    private String teamPhoto;

    @Column(name = "is_active")
    private boolean active = true;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    public Team() {
    }
    

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamType() {
        return teamType;
    }

    public void setTeamType(String teamType) {
        this.teamType = teamType;
    }

    public String getTeamDescription() {
        return teamDescription;
    }

    public void setTeamDescription(String teamDescription) {
        this.teamDescription = teamDescription;
    }

    public String getTeamPhoto() {
        return teamPhoto;
    }

    public void setTeamPhoto(String teamPhoto) {
        this.teamPhoto = teamPhoto;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
