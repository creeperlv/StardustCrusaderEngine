package org.StardustCrusader.GameEngine.Elements;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.StardustCrusader.GameEngine.GameObject;
import org.StardustCrusader.GameEngine.Renderers.TiledImageRenderer;

public class TileBackground extends GameObject {
    public TileBackground(File f, int w, int h) {
        {
            try {
                TiledImageRenderer img = new TiledImageRenderer(f, w, h);
                Components.add(img);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    } public TileBackground(InputStream s, int w, int h) {
        {
            try {
                TiledImageRenderer img = new TiledImageRenderer(s, w, h);
                Components.add(img);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}