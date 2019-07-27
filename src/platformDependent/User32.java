package platformDependent;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Native;
import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WTypes.LPSTR;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.LPARAM;
import com.sun.jna.platform.win32.WinDef.LRESULT;
import com.sun.jna.platform.win32.WinDef.POINT;
import com.sun.jna.platform.win32.WinDef.RECT;
import com.sun.jna.platform.win32.WinDef.UINT;
import com.sun.jna.platform.win32.WinDef.WPARAM;
import com.sun.jna.platform.win32.WinUser.HMONITOR;
import com.sun.jna.platform.win32.WinUser.INPUT;
import com.sun.jna.platform.win32.WinUser.MONITORINFO;
import com.sun.jna.win32.StdCallLibrary;

public interface User32 extends StdCallLibrary {
	public enum Cursor {
    	
        APPSTARTING(32650),
        NORMAL(32512),
        CROSS(32515),
        HAND(32649),
        HELP(32651),
        IBEAM(32513),
        NO(32648),
        SIZEALL(32646),
        SIZENESW(32643),
        SIZENS(32645),
        SIZENWSE(32642),
        SIZEWE(32644),
        UP(32516),
        WAIT(32514),
        PEN(32631);

        private final int code;

        Cursor(final int code) {
        	this.code = code;
        }
        
        public int getCode() {
        	return code;
        }
    }
    
    public static class CURSORINFO extends Structure {

        public int cbSize;
        public int flags;
        public WinDef.HCURSOR hCursor;
        public WinDef.POINT ptScreenPos;

        public CURSORINFO() {
            this.cbSize = Native.getNativeSize(CURSORINFO.class, null);
        }
        
        @Override
        protected List<String> getFieldOrder() {
            return Arrays.asList("cbSize", "flags", "hCursor", "ptScreenPos");
        }
    }
	
	@SuppressWarnings("deprecation")
	static User32 instance = Native.loadLibrary("User32.dll", User32.class);
	static BOOL TRUE = new BOOL(true);
	static BOOL FALSE = new BOOL(false);
	
	/* Documentation :
     *		https://docs.microsoft.com/en-us/windows/desktop/api/winuser/nf-winuser-findwindowa
     * Description :
     * 		Retrieves a handle to the top-level window whose class name and window name match the specified strings.
     * 		This function does not search child windows.
     * 		This function does not perform a case-sensitive search.
     * Arguments :
     * 		lpClassName - The class name or a class atom created by a previous call to the RegisterClass or RegisterClassEx function.
     * 			The atom must be in the low-order word of lpClassName; the high-order word must be zero.
     * 			If lpClassName points to a string, it specifies the window class name.
     * 			The class name can be any name registered with RegisterClass or RegisterClassEx, or any of the predefined control-class names.
     * 			If lpClassName is NULL, it finds any window whose title matches the lpWindowName parameter.
     * Return :
     * 		If the lpWindowName parameter is not NULL, FindWindow calls the GetWindowText function to retrieve the window name for comparison.
     * 		For a description of a potential problem that can arise, see the Remarks for GetWindowText.
     */
    HWND FindWindowA(LPSTR lpClassName, LPSTR processName);
    
    /* Documentation :
     * 		https://docs.microsoft.com/en-us/windows/desktop/api/winuser/nf-winuser-showwindow
     * Description :
     * 		Sets the specified window's show state.
     * Arguments :
     * 		hWnd - A handle to the window.
     * 		nCmdShow - Controls how the window is to be shown.
     * 			This parameter is ignored the first time an application calls ShowWindow, if the program that launched the application provides a STARTUPINFO structure.
     * 			Otherwise, the first time ShowWindow is called, the value should be the value obtained by the WinMain function in its nCmdShow parameter.
     * 			In subsequent calls, this parameter can be one of the following values.
     * Return :
     * 		If the window was previously visible, the return value is nonzero.
     * 		If the window was previously hidden, the return value is zero.
     */
    BOOL ShowWindow(HWND hWnd, int nCmdShow);
    
