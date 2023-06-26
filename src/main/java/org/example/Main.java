package org.example;

import org.example.util.CityCsvReader;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.apache.commons.io.FilenameUtils;

public class Main {
    public static void main(String[] args) throws IOException {
        String path = "Задача ВС Java Сбер.csv";
        File csvFile = new File(path);
        if(csvFile.isFile() && FilenameUtils.getExtension(path).equals("csv")) {
            CityCsvReader cityCsvReader = new CityCsvReader(path);
            cityCsvReader.readAndPrint();
        }
        else {
            System.out.println("Введен некорректный путь до файла");
        }

    }
}