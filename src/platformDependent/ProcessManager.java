package platformDependent;

import java.awt.Rectangle;
import java.util.Queue;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.logging.log4j.LogManager;

public abstract class ProcessManager {
	protected Process process = null;
	protected String processName = null;
	
	protected Rectangle newWindowPos = null;
	
	public static ProcessManager getInstance(String processName) {
		if(Platform.isWindows()) {
			return new WindowsProcessManager(processName);
		}else if(Platform.isLinux()) {
			return new LinuxProcessManager(processName);
		}else if(Platform.isMacOs()) {
			return new MacOsProcessManager(processName);
		}
		return null;
	}
	
	public abstract boolean reInit();
	
	public final boolean hookProcess(Queue<Character> input) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
                    for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                        if ("Windows".equals(info.getName())) {
                            javax.swing.UIManager.setLookAndFeel(info.getClassName());
                            break;
                        }
                    }
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
                    LogManager.getLogger().error(e.getMessage(), e);
                }
				
				UIManager.put("swing.boldMetal", Boolean.FALSE);
			}
		});
		
		return hook(input);
	}
	
	protected abstract boolean hook(Queue<Character> input);
	
	public abstract Rectangle focusWindow();
	
	public abstract boolean restoreWindow();
	
	public abstract boolean inputWindow(Queue<Character> input);

	public Rectangle getNewWindowPos() {
		return newWindowPos;
	}
}