package com.generic.launcher;

import com.generic.UI.LauncherUI;
import com.generic.gameplay.Game;
import com.generic.gameplay.Leaderboard;
import com.generic.gameplay.ScorePair;
import com.generic.player.PlayerManager;

import javax.swing.*;
import java.awt.*;

public class Launcher extends JFrame
{
    public static Launcher instance;

    private LauncherUI UI;
    private PlayerManager pm;
    private Leaderboard l;

    public  Launcher()
    {
        super();
        instance = this;

        UI = new LauncherUI();
        add(UI);
        setTitle("Pengo Launcher");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);


        pm = new PlayerManager();
        l = new Leaderboard();
        l.pull();

        l.addToLeaderboard(new ScorePair("Yann", 100000, true));

        l.push();
        l.print();
    }

    public void SoloModeSelected()
    {
        System.out.println("Solo séléctionné");
        if (pm.isMainProfileChosen())
        {
            this.setVisible(false);
            Game g = new Game();
        }
    }

    public void SoloSettingsSelected()
    {
        System.out.println("Réglages solo séléctionnés");
    }

    public void MultiModeSelected()
    {
        System.out.println("Mode Multi séléctionné");
    }

    public void ProfileModeSelected()
    {
        System.out.println("Profil séléctionné");
        ProfileDialog modal = new ProfileDialog(this, true);

        if (pm.getMainProfile() != null)
        {
            UI.updateProfileMode(pm.getMainProfile().getPseudo());
        }
    }

    public Leaderboard getLeaderboard()
    {
        return this.l;
    }

    public void leaderboardSelected()
    {
        System.out.println("Leaderboard séléctionné");
        LeaderboardDialog modal = new LeaderboardDialog(this, true);
    }

    public void onGameEnded()
    {
        this.setVisible(true);
    }
}
