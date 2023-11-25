package countries.query;

import base.Repository;
import countries.model.Country;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class CountryQueries2Impl extends Repository<Country>
        implements CountryQueries2 {

    public CountryQueries2Impl() throws IOException {
        super(Country.class);
    }

    @Override
    public List<Country> getCountriesOrderByPopulationDesc() {
        return getAll()
                .stream()
                .sorted(Comparator.comparing(Country::getPopulation, Comparator.reverseOrder()))
                .toList();
    }

    @Override
    public List<Country> getCountriesOrderByLengthOfCapitalThenByPopulationDesc() {
        /*
        return getAll()
                .stream()
                .sorted(Comparator.<Country, Integer>comparing(c -> c.getCapital().length())
                        .thenComparing(Country::getPopulation, Comparator.reverseOrder()))
                .toList();
         */

        return getAll()
                .stream()
                .sorted(Comparator.comparing((Country c) -> c.getCapital().length())
                        .thenComparing(Country::getPopulation, Comparator.reverseOrder()))
                .toList();
    }

    @Override
    public List<Country> getCountriesOrderByLengthOfCapitalThenByCapital() {
        return getAll()
                .stream()
                .sorted(Comparator.comparing((Country c) -> c.getCapital().length())
                        .thenComparing(Country::getCapital))
                .toList();
    }

    public static void main(String[] args) throws IOException {
        final var repo = new CountryQueries2Impl();
        System.out.println(repo);
    }
}
