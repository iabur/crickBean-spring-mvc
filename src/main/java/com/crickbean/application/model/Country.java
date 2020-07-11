package com.crickbean.application.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tbl_country")
public class Country implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long country_id;

    @Column(name = "country_name", nullable = false, unique = true)
    private String countryName;

    @Column(name = "logo", nullable = true)
    private String countryLogo;

    @Column(name = "is_active")
    private boolean active = true;

    public Country() {
    }

    public Long getCountry_id() {
        return country_id;
    }

    public void setCountry_id(Long country_id) {
        this.country_id = country_id;
    }

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
}
