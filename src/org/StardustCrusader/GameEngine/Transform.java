package org.StardustCrusader.GameEngine;

public class Transform {
	GameObject parentGameObejct;

	public Transform(GameObject gameObject) {
		parentGameObejct = gameObject;
	}

	public Vector2 GlobalPosition = new Vector2();
	public Vector2 GlobalScale = new Vector2(1);
	public Vector2 Position = new Vector2();
	public Vector2 Scale = new Vector2(1);
	public float Rotation = 0f;

	public void Move(Vector2 v) {
		Move(v, new Vector2(-1000), new Vector2(EngineCanvas.Height+1000));
	}

	public void Move(Vector2 v, Vector2 LT, Vector2 RB) {
		Vector2 POS = Position.Add(v);
		if (POS.x < LT.x)
			return;
		if (POS.y < LT.y)
			return;
		if (POS.x > RB.x)
			return;
		if (POS.y > RB.y)
			return;
		Position = Position.Add(v);
	}

	public void Move(float Distance) {
		Move(Rotation, Distance);
	}

	public void Move(float Direction, float Distance) {

		Move(Direction, Distance, new Vector2(-1000), new Vector2(EngineCanvas.Height+1000));
	}

	public void Move(float Direction, float Distance, Vector2 LT, Vector2 RB) {

		double Tan = Math.abs(Math.tan(Math.toRadians(Direction)));
		// (y=a)/(x=Tan*a)
		// Distance^2=a^2+(Tan*a)^2
		while (Direction < 0) {
			Direction += 360;
		}
		while (Direction > 360) {
			Direction -= 360;
		}
		if (Direction == 90) {
			Move(new Vector2(Distance,0 ), LT, RB);
			return;
		} else if (Direction == 270) {
			Move(new Vector2(-Distance,0), LT, RB);
			return;
		}
		float y = (float) Math.sqrt((Distance * Distance / (1 + Tan * Tan)));
		float x = (float) Tan * y;
		// System.out.println("Target:"+x+","+y);
		if (Direction > 90 && Direction < 270) {
			x = -x;
		}
		if (Direction > 180 && Direction < 360) {
			y = -y;
		}
		if (Distance + (-Distance) == 0) {
			x = -x;
			y = -y;
		}
		if(Direction==180){
			x=0;
			y=Distance;
		}
		if(Direction==0){
			x=0;
			y=-Distance;
		}
		Move(new Vector2(x, y), LT, RB);
	}
}
