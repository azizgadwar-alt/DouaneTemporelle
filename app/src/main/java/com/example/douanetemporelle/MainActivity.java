package com.example.douanetemporelle;

import android.os.Bundle;
//Hethom bech nesta3mlouhom fi l'interface w l'Intents
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    // Houni na3rfou les elements mta3 l'interface
    private EditText edtNom;
    private EditText edtNaissance;
    private EditText edtDestination;
    private Spinner spMotif;
    private Button btnInspecter;

    // Houni n7otou ekher 4 chiffres mta3 matricule
    private static final String MATRICULE = "0307";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        // Houni norbtou les variables m3a les elements mta3 XML
        edtNom = findViewById(R.id.edt_nom_ag);

        edtNaissance = findViewById(R.id.edt_naissance_ag);

        edtDestination = findViewById(R.id.edt_destination_ag);

        spMotif = findViewById(R.id.sp_motif_ag);

        btnInspecter = findViewById(R.id.btn_inspecter_ag);
        // Houni bech na3mlou action ki l'utilisateur yenzel 3al bouton
        btnInspecter.setOnClickListener(v -> {

            // Houni na9raw esm l'utilisateur
            String nom = edtNom.getText().toString();
            // Houni na9raw sne mta3 naissance
            String naissance = edtNaissance.getText().toString();
            // Houni na9raw sne mta3 destination
            String destination = edtDestination.getText().toString();
            // Houni na9raw motif mta3 voyage
            String motif = spMotif.getSelectedItem().toString();
            // Nthabtou elli les champs lkol m3abbin
            if (nom.isEmpty() || naissance.isEmpty() || destination.isEmpty()) {

                Toast.makeText(MainActivity.this, "3abbi les champs lkol", Toast.LENGTH_SHORT).show();

            } else {

                // N7adhrou Intent bech nemchiw lel VerificationActivity
                Intent intent = new Intent(MainActivity.this, VerificationActivity.class);

                // Nab3thou les informations lel Activity 2
                intent.putExtra(genererCle(MATRICULE, "NOM_VOYAGEUR"), nom);
                intent.putExtra(genererCle(MATRICULE, "ANNEE_NAISSANCE"), naissance);
                intent.putExtra(genererCle(MATRICULE, "ANNEE_DESTINATION"), destination);
                intent.putExtra(genererCle(MATRICULE, "MOTIF_VOYAGE"), motif);

                // N7ellou VerificationActivity
                startActivity(intent);
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    // Houni nrakbou les cles mta3 putExtra
    public static String genererCle(String matricule, String nomCle) {
        return matricule + "_" + nomCle;
    }
}