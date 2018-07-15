package pl.mgluchowski;

import java.io.File;

public class ConversionSettings {

    // wartości (zalezoności) ze swinga do konwersji txt
    private static File filePath;
    private static String prefix;
    private static String suffix;
    private static int round;
    private static boolean ruleKB;

    public static void setFilePath(File filePath) {
        ConversionSettings.filePath = filePath;
    }

    public static void setPrefix(String prefix) {
        ConversionSettings.prefix = prefix;
    }

    public static void setSuffix(String suffix) {
        ConversionSettings.suffix = suffix;
    }

    public static void setRound(int round) {
        ConversionSettings.round = round;
    }

    public static void setRuleKB(boolean ruleKB) {
        ConversionSettings.ruleKB = ruleKB;
    }

    public static File getFilePath() {
        return filePath;
    }

    public static String getPrefix() {
        return prefix;
    }

    public static String getSuffix() {
        return suffix;
    }

    public static int getRound() {
        return round;
    }

    public static boolean isRuleKB() {
        return ruleKB;
    }

    public static void read() {
        System.out.println("Parametry konwersji:");
        System.out.println("Ścieżka do pliku: " + filePath);
        System.out.println("Stopień zaokrąglenia: " + round);
        String yesOrNo = ruleKB ? "TAK" : "NIE";
        System.out.println("Czy zastosować reguły K-B: " + yesOrNo);
        System.out.println("Przedrostek dla nazw punktów: " + prefix);
        System.out.println("Przyrostek dla nazw punktów: " + suffix);

    }
}
