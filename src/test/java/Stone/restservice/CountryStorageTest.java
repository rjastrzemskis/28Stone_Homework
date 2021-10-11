package Stone.restservice;

import Stone.restservice.modules.BaseCountry;
import Stone.restservice.storage.CountryStorage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CountryStorageTest {

	@Autowired
	private CountryStorage countryStorage;

	@Test
	public void getCountryAllCountryTest() {
		List<BaseCountry> result = this.countryStorage.getCountries();
		assertThat(result.size()).isEqualTo(31);
	}

	@Test
	public void get10CountryPopulationTest() {
		LinkedHashMap<String, Integer> result = this.countryStorage.countryListSorted10CountryPopulation();
		assertThat(result.size()).isEqualTo(10);
		assertThat(result.values().stream()).isSortedAccordingTo(Comparator.reverseOrder());
	}

	@Test
	public void get10CountryAreaTest() {
		LinkedHashMap<String, Double> result = this.countryStorage.countryListSorted10CountryArea();
		assertThat(result.size()).isEqualTo(10);
		assertThat(result.values().stream()).isSortedAccordingTo(Comparator.reverseOrder());
	}

	@Test
	public void get10CountryDensityTest() {
		LinkedHashMap<String, Double> result = this.countryStorage.countryListSorted10CountryDensity();
		assertThat(result.size()).isEqualTo(10);
		assertThat(result.values().stream()).isSortedAccordingTo(Comparator.reverseOrder());
	}
}
