package org.StardustCrusader.GameEngine.Elements;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import org.StardustCrusader.GameEngine.GameBehavior;
import org.StardustCrusader.GameEngine.GameObject;
import org.StardustCrusader.GameEngine.Renderers.BaseRenderer;

public class RaycastTarget extends GameObject{
    public GameObject TargetRenderer;
    public void GetRaycastArea(){
        for (GameBehavior gameObject : TargetRenderer.Components) {
            if(gameObject.getClass().isAssignableFrom(BaseRenderer.class)){
                BaseRenderer R=(BaseRenderer)gameObject;
                BufferedImage BI=R.GetContent();
                final byte[] pixels = ((DataBufferByte) BI.getRaster().getDataBuffer()).getData();
            }
        }
    }
}
