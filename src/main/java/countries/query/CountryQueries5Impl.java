package countries.query;

import base.Repository;
import countries.model.Country;
import countries.model.Region;

import java.io.IOException;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

public class CountryQueries5Impl extends Repository<Country>
        implements CountryQueries5 {

    public CountryQueries5Impl() throws IOException {
        super(Country.class);
    }

    @Override
    public boolean doesIslandExist() {
        return false;
    }

    @Override
    public Optional<Country> getFirstIsland() {
        return Optional.empty();
    }

    @Override
    public Set<String> getNamesWithSameBounds() {
        return null;
    }

    @Override
    public Set<Long> getSmallestPopulations() {
        return null;
    }

    @Override
    public Set<String> getNamesOfLeastPopulousCountries() {
        return null;
    }

    @Override
    public IntSummaryStatistics getTranslationsStatistics() {
        return null;
    }

    @Override
    public Set<String> getNamesOrderByNumberOfTimezones() {
        return null;
    }

    @Override
    public List<String> getNamesAndTimeZoneOrderByNumberOfTimezones() {
        return null;
    }

    @Override
    public long countCountriesWithoutSpanishTranslation() {
        return getAll()
                .stream()
                .filter(country -> !country.getTranslations().containsKey("ES"))
                .count();
    }

    @Override
    public List<Country> getCountriesWithUnknownArea() {
        return getAll()
                .stream()
                .filter(country -> Objects.isNull(country.getArea()))
                .collect(Collectors.toList());
    }

    @Override
    public Set<String> getDistinctLanguageTags() {
        return getAll()
                .stream()
                .flatMap(country -> country.getTranslations()
                        .values()
                        .stream())
                .sorted()
                .collect(Collectors.toSet());
    }

    @Override
    public OptionalDouble getAverageLengthOfCountryNames() {
        return getAll()
                .stream()
                .map(Country::getName)
                .mapToInt(String::length)
                .average();
    }

    @Override
    public Set<Region> getRegionsHavingUnknownAreas() {
        return getAll()
                .stream()
                .filter(country -> Objects.isNull(country.getArea()))
                .map(Country::getRegion)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getNamesHavingKnownAreBelowOne() {
        return null;
    }

    @Override
    public Set<ZoneId> getDistinctAsianAndEuropeanTimeZones() {
        return null;
    }

    public static void main(String[] args) throws IOException {
        final var repo = new CountryQueries5Impl();
        System.out.println(repo);
    }
}
