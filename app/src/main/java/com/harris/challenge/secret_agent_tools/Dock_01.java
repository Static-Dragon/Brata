package com.harris.challenge.secret_agent_tools;

import java.text.DecimalFormat;
import java.util.Arrays;
/**
 ** Created by Justin Doyle on 3/29/2016.
 **/
public class Dock_01 extends Dock {
    public static double Z = 0, DecelRate, CoastingTime;
    double[] Time = new double[5000];
    double[] TimeCompleted = new double[5000];
    public String Docking(double FuelGiven, double ThrusterAccelRate, double ThrusterDecelRate, double FuelRate, double DockDistance ){
        double TimeDecel;
        for (int i = 0; i < 201; i++){
            double newFuelRate = ThrusterAccelRate * Z;

            if (newFuelRate > FuelGiven){
                break;
            }
            double Velocity = AccelerationRate(ThrusterAccelRate, Z);
            double AccelDistance = CalcDistanceAcceleration(0, Z, ThrusterAccelRate);
            TimeDecel = 0;

            for (int k = 0; k < 201; k++){
                DecelRate = Velocity - DecelerationRate(ThrusterDecelRate, TimeDecel);

                if (DecelRate < .11 && DecelRate >= .1){
                    break;

                }
                TimeDecel += .1;

            }
            double DecelerationDistance = CalcDistanceDeceleration(Velocity, TimeDecel, ThrusterDecelRate);

            double RemainingDistance = DockDistance - (DecelerationDistance + AccelDistance);

            CoastingTime = RemainingDistance / Velocity;

            double TimeComplete = CoastingTime + RoundOff(TimeDecel) + RoundOff(Z);
            for (int e = 0; e < 5000; e++){
                TimeCompleted[e] = TimeComplete;
                Arrays.sort(TimeCompleted);
                if (e > 100){
                    break;
                }

            }

            if (AccelDistance + DecelerationDistance < DockDistance){
                System.out.println(RoundOff(Z) + " = Acceleration Time");
                System.out.println(RoundOff(TimeDecel) + " = Deceleration Time");
                System.out.println(CoastingTime + " = Coasting Time");
                System.out.println(TimeComplete + " = Time To Complete");
                System.out.println();
            }



            Time[i] = Z;
            Z += .1;
        }
        return "Finished Script!";

    }

    public double AccelerationRate(double Accelrate, double BTime){
        double AccelerationR = Accelrate * BTime;
        return AccelerationR;

    }
    public double DecelerationRate(double DecelRate, double BTime){
        double DecelerationR = DecelRate * BTime;
        return DecelerationR;
    }
    public double CalcDistanceAcceleration(double InitialV, double BTime, double AccelerationRate){
        double Distance = (InitialV * BTime) + (.5 * AccelerationRate * Math.pow(BTime, 2));
        return Distance;
    }
    public double CalcDistanceDeceleration(double InitialV, double BTime, double AccelerationRate){
        double Distance = (InitialV * BTime) + (.5 * -AccelerationRate * Math.pow(BTime, 2));
        return Distance;
    }
    public double RoundOff(double V1x){
        DecimalFormat df = new DecimalFormat("#.###");
        V1x = Double.valueOf(df.format(V1x));
        return V1x;
    }

}
