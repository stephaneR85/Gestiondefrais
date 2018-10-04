package com.gestiondefrais.stephanerafflin.gestiondefrais;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by stephane.rafflin on 15/09/2017.
 */

public class ConvertDate {

    public long DateToLong(String dateS){
        java.util.Date date = new java.util.Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        try {
            date = formatter.parse(dateS);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long longDate = date.getTime();
        return longDate;
    }

    public String dateFinMonth(String mois, int annee){
        if(mois == "01" || mois == "03" || mois == "05" || mois == "07" || mois == "08" || mois == "10" || mois == "12"){
            return "31";
        }
        else if (mois == "04" || mois == "06" || mois == "09" || mois == "11"){
            return "30";
        }
        else if (mois == "02" && (annee % 4) == 0){
            return "29";
        }
        else{
            return "28";
        }
    }

    public String convertLongToString(long dateL){
        Calendar c = Calendar.getInstance();
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("dd MM yyyy", Locale.FRANCE);
        c.setTimeInMillis(dateL);
        java.util.Date dateD = c.getTime();
        String dateS = dateFormat.format(dateD);
        return dateS;
    }
}
