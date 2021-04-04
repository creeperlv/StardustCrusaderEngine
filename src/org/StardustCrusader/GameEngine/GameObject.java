package org.StardustCrusader.GameEngine;

import java.util.ArrayList;

public class GameObject extends GameBehavior {
	public String Name = "DefaultGO";
	public boolean isActive = true;
	public Transform transform = new Transform(this);
	public ArrayList<GameObject> Children = new ArrayList<GameObject>();
	public ArrayList<GameBehavior> Components = new ArrayList<GameBehavior>();

	/**
	 * Initiate the object to world space.
	 * 
	 * @param ObjectToInit
	 * @param Position
	 * @param Scale
	 * @return
	 */
	public GameObject Initiate(GameObject ObjectToInit, Vector2 Position, Vector2 Scale) {
		ObjectToInit.transform.Position = Position;
		if (Scale != null)
			ObjectToInit.transform.Scale = Scale;
		ObjectToInit.parentObject = this;
		Children.add(ObjectToInit);
		return ObjectToInit;
	}

	public GameObject FindObjectByName(String Name) {
		for (int i = 0; i < Children.size(); i++) {
			if (Children.get(i).Name == Name)
				return Children.get(i);
		}
		return null;
	}
	private static final void ExecuteInitiate(GameObject GO){
		for (GameObject gameObject : GO.Children) {
			ExecuteInitiate(gameObject);
		}
		for (GameBehavior item : GO.Components) {
			item.Initiated();
		}
	}
	public static GameObject Initiate(GameObject ObjectToInit, Vector2 Position, Vector2 Scale, GameObject Parent) {
		if (Position != null)
			ObjectToInit.transform.Position = Position;
		if (Scale != null)
			ObjectToInit.transform.Scale = Scale;
		ObjectToInit.parentObject = Parent;
		Parent.Children.add(ObjectToInit);
		ExecuteInitiate(ObjectToInit);
		return ObjectToInit;
	}
}