    /* Documentation :
     * 		https://docs.microsoft.com/en-us/windows/desktop/api/winuser/nf-winuser-setforegroundwindow
     *	Description :
     *		Brings the thread that created the specified window into the foreground and activates the window.
     *		Keyboard input is directed to the window, and various visual cues are changed for the user.
     *		The system assigns a slightly higher priority to the thread that created the foreground window than it does to other threads.
     *	Arguments :
     *		A handle to the window that should be activated and brought to the foreground.
     *	Return :
     *		If the window was brought to the foreground, the return value is nonzero.
     *		If the window was not brought to the foreground, the return value is zero.
     */
    BOOL SetForegroundWindow(HWND hWnd);
    
    /* Documentation :
     * 		https://docs.microsoft.com/en-us/windows/desktop/api/winuser/nf-winuser-animatewindow
     * Description :
     * 		Enables you to produce special effects when showing or hiding windows.
     * 		There are four types of animation: roll, slide, collapse or expand, and alpha-blended fade.
     * Arguments :
     * 		hWnd - A handle to the window to animate. The calling thread must own this window.
     * 		dwTime - The time it takes to play the animation, in milliseconds.
     * 			Typically, an animation takes 200 milliseconds to play.
     * 		dwFlags - The type of animation. This parameter can be one or more of the following values.
     * 			Note that, by default, these flags take effect when showing a window.
     * 			To take effect when hiding a window, use AW_HIDE and a logical OR operator with the appropriate flags.
     * Return :
     * 		If the function succeeds, the return value is nonzero.
     * 		If the function fails, the return value is zero.
     */
    BOOL AnimateWindow(HWND hWnd, DWORD dwTime, DWORD dwFlags);
    
    /* Documentation :
     * 		https://docs.microsoft.com/en-us/windows/desktop/api/winuser/nf-winuser-iswindowvisible
     * Description :
     * 		Determines the visibility state of the specified window.
     * Arguments :
     * 		hWnd - A handle to the window to be tested.
     * Return :
     * 		If the specified window, its parent window, its parent's parent window, and so forth, have the WS_VISIBLE style, the return value is nonzero. Otherwise, the return value is zero.
     *		Because the return value specifies whether the window has the WS_VISIBLE style, it may be nonzero even if the window is totally obscured by other windows.
     */
    BOOL IsWindowVisible(HWND hWnd);
    
    /* Documentation :
     * 		https://docs.microsoft.com/en-us/windows/desktop/api/winuser/nf-winuser-enablewindow
     * Description :
     * 		Enables or disables mouse and keyboard input to the specified window or control.
     * 		When input is disabled, the window does not receive input such as mouse clicks and key presses.
     * 		When input is enabled, the window receives all input.
     * Arguments :
     * 		hWnd - A handle to the window to be enabled or disabled.
     * 		bEnable - Indicates whether to enable or disable the window.
     * 			If this parameter is TRUE, the window is enabled.
     * 			If the parameter is FALSE, the window is disabled.
     * Return :
     * 		If the window was previously disabled, the return value is nonzero.
     * 		If the window was not previously disabled, the return value is zero.
     */
    BOOL EnableWindow(HWND hWnd, BOOL bEnable);
    
    /* Documentation :
     *		https://docs.microsoft.com/en-us/windows/desktop/api/winuser/nf-winuser-iswindowenabled
     * Description :
     * 		Determines whether the specified window is enabled for mouse and keyboard input.
     * Arguments :
     * 		hWnd - A handle to the window to be tested.
     * Return :
     * 		If the window is enabled, the return value is nonzero.
     * 		If the window is not enabled, the return value is zero.
     */
    BOOL IsWindowEnabled(HWND hWnd);
    
    /* Documentation :
     * 		https://docs.microsoft.com/en-us/windows/desktop/api/winuser/nf-winuser-getwindowrect
     * Description :
     * 		Retrieves the dimensions of the bounding rectangle of the specified window.
     * 		The dimensions are given in screen coordinates that are relative to the upper-left corner of the screen.
     * Arguments :
     * 		hWnd - A handle to the window.
     * 		lpRect - A pointer to a RECT structure that receives the screen coordinates of the upper-left and lower-right corners of the window.
     * Return :
     * 		If the function succeeds, the return value is nonzero.
     * 		If the function fails, the return value is zero. To get extended error information, call GetLastError.
     */
    BOOL GetWindowRect(HWND hWnd, RECT lpRect);
    
