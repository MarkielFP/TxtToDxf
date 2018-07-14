package pl.mgluchowski;

import java.util.Optional;

public class Coordinate {

    private String number;
    private double coordX;
    private double coordY;
    private Optional<Double> coordH;

    public Coordinate(String number, double coordX, double coordY, Optional<Double> coordH) {
        this.number = number;
        this.coordX = coordX;
        this.coordY = coordY;
        this.coordH = coordH;
    }

    @Override
    public String toString() {
        if (coordH.isPresent()) {
            return "Coordinate{" + "number=" + number + ", X=" + coordX + ", Y=" + coordY + ", H=" + coordH + '}';
        } else {
            return "Coordinate{" + "number=" + number + ", X=" + coordX + ", Y=" + coordY + '}';
        }
    }

}
