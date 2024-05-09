package com.example.atividadesqlite;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    //Criação dos componentes
    EditText gp_name, gp_winner;
    RadioGroup leader_gap;
    RadioButton selected_radio;
    CheckBox crash, ver_win, sar_point;
    Spinner team_win;
    Button submit, next_page;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Atribuição dos componentes
        gp_name = findViewById(R.id.gp_name);
        gp_winner = findViewById(R.id.winner_name);
        leader_gap = findViewById(R.id.leader_gap);
        team_win = findViewById(R.id.team);
        crash = findViewById(R.id.crash);
        ver_win = findViewById(R.id.vespa_win);
        sar_point = findViewById(R.id.bagre);
        //Atribuição dos botões
        submit = findViewById(R.id.submit_button);
        next_page = findViewById(R.id.seecads_button);
        //Criação da DB
        DBHelper db = new DBHelper(this);

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Coleta dos dados dos campos
                String gpName = gp_name.getText().toString();
                String winner = gp_winner.getText().toString();
                selected_radio = findViewById(leader_gap.getCheckedRadioButtonId());
                String leaderGap = selected_radio.getText().toString();
                String winnerTeam = team_win.getSelectedItem().toString();
                Boolean haveCrash = crash.isChecked();
                Boolean vesWin = ver_win.isChecked();
                Boolean bagrePoint = sar_point.isChecked();

                //Tentativa de inserir dado na DB
                boolean response = db.insertGpData(gpName, winner, leaderGap, winnerTeam, haveCrash, vesWin, bagrePoint);

                if (response){
                    Toast.makeText(MainActivity.this,"GP INSERIDO COM SUCESSO", Toast.LENGTH_LONG).show();
                    //Limpa os campos em caso de sucesso
                    gp_name.setText("");
                    gp_winner.setText("");
                    team_win.setSelection(0);
                    crash.setChecked(false);
                    ver_win.setChecked(false);
                    sar_point.setChecked(false);
                }else{
                    Toast.makeText(MainActivity.this, "FALHA AO INSERIR GP", Toast.LENGTH_LONG).show();
                }
            }
        });

        //Botão de navegação para a outra tela
        next_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nav = new Intent(MainActivity.this, ViewValuesActivity.class);
                startActivity(nav);
            }
        });

    }
}