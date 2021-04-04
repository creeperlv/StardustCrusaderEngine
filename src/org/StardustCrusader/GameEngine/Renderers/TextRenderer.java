package org.StardustCrusader.GameEngine.Renderers;

import java.awt.*;
import java.awt.font.*;
import java.awt.image.BufferedImage;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.util.Hashtable;

import org.StardustCrusader.GameEngine.GameBehavior;

public class TextRenderer extends GameBehavior implements BaseRenderer {
	String Content;
	BufferedImage FinalContent;

	public TextRenderer(String content, int w, int h, Font f, Color c) {
		UpdateText(content, w, h, f, c);
	}

	public TextRenderer(String content, int w, int h, Font f, Color c, boolean isCentered) {
		UpdateText(content, w, h, f, c, isCentered);
	}

	@Override
	public BufferedImage GetContent() {
		return FinalContent;
	}

	public void UpdateText(String content, int w, int h, Font f, Color c) {
		UpdateText(content, w, h, f, c, false);

	}

	public void UpdateText(String content, int w, int h, Font f, Color c, boolean isCentered) {
		Content = content;
		FinalContent = new BufferedImage(w, h, 2);
		Graphics2D g = (Graphics2D) FinalContent.getGraphics();
		if (f == null)
			f = new Font("Sans Serif", Font.PLAIN, 12);
		g.setFont(f);
		g.setColor(c);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		float BW = w;
		float dY = 0;
		Hashtable<TextAttribute, Object> attr = new Hashtable<TextAttribute, Object>();
		attr.put(TextAttribute.FAMILY, f.getFamily());
		attr.put(TextAttribute.SIZE, f.getSize());
		String[] Paragraphs = Content.split("\n");
		for (String string : Paragraphs) {

			AttributedString AS = new AttributedString(string, attr);
			AttributedCharacterIterator ACI = AS.getIterator();
			int pS = ACI.getBeginIndex();
			int pE = ACI.getEndIndex();
			FontRenderContext FRC = g.getFontRenderContext();
			LineBreakMeasurer LBM = new LineBreakMeasurer(ACI, FRC);
			LBM.setPosition(pS);
			while (LBM.getPosition() < pE) {
				TextLayout TL = LBM.nextLayout(BW);
				
				float dX =isCentered?(BW- TL.getAdvance())/2:0;
				dY += TL.getAscent();
				TL.draw(g, dX, dY);
				dY += TL.getDescent() + TL.getLeading();
			}
		}
	}

}
