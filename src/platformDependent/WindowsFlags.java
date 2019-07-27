package platformDependent;

import com.sun.jna.platform.win32.WinDef.DWORD;

public interface WindowsFlags {
		//AnimateWindow Flags
			//Animates the window from left to right. This flag can be used with roll or slide animation. It is ignored when used with AW_CENTER or AW_BLEND.
			public static int AW_HOR_POSITIVE = 0x00000001;
			//Animates the window from right to left. This flag can be used with roll or slide animation. It is ignored when used with AW_CENTER or AW_BLEND.
			public static int AW_HOR_NEGATIVE = 0x00000002;
			//Animates the window from top to bottom. This flag can be used with roll or slide animation. It is ignored when used with AW_CENTER or AW_BLEND.
			public static int AW_VER_POSITIVE = 0x00000004;
			//Animates the window from bottom to top. This flag can be used with roll or slide animation. It is ignored when used with AW_CENTER or AW_BLEND.
			public static int AW_VER_NEGATIVE = 0x00000008;
			//Makes the window appear to collapse inward if AW_HIDE is used or expand outward if the AW_HIDE is not used. The various direction flags have no effect.
			public static int AW_CENTER = 0x00000010;
			//Hides the window. By default, the window is shown. 
			public static int AW_HIDE = 0x00010000;
			//Activates the window. Do not use this value with AW_HIDE.
			public static int AW_ACTIVATE = 0x00020000;
			//Uses slide animation. By default, roll animation is used. This flag is ignored when used with AW_CENTER.
			public static int AW_SLIDE = 0x00040000;
			//Uses a fade effect. This flag can be used only if hWnd is a top-level window.
			public static int AW_BLEND = 0x00080000;
		//AnimateWindow Flags
			
		//ShowWindow Flags
			//Hides the window and activates another window.
			public static int SW_HIDE = 0;
			//Activates and displays a window. If the window is minimized or maximized, the system restores it to its original size and position.
				//An application should specify this flag when displaying the window for the first time.
			public static int SW_SHOWNORMAL = 1;
			//Activates the window and displays it as a minimized window.
			public static int SW_SHOWMINIMIZED = 2;
			//Activates the window and displays it as a maximized window.
			public static int SW_MAXIMIZE = 3;
			//Displays a window in its most recent size and position. This value is similar to SW_SHOWNORMAL, except that the window is not activated.
			public static int SW_SHOWNOACTIVATE = 4;
			//Activates the window and displays it in its current size and position.
			public static int SW_SHOW = 5;
			//Minimizes the specified window and activates the next top-level window in the Z order.
			public static int SW_MINIMIZE = 6;
			//Displays the window as a minimized window. This value is similar to SW_SHOWMINIMIZED, except the window is not activated.
			public static int SW_SHOWMINNOACTIVE = 7;
			//Displays the window in its current size and position. This value is similar to SW_SHOW, except that the window is not activated.
			public static int SW_SHOWNA = 8;
			//Activates and displays the window. If the window is minimized or maximized, the system restores it to its original size and position.
				//An application should specify this flag when restoring a minimized window.
			public static int SW_RESTORE = 9;
			//Sets the show state based on the SW_ value specified in the STARTUPINFO structure passed to the CreateProcess function by the program that started the application.
			public static int SW_SHOWDEFAULT = 10;
			//Minimizes a window, even if the thread that owns the window is not responding. This flag should only be used when minimizing windows from a different thread.
			public static int SW_FORCEMINIMIZE = 11;
		//ShowWindow Flags
			
		//Window Handle Flags
			//Places the window at the bottom of the Z order. If the hWnd parameter identifies a topmost window, the window loses its topmost status and is placed at the bottom of all other windows. 
			public static int HWND_BOTTOM = 1;
			//Places the window above all non-topmost windows (that is, behind all topmost windows). This flag has no effect if the window is already a non-topmost window.
			public static int HWND_NOTOPMOST = -2;
			//Places the window at the top of the Z order.
			public static int HWND_TOP = 0;
			//Places the window above all non-topmost windows. The window maintains its topmost position even when it is deactivated.
			public static int HWND_TOPMOST = -1;
		//Window Handle Flags
			
		//SetWindowPos Flags
			//Retains the current size (ignores the cx and cy parameters).
			public static int SWP_NOSIZE = 0x0001;
			//Retains the current position (ignores X and Y parameters).
			public static int SWP_NOMOVE = 0x0002;
			//Retains the current Z order (ignores the hWndInsertAfter parameter).
			public static int SWP_NOZORDER = 0x0004;
			//Does not redraw changes. If this flag is set, no repainting of any kind occurs. This applies to the client area, the non-client area (including the title bar and scroll bars), and any part of the parent window uncovered as a result of the window being moved. When this flag is set, the application must explicitly invalidate or redraw any parts of the window and parent window that need redrawing. 
			public static int SWP_NOREDRAW = 0x0008;
			//Does not activate the window. If this flag is not set, the window is activated and moved to the top of either the topmost or non-topmost group (depending on the setting of the hWndInsertAfter parameter).
			public static int SWP_NOACTIVATE = 0x0010;
			//Draws a frame (defined in the window's class description) around the window.
			public static int SWP_DRAWFRAME = 0x0020;
			//Displays the window.
			public static int SWP_SHOWWINDOW = 0x0040;
			//Hides the window.
			public static int SWP_HIDEWINDOW = 0x0080;
			//Discards the entire contents of the client area. If this flag is not specified, the valid contents of the client area are saved and copied back into the client area after the window is sized or repositioned.
			public static int SWP_NOCOPYBITS = 0x0100;
			//Does not change the owner window's position in the Z order.
			public static int SWP_NOOWNERZORDER = 0x0200;
			//Prevents the window from receiving the WM_WINDOWPOSCHANGING message.
			public static int SWP_NOSENDCHANGING = 0x0400;
			//Prevents generation of the WM_SYNCPAINT message.
			public static int SWP_DEFERERASE = 0x2000;
			//If the calling thread and the thread that owns the window are attached to different input queues, the system posts the request to the thread that owns the window. This prevents the calling thread from blocking its execution while other threads process the request. 
			public static int SWP_ASYNCWINDOWPOS = 0x4000;
		//SetWindowPos Flags
			
