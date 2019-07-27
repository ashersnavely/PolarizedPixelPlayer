/*
 * Contributors: Ash
 * Description: this serves as our panel controller for our frame
 * Date Last Modified: 10/22/2018
 */

package windowManagement;

import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.Shutdown;

public class PanelController {
    
    /*~~~~~~~~~~~~ Construction  ~~~~~~~~~~~~*/
	
	public PanelController(String name) {
		this(name, 700, 500);
	}

	public PanelController(String name, int width, int height) {
		frame = new JFrame(name);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width, height);
        frame.setResizable(false);
		frame.setVisible(true);

		this.stack = new Stack<JPanel>();
	}
    
    /*~~~~~~~~~~~~ Insertion  ~~~~~~~~~~~~*/
    
    /*
     * description: puts a frame onto the stack
     * return: void
     * precondition: void
     * postcondition: the new frame will be on top
     */
    public void openFrame(JPanel newFrame) {
        this.stack.push(newFrame);

        frame.getContentPane().removeAll();
        frame.add(newFrame);
        frame.revalidate();
        frame.pack();
        frame.repaint();
    }
    
    /*~~~~~~~~~~~~ Removal  ~~~~~~~~~~~~*/
    
    /*
     * description: pops off the top of the stack
     * return: void
     * precondition: void
     * postcondition: if there is no frame to pop shutdown is called
     */
    public void closeFrame() {
        if (this.stack.isEmpty()) {
        	new Shutdown();
        }
        this.stack.pop();

        frame.getContentPane().removeAll();
        frame.add(this.stack.peek());
        frame.revalidate();
        frame.pack();
        frame.repaint();
    }
    
	/*~~~~~~~~~~~~ Getters  ~~~~~~~~~~~~*/
    
    /*
     * description: getter
     * return: the top of stack
     * precondition: void
     * postcondition: nothing is changed
     */
	
	public JPanel getCurrentFrame() {
        return stack.peek();
    }
	
	private static JFrame frame;
	private Stack<JPanel> stack;
}
