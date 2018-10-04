package com.gestiondefrais.stephanerafflin.gestiondefrais;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

/**
 * Created by stephane.rafflin on 18/09/2017.
 */

public class HistoActivity extends Activity {

    int idFrais;
    String idPhoto;
    String annee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historique);
        Bundle d = getIntent().getExtras();
        long date1 = d.getLong("dateDebut");
        long date2 = d.getLong("dateFin");
        annee = d.getString("annee");
        ImageButton btnRet = (ImageButton) findViewById(R.id.retourL);
        btnRet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HistoActivity.this, ListMonth.class);
                intent.putExtra("annee",annee);
                startActivity(intent);
            }
        });
        affiche(date1, date2);
    }
    public void affiche(long dateDebut, long dateFin) {
        ListView tab = (ListView) findViewById(R.id.list);
        FraisDB db = new FraisDB(this);
        db.open();
        //String[] from = new String[]{"date","type","montant"};
        //int[] to = new int[]{R.id.col1,R.id.col2,R.id.col3};
        Cursor cur = db.getFrais(dateDebut, dateFin);
        final ArrayList<Frais> tabF = new ArrayList<Frais>();
        int i = 0;
            while (cur.moveToNext()) {
                Frais f = new Frais();
                f.setId(cur.getInt(0));
                f.setDate(cur.getLong(3));
                f.setMontant(cur.getDouble(2));
                f.setType(cur.getString(1));
                tabF.add(f);
                //Log.d("myTag", "type : " + tabF.get(i).getType());
                i++;
            }
        //SimpleCursorAdapter adapter1 = new SimpleCursorAdapter(this, R.layout.item_view, cur, from, to, 0);
        //tab.setAdapter(adapter1);
        ItemAdapter adapter2 = new ItemAdapter(this,tabF);
        tab.setAdapter(adapter2);

        tab.setOnItemClickListener(new ListView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> av, View v, int pos, final long id) {
                Frais item = (Frais) av.getItemAtPosition(pos);
                idFrais = item.getId();
                final String s1 = "Modifier";
                final String s2 = "Supprimer";
                final String s3 = "Voir justificatif";
                PopupMenu popup = new PopupMenu(HistoActivity.this,v);
                MenuInflater inflater = getMenuInflater();
                popup.getMenuInflater().inflate(R.menu.menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch(item.getTitle().toString()){
                            case s1:
                                modif(idFrais);
                                break;
                            case s2:
                                supprime(idFrais);
                                Intent intent = new Intent(HistoActivity.this, ListMonth.class);
                                startActivity(intent);
                                break;
                            case s3 :
                                byte[]bytes = tabByte();
                                if (bytes != null) {
                                    Intent intentphoto = new Intent(HistoActivity.this, PhotoActivity.class);
                                    intentphoto.putExtra("bytes", bytes);
                                    intentphoto.putExtra("id", idFrais);
                                    startActivity(intentphoto);
                                }
                                else{
                                    Toast.makeText(getApplicationContext(), "Pas de justificatif", Toast.LENGTH_SHORT).show();
                                }
                                break;
                        }
                        return true;
                    }
                });
                popup.show();
            }
        });
        db.close();
    }

    public void supprime(int id){
        FraisDB db = new FraisDB(this);
        db.open();
        db.delete(id);
        db.close();
    }

    public void modif(int id){
        Intent intent = new Intent(HistoActivity.this, MainActivity.class);
        intent.putExtra("fraisId", id);
        startActivity(intent);
    }

    public byte[] tabByte(){
        FraisDB db = new FraisDB(this);
        db.open();
        final Frais f = db.getFraisId(idFrais);
        byte[]bytes = f.getJustif();
        db.close();
        return bytes;
    }
}
