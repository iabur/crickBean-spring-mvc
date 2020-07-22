package com.crickbean.application.service;

import com.crickbean.application.dto.CountryDto;
import com.crickbean.application.model.Country;
import com.crickbean.application.model.Role;
import com.crickbean.application.model.User;
import com.crickbean.application.repositories.CountryRepository;
import com.crickbean.application.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class CountryService {
    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private UserRepository userRepository;

    public void save(CountryDto countryDto) {
        Country country = new Country();
        country.setCountryName(countryDto.getCountryName());
        country.setCountryLogo(countryDto.getCountryLogo());
        if (userRepository.findByUsername(countryDto.getCountryName()).isEmpty()) {
            User user = new User();
            user.setUsername(countryDto.getCountryName());
            user.setPassword(passwordEncoder.encode("secret"));
            Set<Role> roles = new HashSet<>();
            roles.add(authorityService.findByRoleName("ROLE_COUNTRY_MANAGER"));
            user.setRoles(roles);
            user.setLogo(countryDto.getCountryLogo());
            userRepository.save(user);
        }
        countryRepository.save(country);
    }

    public List<Country> getAllCountry() {
        return countryRepository.findAll();
    }

    public List<Country> getAllActiveCountry() {
        return countryRepository.findAllByActiveTrue();
    }

    public Optional<Country> getCountryById(Long id) {
        return countryRepository.findById(id);
    }

    public void saveUpdate(CountryDto countryDto, Authentication authentication) {
        Country country = countryRepository.findById(countryDto.getCountryId()).get();
        if (countryRepository.findByCountryName(authentication.getName()) != null) {
            if (country.getCountry_id() == countryRepository.findByCountryName(authentication.getName()).getCountry_id()) {
                uSave(countryDto, country);
            } else {
                throw new RuntimeException("Access Denied");
            }
        } else {
            uSave(countryDto, country);
        }
    }

    public void uSave(CountryDto countryDto, Country country) {
        User user = userRepository.findByUsername(country.getCountryName()).get();
        user.setUsername(countryDto.getCountryName());
        user.setLogo(countryDto.getCountryLogo());
        userRepository.save(user);
        BeanUtils.copyProperties(countryDto, country);
        countryRepository.save(country);
    }

    public void deleteCountryById(Long countryId) {
        Country country = countryRepository.findById(countryId).get();
        if (country.isActive() == true) {
            country.setActive(false);
        } else {
            country.setActive(true);
        }
        countryRepository.save(country);
    }

    public List<Country> searchCountry(String countryString) {
        return countryRepository.findAllByActiveTrueAndCountryNameContains(countryString);
    }

    public Optional<Country> countryById(Long id) {
        return countryRepository.findById(id);
    }
}
