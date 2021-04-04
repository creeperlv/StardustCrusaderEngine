package org.StardustCrusader.GameEngine;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import org.StardustCrusader.GameEngine.Renderers.BaseRenderer;

public class CircleCollider extends GameBehavior implements BaseRenderer {
    public Vector2 PivotPosition;
    public float Radius;
    BufferedImage BI;

    public void GenerateDebugImage(int width, int height) {
        BI = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = BI.createGraphics();
        g.setColor(new Color(0x80, 0x10, 0xFF, 0x90));
        g.fillOval((int) (width - PivotPosition.x - Radius) / 2, (int) (height - PivotPosition.y - Radius) / 2, (int) Radius, (int) Radius);
    }

    public CircleCollider(Vector2 PivotPosition, float Radius) {
        this.Radius = Radius;
        this.PivotPosition = PivotPosition;
    }

    public CircleCollider Duplicate(){
        CircleCollider c=new CircleCollider(PivotPosition,Radius);
        return c;
    }
    public boolean IsHit(CircleCollider cc) {
        Vector2 CCPiv = cc.gameObject.transform.GlobalPosition
                .Add(cc.PivotPosition.Multiply(cc.gameObject.transform.GlobalScale));
        Vector2 ThisPiv = gameObject.transform.GlobalPosition
                .Add(PivotPosition.Multiply(gameObject.transform.GlobalScale));
        float R1 = (float) Math.sqrt((Radius * gameObject.transform.GlobalScale.x) * (Radius * gameObject.transform.GlobalScale.x) + (Radius * gameObject.transform.GlobalScale.y) * (Radius * gameObject.transform.GlobalScale.y));
        float R2 = (float) Math.sqrt((cc.Radius * cc.gameObject.transform.GlobalScale.x) * (cc.Radius * cc.gameObject.transform.GlobalScale.x) + (cc.Radius * cc.gameObject.transform.GlobalScale.y) * (cc.Radius * cc.gameObject.transform.GlobalScale.y));
        if (CCPiv.Distance(ThisPiv) <= R1 + R2){
            return true;
        } else
        return false;
    }

    @Override
    public BufferedImage GetContent() {
        return BI;
    }
}