package pl.mgluchowski.convert;

import pl.mgluchowski.types.Coordinate;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import pl.mgluchowski.output.Output;

public class Conversion {

    public static void convert() throws FileNotFoundException, IOException {
//        ConversionSettings.read();
        List<String> coordinateList = readFile(ConversionSettings.getFilePath());
        List<Coordinate> coorList = stringsToCoords(coordinateList);
        // test formatowania tekstu
//        coorList.forEach(System.out::println);
        Output.export(coorList);
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
        String[] split = cleanText(coordString).split(" ");
        int numberOfFields = split.length;
        switch (numberOfFields) {
            case 4: {
                StringBuilder n = new StringBuilder(split[0]);
                StringBuilder x = new StringBuilder(replaceComma(split[1]));
                StringBuilder y = new StringBuilder(replaceComma(split[2]));
                StringBuilder h = new StringBuilder(replaceComma(split[3]));
                return convertFinally(n, x, y, h);
            }
            case 3: {
                StringBuilder n = new StringBuilder(split[0]);
                StringBuilder x = new StringBuilder(replaceComma(split[1]));
                StringBuilder y = new StringBuilder(replaceComma(split[2]));
                return convertFinally(n, x, y);
            }
            case 2: {
                StringBuilder n = new StringBuilder(split[0]);
                StringBuilder x = new StringBuilder(replaceComma(split[1]));
                return convertFinally(n, x);
            }
            default:
                System.out.println("Błąd danych w pliku TXT");
                return null;
        }
    }

    private static Coordinate convertFinally(StringBuilder n, StringBuilder x, StringBuilder y, StringBuilder h) {
        String id = addPrefixSuffix(n);
        String newX = roundXYH(x, false);
        String newY = roundXYH(y, false);
        String newH = roundXYH(h, true);
        if (!newH.equals("")) {
            return new Coordinate(id, newX, newY, newH);
        } else {
            return new Coordinate(id, newX, newY);
        }
    }

    private static Coordinate convertFinally(StringBuilder n, StringBuilder x, StringBuilder y) {
        String id = addPrefixSuffix(n);
        String newX = roundXYH(x, false);
        String newY = roundXYH(y, false);
        return new Coordinate(id, newX, newY);
    }

    private static Coordinate convertFinally(StringBuilder n, StringBuilder x) {
        String id = addPrefixSuffix(n);
        String newX = roundXYH(x, false);
        return new Coordinate(id, newX);
    }

    private static String addPrefixSuffix(StringBuilder sb) {
        sb.append(ConversionSettings.getSuffix());
        sb.insert(0, ConversionSettings.getPrefix());
        return sb.toString();
    }

    private static String roundXYH(StringBuilder number, boolean isH) {
        int roundPlaces = ConversionSettings.getRound();
        int dotPosition = number.indexOf(".");
        int lenght = number.length();
        boolean isKB = ConversionSettings.isRuleKB();

        if (isH && number.toString().equals("0")) {
            return "";
        }

        if (dotPosition == -1 && roundPlaces == 0) {
            return number.toString();
        }
        if (dotPosition == -1) {
            number.append('.');
            for (int i = 0; i < roundPlaces; i++) {
                number.append('0');
            }
            return number.toString();
        }

        int digitsAfterDot = lenght - (dotPosition + 1);
        if (digitsAfterDot == roundPlaces) {
            return number.toString();
        }
        if (digitsAfterDot < roundPlaces) {
            for (int i = 0; i < (roundPlaces - digitsAfterDot); i++) {
                number.append('0');
            }
            return number.toString();
        }
        if (digitsAfterDot > roundPlaces) {
            return round(number, roundPlaces, isKB);
        }
        return "";
    }

    public static String round(StringBuilder value, int places, boolean isKB) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bd = new BigDecimal(value.toString());
        if (isKB) {
            bd = bd.setScale(places, RoundingMode.HALF_EVEN);
        } else {
            bd = bd.setScale(places, RoundingMode.HALF_UP);
        }
        return bd.toString();
    }

}
