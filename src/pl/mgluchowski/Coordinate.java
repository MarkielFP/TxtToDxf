package pl.mgluchowski;

public class Coordinate {

    private String number;
    private double coordX;
    private double coordY;
    private double coordH;

    boolean isXYH = false;

    public Coordinate(String number, double coordX, double coordY, double coordH) {
        isXYH = true;
        this.number = number;
        this.coordX = coordX;
        this.coordY = coordY;
        this.coordH = coordH;
    }

    public Coordinate(String number, double coordX, double coordY) {
        this.number = number;
        this.coordX = coordX;
        this.coordY = coordY;
    }

    @Override
    public String toString() {
        if (isXYH) {
            return number + " " + coordX + " " + coordY + " " + coordH;
        } else {
            return number + " " + coordX + " " + coordY;
        }
    }

}
