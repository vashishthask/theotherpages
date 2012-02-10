package net.sf.theotherpages.samples.swingsample.ui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import javax.swing.JDesktopPane;
public class RunPaginationDemo extends JFrame{
	JDesktopPane desktop;



    public RunPaginationDemo() {
        super("PaginationSwingDemo");

        //Make the big window be indented 100 pixels from each edge 
        //of the screen.
        int inset = 100;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(inset, inset, 
                  screenSize.width - inset*2, 
                  screenSize.height-inset*2);

        //Quit this app when the big window closes.
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        //Set up the GUI.
        desktop = new JDesktopPane(); //a specialized layered pane
       
        // createFrame(); //here we can create first window
        setContentPane(desktop);
        setJMenuBar(createMenuBar());
        
        //Make dragging faster:
        desktop.putClientProperty("JDesktopPane.dragMode", "outline");
    }

    protected JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("demo");
        menu.setMnemonic(KeyEvent.VK_D);
        JMenuItem menuItem = new JMenuItem("PaginationInAction");
        menuItem.setMnemonic(KeyEvent.VK_N);
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createFrame();
            }
        });
        menu.add(menuItem);
        menuBar.add(menu);

        return menuBar;
    }

    private void createFrame() {
        MyInternalFrame frame = new MyInternalFrame();
    	frame.setVisible(true); 
        desktop.add(frame);
        try {
            frame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {}
    }
    
    private void showSimpleDialog() {
    	  final JDialog d = new JDialog(this, "Run", true);
    	  d.setSize(200, 150);
    	  JLabel l = new JLabel("Pagination Swing Demo", JLabel.CENTER);
    	  d.getContentPane( ).setLayout(new BorderLayout( ));
    	  d.getContentPane( ).add(l, BorderLayout.CENTER);
    	  JButton b = new JButton("Run");
    	  b.addActionListener(new ActionListener( ) {
    	    public void actionPerformed(ActionEvent ev) {
    	      createFrame();
    	      d.setVisible(false);
    	      d.dispose( );
    	    }
    	  });
    	  JPanel p = new JPanel( );     // Flow layout will center button.
    	  p.add(b);
    	  d.getContentPane( ).add(p, BorderLayout.SOUTH);
    	  d.setLocationRelativeTo(this);
    	  d.setVisible(true);
    	}
    
    
    public static void main(String[] args) {
        RunPaginationDemo frame = new RunPaginationDemo();        
        frame.setVisible(true);
        frame.showSimpleDialog();
    }

}
