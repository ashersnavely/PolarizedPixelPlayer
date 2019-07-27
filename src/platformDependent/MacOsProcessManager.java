package platformDependent;

import java.awt.Rectangle;
import java.util.Queue;

class MacOsProcessManager extends ProcessManager {
	
	MacOsProcessManager(String processName) {
	}
	
	@Override
	public boolean reInit() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean hook(Queue<Character> input) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Rectangle focusWindow() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean restoreWindow() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean inputWindow(Queue<Character> input) {
		// TODO Auto-generated method stub
		return false;
	}
}