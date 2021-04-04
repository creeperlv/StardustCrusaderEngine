package org.StardustCrusader.GameEngine;

public class GameLogic extends Thread {
	public boolean Stopped = false;
	boolean isInvokeRequired = false;
	int t = 0;
	public static int GCCycle=25;
	public void Invoke(Runnable r) {
		isInvokeRequired = true;
		r.run();
		isInvokeRequired = false;
	}

	public void run() {
		// Runnable asd=()->{};
		while (Stopped == false) {
			if (isInvokeRequired == false) {

				GameTime.Record();
				EngineObjectPool.canvas.EnginePaint(EngineObjectPool.canvas.getGraphics());
				t++;
				if (t == GCCycle) {
					System.gc();
					t = 0;
				}
			}
		}
	}
}
