package com.example.competences;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;

import java.util.List;

public class CompetenceRepository {

    private CompetenceDao maCompetenceDao;
    private LiveData<List<Competence>> mesCompetences;

    CompetenceRepository(Application application) {
        CompetenceRoomDatabase database = CompetenceRoomDatabase.getDatabase(application);
        maCompetenceDao = database.competenceDao();
        mesCompetences = maCompetenceDao.getToutesCompetences();
    }

    LiveData<List<Competence>> getMesCompetences() {
        return mesCompetences;
    }

    public void insert(Competence uneCompetence) {
        new insertAsyncTask(maCompetenceDao).execute(uneCompetence);
    }

    private static class insertAsyncTask extends AsyncTask<Competence, Void, Void> {
        private CompetenceDao maTacheDao;

        insertAsyncTask(CompetenceDao dao) {
            maTacheDao = dao;
        }

        @Override
        protected Void doInBackground(final Competence... params) {
            maTacheDao.insert(params[0]);
            return null;
        }
    }

    private static class supprimeTache extends AsyncTask<Competence, Void, Void> {
        private CompetenceDao maTacheDao;

        supprimeTache(CompetenceDao dao) {
            maTacheDao = dao;
        }

        @Override
        protected Void doInBackground(final Competence... params) {
            maTacheDao.deleteCompetence(params[0]);
            return null;
        }
    }
}
