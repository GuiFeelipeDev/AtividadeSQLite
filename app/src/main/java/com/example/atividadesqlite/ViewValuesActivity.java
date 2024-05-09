package com.example.atividadesqlite;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.atividadesqlite.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class ViewValuesActivity extends AppCompatActivity {
    //Criação dos componentes
    ListView listViewGp;
    ListAdapter listAdapter;
    ArrayList<ListData> dataArrayList = new ArrayList<>();
    ListData listData;
    Button rollback;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_values);


        //Criação da DB
        DBHelper db = new DBHelper(this);
        //Botão de voltar
        rollback = findViewById(R.id.rollback_button);


        //Cursor da DB usando a função GetGpData
        Cursor cursor = db.getGpData();

        //Atribuição da listview
        listViewGp = findViewById(R.id.listViewGp);

        //Dados que serão passados para a list view formatados
        String gpName, winnerTeam, leaderGap, opt1, opt2, opt3;

        //Passagem do cursor
        if (cursor.getCount()==0){
            //Alerta caso não tenham itens na DB
            Toast.makeText(ViewValuesActivity.this,"NENHUM GP CADASTRADO", Toast.LENGTH_LONG).show();
        }else{
            //Função para percorrer o DB e criar o modelo a ser enviado para a listview
            while(cursor.moveToNext()){
                gpName = cursor.getString(1);
                winnerTeam = cursor.getString(2) + " - " + cursor.getString(4);
                leaderGap = cursor.getString(3);
                opt1 = cursor.getInt(5) == 1? "Teve Batida    " : null;
                opt2 = cursor.getInt(6) == 1? "Verstappen Venceu" : "";
                opt3 = cursor.getInt(7) == 1? "Sargeant não Pontuou" : "";

                listData = new ListData(gpName, winnerTeam, leaderGap, opt1, opt2, opt3);
                //Envio dos dados para a listView
                dataArrayList.add(listData);
            }
        }
        //Criação e consumo do adapter
        listAdapter = new ListAdapter(ViewValuesActivity.this, dataArrayList);
        listViewGp.setAdapter(listAdapter);

        //Botão para voltar as telas
        rollback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
