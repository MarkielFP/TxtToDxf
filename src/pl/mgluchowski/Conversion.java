package pl.mgluchowski;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Conversion {
    
    public static String c01 = "  a 1        123.12    456.45    ";
    
    public static void main(String[] args) {
        c01 = cleanText(c01);
        System.out.println(c01);
        System.out.println(stringToCoord(c01));
    }
    
    public static void convert(File path) throws FileNotFoundException, IOException {
        List<String> coordinateList = readFile(path);
        // test formatowania tekstu
        List<Coordinate> coorList = stringsToCoords(coordinateList);
        coorList.forEach(System.out::println);
    }
    
    private static List<String> readFile(File path) throws FileNotFoundException, IOException {
        List<String> coordinates = new ArrayList<>();
        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            if (line.contains(" ") || line.contains("\t")) {
                coordinates.add(cleanText(line));
            }
        }
        return coordinates;
    }
    
    private static String cleanText(String text) {
        text = text.replaceAll("\t", " ");
        while (text.contains("  ")) {
            text = text.replaceAll("  ", " ");
        }
        if (text.substring(0, 1).contains(" ")) {
            text = text.substring(1);
        }
        int lenght = text.length();
        if (text.substring(lenght - 1).contains(" ")) {
            text = text.substring(0, lenght - 1);
        }
        return text;
    }
    
    private static String replaceComma(String text) {
        return text.replace(",", ".");
    }
    
    private static List<Coordinate> stringsToCoords(List<String> coordsStrings) {
        List<Coordinate> resultList = new ArrayList<>();
        for (int i = 0; i < coordsStrings.size(); i++) {
            resultList.add(stringToCoord(coordsStrings.get(i)));
        }
        return resultList;
    }
    
    private static Coordinate stringToCoord(String coordString) {
        Coordinate result;
        String[] split = cleanText(coordString).split(" ");
        if (split.length <= 3) {
            String N = split[0];
            double X = Double.parseDouble(replaceComma(split[1]));
            double Y = Double.parseDouble(replaceComma(split[2]));
            result = new Coordinate(N, X, Y);
        } else {
            String N = split[0];
            double X = Double.parseDouble(replaceComma(split[1]));
            double Y = Double.parseDouble(replaceComma(split[2]));
            double H = Double.parseDouble(replaceComma(split[3]));
            result = new Coordinate(N, X, Y, H);
        }
        return result;
    }
}
