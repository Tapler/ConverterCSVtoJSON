package com.seroevvlad.CSVToJSON;

import org.json.simple.JSONObject;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Tools {
    public static List<String> FindFilesWithThisExtn(String directory, String extn) {
        List<String> list = new ArrayList<>();
        String filePath = new File("").getAbsolutePath();
        File dir = new File(filePath + directory);

        for (File file : dir.listFiles()) {
            if (file.getName().toLowerCase().endsWith((extn))) {
                System.out.println("Find file : " + file);
                list.add(file.getName());
            }
        }
        if (list.size() == 0) {
            System.out.println("There are no files with " + extn + " Extension");
        }
        return list;
    }

    public static List<String[]> readObjectsFromCsv(InputStream is, String name) {
        System.out.println("Find file: " + name);
        String line;
        try {
            List<String[]> data = new ArrayList<>();
            String cvsSplitBy = ",";
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                if (!line.contains("#")) {
                    data.add(line.split(cvsSplitBy));
                }
            }
            return data;
        } catch (IOException e) {
            System.out.println("Can't parse file: " + name);
            return null;
        }
    }

    public static List Unzip(String path) {
        List<String[]> list = new ArrayList<>();
        try (ZipInputStream zipIs = new ZipInputStream(new FileInputStream(path))) {
            ZipEntry entry;
            while ((entry = zipIs.getNextEntry()) != null) {
                if (entry.getSize() < 1) {
                    System.out.println("File is empty");
                    continue;
                }
                list.addAll(readObjectsFromCsv(zipIs, entry.getName()));
                System.out.println("Parsing file " + entry + " is over");
            }
            return list;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static List<Entity> resultCounter(List<String[]> strings) {
        List<Entity> entities = new ArrayList<>();
        Map<String, String> result = new TreeMap<>();
        for (int i = 0; i < strings.size(); i++) {
            String s = strings.get(i)[0];
            if (!result.containsKey(s.toLowerCase()))
                result.put(s.toLowerCase(), strings.get(i)[1]);
            else
                result.replace(s.toLowerCase(), result.get(s.toLowerCase()) + "," + strings.get(i)[1]);
        }
        for (Map.Entry entry : result.entrySet()) {
            entities.add(new Entity(entry.getKey().toString(), entry.getValue().toString()));
        }
        return entities;
    }

    public static void convertToJsonVar01(List<Entity> entityList, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            JSONObject object = new JSONObject();
            for (Entity entity : entityList) {
                object.put(entity.getName(), entity.getSum());
            }
            writer.write(object.toJSONString());
            writer.flush();
            System.out.println("JSON was created, name: " + fileName);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static int tryParse(Object obj) {
        try {
            return Integer.parseInt((String) obj);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static void convertToJsonVar02(List<String[]> list, List<Entity> entityList, String fileName) {
        for (String[] strings : list) {
            for (Entity entity : entityList) {
                if (strings[0].equals(entity.getName())) {
                    strings[1] = String.valueOf(entity.getSum());
                }
            }
        }
        try (FileWriter writer = new FileWriter(fileName)) {
            JSONObject object = new JSONObject();
            for (String[] str : list) {
                if (tryParse(str[1]) != 0)
                    object.put(str[0], tryParse(str[1]));
                else
                    object.put(str[0], null);
            }
            writer.write(object.toJSONString());
            writer.flush();
            System.out.println("JSON was created, name: " + fileName);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void convertToJsonVar03(List<Entity> entityList, String fileName) {

        try (FileWriter writer = new FileWriter(fileName)) {
            JSONObject object = new JSONObject();
            for (Entity entity : entityList) {
                object.put(entity.getName(), entity.getArray());
            }
            writer.write(object.toJSONString());
            writer.flush();
            System.out.println("JSON was created, name: " + fileName);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
