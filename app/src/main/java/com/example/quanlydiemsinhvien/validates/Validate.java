package com.example.quanlydiemsinhvien.validates;

import java.util.Calendar;

public class Validate {
    public static boolean noString(String temp){
        if(temp.length() != 0){
            return false;
        }
        return true;
    }

    public static boolean lengthOfStringFrom8To32(String temp){
        if(temp.length() >= 8 && temp.length() <= 32){
            return false;
        }
        return true;
    }

    public static boolean isNumeric(String temp){
        for(char c : temp.toCharArray()){
            if(Character.isDigit(c)){
                return true;
            }
        }
        return false;
    }

    public static boolean checkNamBatDau(int yearBD){
        if(yearBD != Calendar.YEAR){
            return false;
        }
        return true;
    }

    public static boolean checkNamKetThuc(int yearBD, int yearKT){
        if(yearKT - yearBD > 0 && yearKT - yearBD == 3){
            return true;
        }
        return false;
    }
}
