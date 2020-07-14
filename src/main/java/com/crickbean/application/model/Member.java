package com.crickbean.application.model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tbl_member")
public class Member implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long memberId;

    @Column(name = "member_name", nullable = false)
    private String memberName;

    @Column(name = "member_userName", nullable = false)
    private String memberUserName;

    @Column(name = "member_password", nullable = false)
    private String memberPassword;

    @Column(name = "member_type", nullable = false)
    private String memberType;

    @Column(name = "member_description", nullable = true)
    private String memberDescription;

    @Column(name = "member_photo", nullable = true)
    private String memberPhoto;

    @Column(name = "is_active")
    private boolean active = true;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    public Member() {
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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", memberName='" + memberName + '\'' +
                ", memberUserName='" + memberUserName + '\'' +
                ", memberPassword='" + memberPassword + '\'' +
                ", memberType='" + memberType + '\'' +
                ", memberDescription='" + memberDescription + '\'' +
                ", memberPhoto='" + memberPhoto + '\'' +
                ", active=" + active +
                ", team=" + team +
                '}';
    }
}
