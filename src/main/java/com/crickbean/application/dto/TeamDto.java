package com.crickbean.application.dto;

public class TeamDto {
    private Long teamId;
    private String teamName;
    private String teamType;
    private String teamDescription;
    private String teamPhoto;
    private boolean active;

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

    @Override
    public String toString() {
        return "TeamDto{" +
                "teamId=" + teamId +
                ", teamName='" + teamName + '\'' +
                ", teamType='" + teamType + '\'' +
                ", teamDescription='" + teamDescription + '\'' +
                ", teamPhoto='" + teamPhoto + '\'' +
                ", active=" + active +
                '}';
    }
}
