package pl.mgluchowski.types;

public class Coordinate {

    private String number;
    private String coordX;
    private String coordY;
    private String coordH;

    private boolean isXYH = false;
    private boolean isXY = false;

    public Coordinate(String number, String coordX, String coordY, String coordH) {
        isXYH = true;
        this.number = number;
        this.coordX = coordX;
        this.coordY = coordY;
        this.coordH = coordH;
    }

    public Coordinate(String number, String coordX, String coordY) {
        isXY = true;
        this.number = number;
        this.coordX = coordX;
        this.coordY = coordY;
    }

    public Coordinate(String number, String coordX) {
        this.number = number;
        this.coordX = coordX;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setCoordX(String coordX) {
        this.coordX = coordX;
    }

    public void setCoordY(String coordY) {
        this.coordY = coordY;
    }

    public void setCoordH(String coordH) {
        this.coordH = coordH;
    }

    public String getNumber() {
        return number;
    }

    public String getCoordX() {
        return coordX;
    }

    public String getCoordY() {
        return coordY;
    }

    public String getCoordH() {
        return coordH;
    }

    @Override
    public String toString() {
        if (isXYH) {
            return number + " " + coordX + " " + coordY + " " + coordH;
        } else if (isXY) {
            return number + " " + coordX + " " + coordY;
        } else {
            return number + " " + coordX;
        }
    }

}
