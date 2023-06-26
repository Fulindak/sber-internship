package org.example.util;

import org.example.model.City;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CityCsvReader {
    private String path;

    public CityCsvReader(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<City> getCities() throws IOException {
        List<City> cities = new ArrayList<>();
        Scanner csvReader = new Scanner(Paths.get(path));
        while(csvReader.hasNextLine()) {
            City city = parseCSVLine(csvReader.nextLine());
            cities.add(city);
        }
        csvReader.close();
        return cities;
    }

    private static City parseCSVLine(String line) {
        List<String> city = List.of(line.split(";"));
        String name = city.get(1);
        String region = city.get(2);
        String district = city.get(3);
        Long population = Long.valueOf(city.get(4));
        String foundation = city.size() == 6 ? city.get(5) : " ";
        return new City(name, region, district, population, foundation);
    }
}
