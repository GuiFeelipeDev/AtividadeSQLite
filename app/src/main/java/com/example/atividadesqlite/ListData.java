package com.example.atividadesqlite;

//Criação da ListData para tratar os dados que vão ser exibidos
public class ListData {
    String gp_name, winner_team, leader_gap, option1, option2, option3;
    public ListData(String gp_name, String winner_team, String leader_gap, String option1, String option2, String option3) {
        this.gp_name = gp_name;
        this.winner_team = winner_team;
        this.leader_gap = leader_gap;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
    }
}
