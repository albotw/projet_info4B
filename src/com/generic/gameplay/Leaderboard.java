package com.generic.gameplay;

import com.generic.player.Player;
import com.generic.player.PlayerManager;

import java.util.ArrayList;

public class Leaderboard {
    //contient des paires Pseudo[string] | score[Integer]
    private ArrayList<ScorePair> ladder;

    public Leaderboard()
    {
        ladder = new ArrayList<ScorePair>();
    }

    public void addToLeaderboard(Player p)
    {
        ScorePair toInsert = new ScorePair(p.getPseudo(), p.getScore().getPoints());

        boolean added = false;
        for (int i = 0; i < ladder.size(); i++)
        {
            if (ladder.get(i).getScore() <=toInsert.getScore())
            {
                ladder.add(i, toInsert);
                added = true;
            }
        }

        if (!added)
        {
            ladder.add(toInsert);
        }
    }



    /**
    public void compareScore(Score s, PlayerManager P, Player p){
        int tab[]= new int[P.getNBPlayers()];
        for(int i = 0; i < P.getNBPlayers(); i++){
            if (p.getScore().getPlayer[i] < p.getScore().getPlayer[i + 1]){
                tab[i] == p.getScore().getPlayer[i+1];
                tab[i+1] == p.getScore().getPlayer[i]
            }
            else
            {
                tab[i]=p.getScore().getPlayer(i);
                tab[i+1]=p.getScore().getPlayer[i];
            }

        }

    }
     */
}

class ScorePair
{
    private String pseudo;
    private int score;

    public ScorePair(String pseudo, int score)
    {
        this.pseudo = pseudo;
        this.score = score;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
