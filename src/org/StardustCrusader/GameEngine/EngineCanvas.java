package org.StardustCrusader.GameEngine;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

import javax.swing.event.MouseInputAdapter;

import org.StardustCrusader.GameEngine.Renderers.*;

enum CanvasScale {
    Fill, Stretch
}

public class EngineCanvas extends Canvas {
    /**
     * 
     */
    public static int Width = 1280;
    public static int Height = 720;
    public static int WWidth = 1280;
    public static int WHeight = 720;
    private static final long serialVersionUID = -7114594528193185832L;
    public CanvasScale Scale = CanvasScale.Stretch;
    EngineCanvas can;

    public EngineCanvas() {
        can = this;
        this.setFocusable(true);
        this.setBackground(Color.black);
        EngineObjectPool.BaseObject = new GameObject();
        EngineObjectPool.BaseObject.transform.Scale = new Vector2(1);
        EngineObjectPool.BaseObject.Name = "BaseObject";
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                InputManager.Set(e.getKeyCode(), true);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                InputManager.Set(e.getKeyCode(), false);
            }
        });
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int MX = e.getX();
                int MY = e.getY();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                int MX = e.getX();
                int MY = e.getY();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
            }
        });
        this.requestFocus();
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                EngineCanvas.WWidth = can.getWidth();
                EngineCanvas.WHeight = can.getHeight();
            }
        });
    }

    Image img;
    Image FinalImg;

    public void EnginePaint(Graphics g) {
        FinalImg = this.createImage(WWidth, WHeight);
        img = this.createImage(Width, Height);
        Scale(FinalImg.getGraphics());
        g.drawImage(FinalImg, 0, 0, null);
    }

    public void Scale(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        // g2d.setColor(Color.BLACK);
        // g2d.fillRect(0, 0, WWidth, WHeight);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);

        RealPaint(img.getGraphics());
        switch (Scale) {
            case Fill:
                g2d.drawImage(img, 0, 0, WWidth, WHeight, 0, 0, Width, Height, null);
                break;
            case Stretch: {
                float aspect = (float) Width / (float) Height;
                float aspect2 = (float) Height / (float) Width;
                float aspect3 = (float) WWidth / (float) WHeight;
                if (aspect > aspect3) {
                    float h = WWidth * aspect2;
                    g2d.drawImage(img, 0, (int) (WHeight - h) / 2, WWidth, (int) h + (int) (WHeight - h) / 2, 0, 0,
                            Width, Height, null);
                } else if (aspect < aspect3) {
                    float w = WHeight * aspect;
                    g2d.drawImage(img, (int) (WWidth - w) / 2, 0, (int) w + (int) (WWidth - w) / 2, WHeight, 0, 0,
                            Width, Height, null);
                } else {
                    g2d.drawImage(img, 0, 0, WWidth, WHeight, 0, 0, Width, Height, null);
                }
            }
                break;
            default:
                break;
        }
        g.setColor(Color.white);
        g.setFont(new Font("Consolas", Font.PLAIN, 18));
        g.drawString("StardustCrusaderEngine@" + EngineInfo.VersionString, 0, 16);
        g.drawString("Development Preview", WWidth - 200, WHeight - 50);
    }

    public void RealPaint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Width, Height);
        g.setColor(Color.WHITE);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        ExecuteRecursively(g2, EngineObjectPool.BaseObject);
        g2.setFont(new Font("Serif", Font.PLAIN, 18));
    }

    /**
     * Where Update() is executed. Usually, it should not be called manually.
     * 
     * @param g
     * @param GO
     */
    public void ExecuteRecursively(Graphics2D g, GameObject GO) {
        if (GO.isActive == false)
            return;
        for (GameBehavior b : GO.Components) {
            if (b.isEnabled == false)
                continue;
            if (BaseRenderer.class.isAssignableFrom(b.getClass())) {
                Image img = ((BaseRenderer) b).GetContent();
                if (img != null) {
                    int imgH = img.getHeight(null);
                    int imgW = img.getWidth(null);
                    if (GO.transform.Rotation != 0) {
                        BufferedImage dst = new BufferedImage((int) imgW, (int) imgH, 2);
                        Graphics2D g2 = dst.createGraphics();
                        g2.rotate(Math.toRadians(GO.transform.Rotation), imgW / 2, imgH / 2);
                        g2.drawImage(img, 0, 0, null);
                        int dx1 = (int) GO.transform.GlobalPosition.x - (int) (imgW * GO.transform.GlobalScale.x) / 2;
                        int dx2 = (int) GO.transform.GlobalPosition.x + (int) (imgW * GO.transform.GlobalScale.x) / 2;
                        int dy1 = (int) GO.transform.GlobalPosition.y - (int) (imgH * GO.transform.GlobalScale.y) / 2;
                        int dy2 = (int) GO.transform.GlobalPosition.y + (int) (imgH * GO.transform.GlobalScale.y) / 2;
                        g.drawImage(dst, dx1, dy1, dx2, dy2, 0, 0, imgW, imgH, null);
                    } else {
                        int dx1 = (int) GO.transform.GlobalPosition.x - (int) (imgW * GO.transform.GlobalScale.x) / 2;
                        int dx2 = (int) GO.transform.GlobalPosition.x + (int) (imgW * GO.transform.GlobalScale.x) / 2;
                        int dy1 = (int) GO.transform.GlobalPosition.y - (int) (imgH * GO.transform.GlobalScale.y) / 2;
                        int dy2 = (int) GO.transform.GlobalPosition.y + (int) (imgH * GO.transform.GlobalScale.y) / 2;
                        g.drawImage(img, dx1, dy1, dx2, dy2, 0, 0, imgW, imgH, null);
                    }

                }
            }
            try {

                b.Update();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < GO.Children.size(); i++) {
            GameObject item = GO.Children.get(i);
            item.transform.GlobalScale = GO.transform.GlobalScale.Multiply(item.transform.Scale);
            item.transform.GlobalPosition = GO.transform.GlobalPosition
                    .Add(item.transform.Position.Multiply(GO.transform.GlobalScale));
            ExecuteRecursively(g, GO.Children.get(i));
        }
    }
}
