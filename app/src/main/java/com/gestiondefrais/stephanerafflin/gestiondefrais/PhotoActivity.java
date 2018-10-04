package com.gestiondefrais.stephanerafflin.gestiondefrais;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by stephane.rafflin on 19/12/2017.
 */

public class PhotoActivity extends Activity {

    int id;
    byte[] bytes;
    FraisDB db = new FraisDB(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_view);
        bytes = getIntent().getExtras().getByteArray("bytes");
        id = getIntent().getExtras().getInt("id");
        Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        ImageView imgv = (ImageView)findViewById(R.id.imageJustif);
        imgv.setImageBitmap(bmp);
        TextView titre = (TextView) findViewById(R.id.titreImage);
        titre.setText(afficheType(id));
        imgv.setClickable(true);
        imgv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                dialogue(id);
            }
        });
    }

    public String afficheType(int id){
        db.open();
        String type = db.getFraisId(id).getType();
        db.close();
        return type;
    }

    public Dialog dialogue(final int id){
        Dialog d;
        db.open();
        final Frais f = db.getFraisId(id);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(f.getType());
        alert.setMessage("Choisir une action :");
        alert.setPositiveButton("Enregistrer la photo sur le téléphone", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                FileOutputStream out = null;
                try {
                    out = new FileOutputStream("justif.jpeg");
                    out = openFileOutput("Justif",MODE_APPEND);
                    out.write(bytes);
                    if(out != null)
                        out.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "Justificatif enregistré", Toast.LENGTH_SHORT).show();
            }
        });
        alert.setNeutralButton("Supprimer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                int rep = db.supPhoto(id);
                Toast.makeText(getApplicationContext(), "Photo supprimée", Toast.LENGTH_SHORT).show();
            }
        });
        alert.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        d = alert.create();
        d.show();
        return d;
    }

}