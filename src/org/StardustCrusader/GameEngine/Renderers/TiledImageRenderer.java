package org.StardustCrusader.GameEngine.Renderers;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

import org.StardustCrusader.GameEngine.GameBehavior;

public class TiledImageRenderer extends GameBehavior implements BaseRenderer {
    BufferedImage image00;
    BufferedImage image01;
    int W, H;

    public TiledImageRenderer(File f, int w, int h) throws IOException {
        image00 = ImageIO.read(f);
        W = w;
        H = h;
        GenerateImage();
    }
    public TiledImageRenderer(InputStream s, int w, int h) throws IOException {
        image00 = ImageIO.read(s);
        W = w;
        H = h;
        GenerateImage();
    }

    public TiledImageRenderer(BufferedImage f, int w, int h) {
        image00 = f;
        W = w;
        H = h;
        GenerateImage();
    }

    void GenerateImage() {
        int IW = W * image00.getWidth();
        int IH = H * image00.getHeight();
        int OW=image00.getWidth();
        int OH=image00.getHeight();
        image01 = new BufferedImage(IW, IH, 2);
        Graphics2D g= image01.createGraphics();
        for (int i = 0; i < W; i++) {
            for (int j = 0; j < H; j++) {
                g.drawImage(image00, null, i*OW, j*OH);
            }
        }
    }

    public void UpdateImage(File f, int w, int h) throws Exception {
        image00 = ImageIO.read(f);
        W = w;
        H = h;
        GenerateImage();
    }

    public void UpdateImage(BufferedImage f, int w, int h) {
        image00 = f;
        W = w;
        H = h;
        GenerateImage();
    }

    @Override
    public BufferedImage GetContent() {
        return image01;
    }
}