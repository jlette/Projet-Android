package com.example.competences;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private CompetenceViewModel uneCompetenceViewModel;
    public CompetenceListAdapter adapter;

    public static final int NEW_COMPETENCE_ACTIVITY_REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NouvelleCompetence.class);
                startActivityForResult(intent, NEW_COMPETENCE_ACTIVITY_REQUEST_CODE);
            }
        });
        adapter = new CompetenceListAdapter(this);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final CompetenceListAdapter adapter = new CompetenceListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        uneCompetenceViewModel = ViewModelProviders.of(this).get(CompetenceViewModel.class);

        uneCompetenceViewModel.getMesCompetences().observe(this, new Observer<List<Competence>>() {
            @Override
            public void onChanged(@NonNull List<Competence> competences) {
                adapter.setMesCompetences(competences);
            }
        });

        ItemTouchHelper monHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                int position = viewHolder.getAdapterPosition();
                Competence maCompetence = adapter.getCompetenceAlaPosition(position);
                Toast.makeText(MainActivity.this,"suppression de "+
                        maCompetence.getNomCompetence(),Toast.LENGTH_LONG).show();

                uneCompetenceViewModel.deleteCompetence(maCompetence);
            }
        });

        monHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_COMPETENCE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
            Competence competence = new Competence(data.getStringExtra(NouvelleCompetence.EXTRA_REPLY));
            uneCompetenceViewModel.insert(competence);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    "Competence vide: non sauvegardé",
                    Toast.LENGTH_LONG).show();

        }
    }


}