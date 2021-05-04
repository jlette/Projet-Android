package com.example.competences;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CompetenceListAdapter extends RecyclerView.Adapter<CompetenceListAdapter.CompetenceViewHolder> {
    class CompetenceViewHolder extends RecyclerView.ViewHolder{
        private final TextView competenceItemView;
        private CompetenceViewHolder(View competenceView) {
            super(competenceView);
            competenceItemView = competenceView.findViewById(R.id.textView);
        }
    }
    private final LayoutInflater monInflater;
    private List<Competence> mesCompetences; // Competences en cache
    CompetenceListAdapter(Context context) {monInflater = LayoutInflater.from(context);}


    @NonNull
    @Override
    public CompetenceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = monInflater.inflate(R.layout.recyclerview_item, parent,false);
        return new CompetenceViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CompetenceViewHolder holder, int position) {
        if (mesCompetences != null){
            Competence competenceCourante = mesCompetences.get(position);
            holder.competenceItemView.setText(competenceCourante.getNomCompetence());
        } else {// pas de donnée à afficher
            holder.competenceItemView.setText("Aucune compétence à afficher");}
    }
    void setMesCompetences(List<Competence> competences){
        mesCompetences = competences;
        notifyDataSetChanged();

    }

    //gere le cas où competences pas encore chargées.
    @Override
    public int getItemCount(){
        if (mesCompetences != null)
            return mesCompetences.size();
        else return 0;
    }

    public Competence getCompetenceAlaPosition(int position){
        return mesCompetences.get(position);
    }
}
