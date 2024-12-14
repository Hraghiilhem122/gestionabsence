package com.example.gestionabscence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "absences.db";
    private static final int DATABASE_VERSION = 1;

    // Table enseignants
    private static final String TABLE_ENSEIGNANTS = "enseignants";
    private static final String COL_ID = "id";
    private static final String COL_NOM = "nom";
    private static final String COL_EMAIL = "email";

    // Table absences
    private static final String TABLE_ABSENCES = "absences";
    private static final String COL_DATE = "date";
    private static final String COL_MOTIF = "motif";
    private static final String COL_ENSEIGNANT_ID = "enseignant_id";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableEnseignants = "CREATE TABLE " + TABLE_ENSEIGNANTS + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NOM + " TEXT, " +
                COL_EMAIL + " TEXT)";
        db.execSQL(createTableEnseignants);

        String createTableAbsences = "CREATE TABLE " + TABLE_ABSENCES + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_DATE + " TEXT, " +
                COL_MOTIF + " TEXT, " +
                COL_ENSEIGNANT_ID + " INTEGER, " +
                "FOREIGN KEY (" + COL_ENSEIGNANT_ID + ") REFERENCES " + TABLE_ENSEIGNANTS + "(" + COL_ID + "))";
        db.execSQL(createTableAbsences);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ENSEIGNANTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ABSENCES);
        onCreate(db);
    }

    public boolean ajouterEnseignant(String nom, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NOM, nom);
        values.put(COL_EMAIL, email);
        long result = db.insert(TABLE_ENSEIGNANTS, null, values);
        return result != -1;
    }

    public boolean ajouterAbsence(String date, String motif, int enseignantId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_DATE, date);
        values.put(COL_MOTIF, motif);
        values.put(COL_ENSEIGNANT_ID, enseignantId);
        long result = db.insert(TABLE_ABSENCES, null, values);
        return result != -1;
    }

    public Cursor getAbsences(int enseignantId) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_ABSENCES, null, COL_ENSEIGNANT_ID + "=?", new String[]{String.valueOf(enseignantId)}, null, null, null);
    }
}

