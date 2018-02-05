package pl.mateusz.springBaza.utils;

import java.time.LocalDate;

public class UtilNote {

    //Metoda zwracająca date w formacie yyyy/MM/dd jako String
    public static String nextData(){
        LocalDate date = LocalDate.now();
        String nextData="";
        String month ="/"+ String.valueOf(date.getMonthValue());
        String day ="/"+ String.valueOf(date.getDayOfMonth());

        nextData+=date.getYear();
        if (date.getMonthValue()<10){
            month="/0"+date.getMonthValue();
        }if (date.getDayOfMonth()<10){
            day="/0"+date.getDayOfMonth();
        }
        nextData+=month+day;
        return nextData;
    }

    /*Metoda zwraca kolejny numer banknotu,
    Przyjmuje jako argument otatni numer banknotu*/

    public static String nextNumber(String afterNumber){
        String number;
        if(afterNumber.length()==1){
            number="1";
        }else {
            number = afterNumber.substring(afterNumber.length() - 4, afterNumber.length());
            int nextNumber = Integer.parseInt(number) + 1;
            number = String.valueOf(nextNumber);
        }

        while (number.length()<5){
            number="0"+number;
        }
        if (number.length()==5){
            number="/"+number;
        }
        return number;
    }

}
