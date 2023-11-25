package countries.query;

import base.Repository;
import countries.model.Country;
import countries.model.Region;
import lombok.NonNull;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CountryQueries4Impl extends Repository<Country>
        implements CountryQueries4 {

    public CountryQueries4Impl() throws IOException {
        super(Country.class);
    }

    @Override
    public Map<String, Country> getCountriesByCodes() {
        /*
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

         */

        /*
        return getAll()
                .stream()
                .collect(Collector.of(
                        TreeMap::new,
                        (Map<String, Country> map, Country country) -> map.put(country.getCode(), country),
                        (left, right) ->{
                            left.putAll(right);
                            return left;
                        },
                        Collections::unmodifiableMap
                ));
         */

        return getAll()
                .stream()
                // .collect(Collectors.toMap(Country::getCode, c -> c));
                .collect(Collectors.toMap(Country::getCode, Function.identity()));
    }

    @Override
    public Map<Region, Long> getCountOfCountriesByRegions() {

        /*
        return getAll()
                .stream()
                .collect(Collectors.groupingBy(
                        Country::getRegion,
                        Collectors.counting()
                ));
         */

        /*
        return getAll()
                .stream()
                .collect(Collectors.groupingBy(
                        Country::getRegion,
                        Collector.of(
                                ArrayList::new,
                                (List<Country> countries, Country country) -> countries.add(country),
                                (List<Country> left, List<Country> right) -> {
                                    left.addAll(right);
                                    return left;
                                },
                                countries -> (long) countries.size()
                        )
                ));

         */

        /*
        return getAll()
                .stream()
                .collect(Collectors.groupingBy(
                        Country::getRegion,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                countries -> (long) countries.size()
                        )
                ));

         */

        return getAll()
                .stream()
                .collect(Collectors.groupingBy(
                        Country::getRegion,
                        () -> new EnumMap<>(Region.class),
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                countries -> (long) countries.size()
                        )
                ));


    }

    @Override
    public Map<Region, Set<Country>> getCountriesByRegions() {
        return getAll().stream()
                .collect(Collectors.groupingBy(
                        Country::getRegion,
                        Collectors.toSet()
                ));
    }

    @Override
    public Map<Region, Optional<Country>> getMostPopulousCountryByRegions() {
        return getAll().stream()
                .collect(Collectors.groupingBy(
                        Country::getRegion,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                countries -> countries
                                        .stream()
                                        .reduce((a, b) -> a.getPopulation() > b.getPopulation() ? a : b)
                        )
                ));
    }

    @Override
    public Map<Region, List<Country>> getCountriesByRegionsOrderByCapitals() {
        return getAll().stream()
                .collect(Collectors.groupingBy(
                        Country::getRegion,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                countries -> countries
                                        .stream()
                                        .sorted(Comparator.comparing(Country::getCapital))
                                        .toList()
                        )
                ));
    }

    @Override
    public Map<Region, Set<Country>> getCountriesByRegionsFilterByPopulation(long lowerBound, long upperBound) {
        return getAll().stream()
                .collect(Collectors.groupingBy(
                        Country::getRegion,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                countries -> countries
                                        .stream()
                                        .filter(country -> country.getPopulation() >= lowerBound && country.getPopulation() <= upperBound)
                                        .collect(Collectors.toSet())
                        )
                ));
    }

    @Override
    public Map<Region, Map<String, Country>> getCountriesByRegionsAndCodes() {
        return getAll()
                .stream()
                .collect(Collectors.groupingBy(
                        Country::getRegion,
                        Collectors.toMap(Country::getCode, Function.identity())
                ));
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
        return getAll()
                .stream()
                .collect(Collectors.groupingBy(
                        country -> country.getName().substring(0, 1),
                        Collectors.groupingBy(
                                Country::getRegion,
                                Collectors.toSet())
                ));
    }

    @Override
    public Map<Region, Set<String>> getLocalizedCountryNamesByRegions(@NonNull String locale) {
        return getAll()
                .stream()
                .collect(Collectors.groupingBy(
                        Country::getRegion,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                countries -> countries.stream()
                                        .map(country -> country.getTranslations().getOrDefault(locale, "unknown"))
                                        .collect(Collectors.toSet())
                        )
                ));
    }

    @Override
    public Map<String, Set<String>> getCountryNamesByLocales() {
        return getAll()
                .stream()
                .collect(Collectors.toMap(
                        Country::getName,
                        country -> new HashSet<>(country.getTranslations().values())
                ));
    }

    @Override
    public Map<String, Set<String>> getCountryNamesByLocales(@NonNull Region region) {
        return getAll()
                .stream()
                .filter(country -> country.getRegion() == region)
                .collect(Collectors.toMap(
                        Country::getName,
                        country -> new HashSet<>(country.getTranslations().values())
                ));
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
