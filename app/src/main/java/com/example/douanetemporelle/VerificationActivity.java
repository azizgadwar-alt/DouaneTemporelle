
package com.example.douanetemporelle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class VerificationActivity extends AppCompatActivity {

    // 1. Matricule bech ta3mel génération lel les clés
    private static final String MATRICULE = "0307";

    // 2. déclaration des éléments XML
    private TextView txtNom, txtAge, txtMotif;
    private Button btnApprouver, btnRefuser;

    // 3. Nom du voyageur pour le transmettre à ResultActivity
    private String nomVoyageur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        // 4. liaison entre Java et XML
        txtNom = findViewById(R.id.txt_nom_cj);
        txtAge = findViewById(R.id.txt_age_cj);
        txtMotif = findViewById(R.id.txt_motif_cj);

        btnApprouver = findViewById(R.id.btn_approuver_cj);
        btnRefuser = findViewById(R.id.btn_refuser_cj);

        // 5. hedhi ta3mel récupération lel les données eli b3atehom  MainActivity
        nomVoyageur = getIntent().getStringExtra(
                MainActivity.genererCle(MATRICULE, "NOM_VOYAGEUR"));

        int anneeNaissance = getIntent().getIntExtra(
                MainActivity.genererCle(MATRICULE, "ANNEE_NAISSANCE"), 0);

        int anneeDestination = getIntent().getIntExtra(
                MainActivity.genererCle(MATRICULE, "ANNEE_DESTINATION"), 0);

        String motif = getIntent().getStringExtra(
                MainActivity.genererCle(MATRICULE, "MOTIF_VOYAGE"));

        // 6. te7seb l'âge temporel
        int ageTemporel = anneeDestination - anneeNaissance;

        // 7. ta3mel affichage informations du voyageur
        txtNom.setText("Nom du voyageur : " + nomVoyageur);
        txtAge.setText("Âge temporel : " + ageTemporel + " ans");
        txtMotif.setText("Motif : " + motif);

        // 8. hedhi ta3mel vérification lil condition  paradoxe
        int difference = Math.abs(anneeDestination - anneeNaissance);

        if ("Modification du passé".equals(motif)
                && estPremier(difference)) {

            // 9. ta3mel désactivation du bouton Approuver
            btnApprouver.setEnabled(false);
        }

        // 10. Bouton Approuver
        btnApprouver.setOnClickListener(v -> {
            ouvrirResultat(true);
        });

        // 11. Bouton Refuser
        btnRefuser.setOnClickListener(v -> {
            ouvrirResultat(false);
        });
    }

    // 12.  hedhi fonction t3awena bech na3rfo  nombre  premier wala le
    private boolean estPremier(int nombre) {

        if (nombre < 2) {
            return false;
        }

        for (int i = 2; i * i <= nombre; i++) {
            if (nombre % i == 0) {
                return false;
            }
        }

        return true;
    }

    // 13. hedhi bech t7el activité ResultActivity
    private void ouvrirResultat(boolean verdict) {

        Intent intent = new Intent(
                VerificationActivity.this,
                ResultActivity.class
        );

        // 14. Transmission du verdict et du nom
        intent.putExtra(
                MainActivity.genererCle(MATRICULE, "VERDICT"),
                verdict
        );

        intent.putExtra(
                MainActivity.genererCle(MATRICULE, "NOM_VOYAGEUR"),
                nomVoyageur
        );

        startActivity(intent);

        // 15. hedhi bech tsaker VerificationActivity
        finish();
    }
}
