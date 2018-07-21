package pl.mgluchowski;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Output {

    // zapisywanie wynikowych plików txt i dxf w dwóch wątkach
    public static void export(List<Coordinate> coorList) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Runnable threadTxt = () -> {
            System.out.println("Aktualny wątek dla TXT: " + Thread.currentThread().getName());
            try {
                exportTxt(coorList);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Output.class.getName()).log(Level.SEVERE, null, ex);
            }
        };

        Runnable threadDxf = () -> {
            System.out.println("Aktualny wątek dla DXF: " + Thread.currentThread().getName());
            exportDxf(coorList);
        };

        executor.submit(threadTxt);
        executor.submit(threadDxf);

        executor.shutdown();
    }

    private static void exportTxt(List<Coordinate> coorList) throws FileNotFoundException {
        String file = ConversionSettings.getFilePath().toString();
        StringBuilder newFile = new StringBuilder();
        String[] fileArray = file.split("\\\\");
        int lengthArr = fileArray.length;
        fileArray[lengthArr - 1] = "new" + fileArray[lengthArr - 1];
        System.out.println(fileArray[lengthArr - 1]);
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

    private static void exportDxf(List<Coordinate> coorList) {

    }

}
