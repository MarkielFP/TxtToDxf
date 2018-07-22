package pl.mgluchowski.types;

import pl.mgluchowski.output.DxfFileSettings;

public class DxfText {

    private final Coordinate coordinate;
    private final int id;
    private final String name;
    private final String x;
    private final String y;
    private String h;
    private final String layerName;

    public DxfText(Coordinate coordinate, int id) {
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
        String yForH = Double.toString(Double.parseDouble(y) - 0.6);
        if (y == null) {
            return "";
        }
//        if (h == null) {
//            // wstawianie samej nazwy
//            return "TEXT\n"
//                    + "  5\n"
//                    + (100 + id) + "A\n"
//                    + "330\n"
//                    + "1F\n"
//                    + "100\n"
//                    + "AcDbEntity\n"
//                    + "  8\n"
//                    + layerName + "\n"
//                    + "100\n"
//                    + "AcDbText\n"
//                    + " 10\n"
//                    + x + "\n"
//                    + " 20\n"
//                    + y + "\n"
//                    + " 30\n"
//                    + "0.0\n"
//                    + " 40\n"
//                    + "0.4\n"
//                    + "  1\n"
//                    + name + "\n"
//                    + " 41\n"
//                    + "0.9\n"
//                    + "  7\n"
//                    + "TechnicalLettering\n"
//                    + "100\n"
//                    + "AcDbText\n"
//                    + "  0\n";
//        }
        // wstawianie nazwy i wysokości punktu
        return "TEXT\n"
                + "  5\n"
                + (100 + id) + "A\n"
                + "330\n"
                + "1F\n"
                + "100\n"
                + "AcDbEntity\n"
                + "  8\n"
                + layerName + "\n"
                + "100\n"
                + "AcDbText\n"
                + " 10\n"
                + x + "\n"
                + " 20\n"
                + y + "\n"
                + " 30\n"
                + "0.0\n"
                + " 40\n"
                + "0.4\n"
                + "  1\n"
                + name + "\n"
                + " 41\n"
                + "0.9\n"
                + "  7\n"
                + "TechnicalLettering\n"
                + "100\n"
                + "AcDbText\n"
                + "  0\n"
                + "TEXT\n"
                + "  5\n"
                + (100 + id) + "B\n"
                + "330\n"
                + "1F\n"
                + "100\n"
                + "AcDbEntity\n"
                + "  8\n"
                + layerName + "\n"
                + "100\n"
                + "AcDbText\n"
                + " 10\n"
                + x + "\n"
                + " 20\n"
                + yForH + "\n"
                + " 30\n"
                + "0.0\n"
                + " 40\n"
                + "0.4\n"
                + "  1\n"
                + h + "\n"
                + " 41\n"
                + "0.9\n"
                + "  7\n"
                + "TechnicalLettering\n"
                + "100\n"
                + "AcDbText\n"
                + "  0\n";
    }

}
