package pl.mgluchowski.types;

import pl.mgluchowski.output.DxfFileSettings;

public class DxfText {

    private Coordinate coordinate;
    private String name = coordinate.getNumber();
    private String x = coordinate.getCoordX();
    private String y = coordinate.getCoordY();
    private String layerName;

    public DxfText(Coordinate coordinate) {
        this.layerName = DxfFileSettings.layerName;
        this.coordinate = coordinate;
    }

    @Override
    public String toString() {
        if (y == null) {
            return "";
        }
        return "TEXT\n"
                + "5\n"
                + "2BB\n"
                + "330\n"
                + "1F\n"
                + "100\n"
                + "AcDbEntity\n"
                + "8\n"
                + layerName + "\n"
                + "100\n"
                + "AcDbText\n"
                + "10\n"
                + x + "\n"
                + "20\n"
                + y + "\n"
                + "30\n"
                + "0.0\n"
                + "40\n"
                + "0.4\n"
                + "1\n"
                + name + "\n"
                + "41\n"
                + "0.9\n"
                + "7\n"
                + "TechnicalLettering\n"
                + "100\n"
                + "AcDbText\n"
                + "0";
    }

}
