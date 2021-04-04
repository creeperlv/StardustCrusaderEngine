package org.StardustCrusader.GameEngine.Renderers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.StardustCrusader.GameEngine.GameBehavior;

public class ImageRenderer extends GameBehavior implements BaseRenderer{
	BufferedImage image00;
	public ImageRenderer(File f) throws IOException {
		image00=ImageIO.read(f);
	}
	public ImageRenderer(InputStream IS) throws IOException {
		image00=ImageIO.read(IS);
	}
	public ImageRenderer(BufferedImage f) {
		image00=f;
	}
	public void UpdateImage(File f)throws Exception {
		image00=ImageIO.read(f);
	}
	public void UpdateImage(InputStream IS)throws Exception {
		image00=ImageIO.read(IS);
	}
	public void UpdateImage(BufferedImage f) {
		image00=f;
	}
	@Override
	public BufferedImage GetContent() {
		return image00;
	}
}
