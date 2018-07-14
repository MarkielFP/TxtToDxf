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
         System.out.println(cleanText(c01));
    }
    
    public static void convert(File path) throws FileNotFoundException, IOException {
        List<String> coordinateList = readFile(path);
        coordinateList.forEach((x) -> System.out.println(x));
    }

    private static List<String> readFile(File path) throws FileNotFoundException, IOException {
        List<String> coordinates = new ArrayList<>();
        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            coordinates.add(line);
        }
        return coordinates;
    }

    private static String cleanText(String text) {
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

//    private static Coordinate stringToCoord(String coordString) {
//        String[] split = cleanText(coordString).split(" ");
//        if (split.length) {
//            Coordinate 
//        }
//
//        result = new Coordinate(c01, 0, 0, coordH)
//    }
}
