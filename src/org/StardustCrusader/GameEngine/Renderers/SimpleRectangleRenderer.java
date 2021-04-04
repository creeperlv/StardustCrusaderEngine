package org.StardustCrusader.GameEngine.Renderers;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics;

import org.StardustCrusader.GameEngine.GameBehavior;

public class SimpleRectangleRenderer extends GameBehavior implements BaseRenderer {
    BufferedImage img;
int w,h;
    public SimpleRectangleRenderer(int w, int h, Color c) {
        img = new BufferedImage(w, h, 2);
        this.w=w;
        this.h=h;
        Graphics g=img.getGraphics();
        g.setColor(c);
        g.fillRect(0, 0, w, h);
    }
    public void SetColor(Color c){
        img = new BufferedImage(w, h, 2);
        Graphics g=img.getGraphics();
        // g.clearRect(0, 0, w, h);
        g.setColor(c);
        g.fillRect(0, 0, w, h);
    }
    @Override
    public BufferedImage GetContent() {
        return img;
    }

}