    /* Documentation :
     * 		https://docs.microsoft.com/en-us/windows/desktop/api/winuser/nf-winuser-movewindow
     * Description :
     * 		Indicates whether the window is to be repainted.
     * 		If this parameter is TRUE, the window receives a message.
     * 		If the parameter is FALSE, no repainting of any kind occurs.
     * 		This applies to the client area, the non-client area (including the title bar and scroll bars), and any part of the parent window uncovered as a result of moving a child window.
     * Arguments :
     * 		hWnd - A handle to the window.
     * 		X - The new position of the left side of the window.
     * 		Y - The new position of the top of the window.
     * 		nWidth - The new width of the window.
     * 		nHeight - The new height of the window.
     * 		bRepaint - Indicates whether the window is to be repainted.
     * 			If this parameter is TRUE, the window receives a message.
     * 			If the parameter is FALSE, no repainting of any kind occurs.
     * 			This applies to the client area, the non-client area (including the title bar and scroll bars), and any part of the parent window uncovered as a result of moving a child window.
     * Return :
     * 		If the function succeeds, the return value is nonzero.
     * 		If the function fails, the return value is zero. To get extended error information, call GetLastError.
     */
    BOOL MoveWindow(HWND hWnd, int X, int Y, int nWidth, int nHeight, BOOL bRepaint);
    
    /* Documentation :
     * 		https://docs.microsoft.com/en-us/windows/desktop/api/winuser/nf-winuser-setwindowpos
     * Description :
     * 		Changes the size, position, and Z order of a child, pop-up, or top-level window. These windows are ordered according to their appearance on the screen.
     * 		The topmost window receives the highest rank and is the first window in the Z order.
     * Arguments :
     * 		hWnd - A handle to the window.
     * 		hWndInsertAfter - one of the window handle flags
     * 		X - The new position of the left side of the window, in client coordinates.
     * 		Y - The new position of the top of the window, in client coordinates.
     * 		cx - The new width of the window, in pixels.
     * 		cy - The new height of the window, in pixels.
     * 		uFlags - one of the SetWindowPos flags
     * Return :
     * 		If the function succeeds, the return value is nonzero.
     * 		If the function fails, the return value is zero. To get extended error information, call GetLastError.
     */
    BOOL SetWindowPos(HWND hWnd, HWND hWndInsertAfter, int X, int Y, int cx, int cy, int uFlags);
    
    /* Documentation :
     * 		https://docs.microsoft.com/en-us/windows/desktop/api/winuser/nf-winuser-getcursorpos
     * Definition :
     * 		Retrieves the position of the mouse cursor, in screen coordinates.
     * Arguments :
     * 		lpPoint - A pointer to a POINT structure that receives the screen coordinates of the cursor.
     * Return :
     * 		Returns nonzero if successful or zero otherwise. To get extended error information, call GetLastError.
     */
    BOOL GetCursorPos(POINT lpPoint);
    
    /* Documentation :
     * 		https://docs.microsoft.com/en-us/windows/desktop/api/winuser/nf-winuser-setcursorpos
     * Definition :
     * 		Moves the cursor to the specified screen coordinates.
     * 		If the new coordinates are not within the screen rectangle set by the most recent ClipCursor function call, the system automatically adjusts the coordinates so that the cursor stays within the rectangle.
     * Arguments :
     * 		X - The new x-coordinate of the cursor, in screen coordinates.
     * 		Y - The new y-coordinate of the cursor, in screen coordinates.
     * Return :
     * 		Returns nonzero if successful or zero otherwise. To get extended error information, call GetLastError.
     */
    BOOL SetCursorPos(int X, int Y);
    
    /* Documentation :
     * 		https://docs.microsoft.com/en-us/windows/desktop/api/winuser/nf-winuser-sendinput
     * Definition :
     * 		Synthesizes keystrokes, mouse motions, and button clicks.
     * Arguments :
     * 		cInputs - The number of structures in the pInputs array.
     * 		pInputs - An array of INPUT structures.
     * 			Each structure represents an event to be inserted into the keyboard or mouse input stream.
     * 		cbSize - The size, in bytes, of an INPUT structure.
     * 			If cbSize is not the size of an INPUT structure, the function fails.
     * Return :
     * 		The function returns the number of events that it successfully inserted into the keyboard or mouse input stream.
     * 		If the function returns zero, the input was already blocked by another thread. To get extended error information, call GetLastError.
     * 		This function fails when it is blocked by UIPI. Note that neither GetLastError nor the return value will indicate the failure was caused by UIPI blocking.
     */
    UINT SendInput(UINT cInputs, INPUT[] pInputs, int cbSize);
    
