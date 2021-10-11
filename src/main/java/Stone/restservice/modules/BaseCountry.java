package Stone.restservice.modules;

import java.util.List;

public class BaseCountry {
    public String name;
    public String capital;
    public List<Currency> currencies;
    public int population;
    public double area;

    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public int getPopulation() {
        return population;
    }

    public double getArea() {
        return area;
    }
}
