package com.crickbean.application.dto;

public class CountryDto {
    private Long countryId;
    private String countryName;
    private String countryLogo;
    private boolean active;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryLogo() {
        return countryLogo;
    }

    public void setCountryLogo(String countryLogo) {
        this.countryLogo = countryLogo;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    @Override
    public String toString() {
        return "CountryDto{" +
                "countryId=" + countryId +
                ", countryName='" + countryName + '\'' +
                ", countryLogo='" + countryLogo + '\'' +
                ", active=" + active +
                '}';
    }
}
