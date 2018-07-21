package pl.mgluchowski.output;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import pl.mgluchowski.convert.ConversionSettings;
import pl.mgluchowski.types.Coordinate;

public class TxtOut {

    public static void exportTxt(List<Coordinate> coorList) throws FileNotFoundException {
        System.out.println("Aktualny wątek dla TXT: " + Thread.currentThread().getName());
        String file = ConversionSettings.getFilePath().toString();
        StringBuilder newFile = new StringBuilder();
        String[] fileArray = file.split("\\\\");
        int lengthArr = fileArray.length;
        fileArray[lengthArr - 1] = "new" + fileArray[lengthArr - 1];
        for (int i = 0; i < lengthArr; i++) {
            newFile = newFile.append(fileArray[i]);
            if (i != (lengthArr - 1)) {
                newFile = newFile.append('\\');
            }
        }
        try (PrintWriter pw = new PrintWriter(newFile.toString())) {
            int lenghtCL = coorList.size();
            for (int i = 0; i < lenghtCL; i++) {
                pw.println(coorList.get(i).toString());
            }
        }
        System.out.println("Utworzono plik txt: " + newFile.toString());
    }

}
