package pl.mgluchowski.output;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import pl.mgluchowski.convert.ConversionSettings;
import pl.mgluchowski.types.Coordinate;
import pl.mgluchowski.types.DxfPoint;
import pl.mgluchowski.types.DxfText;

public class DxfOut {

    public static void exportDxf(List<Coordinate> coorList) throws FileNotFoundException, IOException {

        System.out.println("Aktualny wątek dla DXF: " + Thread.currentThread().getName());

        String outputFilePath = initOutputFile();

        FileWriter outFile = createOrOpenFile(outputFilePath, true);

        writeData(inputFileFromResource("dxfHeader.txt"), outFile);

        outFile = createOrOpenFile(outputFilePath, false);

        writeData(DxfFileSettings.layerName, outFile);

        outFile = createOrOpenFile(outputFilePath, false);

        writeData(inputFileFromResource("dxfBody.txt"), outFile);

        outFile = createOrOpenFile(outputFilePath, false);

        writeData(inputPointsAndTexts(coorList).toString(), outFile);

        outFile = createOrOpenFile(outputFilePath, false);

        writeData(inputFileFromResource("dxfFooter.txt"), outFile);

        System.out.println("Utworzono plik dxf: " + outputFilePath);
    }

    private static String initOutputFile() {
        String file = ConversionSettings.getFilePath().toString();
        StringBuilder newFile = new StringBuilder();
        String[] fileArray = file.split("\\\\");
        int lengthArr = fileArray.length;
        int lenghtFile = fileArray[lengthArr - 1].length();
        fileArray[lengthArr - 1] = "new" + fileArray[lengthArr - 1].substring(0, lenghtFile - 4) + ".dxf";
        for (int i = 0; i < lengthArr; i++) {
            newFile = newFile.append(fileArray[i]);
            if (i != (lengthArr - 1)) {
                newFile = newFile.append('\\');
            }
        }
        return newFile.toString();
    }

    private static String inputFileFromResource(String resource) throws UnsupportedEncodingException, IOException {
        // this is the path within the jar file
        InputStream input = DxfOut.class.getResourceAsStream("/resources/" + resource);
        if (input == null) {
            // this is how we load file within editor (eg eclipse)
            input = DxfOut.class.getClassLoader().getResourceAsStream(resource);
        }
//        Scanner s = new Scanner(input).useDelimiter("\\A");
//        String result = s.hasNext() ? s.next() : "";

        final int bufferSize = 1024;
        final char[] buffer = new char[bufferSize];
        final StringBuilder out = new StringBuilder();
        Reader in = new InputStreamReader(input, "UTF-8");
        for (;;) {
            int rsz = in.read(buffer, 0, buffer.length);
            if (rsz < 0) {
                break;
            }
            out.append(buffer, 0, rsz);
        }
        return out.toString();
    }

    private static FileWriter createOrOpenFile(String where, boolean wantNewFile) throws IOException {
        if (wantNewFile) {
            return new FileWriter(where, false);
        } else {
            return new FileWriter(where, true);
        }
    }

    private static void writeData(String what, FileWriter fw) throws IOException {
        try (
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw)) {

            pw.println(what);
            bw.close();
        } catch (IOException e) {
            System.out.println("no nie idzie");
        }

    }

    private static StringBuilder inputPointsAndTexts(List<Coordinate> coorList) {
        int id = 0;
        int lenght = coorList.size();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lenght - 1; i++) {
            sb.append(inputPointAndText(coorList.get(id), id));
            id++;
        }
        sb.append(inputLastPointAndText(coorList.get(id), id));
        return sb;
    }

    private static StringBuilder inputPointAndText(Coordinate coor, int id) {
        StringBuilder sb = new StringBuilder();
        DxfPoint dxfPoint = new DxfPoint(coor, id);
        DxfText dxfText = new DxfText(coor, id);
        return sb.append(dxfPoint.toString()).append(dxfText.toString());
    }

    private static StringBuilder inputLastPointAndText(Coordinate coor, int id) {
        StringBuilder sb = new StringBuilder();
        DxfPoint dxfPoint = new DxfPoint(coor, id);
        DxfText dxfText = new DxfText(coor, id);
        String newLastPointText = deleteLastNewLine(dxfText.toString());

        return sb.append(dxfPoint.toString()).append(newLastPointText);
    }

    private static String deleteLastNewLine(String lastPointText) {
        return lastPointText.substring(0, lastPointText.length() - 1);
    }

}
