package com.gestiondefrais.stephanerafflin.gestiondefrais;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.widget.DatePicker;
import android.widget.Toast;

/**
 * Created by stephane.rafflin on 15/09/2017.
 */

public class Date extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date);

        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
        Calendar cal = Calendar.getInstance();
        datePicker.init(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener(){
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDateChanged(DatePicker datePicker,int i, int i1, int i2){
                Intent intent = new Intent(Date.this, MainActivity.class);
                String mois = conv(datePicker.getMonth()+1);
                String jour = conv(datePicker.getDayOfMonth());
                intent.putExtra("date", jour +"-"+mois+"-"+datePicker.getYear());
                startActivity(intent);
            }
        });
    }

    public String conv(int m){
        String convert;
        if(m < 10){
            convert = "0"+m;
        }
        else{
            convert = Integer.toString(m);
        }
        return convert;
    }
}


