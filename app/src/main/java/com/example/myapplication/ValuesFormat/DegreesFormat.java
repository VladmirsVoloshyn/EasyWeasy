package com.example.myapplication.ValuesFormat;

public class DegreesFormat {
    public static double formatToCelsius(double kelvinDegree){
        return  (kelvinDegree - 276.14);
    }

    public double formatToKelvin(double celsiusDegree){
        return (celsiusDegree + 276.14);
    }
}
