package countries.query;

import base.Repository;
import countries.model.Country;
import countries.model.Region;
import lombok.NonNull;

import java.io.IOException;
import java.math.BigInteger;
import java.util.DoubleSummaryStatistics;
import java.util.Objects;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CountryQueries6Impl extends Repository<Country>
        implements CountryQueries6 {

    public CountryQueries6Impl() throws IOException {
        super(Country.class);
    }

    @Override
    public Optional<Country> getTheGreatestCountry() {
        return Optional.empty();
    }

    @Override
    public DoubleSummaryStatistics getStatisticsOfAreas() {
        return null;
    }

    @Override
    public Optional<BigInteger> getTotalAreaAsOptional() {
        return Optional.empty();
    }

    @Override
    public BigInteger getTotalAreaAsBigInteger() {
        return null;
    }

    @Override
    public String getCommaSeparatedOrderedCountryNames() {
        /*
        return getAll()
                .stream()
                .map(Country::getName)
                .sorted()
                .reduce(
                        "",
                        (a, b) -> a + "," + b
                );

         */

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
        final var repo = new CountryQueries6Impl();
        System.out.println(repo);
        System.out.println(repo.getCommaSeparatedOrderedCountryNames());
    }
}
