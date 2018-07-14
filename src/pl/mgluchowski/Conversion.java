package pl.mgluchowski;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Conversion {

    public static String c01 = "1 123.12 456.45";

    public static void convert(File path) {
        List<String> coordinateList = readFile(path);
        coordinateList.forEach((x) -> System.out.println(x));
    }

    private static List<String> readFile(File path) {
        List<String> coordinates = new ArrayList<>();
        coordinates.add(c01);
        return coordinates;
    }

}
