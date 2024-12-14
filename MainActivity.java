package com.example.gestionabscence;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    //@Override
    //protected void onCreate(Bundle savedInstanceState) {
       // super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);
       // ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
      //      Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
    //        v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
  //          return insets;
    //    });
  //  }
        private DatabaseHelper db;
        private EditText nomInput, emailInput, dateInput, motifInput, enseignantIdInput;
        private Button ajouterEnseignantBtn, ajouterAbsenceBtn, afficherAbsencesBtn;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            db = new DatabaseHelper(this);

            // Références des vues
            nomInput = findViewById(R.id.nomInput);
            emailInput = findViewById(R.id.emailInput);
            dateInput = findViewById(R.id.dateInput);
            motifInput = findViewById(R.id.motifInput);
            enseignantIdInput = findViewById(R.id.enseignantIdInput);

            ajouterEnseignantBtn = findViewById(R.id.ajouterEnseignantBtn);
            ajouterAbsenceBtn = findViewById(R.id.ajouterAbsenceBtn);
            afficherAbsencesBtn = findViewById(R.id.afficherAbsencesBtn);

            // Ajouter un enseignant
            ajouterEnseignantBtn.setOnClickListener(v -> {
                String nom = nomInput.getText().toString();
                String email = emailInput.getText().toString();
                if (db.ajouterEnseignant(nom, email)) {
                    Toast.makeText(MainActivity.this, "Enseignant ajouté", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Erreur lors de l'ajout", Toast.LENGTH_SHORT).show();
                }
            });

            // Ajouter une absence
            ajouterAbsenceBtn.setOnClickListener(v -> {
                String date = dateInput.getText().toString();
                String motif = motifInput.getText().toString();
                int enseignantId = Integer.parseInt(enseignantIdInput.getText().toString());
                if (db.ajouterAbsence(date, motif, enseignantId)) {
                    Toast.makeText(MainActivity.this, "Absence ajoutée", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Erreur lors de l'ajout", Toast.LENGTH_SHORT).show();
                }
            });

            // Afficher les absences
            afficherAbsencesBtn.setOnClickListener(v -> {
                int enseignantId = Integer.parseInt(enseignantIdInput.getText().toString());
                Cursor cursor = db.getAbsences(enseignantId);
                if (cursor.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "Aucune absence trouvée", Toast.LENGTH_SHORT).show();
                    return;
                }

                StringBuilder buffer = new StringBuilder();
                while (cursor.moveToNext()) {
                    buffer.append("Date : ").append(cursor.getString(1)).append("\n");
                    buffer.append("Motif : ").append(cursor.getString(2)).append("\n\n");
                }
                Toast.makeText(MainActivity.this, buffer.toString(), Toast.LENGTH_LONG).show();
            });
        }
    }

