package com.gestiondefrais.stephanerafflin.gestiondefrais;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;

/**
 * Created by stephane.rafflin on 15/09/2017.
 */
public class MainActivity extends AppCompatActivity {

    long dateL;
    String dateS;
    int id;
    Frais fraisMod;
    byte[] img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final FraisDB fraisDb = new FraisDB(this);
        fraisDb.open();
        id = getIntent().getIntExtra("fraisId", 0);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageButton btnHisto = (ImageButton) findViewById(R.id.btnHisto);
        btnHisto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListMonth.class);
                startActivity(intent);
            }
        });

        ImageButton b = (ImageButton) findViewById(R.id.SaisirDate);
        final TextView dateChoi = (TextView) findViewById(R.id.dateChoi);
        final EditText montText = (EditText) findViewById(R.id.montant);
        final EditText edText = ((EditText) findViewById(R.id.type));
        if(id ==0) {
            b.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, Date.class);
                    startActivity(intent);
                }
            });
        }
        else{
            fraisMod = fraisDb.getFraisId(id);
            ConvertDate cd = new ConvertDate();
            dateL = fraisMod.getDate();
            dateS = cd.convertLongToString(dateL);
            img = fraisMod.getJustif();
            dateChoi.setText(dateS);
            edText.setText(fraisMod.getType());
            montText.setText(String.valueOf(fraisMod.getMontant()));
        }
        if(getIntent().getStringExtra("date") != null) {
            dateS = getIntent().getStringExtra("date");
            ConvertDate convDate = new ConvertDate();
            dateL = convDate.DateToLong(dateS);
            dateChoi.setText(dateS);
        }
        ImageButton photo = (ImageButton) findViewById(R.id.Photo);
        photo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent ci = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(ci, 2);
            }
        });
        ImageButton b2 = (ImageButton) findViewById(R.id.enregistrer);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type = ((EditText) findViewById(R.id.type)).getText().toString();
                edText.setEnabled(false);
                edText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (!hasFocus) {
                            hideKeyboard(getApplicationContext(), v);
                        }
                    }
                });
                String mont = montText.getText().toString();
                double m = Float.parseFloat(mont);
                Frais frais = new Frais(type, m, dateL, img);
                if(id == 0) {
                    try {
                        fraisDb.insertFrais(frais);
                        Toast.makeText(getApplicationContext(), "Frais bien enregistrés", Toast.LENGTH_SHORT).show();
                    }catch(java.lang.Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Il manque des informations", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    try {
                        fraisDb.modif(id, frais);
                        Toast.makeText(getApplicationContext(), "Frais bien modifiés", Toast.LENGTH_SHORT).show();
                    }catch(Exception e){
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Il manque des informations", Toast.LENGTH_SHORT).show();
                    }
                }
                fraisDb.close();
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public static void hideKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode==2){
            if(resultCode == Activity.RESULT_OK){
                Bitmap bit=(Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bit.compress(Bitmap.CompressFormat.PNG, 100, baos);
                img = baos.toByteArray();
            }
        }
    }
}