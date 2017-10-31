package repository;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import model.Country;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CountryRepository {

    private String csvFile = "T:/JavaLabs/Lab1_3/resources/countries.csv";
    private String[] header = new String[]{"countryName", "capital", "continent"};
    public static final String COUNTRIES = "countries";

    public void getCountries(){
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] country = line.split(cvsSplitBy);
                System.out.println(country[0] + " " + country[1] + " " + country[2]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Country> getAllCountriesOnServer() {
        List<Country> countries = new ArrayList<>();
        try {
            CSVReader reader = new CSVReader(new FileReader(csvFile));
            ColumnPositionMappingStrategy<Country> beanStrategy = new ColumnPositionMappingStrategy<>();
            beanStrategy.setType(Country.class);
            beanStrategy.setColumnMapping(header);
            CsvToBean<Country> csvToBean = new CsvToBean<>();
            csvToBean.setMappingStrategy(beanStrategy);
            csvToBean.setCsvReader(reader);
            countries = csvToBean.parse();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(Country country: countries){
            if(country.getCountryName().equals(header[0])){
                countries.remove(country);
                break;
            }
        }
        return countries;
    }

    public void storeCountryOnServer(Country country) {
        try {
            List<Country> countries = getAllCountriesOnServer();
            countries = Country.addCountry(country, countries);
            FileWriter writer = new FileWriter(csvFile);
            CSVWriter csvWriter = new CSVWriter(writer);
            List<String[]> data = toStringArray(countries);
            csvWriter.writeAll(data);
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String[]> toStringArray(List<Country> countries) {
        List<String[]> records = new ArrayList<>();
        records.add(header);
        Iterator<Country> it = countries.iterator();
        while (it.hasNext()) {
            Country country = it.next();
            records.add(new String[]{country.getCountryName(), country.getCapital(), country.getContinent()});
        }
        return records;
    }

    public List<Country> getAllCountriesOnSession(HttpSession session){
        return (List<Country>) session.getAttribute(COUNTRIES);
    }

    public void storeCountryOnSession(Country country, HttpSession session){
        List<Country> countries = getAllCountriesOnSession(session);
        countries = Country.addCountry(country, countries);
        session.setAttribute(COUNTRIES, countries);
    }

    public static void main(String[] args){
        CountryRepository repository = new CountryRepository();
        repository.getAllCountriesOnServer();
        repository.getCountries();
    }
}
