package com.seroevvlad.CSVToJSON;

import java.util.ArrayList;
import java.util.List;

import static com.seroevvlad.CSVToJSON.Tools.*;

public class CSVConverter {

    public static void main(String[] args) {
        String fileLocation = "";
        String searchThisExtn = ".zip";
        List<String> files = Tools.FindFilesWithThisExtn(fileLocation, searchThisExtn);

        List<String[]> entitiesAll = new ArrayList<>();

        for (String file : files) {
            System.out.println("Unzip file: " + file);
            entitiesAll.addAll(Unzip(file));
        }

        //список объектов Entity
        List<Entity> list = resultCounter(entitiesAll);

        //для варианта 2
        List<String[]> listVar02 = new ArrayList<>();
        for (Entity ent : list) {
            listVar02.add(new String[]{ent.getName(), null});
            listVar02.add(new String[]{ent.getName().toUpperCase(), null});
        }
        //одна метка - итоговое количество
        convertToJsonVar01(list, "JSONVar01.json");
        //заранее подготовленный список меток, метки без количества - null
        convertToJsonVar02(listVar02, list, "JSONVar02.json");
        //одна метка - массив всех значений, по убыванию.
        convertToJsonVar03(list, "JSONVar03.json");
    }
}
