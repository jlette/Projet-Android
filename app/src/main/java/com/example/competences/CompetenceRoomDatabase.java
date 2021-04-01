package com.example.competences;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Competence.class}, version = 1)
public abstract class CompetenceRoomDatabase extends RoomDatabase {

    public abstract CompetenceDao competenceDao();

    private static volatile CompetenceRoomDatabase INSTANCE;

    static CompetenceRoomDatabase getDatabase(final Context context){
        if (INSTANCE==null){
            synchronized (CompetenceRoomDatabase.class){
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CompetenceRoomDatabase.class,"competence_database").addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            new PeuplementDbAsync(INSTANCE).execute();
        }
    };

    private static class PeuplementDbAsync extends AsyncTask<Void, Void, Void> {
        private final CompetenceDao mDao;

        PeuplementDbAsync(CompetenceRoomDatabase db) {
            mDao = db.competenceDao();
        }

        @Override
        protected Void doInBackground(final Void... params){
            mDao.deleteAll();
            Competence uneNouvelleCompetence = new Competence("Bonjour les newbies");
            mDao.insert(uneNouvelleCompetence);
            uneNouvelleCompetence = new Competence("Au boulot !");
            mDao.insert(uneNouvelleCompetence);
            return null;
        }
    }
}
