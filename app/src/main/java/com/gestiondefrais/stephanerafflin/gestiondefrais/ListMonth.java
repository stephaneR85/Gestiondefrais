package com.gestiondefrais.stephanerafflin.gestiondefrais;

import android.content.Intent;
import android.database.Cursor;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by stephane.rafflin on 21/09/2017.
 */

public class ListMonth extends AppCompatActivity {
    String year;
    EditText annee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listmonth);
        annee = (EditText) findViewById(R.id.annee);
        if(getIntent().getExtras()==null) {
            Calendar cal = Calendar.getInstance();
            int a = cal.get(Calendar.YEAR);
            year = Integer.toString(a);
        }
        else{
            Bundle d = getIntent().getExtras();
            year = d.getString("annee");
        }
        annee.setText(year);
        ImageButton btnRet = (ImageButton) findViewById(R.id.retourM);
        btnRet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListMonth.this, MainActivity.class);
                startActivity(intent);
            }
        });
        ImageButton btnActu = (ImageButton) findViewById(R.id.actu);
        btnActu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                year = annee.getText().toString();
                Intent intent = new Intent(ListMonth.this, ListMonth.class);
                intent.putExtra("annee",year);
                finish();
                startActivity(intent);
            }
        });

        TextView mont1 = (TextView) findViewById(R.id.montJanvier);
        float m1 = calculMois("01", year);
        mont1.setText(m1+" €");
        ImageButton b1 = (ImageButton) findViewById(R.id.bouton1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListMonth.this, HistoActivity.class);
                ConvertDate conv = new ConvertDate();
                long dateDebutMois = conv.DateToLong("01-01-"+year);
                String nbJour = conv.dateFinMonth("01", Integer.parseInt(year));
                long dateFinMois = conv.DateToLong((nbJour+"-01-"+year));
                intent.putExtra("dateDebut", dateDebutMois);
                intent.putExtra("dateFin", dateFinMois);
                intent.putExtra("annee", annee.getText().toString());
                startActivity(intent);
            }
        });

        TextView mont2 = (TextView) findViewById(R.id.montFevrier);
        float m2 = calculMois("02", year);
        mont2.setText(m2 +" €");
        ImageButton b2 = (ImageButton) findViewById(R.id.bouton2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListMonth.this, HistoActivity.class);
                ConvertDate conv = new ConvertDate();
                long dateDebutMois = conv.DateToLong("01-02-"+year);
                String nbJour = conv.dateFinMonth("02", Integer.parseInt(year));
                long dateFinMois = conv.DateToLong((nbJour+"-02-"+year));
                intent.putExtra("dateDebut", dateDebutMois);
                intent.putExtra("dateFin", dateFinMois);
                intent.putExtra("annee", annee.getText().toString());
                startActivity(intent);
            }
        });
        TextView mont3 = (TextView) findViewById(R.id.montMars);
        float m3 = calculMois("03", year);
        mont3.setText(m3 +" €");
        ImageButton b3 = (ImageButton) findViewById(R.id.bouton3);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListMonth.this, HistoActivity.class);
                ConvertDate conv = new ConvertDate();
                long dateDebutMois = conv.DateToLong("01-03-"+year);
                String nbJour = conv.dateFinMonth("03", Integer.parseInt(year));
                long dateFinMois = conv.DateToLong((nbJour+"-03-"+year));
                intent.putExtra("dateDebut", dateDebutMois);
                intent.putExtra("dateFin", dateFinMois);
                intent.putExtra("annee", annee.getText().toString());
                startActivity(intent);
            }
        });
        TextView mont4 = (TextView) findViewById(R.id.montAvril);
        float m4 = calculMois("04", year);
        mont4.setText(m4 +" €");
        ImageButton b4 = (ImageButton) findViewById(R.id.bouton4);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListMonth.this, HistoActivity.class);
                ConvertDate conv = new ConvertDate();
                long dateDebutMois = conv.DateToLong("01-04-"+year);
                String nbJour = conv.dateFinMonth("04", Integer.parseInt(year));
                long dateFinMois = conv.DateToLong((nbJour+"-04-"+year));
                intent.putExtra("dateDebut", dateDebutMois);
                intent.putExtra("dateFin", dateFinMois);
                intent.putExtra("annee", annee.getText().toString());
                startActivity(intent);
            }
        });
        TextView mont5 = (TextView) findViewById(R.id.montMai);
        float m5 = calculMois("05", year);
        mont5.setText(m5 +" €");
        ImageButton b5 = (ImageButton) findViewById(R.id.bouton5);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListMonth.this, HistoActivity.class);
                ConvertDate conv = new ConvertDate();
                long dateDebutMois = conv.DateToLong("01-05-"+year);
                String nbJour = conv.dateFinMonth("05", Integer.parseInt(year));
                long dateFinMois = conv.DateToLong((nbJour+"-05-"+year));
                intent.putExtra("dateDebut", dateDebutMois);
                intent.putExtra("dateFin", dateFinMois);
                intent.putExtra("annee", annee.getText().toString());
                startActivity(intent);
            }
        });
        TextView mont6 = (TextView) findViewById(R.id.montJuin);
        float m6 = calculMois("06", year);
        mont6.setText(m6 +" €");
        ImageButton b6 = (ImageButton) findViewById(R.id.bouton6);
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListMonth.this, HistoActivity.class);
                ConvertDate conv = new ConvertDate();
                long dateDebutMois = conv.DateToLong("01-06-"+year);
                String nbJour = conv.dateFinMonth("06", Integer.parseInt(year));
                long dateFinMois = conv.DateToLong((nbJour+"-06-"+year));
                intent.putExtra("dateDebut", dateDebutMois);
                intent.putExtra("dateFin", dateFinMois);
                intent.putExtra("annee", annee.getText().toString());
                startActivity(intent);
            }
        });
        TextView mont7 = (TextView) findViewById(R.id.montJuillet);
        float m7 = calculMois("07", year);
        mont7.setText(m7 +" €");
        ImageButton b7 = (ImageButton) findViewById(R.id.bouton7);
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListMonth.this, HistoActivity.class);
                ConvertDate conv = new ConvertDate();
                long dateDebutMois = conv.DateToLong("01-07-"+year);
                String nbJour = conv.dateFinMonth("07", Integer.parseInt(year));
                long dateFinMois = conv.DateToLong((nbJour+"-07-"+year));
                intent.putExtra("dateDebut", dateDebutMois);
                intent.putExtra("dateFin", dateFinMois);
                intent.putExtra("annee", annee.getText().toString());
                startActivity(intent);
            }
        });
        TextView mont8 = (TextView) findViewById(R.id.montAout);
        float m8 = calculMois("08", year);
        mont8.setText(m8 +" €");
        ImageButton b8 = (ImageButton) findViewById(R.id.bouton8);
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListMonth.this, HistoActivity.class);
                ConvertDate conv = new ConvertDate();
                long dateDebutMois = conv.DateToLong("01-08-"+year);
                String nbJour = conv.dateFinMonth("08", Integer.parseInt(year));
                long dateFinMois = conv.DateToLong((nbJour+"-08-"+year));
                intent.putExtra("dateDebut", dateDebutMois);
                intent.putExtra("dateFin", dateFinMois);
                intent.putExtra("annee", annee.getText().toString());
                startActivity(intent);
            }
        });
        TextView mont9 = (TextView) findViewById(R.id.montSeptembre);
        float m9 = calculMois("09", year);
        mont9.setText(m9 +" €");
        ImageButton b9 = (ImageButton) findViewById(R.id.bouton9);
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListMonth.this, HistoActivity.class);
                ConvertDate conv = new ConvertDate();
                long dateDebutMois = conv.DateToLong("01-09-"+year);
                String nbJour = conv.dateFinMonth("09", Integer.parseInt(year));
                long dateFinMois = conv.DateToLong((nbJour+"-09-"+year));
                intent.putExtra("dateDebut", dateDebutMois);
                intent.putExtra("dateFin", dateFinMois);
                intent.putExtra("annee", annee.getText().toString());
                startActivity(intent);
            }
        });
        TextView mont10 = (TextView) findViewById(R.id.montOctobre);
        float m10 = calculMois("10", year);
        mont10.setText(m10 +" €");
        ImageButton b10 = (ImageButton) findViewById(R.id.bouton10);
        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListMonth.this, HistoActivity.class);
                ConvertDate conv = new ConvertDate();
                long dateDebutMois = conv.DateToLong("01-10-"+year);
                String nbJour = conv.dateFinMonth("10", Integer.parseInt(year));
                long dateFinMois = conv.DateToLong((nbJour+"-10-"+year));
                intent.putExtra("dateDebut", dateDebutMois);
                intent.putExtra("dateFin", dateFinMois);
                intent.putExtra("annee", annee.getText().toString());
                startActivity(intent);
            }
        });
        TextView mont11 = (TextView) findViewById(R.id.montNovembre);
        float m11 = calculMois("11", year);
        mont11.setText(m11 +" €");
        ImageButton b11 = (ImageButton) findViewById(R.id.bouton11);
        b11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListMonth.this, HistoActivity.class);
                ConvertDate conv = new ConvertDate();
                long dateDebutMois = conv.DateToLong("01-11-"+year);
                String nbJour = conv.dateFinMonth("11", Integer.parseInt(year));
                long dateFinMois = conv.DateToLong((nbJour+"-11-"+year));
                intent.putExtra("dateDebut", dateDebutMois);
                intent.putExtra("dateFin", dateFinMois);
                intent.putExtra("annee", annee.getText().toString());
                startActivity(intent);
            }
        });
        TextView mont12 = (TextView) findViewById(R.id.montDecembre);
        float m12 = calculMois("12", year);
        mont12.setText(m12 +" €");
        ImageButton b12 = (ImageButton) findViewById(R.id.bouton12);
        b12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListMonth.this, HistoActivity.class);
                ConvertDate conv = new ConvertDate();
                long dateDebutMois = conv.DateToLong("01-12-"+year);
                String nbJour = conv.dateFinMonth("12", Integer.parseInt(year));
                long dateFinMois = conv.DateToLong((nbJour+"-12-"+year));
                intent.putExtra("dateDebut", dateDebutMois);
                intent.putExtra("dateFin", dateFinMois);
                intent.putExtra("annee", annee.getText().toString());
                startActivity(intent);
            }
        });

    }
    public float calculMois(String m, String a){
        float somme = 0;
        ConvertDate conv = new ConvertDate();
        long dateDebutMois = conv.DateToLong("01-"+m+"-"+a);
        int annee = Integer.parseInt(a);
        String jourDsMois = conv.dateFinMonth(m, annee);
        long dateFinMois = conv.DateToLong(jourDsMois + "-" + m + "-" + a);
        FraisDB db = new FraisDB(this);
        db.open();
        Cursor c = db.getFrais(dateDebutMois, dateFinMois);
        try {
            if (c.moveToFirst()) {
                do {
                    float d = (float) c.getDouble(2);
                    somme = somme+d;
                } while (c.moveToNext());
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "No found" + e.getMessage(), Toast.LENGTH_LONG).show();
        }
        c.close();
        db.close();
        return somme;
    }

}
