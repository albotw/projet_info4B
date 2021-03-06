package com.generic.net.score;

import com.generic.launcher.Leaderboard;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;

public class ScoreServer extends Thread {
    private final int port = 9090;
    private Leaderboard l;
    public static ScoreServer instance;
    private ServerSocket s;
    private boolean flush;

    public ScoreServer() throws Exception {
        s = new ServerSocket(port);
        instance = this;
        flush = false;

        System.out.println("SOCKET ECOUTE CREE => " + s);

        loadSavefile();
        start();
    }

    public void run() {
        while (!flush) {
            try {
                Connexion connexion = new Connexion(s.accept());
                connexion.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void loadSavefile() {
        try {
            FileInputStream fis = new FileInputStream("saves/ladder.sav");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object tmp = ois.readObject();

            l = (Leaderboard) (tmp);
            if (l != null) {
                System.out.println("--- Loaded savefile ---");
            } else {
                System.out.println("--- Error on load -> blank ladder ---");
                l = new Leaderboard();
            }
            ois.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeSavefile() {
        try {
            FileOutputStream fos = new FileOutputStream("saves/ladder.sav");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(l);

            System.out.println("--- wrote savefile ---");
            oos.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Leaderboard getLeaderboard() {
        return this.l;
    }

    public void flush()
    {
        this.flush = true;
        try{
            s.close();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
