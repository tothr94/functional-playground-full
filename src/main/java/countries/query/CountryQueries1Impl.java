package countries.query;

import base.Repository;
import countries.model.Country;
import countries.model.Region;
import lombok.NonNull;

import java.io.IOException;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.function.Predicate;
import java.util.stream.Collector;

public class CountryQueries1Impl extends Repository<Country>
        implements CountryQueries1 {

    public CountryQueries1Impl() throws IOException {
        super(Country.class);
    }

    @Override
    public long getMaximumPopulation() {
        return getAll()
                .stream()
                .mapToLong(Country::getPopulation)
                .sum();
    }

    @Override
    public double getAveragePopulation() {
        return getAll()
                .stream()
                .mapToLong(Country::getPopulation)
                .sum();
    }

    @Override
    public long getCountOfEuropeanCountries() {
        /*
        return getAll()
                .stream()
                .filter(c -> c.getRegion() == Region.EUROPE)
                .count();
         */

        Predicate<Country> europeanPredicate = new Predicate<Country>() {
            @Override
            public boolean test(Country country) {
                return country.getRegion() == Region.EUROPE;
            }
        };

        return getAll()
                .stream()
                .filter(europeanPredicate)
                .count();
    }

    @Override
    public long getCountOfCountriesFilterByRegion(@NonNull Region region) {
        Predicate<Country> regionPredicate = new Predicate<Country>() {
            @Override
            public boolean test(Country country) {
                return country.getRegion() == region;
            }
        };

        return getAll()
                .stream()
                .filter(regionPredicate)
                .count();
    }

    @Override
    public long getPopulationByRegion(Region region) {
        return getAll()
                .stream()
                .filter(country -> country.getRegion() == region)
                .mapToLong(country -> country.getPopulation())
                .sum();
    }

    @Override
    public boolean isPopulationExists(long population) {
        /*
        return getAll()
                .stream()
                .filter(country -> country.getPopulation() == population)
                .count() > 0;

         */

        return getAll()
                .stream()
                .anyMatch(country -> country.getPopulation() == population);
    }

    @Override
    public Country getCountryByCode(@NonNull String code) {
        return getAll()
                .stream()
                .filter(country -> Objects.equals(country.getCode(), code))
                .findFirst()
                .orElseThrow();
    }

    @Override
    public Optional<Country> getOptionalCountryByCode(@NonNull String code) {
        return getAll()
                .stream()
                .filter(country -> Objects.equals(country.getCode(), code))
                .findFirst();
    }

    @Override
    public Optional<Country> getMostPopulousCountryByRegion(@NonNull Region region) {
        /*
        return getAll()
                .stream()
                .filter(country -> country.getRegion() == region)
                .min(Comparator.comparing(Country::getPopulation, Comparator.reverseOrder()));

         */

        return getAll()
                .stream()
                .filter(country -> country.getRegion() == region)
                .reduce((a, b) -> a.getPopulation() > b.getPopulation() ? a : b);
    }

    @Override
    public Optional<Country> getFirstCountryByStartingLetter(char letter) {
        return Optional.empty();
    }

    @Override
    public String getCommaSeparatedOrderedCountryNames() {
        return getAll()
                .stream()
                .map(Country::getName)
                .sorted()
                .collect(Collector.of(
                        () -> new StringJoiner(","),
                        StringJoiner::add,
                        StringJoiner::merge,
                        StringJoiner::toString
                ));
    }

    public static void main(String[] args) throws IOException {
        final var repo = new CountryQueries1Impl();
        System.out.println(repo);
        repo.getPopulationByRegion(Region.EUROPE);
    }
}
