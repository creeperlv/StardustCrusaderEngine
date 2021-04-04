package org.StardustCrusader.GameEngine;

public class GameTime {
	public static long t0;
	public static long t1=System.currentTimeMillis();
	public static float TimeScale=1;
	public static void Record() {
		t0=t1;
		t1=System.currentTimeMillis();
	}
	public static float GetRealDeltaTime(){
		return (((float)(t1-t0))/1000.0f);
	}
	public static float GetDeltaTime() {
		return (((float)(t1-t0))/1000.0f)*TimeScale;
	}
}
