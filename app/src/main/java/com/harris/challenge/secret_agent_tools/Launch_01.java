package com.harris.challenge.secret_agent_tools;
import java.text.DecimalFormat;
/**
 ** Created by Justin Doyle on 3/29/2016.
 **/
public class Launch_01 extends Dock_01 {
    double MPD = 111195.077912;
    public double CenterPointX, CenterPointY;
    public static double offset, originLon, originLat, V1x, V1y, V2x, V2y, V3y, V3x, sideLength ;
    public String LaunchV3(double x1, double y1){ // Obtains triangle point V3
        double newSide = sideLength/2;
        double HypotenuseSquared = Math.pow(sideLength, 2);
        double newSide2 = Math.pow(newSide, 2);
        double middleLine = Math.sqrt(HypotenuseSquared - newSide2);

        V3y = y1 + (newSide/MPD);
        V3x = x1 + (middleLine/MPD);

        String V1xR = Double.toString(RoundOff(V3x));
        String V1yR = Double.toString(RoundOff(V3y));

        return "V3 = " + V1yR + " = X;" + V1xR + " = Y;";
    }
    public String LaunchCenter(double x1, double y1, double x2, double y2, double x3, double y3){
        double centerX = (x1 + x2 + x3)/3; double centerY = (y1 + y2 + y3)/3;

        CenterPointX = RoundOff(centerX);
        CenterPointY = RoundOff(centerY);
        return "Center: " + CenterPointX + " = X; " + CenterPointY + " = Y;";
    }
    public String Launch(double x1, double y1, double sideLengthG, double offsetang){
        sideLength = sideLengthG;
        double OffsetAngle = offsetang;
        offset = Math.toRadians(offset);
        originLon = y1; originLat = x1;
        System.out.println("V1 = " + Double.toString(x1) + " = X;" + Double.toString(y1) + " = Y;");

        double newLength = sideLength/MPD;
        V2x = x1 + newLength;
        V2x = RoundOff(V2x);
        // ^^ First Coords of Vertices B4 Rotation
        V1x = x1;
        V1y = y1;

        Launch_01 Y;
        Y = new Launch_01();
        System.out.println("V2 = " + Double.toString(V2x) + " = X;" + Double.toString(y1) + " = Y;");
        String LaunchV3c = Y.LaunchV3(y1, x1); // Problem
        System.out.println(LaunchV3c);
        String LaunchC = Y.LaunchCenter(x1, y1, V2x, y1, V3y, V3x);
        System.out.println(LaunchC);
        System.out.println("Initializing Rotation...");
        System.out.println(rotation(x1, y1, OffsetAngle, false));
        System.out.println(rotation(V2x, y1, OffsetAngle, false));
        System.out.println(rotation(V3y, V3x, OffsetAngle, false));
        System.out.println(rotation(CenterPointX, CenterPointY, OffsetAngle, true));
        return "";
    }
    public double RoundOff(double V1x){
        DecimalFormat df = new DecimalFormat("#.######");
        V1x = Double.valueOf(df.format(V1x));
        return V1x;
    }
    public String rotation(double lat, double lon, double OffsetAngle, boolean center){
        double newLat = lat - originLat;
        double newLon = lon - originLon;
        double Radian = Math.toRadians(OffsetAngle);
        double latFirstPart = newLat * Math.cos(Radian);
        double latSecondPart = newLon * Math.sin(Radian);

        double lonFirstPart = newLat * Math.sin(Radian);
        double lonSecondPart = newLon * Math.cos(Radian);

        double newPointLat = (latFirstPart - latSecondPart) + originLat;
        double newPointLon = (lonFirstPart + lonSecondPart) + originLon;

        newPointLat = RoundOff(newPointLat);
        newPointLon = RoundOff(newPointLon);

        if (center == false) {
            return newPointLat + " = X; " + newPointLon + " = Y;";

        }
        return newPointLat + " = Center Point X; " + newPointLon + " = Center Point Y";
    }

}
