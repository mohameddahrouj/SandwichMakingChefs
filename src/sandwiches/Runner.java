package sandwiches;

/**
 * Initializes the table, agent and three chefs in a GUI
 * Includes the main method for simulation
 * @author Mohamed Dahrouj
 *
 */

import javax.swing.*;
import javax.swing.text.DefaultCaret;

import java.awt.event.*;

class Runner extends JFrame
{
	private static final long serialVersionUID = 1L;

	/**
     * JTextArea for the Agent
     */
    private JTextArea agentTA;

    /**
     * JTextArea for Chef 1
     */
    private JTextArea chefTA1;

    /**
     * JTextArea for Chef 2
     */
    private JTextArea chefTA2;
    
    /**
     * JTextArea for Chef 3
     */
    private JTextArea chefTA3;

    /**
     * JTextArea for the thread executing main().
     */
    private JTextArea status;

    /**
     * Build the GUI.
     */
    public Runner(String title) {
        super(title);
        Box box = Box.createVerticalBox();

        agentTA = new JTextArea(5,40);
        agentTA.setEditable(false);
        JScrollPane pane1 =
            new JScrollPane(agentTA, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane1.setBorder(BorderFactory.createTitledBorder("Agent"));
        box.add(pane1);
        DefaultCaret agentcaret = (DefaultCaret) agentTA.getCaret();
        agentcaret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        chefTA1 = new JTextArea(5,40);
        chefTA1.setEditable(false);
        JScrollPane pane2 =
            new JScrollPane(chefTA1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane2.setBorder(BorderFactory.createTitledBorder("Chef 1 - Bread"));
        box.add(pane2);
        DefaultCaret caret1 = (DefaultCaret) chefTA1.getCaret();
        caret1.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        chefTA2 = new JTextArea(5,40);
        chefTA2.setEditable(false);
        JScrollPane pane3 =
            new JScrollPane(chefTA2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane3.setBorder(BorderFactory.createTitledBorder("Chef 2 - Peanut Butter"));
        box.add(pane3);
        DefaultCaret caret2 = (DefaultCaret) chefTA2.getCaret();
        caret2.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        
        chefTA3 = new JTextArea(5,40);
        chefTA3.setEditable(false);
        JScrollPane pane4 =
            new JScrollPane(chefTA3, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane4.setBorder(BorderFactory.createTitledBorder("Chef 3 - Jam"));
        box.add(pane4);
        DefaultCaret caret3 = (DefaultCaret) chefTA3.getCaret();
        caret3.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        status = new JTextArea(5, 40);
        status.setEditable(false);
        JScrollPane pane5 =
            new JScrollPane(status, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane5.setBorder(BorderFactory.createTitledBorder("Status"));
        box.add(pane5);
        DefaultCaret statuscaret = (DefaultCaret) status.getCaret();
        statuscaret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        getContentPane().add(box);
    }

    public static void main(String[] args) {
        Runner frame = new Runner("Sandwich Maker Problem");

        /* Instantiate an anonymous subclass of WindowAdapter, and register it
         * as the frame's WindowListener.
         * windowClosing() is invoked when the frame is in the process of being
         * closed to terminate the application.
         */
        frame.addWindowListener(
            new WindowAdapter() {
               public void windowClosing(WindowEvent e) {
                  System.exit(0);
               }
            }
        );

        // Size the window to fit the preferred size and layout of its
        // subcomponents, then show the window.
        frame.pack();
        frame.setVisible(true);
        
		Table table = new Table();
		Thread agentThread = new Thread(new Agent(table, frame.agentTA), "Agent");
		frame.status.append("Created: " + agentThread + '\n');
		Thread chef1Thread = new Thread(new Chef(table, Ingredient.BREAD, frame.chefTA1), "Chef 1");
		frame.status.append("Created: " + chef1Thread + '\n');
		Thread chef2Thread = new Thread(new Chef(table, Ingredient.PEANUTBUTTER, frame.chefTA2), "Chef 2");
		frame.status.append("Created: " + chef2Thread + '\n');
		Thread chef3Thread = new Thread(new Chef(table,Ingredient.JAM, frame.chefTA3), "Chef 3");
		frame.status.append("Created: " + chef3Thread + '\n');
		
		frame.status.append("Starting threads...\n");
		agentThread.start();
		chef1Thread.start();
		chef2Thread.start();
		chef3Thread.start();
    }
}
