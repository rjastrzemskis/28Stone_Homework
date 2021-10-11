package Stone.restservice.storage;

import Stone.restservice.modules.BaseCountry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CountryStorage {

    @Autowired
    private RestTemplate restTemplate;
    private static final String filter = "name,capital,currencies,population,area";
    private static final String url = "https://restcountries.com/v2/regionalbloc/eu?fields=" + filter;

    public List<BaseCountry> getCountries() {
        BaseCountry[] countries = restTemplate.getForObject(url, BaseCountry[].class);
        List<BaseCountry> countryList = Arrays.asList(countries);
        return countryList;
    }

    public LinkedHashMap<String, Integer> countryListSorted10CountryPopulation() {
        Hashtable<String, Integer> CountryByPopulation = getCountries().stream().collect(Collectors
                .toMap(s -> s.getName(), BaseCountry::getPopulation, (a, b) -> b, Hashtable::new));

        return CountryByPopulation.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(10)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    public LinkedHashMap<String, Double> countryListSorted10CountryArea() {
        Hashtable<String, Double> CountryByArea = getCountries().stream().collect(Collectors
                .toMap(s -> s.getName(), BaseCountry::getArea, (a, b) -> b, Hashtable::new));

        return CountryByArea.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(10)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    public LinkedHashMap<String, Double> countryListSorted10CountryDensity() {
        Hashtable<String, Double> CountryByPopulationDensity = getCountries().stream().collect(Collectors
                .toMap(s -> s.getName(), s -> (int) (Math.round(s.getPopulation() /
                        s.getArea() * 100)) / 100.0, (a, b) -> b, Hashtable::new));

        return CountryByPopulationDensity.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(10)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }
}
