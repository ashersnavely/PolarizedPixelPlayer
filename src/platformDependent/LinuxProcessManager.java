package platformDependent;

import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

class LinuxProcessManager extends ProcessManager {
	protected static List<Integer> processList = null;
	
	LinuxProcessManager(String processName) {
		processList = new ArrayList<Integer>();
		
		try {
			process = Runtime.getRuntime().exec(
					"ps axo comm,pid |"
					+ "egrep " + processName + " |"
					+ "awk '{print $2}'");
			
			BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
			while(br.ready()) {
				processList.add(Integer.parseInt(br.readLine()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
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