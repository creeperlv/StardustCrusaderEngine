package org.StardustCrusader.GameEngine.Elements;

import java.awt.Color;
import java.awt.Font;

import org.StardustCrusader.GameEngine.*;
import org.StardustCrusader.GameEngine.Renderers.*;

public class UIText extends GameObject {
	public UIText(String content,int w,int h,Font f,Color c) {
		
		Components.add(new TextRenderer(content,w,h,f,c));
	}
	public UIText(String content,int w,int h,Font f,Color c,boolean isCentered) {
		
		Components.add(new TextRenderer(content,w,h,f,c,isCentered));
	}
	public void SetText(String content,int w,int h,Font f,Color c) {
		((TextRenderer)Components.get(0)).UpdateText(content,w,h,f,c);
	}
	public void SetText(String content,int w,int h,Font f,Color c,boolean isCentered) {
		((TextRenderer)Components.get(0)).UpdateText(content,w,h,f,c,isCentered);
	}
}
