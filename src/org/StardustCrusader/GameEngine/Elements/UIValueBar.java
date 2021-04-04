package org.StardustCrusader.GameEngine.Elements;

import java.awt.image.BufferedImage;
import java.awt.*;
import org.StardustCrusader.GameEngine.Renderers.*;
import org.StardustCrusader.GameEngine.GameObject;

public class UIValueBar extends GameObject implements BaseRenderer {
    BufferedImage Img;
    Color BG;
    Color FG;
    int W, H;
    float MAX;
    float MIN;
    float VAL;

    public UIValueBar(int width, int height, Color Background, Color Foreground, float MaxValue, float MinValue,
            float Value) {
        super();
        Img = new BufferedImage(width, height, 2);
        MAX = MaxValue;
        MIN = MinValue;
        W = width;
        H = height;
        BG=Background;
        FG=Foreground;
        SetValue(Value);
        Components.add(this);
    }

    public void SetValue(float Value) {
        VAL = Value;
        Graphics2D g = Img.createGraphics();
        g.setColor(BG);
        g.fillRect(0, 0, W, H);
        g.setColor(FG);
        g.fillRect(0, 0, (int)(W*((VAL-MIN)/(MAX-MIN))), H);
        g.drawRect(0, 0, W-1, H-1);
    }

    @Override
    public BufferedImage GetContent() {
        return Img;
    }

}