package com.seroevvlad.CSVToJSON;

import java.util.ArrayList;
import java.util.List;

import static com.seroevvlad.CSVToJSON.Tools.*;

public class CSVConverter {

    public static void main(String[] args) {
        String fileLocation = "";
        String searchThisExtn = ".zip";
        List<String> files = FindFilesWithThisExtn(fileLocation, searchThisExtn);

        List<String[]> entitiesAll = new ArrayList<>();

        for (String file : files) {
            System.out.println("Unzip file: " + file);
            entitiesAll.addAll(Unzip(file));
        }

        //список объектов Entity
        List<Entity> entityList = resultCounter(entitiesAll);

        //одна метка - итоговое количество
        convertToJsonVar01(entityList, "JSONVar01.json");
        //заранее подготовленный список меток, метки без количества - null
        convertToJsonVar02(entityList, "JSONVar02.json");
        //одна метка - массив всех значений, по убыванию.
        convertToJsonVar03(entityList, "JSONVar03.json");
    }
}
