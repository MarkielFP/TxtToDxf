package pl.mgluchowski.output;

import pl.mgluchowski.convert.ConversionSettings;

public class DxfFileSettings {

    public static String layerName = setLayerName();

    private static String setLayerName() {
        String file = ConversionSettings.getFilePath().toString();
        String[] fileArray = file.split("\\\\");
        int lengthArr = fileArray.length;
        String fileName = fileArray[lengthArr - 1];
        if (fileName.endsWith(".txt")) {
            return fileName.substring(0, fileName.length() - 4);
        }
        return fileName;
    }



}
