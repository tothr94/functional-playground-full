package countries.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;


/**
 * Egy országot ábrázoló osztály.
 */
public final class Country {

    /**
     * Az ország kódja (egyedi azonosítója).
     */
    @JsonProperty("alpha2Code")
    private final
    String code;
    /**
     * Az ország neve.
     */
    private final String name;
    /**
     * Az ország fővárosa.
     */
    private final String capital;
    /**
     * Az ország régiója.
     */
    private final Region region;
    /**
     * Az ország népessége.
     */
    private final long population;
    /**
     * Az ország területe.
     */
    private final BigDecimal area;
    /**
     * Az ország nevének fordításai különböző nyelveken.
     */
    private final Map<String, String> translations;
    /**
     * Az ország időzónái.
     */
    private final List<ZoneId> timezones;

    public Country(String code, String name, String capital, Region region, long population, BigDecimal area, Map<String, String> translations, List<ZoneId> timezones) {
        this.code = code;
        this.name = name;
        this.capital = capital;
        this.region = region;
        this.population = population;
        this.area = area;
        this.translations = translations;
        this.timezones = timezones;
    }

    private Country() {
        this.code = null;
        this.name = null;
        this.capital = null;
        this.region = null;
        this.population = 0;
        this.area = null;
        this.translations = null;
        this.timezones = null;
    }

    public static CountryBuilder builder() {
        return new CountryBuilder();
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public String getCapital() {
        return this.capital;
    }

    public Region getRegion() {
        return this.region;
    }

    public long getPopulation() {
        return this.population;
    }

    public BigDecimal getArea() {
        return this.area;
    }

    public Map<String, String> getTranslations() {
        return this.translations;
    }

    public List<ZoneId> getTimezones() {
        return this.timezones;
    }

    public String toString() {
        return "Country(code=" + this.getCode() + ", name=" + this.getName() + ", capital=" + this.getCapital() + ", region=" + this.getRegion() + ", population=" + this.getPopulation() + ", area=" + this.getArea() + ", translations=" + this.getTranslations() + ", timezones=" + this.getTimezones() + ")";
    }

    public Country withCode(String code) {
        return this.code == code ? this : new Country(code, this.name, this.capital, this.region, this.population, this.area, this.translations, this.timezones);
    }

    public Country withName(String name) {
        return this.name == name ? this : new Country(this.code, name, this.capital, this.region, this.population, this.area, this.translations, this.timezones);
    }

    public Country withCapital(String capital) {
        return this.capital == capital ? this : new Country(this.code, this.name, capital, this.region, this.population, this.area, this.translations, this.timezones);
    }

    public Country withRegion(Region region) {
        return this.region == region ? this : new Country(this.code, this.name, this.capital, region, this.population, this.area, this.translations, this.timezones);
    }

    public Country withPopulation(long population) {
        return this.population == population ? this : new Country(this.code, this.name, this.capital, this.region, population, this.area, this.translations, this.timezones);
    }

    public Country withArea(BigDecimal area) {
        return this.area == area ? this : new Country(this.code, this.name, this.capital, this.region, this.population, area, this.translations, this.timezones);
    }

    public Country withTranslations(Map<String, String> translations) {
        return this.translations == translations ? this : new Country(this.code, this.name, this.capital, this.region, this.population, this.area, translations, this.timezones);
    }

    public Country withTimezones(List<ZoneId> timezones) {
        return this.timezones == timezones ? this : new Country(this.code, this.name, this.capital, this.region, this.population, this.area, this.translations, timezones);
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Country)) return false;
        final Country other = (Country) o;
        final Object this$code = this.getCode();
        final Object other$code = other.getCode();
        if (this$code == null ? other$code != null : !this$code.equals(other$code)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $code = this.getCode();
        result = result * PRIME + ($code == null ? 43 : $code.hashCode());
        return result;
    }

    public static class CountryBuilder {
        private String code;
        private String name;
        private String capital;
        private Region region;
        private long population;
        private BigDecimal area;
        private Map<String, String> translations;
        private List<ZoneId> timezones;

        CountryBuilder() {
        }

        @JsonProperty("alpha2Code")
        public CountryBuilder code(String code) {
            this.code = code;
            return this;
        }

        public CountryBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CountryBuilder capital(String capital) {
            this.capital = capital;
            return this;
        }

        public CountryBuilder region(Region region) {
            this.region = region;
            return this;
        }

        public CountryBuilder population(long population) {
            this.population = population;
            return this;
        }

        public CountryBuilder area(BigDecimal area) {
            this.area = area;
            return this;
        }

        public CountryBuilder translations(Map<String, String> translations) {
            this.translations = translations;
            return this;
        }

        public CountryBuilder timezones(List<ZoneId> timezones) {
            this.timezones = timezones;
            return this;
        }

        public Country build() {
            return new Country(this.code, this.name, this.capital, this.region, this.population, this.area, this.translations, this.timezones);
        }

        public String toString() {
            return "Country.CountryBuilder(code=" + this.code + ", name=" + this.name + ", capital=" + this.capital + ", region=" + this.region + ", population=" + this.population + ", area=" + this.area + ", translations=" + this.translations + ", timezones=" + this.timezones + ")";
        }
    }
}
