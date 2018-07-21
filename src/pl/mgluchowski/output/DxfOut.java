package pl.mgluchowski.output;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import pl.mgluchowski.convert.ConversionSettings;
import pl.mgluchowski.types.Coordinate;
import pl.mgluchowski.types.DxfPoint;
import pl.mgluchowski.types.DxfText;

public class DxfOut {

    public static void exportDxf(List<Coordinate> coorList) throws FileNotFoundException {

        System.out.println("Aktualny wątek dla DXF: " + Thread.currentThread().getName());

        String file = ConversionSettings.getFilePath().toString();
        StringBuilder newFile = new StringBuilder();
        String[] fileArray = file.split("\\\\");
        int lengthArr = fileArray.length;
        int lenghtFile = fileArray[lengthArr - 1].length();
        fileArray[lengthArr - 1] = "new" + fileArray[lengthArr - 1].substring(0, lenghtFile - 4) + ".dxf";
        for (int i = 0; i < lengthArr; i++) {
            newFile = newFile.append(fileArray[i]);
            if (i != (lengthArr - 1)) {
                newFile = newFile.append('\\');
            }
        }
        try (PrintWriter pw = new PrintWriter(newFile.toString())) {
            int lenghtCL = coorList.size();

            for (int i = 0; i < lenghtCL; i++) {
                DxfPoint point = new DxfPoint(coorList.get(i));
                DxfText text = new DxfText(coorList.get(i));
                pw.println(point.toString());
                pw.println(text.toString());
            }

        }

        System.out.println("Utworzono plik dxf: " + newFile.toString());
    }
}