    /* Documentation :
     * 		https://docs.microsoft.com/en-us/windows/desktop/api/winuser/nf-winuser-getsyscolor
     * Definition :
     * 		Retrieves the current color of the specified display element. Display elements are the parts of a window and the display that appear on the system display screen.
     * Arguments :
     * 		nIndex - The display element whose color is to be retrieved. This parameter can be one of the Color flags
     * Return :
     * 		The function returns the red, green, blue (RGB) color value of the given element.
     * 		If the nIndex parameter is out of range, the return value is zero.
     * 		Because zero is also a valid RGB value, you cannot use GetSysColor to determine whether a system color is supported by the current platform.
     * 		Instead, use the GetSysColorBrush function, which returns NULL if the color is not supported.
     */
    DWORD GetSysColor(int nIndex);
    
    /* Documentation :
     * 		https://docs.microsoft.com/en-us/windows/desktop/api/wingdi/nf-wingdi-getrvalue
     * Definition :
     * 		The GetRValue macro retrieves an intensity value for the red component of a red, green, blue (RGB) value.
     * Arguments :
     * 		rgb - Specifies an RGB color value.
     * Return :
     * 		None
     */
    void GetRValue(DWORD rgb);
    
    /* Documentation :
     * 		https://docs.microsoft.com/en-us/windows/desktop/api/wingdi/nf-wingdi-getgvalue
     * Definition :
     * 		The GetGValue macro retrieves an intensity value for the green component of a red, green, blue (RGB) value.
     * Arguments :
     * 		rgb - Specifies an RGB color value.
     * Return :
     * 		None
     */
    void GetGValue(DWORD rgb);
    
    /* Documentation :
     * 		https://docs.microsoft.com/en-us/windows/desktop/api/wingdi/nf-wingdi-getbvalue
     * Definition :
     * 		The GetBValue macro retrieves an intensity value for the blue component of a red, green, blue (RGB) value.
     * Arguments :
     * 		rgb - Specifies an RGB color value.
     * Return :
     * 		None
     */
    void GetBValue(DWORD rbg);
    
    /* Documentation :
     * 		https://docs.microsoft.com/en-us/windows/desktop/api/winuser/nf-winuser-setsyscolors
     * Definition :
     * 		Sets the colors for the specified display elements. Display elements are the various parts of a window and the display that appear on the system display screen.
     * Arguments :
     * 		cElements - The number of display elements in the lpaElements array.
     * 		lpaElements - An array of integers that specify the display elements to be changed. For a list of display elements, see GetSysColor.
     * 		lpaRgbValues - An array of COLORREF values that contain the new red, green, blue (RGB) color values for the display elements in the array pointed to by the lpaElements parameter.
     * 		To generate a COLORREF, use the RGB macro.
     * Return :
     * 		If the function succeeds, the return value is a nonzero value.
     * 		If the function fails, the return value is zero. To get extended error information, call GetLastError.
     */
    BOOL SetSysColors(int cElements, int[] lpaElements, DWORD[] lpaRgbValues);
    
    /* Documentation :
     * 		https://docs.microsoft.com/en-us/windows/desktop/api/winuser/nf-winuser-showcursor
     * Definition :
     * 		Displays or hides the cursor.
     * 		The cursor is displayed only if the display count is greater than or equal to 0.
     * 		If a mouse is installed, the initial display count is 0.
     * 		If no mouse is installed, the display count is –1.
     * Arguments :
     * 		bShow - If bShow is TRUE, the display count is incremented by one.
     * 			If bShow is FALSE, the display count is decremented by one.
     * Return :
     * 		The return value specifies the new display counter.
     */
    int ShowCursor(BOOL bShow);
    
    /* Documentation :
     * 		https://docs.microsoft.com/en-us/windows/desktop/api/winuser/nf-winuser-getcursorinfo
     * Definition :
     * 		Retrieves information about the global cursor.
     * Arguments :
     * 		pci - A pointer to a CURSORINFO structure that receives the information.
     * 			Note that you must set the cbSize member to sizeof(CURSORINFO) before calling this function.
     * Return :
     * 		If the function succeeds, the return value is nonzero.
     * 		If the function fails, the return value is zero. To get extended error information, call GetLastError.
     */
    BOOL GetCursorInfo(CURSORINFO pci);
    
