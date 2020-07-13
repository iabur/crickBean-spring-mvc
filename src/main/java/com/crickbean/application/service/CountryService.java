package com.crickbean.application.service;

import com.crickbean.application.dto.CountryDto;
import com.crickbean.application.model.Country;
import com.crickbean.application.repositories.CountryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CountryService {
    @Autowired
    private CountryRepository countryRepository;

    public void save(CountryDto countryDto){
        Country country = new Country();
        country.setCountryName(countryDto.getCountryName());
        country.setCountryLogo(countryDto.getCountryLogo());
        countryRepository.save(country);
    }
    public List<Country> getAllCountry(){
        return countryRepository.findAll();
    }
    public List<Country> getAllActiveCountry(){
        return countryRepository.findAllByActiveTrue();
    }
    public Optional<Country> getCountryById(Long id){
       return countryRepository.findById(id);
    }

    public void saveUpdate(CountryDto countryDto){
        Country country = countryRepository.findById(countryDto.getCountryId()).get();
        BeanUtils.copyProperties(countryDto, country);
        countryRepository.save(country);
    }

    public void deleteCountryById(Long countryId) {
        Country country = countryRepository.findById(countryId).get();
        if(country.isActive() == true){
            country.setActive(false);
        }
        else {
            country.setActive(true);
        }
        countryRepository.save(country);
    }

    public List<Country> searchCountry(String countryString){
        return countryRepository.findAllByActiveTrueAndCountryNameContains(countryString);
    }

    public Optional<Country> countryById(Long id){
        return countryRepository.findById(id);
    }
}
