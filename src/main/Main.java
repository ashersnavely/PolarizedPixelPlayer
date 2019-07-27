package main;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.io.File;

import imageProcessor.ScreenCapturer;
import imageProcessor.templateMatching;
import platformDependent.*;

public class Main {
	public static void main(String[] args) {
		
		ProcessManager pm = ProcessManager.getInstance("Polarized Pixel");
		Rectangle windowPos = pm.focusWindow();
		
		ScreenCapturer stalker = null;
		try {
			stalker = new ScreenCapturer(windowPos);
		} catch (AWTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		File screenCapture = stalker.captureToFile();
		
		templateMatching.detectMatches(screenCapture, screenCapture);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		pm.restoreWindow();
	}
}
