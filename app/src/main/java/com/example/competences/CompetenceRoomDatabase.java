package com.example.competences;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {Competence.class}, version = 1)
public abstract class CompetenceRoomDatabase extends RoomDatabase {

    public abstract CompetenceDao competenceDao();

    private static volatile CompetenceRoomDatabase INSTANCE;

    static CompetenceRoomDatabase getDatabase(final Context context){
        if (INSTANCE==null){
            synchronized (CompetenceRoomDatabase.class){
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CompetenceRoomDatabase.class,"competence_database").build();
                }
            }
        }
        return INSTANCE;
    }
}
