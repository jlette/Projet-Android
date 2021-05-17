package com.example.competences;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CompetenceViewModel extends AndroidViewModel {

    private CompetenceRepository monRepository;

    private LiveData<List<Competence>> mesCompetences;

    public CompetenceViewModel (Application application) {
        super(application);
        monRepository = new CompetenceRepository(application);
        mesCompetences = monRepository.getMesCompetences();
    }

    LiveData<List<Competence>> getMesCompetences(){return mesCompetences;}


    public void deleteCompetence(Competence uneCompetence) {
        monRepository.delete(uneCompetence);
    }

    public void insert(Competence uneCompetence) { monRepository.insert(uneCompetence);}

    public void deleteAllCompetence(){
        monRepository.deleteAllcompetence();
    }
}
