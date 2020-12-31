package com.seroevvlad.CSVToJSON;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Entity {
    private String name;
    private String value;
    private List<Integer> array;
    private int sum;

    public Entity(String name, String value) {
        this.name = name.toLowerCase();
        this.value = value;
        this.array = valueToIntegerArray();
        this.sum = SumArray();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<Integer> getArray() {
        return array;
    }

    public void setArray(List<Integer> array) {
        this.array = array;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public List<Integer> valueToIntegerArray() {
        if (value != null) {
            String[] valuesArray = value.split(",");
            ArrayList<Integer> result = new ArrayList<>();
            for (int i = 0; i < valuesArray.length; i++) {
                try {
                    result.add(Integer.parseInt(valuesArray[i]));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            Collections.sort(result, Collections.reverseOrder());
            return result;
        }
        return null;
    }

    public int SumArray() {
        if (array.size() > 0) {
            int sum = 0;
            for (int i : array) {
                sum += i;
            }
            return sum;
        } else return 0;
    }
}
