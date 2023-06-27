package org.example.util;

import org.example.model.City;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.out;

public class CitiesDirectory {
    private String path;
    private List<City> cities;
    private final String separatingChar;

    public CitiesDirectory(String path, String separatingChar) throws IOException {
        this.path = path;
        this.separatingChar = separatingChar;
        cities = getCitiesFromCsv();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public List<City> getCitiesFromCsv() throws IOException {
        List<City> cities = new ArrayList<>();
        Scanner csvReader = new Scanner(Paths.get(path));
        while(csvReader.hasNextLine()) {
            City city = parseCSVLine(csvReader.nextLine());
            cities.add(city);
        }
        csvReader.close();
        if(!cities.equals(this.cities))
        {
            setCities(cities);
        }
        return cities;
    }

    private  City parseCSVLine(String line) {
        List<String> city = List.of(line.split(separatingChar));
        Long id = Long.valueOf(city.get(0));
        String name = city.get(1);
        String region = city.get(2);
        String district = city.get(3);
        Long population = Long.valueOf(city.get(4));
        String foundation = city.size() == 6 ? city.get(5) : " ";
        return new City(id, name, region, district, population, foundation);
    }

    public List<City> sortedByCityName() {
        List<City> sortedList = cities;
        sortedList.sort((city1, city2) -> city1.getName().compareToIgnoreCase(city2.getName()));
        return sortedList;
    }

    public List<City> sortedByName() {
        return cities.stream()
                .sorted(Comparator.comparing(City::getName))
                .collect(Collectors.toList());
    }

    public List<City> sortedByNameAndDistrict() {
        return cities.stream()
                .sorted(Comparator.comparing(City::getName))
                .sorted(Comparator.comparing(City::getDistrict))
                .collect(Collectors.toList());
    }

    public City getCityWithMaxPopulation() {
        return cities.stream()
                .max(Comparator.comparing(City::getPopulation))
                .get();
    }

    public Map<String, Long> getNumberCitiesInRegions() {
        return cities.stream()
                .collect(Collectors
                        .groupingBy(City::getRegion, Collectors.counting()));
    }

    public void printCitiesDirectory() {
        getCities().forEach(city -> out.println(city.toString()));
    }

}
