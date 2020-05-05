package com.generic.graphics;

import javax.swing.*;
import java.awt.*;

import static com.generic.gameplay.CONFIG.*;

public class RenderPanel extends JPanel{

    private SpriteManager sm = SpriteManager.instance;

    public RenderPanel()
    {
        super();
        this.setBackground(BG_DEFAULT_COLOR);
    }

    private void draw(Graphics g){
        Graphics2D g2d = (Graphics2D)g;

        g2d.setColor(Color.black);

        for (int i = 0; i < sm.getBSize(); i++){
            Sprite sp = sm.getSprite(i, "background");
            if (sp != null) g2d.drawImage(sp.getTexture(), sp.getX(), sp.getY(), this);
        }

        for (int i = 0; i < sm.getFSize(); i++)
        {
            Sprite sp = sm.getSprite(i, "foreground");
            if (sp != null) g2d.drawImage(sp.getTexture(), sp.getX(), sp.getY(), this);
        }

        //System.out.println("repainted");
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(this.getWidth(), this.getHeight());
    }

    @Override
    public void paintComponent(Graphics g)
    {
        //System.out.println("called paintComponent");
        super.paintComponent(g);
        draw(g);
        Toolkit.getDefaultToolkit().sync();
    }

}