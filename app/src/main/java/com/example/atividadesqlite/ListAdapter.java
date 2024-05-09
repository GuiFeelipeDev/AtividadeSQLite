package com.example.atividadesqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<ListData> {
    public ListAdapter(@NonNull Context context, ArrayList<ListData> dataArrayList) {
        super(context, R.layout.list_item, dataArrayList);
    }
    //Criação do adapter no modelo a ser exibido
    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        ListData listData = getItem(position);

        if(view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        TextView gp_name = view.findViewById(R.id.list_gp_name);
        TextView winner_team = view.findViewById(R.id.list_winner_name);
        TextView leader_gap = view.findViewById(R.id.list_leader_gap);
        TextView opt1 = view.findViewById(R.id.optional1);
        TextView opt2 = view.findViewById(R.id.optional2);
        TextView opt3 = view.findViewById(R.id.optional3);

        gp_name.setText(listData.gp_name);
        winner_team.setText(listData.winner_team);
        leader_gap.setText(listData.leader_gap);
        opt1.setText(listData.option1);
        opt2.setText(listData.option2);
        opt3.setText(listData.option3);

        return view;
    }
}
