package eu.ase.jcf.classes;

public class Country {
    private final int idCountry;
    private final String name;

    public Country(int idCountry, String codeNameCountry) {
        this.idCountry = idCountry;
        this.name = codeNameCountry;
    }

    public void print() {
        System.out.println("Country - id = " + idCountry
                + ", codeNameCountry = " + name);
    }
}