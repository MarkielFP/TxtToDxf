package pl.mgluchowski.output;

import java.util.List;
import pl.mgluchowski.types.Coordinate;

public class DxfOut {

    public static void exportDxf(List<Coordinate> coorList) {
        
        System.out.println("Aktualny wątek dla DXF: " + Thread.currentThread().getName());
        
    }
}
