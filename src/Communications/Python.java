package src.Communications;

import Model.Segment;
import Representation.Matrix;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class Python {

    //potential useful commands:
    //Command("_command")
    //LastCreatedObjects(select=true/false)

    public static void translateToPythonFile(String filename, ArrayList<Matrix> matrices, ArrayList<Segment> segments) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));

            writer.write("import rhinoscriptsyntax as rs\n");

            //Draw lines
            for(int i = 0; i < segments.size(); i++) {
                writer.write("points" + i + " = " + Python.stringifyPointsForArray(segments.get(i)) + ";\n");
                writer.write("objectId" + i + " = rs.AddPolyline(points" + i + ")\n");
                writer.write("print(objectId" + i + ")\n\n");
                writer.write("rs.LastCreatedObjects(True)\n");
            }

            //Draw buildings

            for(int i = 0; i < matrices.size(); i++) {
                for(int j = 0; j < matrices.get(i).rows(); j++ ) {
                    writer.write("objectId" + i + "_" + j + " = rs.AddPoint(" + matrices.get(i).getEntryAt(j).x +
                    ',' + matrices.get(i).getEntryAt(j).y + ',' + matrices.get(i).getEntryAt(j).z + ")\n");

                }
            }


            writer.close();
        }
        catch(Exception e) {
            System.out.println("Error trying to create python file " + filename);
        }
    }

    public static String stringifyPointsForArray(Segment segment) {
        //Takes segment.startPoint, segment.endPoint to
        // "[(startPoint.x, startPoint.y, startPoint.z),(endPoint.x, endPoint.y, endPoint.z)]"

        String stringified = "[(" + segment.start.x + ',' + segment.start.y + ',' + segment.start.z + "),(" +
                segment.end.x + ',' + segment.end.y + ',' + segment.end.z + ")]";


        return stringified;
    }

}