		//Color Flags
			//Scroll bar gray area.
			public static int COLOR_SCROLLBAR = 0;
			//Desktop.
			public static int COLOR_DESKTOP = 1;
			//Active window title bar. The associated foreground color is COLOR_CAPTIONTEXT. Specifies the left side color in the color gradient of an active window's title bar if the gradient effect is enabled.
			public static int COLOR_ACTIVECAPTION = 2;
			//Inactive window caption. The associated foreground color is COLOR_INACTIVECAPTIONTEXT. Specifies the left side color in the color gradient of an inactive window's title bar if the gradient effect is enabled.
			public static int COLOR_INACTIVECAPTION = 3;
			//Menu background. The associated foreground color is COLOR_MENUTEXT.
			public static int COLOR_MENU = 4;
			//Window background. The associated foreground colors are COLOR_WINDOWTEXT and COLOR_HOTLITE.
			public static int COLOR_WINDOW = 5;
			//Window frame.
			public static int COLOR_WINDOWFRAME = 6;
			//Text in menus. The associated background color is COLOR_MENU.
			public static int COLOR_MENUTEXT = 7;
			//Text in windows. The associated background color is COLOR_WINDOW.
			public static int COLOR_WINDOWTEXT = 8;
			//Text in caption, size box, and scroll bar arrow box. The associated background color is COLOR_ACTIVECAPTION.
			public static int COLOR_CAPTIONTEXT = 9;
			//Active window border.
			public static int COLOR_ACTIVEBORDER = 10;
			//Inactive window border.
			public static int COLOR_INACTIVEBORDER = 11;
			//Background color of multiple document interface (MDI) applications.
			public static int COLOR_APPWORKSPACE = 12;
			//Item(s) selected in a control. The associated foreground color is COLOR_HIGHLIGHTTEXT.
			public static int COLOR_HIGHLIGHT = 13;
			//Text of item(s) selected in a control. The associated background color is COLOR_HIGHLIGHT.
			public static int COLOR_HIGHLIGHTTEXT = 14;
			//Face color for three-dimensional display elements and for dialog box backgrounds. The associated foreground color is COLOR_BTNTEXT.
			public static int COLOR_3DFACE = 15;
			//Shadow color for three-dimensional display elements (for edges facing away from the light source).
			public static int COLOR_3DSHADOW = 16;
			//Grayed (disabled) text. This color is set to 0 if the current display driver does not support a solid gray color.
			public static int COLOR_GRAYTEXT = 17;
			//Text on push buttons. The associated background color is COLOR_BTNFACE.
			public static int COLOR_BTNTEXT = 18;
			//Color of text in an inactive caption. The associated background color is COLOR_INACTIVECAPTION.
			public static int COLOR_INACTIVECAPTIONTEXT = 19;
			//Highlight color for three-dimensional display elements (for edges facing the light source.)
			public static int COLOR_3DHIGHLIGHT = 20;
			//Dark shadow for three-dimensional display elements.
			public static int COLOR_3DDKSHADOW = 21;
			//Light color for three-dimensional display elements (for edges facing the light source.)
			public static int COLOR_3DLIGHT = 22;
			//Text color for tooltip controls. The associated background color is COLOR_INFOBK.
			public static int COLOR_INFOTEXT = 23;
			//Background color for tooltip controls. The associated foreground color is COLOR_INFOTEXT.
			public static int COLOR_INFOBK = 24;
			//Color for a hyperlink or hot-tracked item. The associated background color is COLOR_WINDOW.
			public static int COLOR_HOTLIGHT = 26;
			//Right side color in the color gradient of an active window's title bar. COLOR_ACTIVECAPTION specifies the left side color. Use SPI_GETGRADIENTCAPTIONS with the SystemParametersInfo function to determine whether the gradient effect is enabled.
			public static int COLOR_GRADIENTACTIVECAPTION = 27;
			//Right side color in the color gradient of an inactive window's title bar. COLOR_INACTIVECAPTION specifies the left side color.
			public static int COLOR_GRADIENTINACTIVECAPTION = 28;
			//The color used to highlight menu items when the menu appears as a flat menu (see SystemParametersInfo). The highlighted menu item is outlined with COLOR_HIGHLIGHT. Windows 2000:  This value is not supported.
			public static int COLOR_MENUHILIGHT = 29;
			//The background color for the menu bar when menus appear as flat menus (see SystemParametersInfo). However, COLOR_MENU continues to specify the background color of the menu popup. Windows 2000:  This value is not supported.
			public static int COLOR_MENUBAR = 30;
		//Color Flags
			
		//dwFlags
			//Returns a handle to the display monitor that is nearest to the window.
			public static DWORD MONITOR_DEFAULTTONEAREST = new DWORD(0);
			//Returns NULL.
			public static DWORD MONITOR_DEFAULTTONULL = new DWORD(1);
			//Returns a handle to the primary display monitor.
			public static DWORD MONITOR_DEFAULTTOPRIMARY = new DWORD(2);
		//dwFlags
}
