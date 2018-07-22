package pl.mgluchowski.output;

import pl.mgluchowski.types.Coordinate;
import java.io.FileNotFoundException;
import java.io.IOException;
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
            try {
                TxtOut.exportTxt(coorList);
            } catch (IOException ex) {
                Logger.getLogger(Output.class.getName()).log(Level.SEVERE, null, ex);
            }
        };

        Runnable threadDxf = () -> {
            try {
                DxfOut.exportDxf(coorList);
            } catch (IOException ex) {
                Logger.getLogger(Output.class.getName()).log(Level.SEVERE, null, ex);
            }
        };

        executor.submit(threadTxt);
        executor.submit(threadDxf);

        executor.shutdown();
    }

}
