package org.StardustCrusader.GameEngine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

//import org.StardustCrusader.GameEngine.Renderers.*;

public class Collider extends GameBehavior {
	//implements BaseRenderer {
	public Vector2 PivotPosition = new Vector2(0);
	public Vector2 Size = new Vector2(0);
	BufferedImage img;

	public void Start() {

	}

	public void ReCalcuteDbg(Vector2 ObjS) {
		img = new BufferedImage((int) ObjS.x, (int) ObjS.y, 2);
		Graphics g = img.getGraphics();
		g.setColor(new Color(174, 54, 200, 125));
		int a1 = (int) (PivotPosition.x - Size.x / 2);
		int a2 = (int) (PivotPosition.y - Size.y / 2);
		int a3 = (int) (Size.x);
		int a4 = (int) (Size.y);
		g.fillRect(a1, a2, a3, a4);
		;
	}
	public Collider Duplicate(){
		Collider c=new Collider();
		c.PivotPosition=PivotPosition;
		c.Size=Size;
		return c;
	}
	public boolean isHit(Collider c) {
		Vector2 center = c.gameObject.transform.GlobalPosition
				.Add(new Vector2(c.PivotPosition.x * c.gameObject.transform.GlobalScale.x,
						c.PivotPosition.y * c.gameObject.transform.GlobalScale.y));
		return isPointInside(center.Add(new Vector2(c.Size.x * c.gameObject.transform.GlobalScale.x / 2, 0)))
				|| isPointInside(center.Add(new Vector2(-c.Size.x * c.gameObject.transform.GlobalScale.x / 2, 0)))
				|| isPointInside(center.Add(new Vector2(0, -c.Size.y * c.gameObject.transform.GlobalScale.y / 2)))
				|| isPointInside(center.Add(new Vector2(0, c.Size.y * c.gameObject.transform.GlobalScale.y / 2)));
		// return false;
	}

	boolean isPointInside(Vector2 point) {
		Vector2 center = gameObject.transform.GlobalPosition.Add(new Vector2(
				PivotPosition.x * gameObject.transform.GlobalScale.x, PivotPosition.y * gameObject.transform.GlobalScale.y));
		if (point.x > center.x - Size.x * gameObject.transform.GlobalScale.x / 2
				&& point.x < center.x + Size.x * gameObject.transform.GlobalScale.x / 2) {
			if (point.y > center.y - Size.y * gameObject.transform.GlobalScale.y / 2
					&& point.y < center.y + Size.y * gameObject.transform.GlobalScale.y / 2) {
				return true;
			}
			return false;
		} else {
			return false;
		}
	}

//	@Override
	public BufferedImage GetContent() {
	return img;
	}
}
