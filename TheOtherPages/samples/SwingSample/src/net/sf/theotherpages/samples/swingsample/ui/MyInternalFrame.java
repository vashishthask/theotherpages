package net.sf.theotherpages.samples.swingsample.ui;

import javax.swing.JInternalFrame;

public class MyInternalFrame extends JInternalFrame {
    static int openFrameCount = 0;
    static final int xOffset = 30, yOffset = 30;

    public MyInternalFrame() {
        super("Document #" + (++openFrameCount), 
              true, //resizable
              true, //closable
              true, //maximizable
              true);//iconifiable

        //...Create the GUI and put it in the window...
        MyPanel newContentPane = new MyPanel();
        this.add(newContentPane);
        //...Then set the window size or call pack...
       // setSize(300,300);
        pack();
        
        //Set the window's location.
        setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
     
        
    }
    

}
