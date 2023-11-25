package countries.query;

import base.Repository;
import countries.model.Country;
import countries.model.Region;
import lombok.NonNull;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CountryQueries3Impl extends Repository<Country>
        implements CountryQueries3 {

    public CountryQueries3Impl() throws IOException {
        super(Country.class);
    }

    @Override
    public Set<String> getCountryNames() {
        /*
        return getAll()
                .stream()
                .map(Country::getName)
                .collect(Collectors.toSet());

         */

        /*
        return getAll()
                .stream()
                .map(Country::getName)
                .collect(Collectors.toCollection(HashSet::new));
         */

        return getAll()
                .stream()
                .map(Country::getName)
                .collect(Collectors.toCollection(TreeSet::new));

    }

    @Override
    public List<String> getCapitalsOrderByName() {
        return getAll()
                .stream()
                .map(Country::getCapital)
                .sorted()
                .toList();
    }

    @Override
    public List<String> getCapitalsOrderByNameDesc() {
        return getAll()
                .stream()
                .map(Country::getCapital)
                .sorted(Comparator.reverseOrder())
                .toList();
    }

    @Override
    public Set<String> getNamesOfEuropeanCountries() {
        return getAll()
                .stream()
                .filter(c -> c.getRegion() == Region.EUROPE)
                .map(Country::getName)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getNamesOfCountriesFilterByContinent(@NonNull Region region) {
        return getAll()
                .stream()
                .filter(c -> c.getRegion() == region)
                .map(Country::getName)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Country> getCountriesBelowPopulationLimit(int limit) {
        return getAll()
                .stream()
                .filter(c -> c.getPopulation() < limit)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Long> getPopulationsByRegion(@NonNull Region region) {
        return getAll()
                .stream()
                .filter(c -> c.getRegion() == region)
                .map(Country::getPopulation)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Country> getCountriesByPopulation(long population) {
        return getAll()
                .stream()
                .filter(c -> c.getPopulation() == population)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Country> getCountriesByPopulation(long lowerBound, long upperBound) {
        /*
        return getAll()
                .stream()
                .filter(c -> c.getPopulation() >= lowerBound && c.getPopulation() <= upperBound)
                .collect(Collectors.toSet());

         */

        /*
        return getAll()
                .stream()
                .filter(c -> c.getPopulation() >= lowerBound && c.getPopulation() <= upperBound)
                .collect(Collector.of(
                        TreeSet::new,
                        (TreeSet<Country> r, Country c) -> r.add(c),
                        (left, right) -> {
                            left.addAll(right);
                            return left;
                        }
                ));

         */

        return getAll()
                .stream()
                .filter(c -> c.getPopulation() >= lowerBound && c.getPopulation() <= upperBound)
                .collect(Collector.of(
                        TreeSet::new,
                        TreeSet::add,
                        (left, right) -> {
                            left.addAll(right);
                            return left;
                        })
                );
    }

    public static void main(String[] args) throws IOException {
        final var repo = new CountryQueries3Impl();
        System.out.println(repo);
        System.out.println(repo.getCapitalsOrderByName());
    }
}
