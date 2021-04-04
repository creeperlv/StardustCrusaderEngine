package org.StardustCrusader.GameEngine;

import java.io.*;
import java.util.Random;

public class Scene {
	int ID=-1;
	public int GetID() {
		if(ID==-1)ID=(new Random()).nextInt(1000000);
		return ID;
	}
	public GameObject RootGameObject;
	private boolean Active=false;
	public Scene(BufferedInputStream BIS){

	}
	public boolean IsActive() {
		return Active;
	}
	public void Active() {
		Active=true;
	}
	public static Scene LoadFromSceneFile(String str) {
		try (FileInputStream FIS=new FileInputStream(new File(str))){
			return LoadFromSceneStream(new BufferedInputStream(FIS));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static Scene LoadFromSceneStream(BufferedInputStream SceneStream) {
		return null;
	}
	/*
	 * SceneName NAME
	 * Address PATH
	 * -GameObject NAME
	 * +Transfrom <X> <Y> <W> <H> <R>
	 * +IMG <Image-Path>
	 * --GameObject NAME
	 * +Transfrom <X> <Y> <W> <H> <R>
	 * ---GameObject NAME
	 * +Transfrom <X> <Y> <W> <H> <R>
	 * +Script <Class-Scheme>
	 * ++Field <Field Name> <TPYE> <Value>
	 * ++Field <Field Name> <TYPE> <Value>
	 * ----GameObject NAME
	 * +Transfrom <X> <Y> <W> <H> <R>
	 * +IMG <Image-Path>
	 * --GameObject NAME
	 * +Transfrom <X> <Y> <W> <H> <R>
	 */
	/*
	 * SN <NAME>
	 * ADD <PATH>
	 * -GO <NAME>
	 * +T <X> <Y> <W> <H> <R>
	 * +I <PATH>
	 * +S <CLASS-SCHEME>
	 * ++F <FIELD-NAME> <TYPE> <VALUE>
	 * ++F <FIELD-NAME> <TYPE> <VALUE>
	 */
	/*
	 * 0x00 <NAME>
	 * 0x01 <PATH>
	 * -0x02 <NAME>
	 * +0x03 <X> <Y> <W> <H> <R>
	 * +0x04 <Image-Path>
	 * --0x02 <NAME>
	 * +0x03 <X> <Y> <W> <H> <R>
	 * +0x05 <Class-Name>
	 * ++0x06 <Field Name> <TYPE> <VALUE>
	 */
}
