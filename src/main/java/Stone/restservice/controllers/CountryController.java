package Stone.restservice.controllers;

import Stone.restservice.modules.BaseCountry;
import Stone.restservice.storage.CountryStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/eu")
public class CountryController {
    private final CountryStorage countryStorage;

    @Autowired
    public CountryController(CountryStorage countryStorage) {
        this.countryStorage = countryStorage;
    }

    @GetMapping
    public List<BaseCountry> getCountries() {
        return this.countryStorage.getCountries();
    }

    @GetMapping("/population")
    public LinkedHashMap<String, Integer> get10BiggestPopulationCountries() {
        return this.countryStorage.countryListSorted10CountryPopulation();
    }

    @GetMapping("/area")
    public LinkedHashMap<String, Double> get10BiggestAreaCountries() {
        return this.countryStorage.countryListSorted10CountryArea();
    }

    @GetMapping("/density")
    public LinkedHashMap<String, Double> get10BiggestPopulationDensityCountries() {
        return this.countryStorage.countryListSorted10CountryDensity();
    }
}
