package imageProcessor;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ScreenCapturer extends Robot {
	private static long UUIDCounter = 0;
	private String uniqueID = Long.toString(UUIDCounter++);
	
	private Rectangle captureArea;
	private File captureFile;
	
	public ScreenCapturer() throws AWTException {
		this(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
	}
	
	public ScreenCapturer(Rectangle windowRectangle) throws AWTException {
		captureArea = windowRectangle;
		
		try {
			captureFile = File.createTempFile("cap" + uniqueID, ".temp.jpg");
			captureFile.deleteOnExit();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public BufferedImage capture() {
		return createScreenCapture(captureArea);
	}
	
	public File captureToFile() {
		try {
			ImageIO.write(capture(), "jpg", captureFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return captureFile;
	}

	public Rectangle getCaptureArea() {
		return captureArea;
	}

	public void setCaptureArea(Rectangle captureArea) {
		this.captureArea = captureArea;
	}
}