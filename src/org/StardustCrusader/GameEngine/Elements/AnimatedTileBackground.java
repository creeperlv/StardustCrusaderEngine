package org.StardustCrusader.GameEngine.Elements;

import java.io.File;
import java.io.InputStream;

import org.StardustCrusader.GameEngine.EngineCanvas;
import org.StardustCrusader.GameEngine.GameTime;
import org.StardustCrusader.GameEngine.Vector2;
import org.StardustCrusader.GameEngine.Renderers.TiledImageRenderer;

public class AnimatedTileBackground extends TileBackground {
    public Vector2 Speed;

    public AnimatedTileBackground(File f, int w, int h, Vector2 Speed) {
        super(f, w, h);
        Components.add(this);
        this.Speed = Speed;
        CalculateTime();
    }

    public AnimatedTileBackground(InputStream s, int w, int h, Vector2 Speed) {
        super(s, w, h);
        Components.add(this);
        this.Speed = Speed;
        CalculateTime();
    }

    void CalculateTime() {
        int wi = ((TiledImageRenderer) Components.get(0)).GetContent().getWidth();
        int he = ((TiledImageRenderer) Components.get(0)).GetContent().getHeight();
        if (Speed.x != 0)
            HTimeMax = (wi - EngineCanvas.Width) / Speed.x;
        else
            HTimeMax = -1;
        if (Speed.y != 0)
            VTimeMax = (he - EngineCanvas.Height) / Speed.y;
        else
            VTimeMax = -1;

    }

    float HTime;
    float VTime;

    float HTimeMax;
    float VTimeMax;
    boolean isStop = false;

    public void Update() {
        this.transform.Move(Speed.Multiply(-GameTime.GetDeltaTime()));
        if (HTime != -1) {
            HTime += GameTime.GetDeltaTime();
            if(HTime>=HTimeMax){
                transform.Position.x=0;HTime=0;
            }
        }
        if (VTime != -1) {
            VTime += GameTime.GetDeltaTime();
            if(VTime>=VTimeMax){
                transform.Position.y=0;
                VTime=0;
            }
        }
    }
}