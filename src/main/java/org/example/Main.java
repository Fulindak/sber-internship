package org.example;

import org.example.model.City;
import org.example.util.CitiesDirectory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.*;


import static java.lang.System.*;

public class Main {

    private  static final String path = "Задача ВС Java Сбер.csv";
    public static void main(String[] args){
        Logger log = LoggerFactory.getLogger(Main.class);
        try {
            CitiesDirectory citiesDirectory = new CitiesDirectory(path, ";");
            //Задача № 1 Вывод данных справочника

            citiesDirectory.printCitiesDirectory();

            //Задача № 2 Сортировка справочника
            //Сортировка списка городов по наименованию в алфавитном порядке по убыванию без учета регистра

            citiesDirectory.sortedByCityName().forEach(city -> out.println(city.toString()));

            // Сортировка списка городов по федеральному округу и наименованию города внутри каждого
            // федерального округа в алфавитном порядке по убыванию с учетом регистра

            citiesDirectory.sortedByNameAndDistrict().forEach(city -> out.println(city.toString()));

            //Задача № 3 Поиск города с наибольшим количеством жителей

            City city = citiesDirectory.getCityWithMaxPopulation();
            out.println(String.format("[%d] = %s",citiesDirectory.getCities().indexOf(city), city.getPopulation()));

            //Задача № 4 Поиск количества городов в разрезе регионов.
            // Необходимо определить количество городов в каждом регионе.

            citiesDirectory.getNumberCitiesInRegions().forEach((k, v) -> System.out.println(MessageFormat.format(" {0} = {1}", k, v)));

        } catch (IOException e) {
            log.error("Неверный адрес или файл не является csv: ", e);

        }


    }

}