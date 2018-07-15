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

    public void setNumber(String number) {
        this.number = number;
    }

    public void setCoordX(double coordX) {
        this.coordX = coordX;
    }

    public void setCoordY(double coordY) {
        this.coordY = coordY;
    }

    public void setCoordH(double coordH) {
        this.coordH = coordH;
    }

    public String getNumber() {
        return number;
    }

    public double getCoordX() {
        return coordX;
    }

    public double getCoordY() {
        return coordY;
    }

    public double getCoordH() {
        return coordH;
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
