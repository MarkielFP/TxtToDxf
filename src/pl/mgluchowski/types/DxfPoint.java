package pl.mgluchowski.types;

import pl.mgluchowski.output.DxfFileSettings;

public class DxfPoint {

    private Coordinate coordinate;
    private int id;
    private String name;
    private String x;
    private String y;
    private String h;
    private String layerName;

    public DxfPoint(Coordinate coordinate, int id) {
        this.coordinate = coordinate;
        this.id = id;
        this.name = coordinate.getNumber();
        this.x = coordinate.getCoordX();
        this.y = coordinate.getCoordY();
        this.h = coordinate.getCoordH();
        this.layerName = DxfFileSettings.layerName;
    }

    @Override
    public String toString() {
        if (y == null) {
            System.out.println(
                    "Punkt nr: " + name + " pominięto w dxf ze względu na brak wsp Y");
            return "";
        }
        if (h == null) {
            h = "0.0";
        }
        return "POINT\n"
                + "  5\n"
                + (1000 + id) + "\n"
                + "330\n"
                + "1F\n"
                + "100\n"
                + "AcDbEntity\n"
                + "  8\n"
                + layerName + "\n"
                + "100\n"
                + "AcDbPoint\n"
                + " 10\n"
                + x + "\n"
                + " 20\n"
                + y + "\n"
                + " 30\n"
                + h + "\n"
                + "  0\n";
    }

}
