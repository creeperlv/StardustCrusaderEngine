package org.StardustCrusader.GameEngine;

public class Vector2 {
	public float x=0;
	public float y=0;
	public Vector2() {
		x=0;y=0;
	}
	public Vector2(float d) {
		x=y=d;
	}
	public Vector2(float x,float y) {
		this.x=x;
		this.y=y;
	}
	public float Length() {
		return (float)Math.sqrt(x*x+y*y);
	}
	public static Vector2 Subtract(Vector2 v1, Vector2 v2) {
		return new Vector2(v1.x-v2.x,v1.y-v2.y);
	}
	public Vector2 Subtract(Vector2 v) {
		return new Vector2(this.x-v.x,this.y-v.y);
	}
	public Vector2 Add(Vector2 v) {
		return new Vector2(this.x+v.x,this.y+v.y);
	}
	public float Distance(Vector2 v) {
		return Subtract(this,v).Length();
	}
	public Vector2 Multiply(float a) {
		return new Vector2(x*a,y*a);
	}
	public Vector2 Multiply(Vector2 v) {
		return new Vector2(x*v.x,y*v.y);
	}
	public static Vector2 Add(Vector2 v1,Vector2 v2) {
		return new Vector2(v1.x+v2.x,v1.y+v2.y);
	}
}
