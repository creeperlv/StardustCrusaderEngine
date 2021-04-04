package org.StardustCrusader.GameEngine;

import java.util.HashMap;

public class InputManager {
	static HashMap<Integer, Boolean> keys = new HashMap<Integer, Boolean>();
	static HashMap<Integer, Boolean> keys2 = new HashMap<Integer, Boolean>();

	public static void Set(int keycode, boolean status) {
		keys.put(keycode, status);
		keys2.put(keycode, status);
	}

	public static boolean KeyDown(int keycode) {
		if (keys.containsKey(keycode))
			return keys.get(keycode);
		else
			return false;
	}

	public static boolean KeyTyped(int keycode) {
		if (keys2.containsKey(keycode)) {
			boolean a = keys2.get(keycode);
			if(a==true)
			keys2.put(keycode, false);
			return a;
		} else
			return false;
	}
}
