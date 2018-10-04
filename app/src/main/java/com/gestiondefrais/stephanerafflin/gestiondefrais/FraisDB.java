package com.gestiondefrais.stephanerafflin.gestiondefrais;

/**
 * Created by stephane.rafflin on 11/09/2017.
 */
        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.widget.Toast;

/**
 * Created by stephane.rafflin on 15/09/2017.
 */
public class FraisDB {

    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "gestion.db";
    private static final String TABLE_FRAIS = "Frais";
    private static final String COL_ID = "_id";
    private static final String COL_TYPE = "type";
    private static final String COL_MONTANT = "montant";
    private static final String COL_DATE = "date";
    private static final String COL_JUSTIF = "justif";
    private SQLiteDatabase db;
    private MySQLiteDataBase maBaseSQLite;
    private static final String createTable = "CREATE TABLE "
            + TABLE_FRAIS + " (" + COL_ID
            + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " + COL_TYPE
            + " TEXT NOT NULL, " + COL_MONTANT + " DOUBLE NOT NULL, "
            + COL_DATE + " LONG NOT NULL, "
            + COL_JUSTIF + " BLOB )";
    public Context context;

    public FraisDB(Context context){
        maBaseSQLite = new MySQLiteDataBase(context, NOM_BDD, null, VERSION_BDD, createTable, TABLE_FRAIS);
        this.context = context;
    }

    public void open() {
        db = maBaseSQLite.getWritableDatabase();
    }

    public void close() {
        db.close();
    }


    public long insertFrais(Frais frais) {
        ContentValues values = new ContentValues();
        values.put(COL_TYPE, frais.getType());
        values.put(COL_MONTANT, frais.getMontant());
        values.put(COL_DATE, frais.getDate());
        values.put(COL_JUSTIF, frais.getJustif());
        return db.insert(TABLE_FRAIS, null, values);
    }

    public Cursor getFrais(long dateD, long dateF) {
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_FRAIS + " WHERE date>=? AND date<=? ORDER BY date"
                , new String[]{String.valueOf(dateD), String.valueOf(dateF)});
        return c;
    }

    public Cursor getCurId(int id)
    {
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_FRAIS + " WHERE _id=?", new String[]{String.valueOf(id)});
        return  c;
    }

    public Frais getFraisId(int id) {
        Cursor curs = this.getCurId(id);
        if (curs.getCount() == 0)
            return null;
        curs.moveToFirst();
        Frais frais = new Frais();
        frais.setId(curs.getInt(0));
        frais.setType(curs.getString(1));
        frais.setMontant(curs.getFloat(2));
        frais.setDate(curs.getLong(3));
        frais.setJustif(curs.getBlob(4));
        curs.close();
        return frais;
    }

    public int delete(int id) {
        return db.delete(TABLE_FRAIS, COL_ID + " = " + id, null);
    }

    public int modif(int id, Frais frais) {
        ContentValues values = new ContentValues();
        values.put(COL_TYPE, frais.getType());
        values.put(COL_MONTANT, frais.getMontant());
        values.put(COL_JUSTIF, frais.getJustif());
        return db.update(TABLE_FRAIS, values, COL_ID + " = " + id, null);
    }

    public int supPhoto(int id){
        ContentValues values = new ContentValues();
        byte[]bytes = null;
        values.put(COL_JUSTIF, bytes);
        return db.update(TABLE_FRAIS, values, COL_ID + " = " + id, null);
    }

    public void remove(){
        db.execSQL("DROP TABLE frais;");
    }

}
