package com.example.douanetemporelle;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class ResultActivity extends AppCompatActivity {

    // 1. Matricule bech na3mlou génération lel clés
    private static final String MATRICULE = "0307";

    // 2. Déclaration des éléments XML
    private ConstraintLayout layoutResult;
    private TextView txtResultat, txtMessage;
    private Button btnNouveau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // 3. Liaison entre Java et XML
        layoutResult = findViewById(R.id.layout_result_ag);
        txtResultat = findViewById(R.id.txt_resultat_ag);
        txtMessage = findViewById(R.id.txt_message_ag);
        btnNouveau = findViewById(R.id.btn_nouveau_ag);

        // 4. Récupération des informations mel VerificationActivity
        Intent intent = getIntent();

        boolean verdict = intent.getBooleanExtra(
                MainActivity.genererCle(MATRICULE, "VERDICT"),
                false
        );

        String nomVoyageur = intent.getStringExtra(
                MainActivity.genererCle(MATRICULE, "NOM_VOYAGEUR")
        );

        // 5. Vérification du nom
        if (nomVoyageur == null || nomVoyageur.trim().isEmpty()) {
            nomVoyageur = "Voyageur";
        }

        // 6. Affichage du résultat selon le verdict
        if (verdict) {

            // Voyage approuvé : background vert
            layoutResult.setBackgroundColor(Color.GREEN);
            txtResultat.setText("Voyage approuvé !");
            txtMessage.setText("Bon voyage " + nomVoyageur);

        } else {

            // Voyage refusé : background rouge
            layoutResult.setBackgroundColor(Color.RED);
            txtResultat.setText("Voyage refusé !");
            txtMessage.setText(
                    nomVoyageur + " est en état d'arrestation temporelle"
            );
        }

        // 7. Bouton Nouveau voyageur
        btnNouveau.setOnClickListener(v -> {

            // Nrarj3ou lel MainActivity w nsakrou les Activities lokhrin
            Intent retour = new Intent(
                    ResultActivity.this,
                    MainActivity.class
            );

            retour.setFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK
            );

            startActivity(retour);
            finish();
        });
    }
}