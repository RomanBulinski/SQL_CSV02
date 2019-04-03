package DAO;

import java.io.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CSVLoader {

    private String delimiter;
    private String[] headers;

    public List<List<String>> getInputFile(String inputFilePath, String delimiter) {

        this.delimiter = delimiter;

        List<List<String>> inputListofMaps = new ArrayList<>();
        try {
            File inputF = new File(inputFilePath);
            InputStream inputFS = new FileInputStream(inputF);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));
            inputListofMaps = br.lines().skip(1).map(mapToItem).collect(Collectors.toList());
            br.close();
        } catch (IOException e) {

        }
        return inputListofMaps;
    }

    private Function<String, List<String>> mapToItem = (line) -> {
        String[] p = line.split(delimiter);// a CSV
        List<String> map = new ArrayList<>();
        for (int i = 0; i < p.length; i++) {
            map.add(p[i]);
        }
        return map;
    };

    public String[] getFirstLine(String inputFilePath, String delimiter) {
        String[] firstLine = new String[0];
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
            String line = br.readLine();
            firstLine = line.split(delimiter);
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return firstLine;
    }


}