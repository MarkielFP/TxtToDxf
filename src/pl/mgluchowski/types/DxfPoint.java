package pl.mgluchowski.types;

import pl.mgluchowski.output.DxfFileSettings;

public class DxfPoint {

    private Coordinate coordinate;
    private String name = coordinate.getNumber();
    private String x = coordinate.getCoordX();
    private String y = coordinate.getCoordY();
    private String h = coordinate.getCoordH();
    private String layerName;

    public DxfPoint(Coordinate coordinate) {
        this.layerName = DxfFileSettings.layerName;
        this.coordinate = coordinate;
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
                + "5\n"
                + "271\n"
                + "330\n"
                + "1F\n"
                + "100\n"
                + "AcDbEntity\n"
                + "8\n"
                + layerName + "\n"
                + "100\n"
                + "AcDbPoint\n"
                + "10\n"
                + x + "\n"
                + "20\n"
                + y + "\n"
                + "30\n"
                + h + "\n"
                + "0";
    }

}
