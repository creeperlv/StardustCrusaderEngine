package org.StardustCrusader.GameEngine;

import java.util.Random;

public class GameBehavior {
	protected int ID = -1;
	public GameObject gameObject;
	public GameObject parentObject;
	public boolean isEnabled = true;

	public final int GetID() {
		if (ID == -1) {
			Random r = new Random();
			ID = r.nextInt(100000);
		}
		return ID;
	}

	public GameBehavior() {
		Start();
	}

	public void Initiated() {
	}

	public void Start() {
	}

	public void Update() {
	}
}
