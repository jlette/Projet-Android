package com.example.competences;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "competence_table")

public class Competence {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name= "nomCompetence")

    private String nomCompetence ;

    public Competence (String nomCompetence){this.nomCompetence = nomCompetence;}
    public String getNomCompetence(){return nomCompetence;}

}
