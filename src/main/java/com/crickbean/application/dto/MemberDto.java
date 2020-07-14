package com.crickbean.application.dto;

public class MemberDto {
    private Long memberId;
    private String memberName;
    private String memberUserName;
    private String memberPassword;
    private String memberType;
    private String memberDescription;
    private String memberPhoto;
    private boolean active;
    private Long teamId;

    public MemberDto() {
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberUserName() {
        return memberUserName;
    }

    public void setMemberUserName(String memberUserName) {
        this.memberUserName = memberUserName;
    }

    public String getMemberPassword() {
        return memberPassword;
    }

    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public String getMemberDescription() {
        return memberDescription;
    }

    public void setMemberDescription(String memberDescription) {
        this.memberDescription = memberDescription;
    }

    public String getMemberPhoto() {
        return memberPhoto;
    }

    public void setMemberPhoto(String memberPhoto) {
        this.memberPhoto = memberPhoto;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    @Override
    public String toString() {
        return "MemberDto{" +
                "memberId=" + memberId +
                ", memberName='" + memberName + '\'' +
                ", memberUserName='" + memberUserName + '\'' +
                ", memberPassword='" + memberPassword + '\'' +
                ", memberType='" + memberType + '\'' +
                ", memberDescription='" + memberDescription + '\'' +
                ", memberPhoto='" + memberPhoto + '\'' +
                ", active=" + active +
                ", teamId=" + teamId +
                '}';
    }
}