    /* Documentation :
     * 		https://docs.microsoft.com/en-us/windows/desktop/api/winuser/nf-winuser-clipcursor
     * Definition :
     * 		Confines the cursor to a rectangular area on the screen.
     * 		If a subsequent cursor position (set by the SetCursorPos function or the mouse) lies outside the rectangle, the system automatically adjusts the position to keep the cursor inside the rectangular area.
     * Arguments :
     * 		lpRect - A pointer to the structure that contains the screen coordinates of the upper-left and lower-right corners of the confining rectangle.
     * 			If this parameter is NULL, the cursor is free to move anywhere on the screen.
     * Return :
     * 		If the function succeeds, the return value is nonzero.
     * 		If the function fails, the return value is zero. To get extended error information, call GetLastError.
     * 
     */
    BOOL ClipCursor(RECT lpRect);
    
    /* Documentation :
     *		https://docs.microsoft.com/en-us/windows/desktop/api/winuser/nf-winuser-sendmessage
     * Definition :
     * 		Sends the specified message to a window or windows.
     * 		The SendMessage function calls the window procedure for the specified window and does not return until the window procedure has processed the message.
     * 		To send a message and return immediately, use the SendMessageCallback or SendNotifyMessage function.
     * 		To post a message to a thread's message queue and return immediately, use the PostMessage or PostThreadMessage function.
     * Arguments :
     * 		hWnd - A handle to the window whose window procedure will receive the message. If this parameter is HWND_BROADCAST ((HWND)0xffff), the message is sent to all top-level windows in the system, including disabled or invisible unowned windows, overlapped windows, and pop-up windows; but the message is not sent to child windows. Message sending is subject to UIPI. The thread of a process can send messages only to message queues of threads in processes of lesser or equal integrity level.
     * 		Msg - The message to be sent.
     * 		wParam - Additional message-specific information.
     * 		lParam - Additional message-specific information.
     * Return :
     * 		The return value specifies the result of the message processing; it depends on the message sent.
     */
    LRESULT SendMessage(HWND hWnd, UINT Msg, WPARAM wParam, LPARAM lParam);
    
    /* Documentation :
     * 		https://docs.microsoft.com/en-us/windows/desktop/api/winuser/nf-winuser-monitorfromwindow
     * Definition : 
     * 		The MonitorFromWindow function retrieves a handle to the display monitor that has the largest area of intersection with the bounding rectangle of a specified window.
     * Arguments :
     * 		hWnd - A handle to the window of interest.
     * 		dwFlags - Determines the function's return value if the window does not intersect any display monitor.
     * 			This can use any of the dwFlags
     * Return :
     * 		If the window intersects one or more display monitor rectangles, the return value is an HMONITOR handle to the display monitor that has the largest area of intersection with the window.
     * 		If the window does not intersect a display monitor, the return value depends on the value of dwFlags.
     */
    HMONITOR MonitorFromWindow(HWND hWnd, DWORD dwFlags);
    
    /* Documentation :
     * 		https://docs.microsoft.com/en-us/windows/desktop/api/winuser/nf-winuser-getmonitorinfoa
     * Definition :
     * 		The GetMonitorInfo function retrieves information about a display monitor.
     * Arguments :
     * 		hMonitor - A handle to the display monitor of interest.
     * 		lpmi - A pointer to a MONITORINFO or MONITORINFOEX structure that receives information about the specified display monitor.
     * 			You must set the cbSize member of the structure to sizeof(MONITORINFO) or sizeof(MONITORINFOEX) before calling the GetMonitorInfo function.
     * 			Doing so lets the function determine the type of structure you are passing to it.
     * 			The MONITORINFOEX structure is a superset of the MONITORINFO structure.
     * 			It has one additional member: a string that contains a name for the display monitor.
     * 			Most applications have no use for a display monitor name, and so can save some bytes by using a MONITORINFO structure.
     * Return :
     * 		If the function succeeds, the return value is nonzero.
     * 		If the function fails, the return value is zero.
     */
    BOOL GetMonitorInfoA(HMONITOR hMonitor, MONITORINFO lpi);
}