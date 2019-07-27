package platformDependent;

import java.awt.Rectangle;
import java.util.Queue;

import com.sun.jna.platform.win32.WTypes.LPSTR;
import com.sun.jna.platform.win32.WinDef.*;
//import com.sun.jna.platform.win32.WinUser.HMONITOR;
//import com.sun.jna.platform.win32.WinUser.MONITORINFO;

//import platformDependent.User32.CURSORINFO;
import platformDependent.WindowsFlags;

class WindowsProcessManager extends ProcessManager {
	private HWND hWnd;
	//private MONITORINFO monitorInfo;
	private POINT cursorPos;
	//private CURSORINFO cursorInfo;
	private RECT oldWindowPos;
	
	WindowsProcessManager(String processName) {
		this.processName = processName;
		hWnd = User32.instance.FindWindowA(null, new LPSTR(processName));
	}
	
	@Override
	public boolean reInit() {
		hWnd = User32.instance.FindWindowA(null, new LPSTR(processName));
		return hWnd == null;
	}
	
	@Override
	public boolean hook(Queue<Character> input) {
		//disable input to the game
		
		//while exit is not pressed on the swing gui
			//do
			//if window is not at second to top z level then make it so
				//spawn angry weaboo banner telling the user to stop
			//if swing is not at foreground (activated too) then make it so
				//guan ni pi shi
			//if mouse is not at top left move it back
				//spawn angry weaboo swing banner again
			//if keystrokes are in queue from AI pass it on to the game
		//done
			
		return false;
	}
	
	@Override
	public Rectangle focusWindow() {
		if (hWnd != null) {
			User32.instance.ShowWindow(hWnd, WindowsFlags.SW_RESTORE);
			User32.instance.SetForegroundWindow(hWnd);
			
			//HMONITOR hMonitor = User32.instance.MonitorFromWindow(hWnd, WindowsFlags.MONITOR_DEFAULTTONULL);
			//monitorInfo = new MONITORINFO();
			//User32.instance.GetMonitorInfoA(hMonitor, monitorInfo);
			
			oldWindowPos = new RECT();
			User32.instance.GetWindowRect(hWnd, oldWindowPos);
			User32.instance.MoveWindow(hWnd, 0, 0, oldWindowPos.right, oldWindowPos.bottom, User32.TRUE);
			newWindowPos = new Rectangle(0, 0, oldWindowPos.right, oldWindowPos.bottom);
			
			//cursorInfo = new CURSORINFO();
			//User32.instance.GetCursorInfo(cursorInfo);
			//User32.instance.ShowCursor(new BOOL(false));
			
			cursorPos = new POINT(0, 0);
			User32.instance.GetCursorPos(cursorPos);
			User32.instance.ClipCursor(new RECT());
			
			User32.instance.EnableWindow(hWnd, new BOOL(false));
		}
		else{
			System.out.println(processName + " is not running");
		}
		
		return newWindowPos;
	}

	@Override
	public boolean restoreWindow() {
		if(hWnd != null) {
			User32.instance.ShowWindow(hWnd, WindowsFlags.SW_MINIMIZE);
			User32.instance.SetCursorPos(cursorPos.x, cursorPos.y);
			User32.instance.MoveWindow(hWnd, oldWindowPos.left, oldWindowPos.top, oldWindowPos.right, oldWindowPos.bottom, User32.TRUE);
		}
		else {
			System.out.println(processName + " is not running");
		}
		
		return hWnd == null;
	}

	@Override
	public boolean inputWindow(Queue<Character> input) {
		if(hWnd != null) {
			
		}
		
		return false;
	}
}