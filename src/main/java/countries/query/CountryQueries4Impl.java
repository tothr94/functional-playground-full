package countries.query;

import base.Repository;
import countries.model.Country;
import countries.model.Region;
import lombok.NonNull;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CountryQueries4Impl extends Repository<Country>
        implements CountryQueries4 {

    public CountryQueries4Impl() throws IOException {
        super(Country.class);
    }

    @Override
    public Map<String, Country> getCountriesByCodes() {
        return getAll()
                .stream()
                .collect(Collector.<Country, Map<String, Country>, Map<String, Country>>of(
                        TreeMap::new,
                        (map, country) -> map.put(country.getCode(), country),
                        (left, right) ->{
                            left.putAll(right);
                            return left;
                        },
                        Collections::unmodifiableMap
                ));
    }

    @Override
    public Map<Region, Long> getCountOfCountriesByRegions() {
        return null;
    }

    @Override
    public Map<Region, Set<Country>> getCountriesByRegions() {
        return null;
    }

    @Override
    public Map<Region, Optional<Country>> getMostPopulousCountryByRegions() {
        return null;
    }

    @Override
    public Map<Region, List<Country>> getCountriesByRegionsOrderByCapitals() {
        return null;
    }

    @Override
    public Map<Region, Set<Country>> getCountriesByRegionsFilterByPopulation(long lowerBound, long upperBound) {
        return null;
    }

    @Override
    public Map<Region, Map<String, Country>> getCountriesByRegionsAndCodes() {
        return null;
    }

    @Override
    public Map<Region, Map<String, Set<Country>>> getCountriesByRegionsAndFirstLetters() {
        return getAll()
                .stream()
                .collect(Collectors.groupingBy(
                        Country::getRegion,
                        Collectors.groupingBy(
                                country -> country.getName().substring(0, 1),
                                Collectors.toSet())
                ));
    }

    @Override
    public Map<String, Map<Region, Set<Country>>> getCountriesByFirstLettersAndRegions() {
        return null;
    }

    @Override
    public Map<Region, Set<String>> getLocalizedCountryNamesByRegions(@NonNull String locale) {
        return null;
    }

    @Override
    public Map<String, Set<String>> getCountryNamesByLocales() {
        return null;
    }

    @Override
    public Map<String, Set<String>> getCountryNamesByLocales(@NonNull Region region) {
        return null;
    }

    @Override
    public Map<Region, Optional<String>> getFirstLocalizedCountryNamesByRegions(@NonNull String locale) {
        return null;
    }

    @Override
    public Map<Region, Optional<Country>> getFirstLocalizedCountriesByRegions(@NonNull String locale) {
        return null;
    }

    public static void main(String[] args) throws IOException {
        final var repo = new CountryQueries4Impl();
        System.out.println(repo);
    }
}
