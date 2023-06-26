package org.example;

import org.example.util.CityCsvReader;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.apache.commons.io.FilenameUtils;

import static java.lang.System.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String path = "Задача ВС Java Сбер.csv";
        File csvFile = new File(path);
        if(csvFile.isFile() && FilenameUtils.getExtension(path).equals("csv")) {
            CityCsvReader cityCsvReader = new CityCsvReader(path);
            cityCsvReader.getCities().forEach(city -> out.println(city.toString()));
        }
        else {
            out.println("Введен некорректный путь до файла");
        }

    }
}