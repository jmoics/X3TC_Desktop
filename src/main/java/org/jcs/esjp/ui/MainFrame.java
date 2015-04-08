package org.jcs.esjp.ui;

import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class MainFrame
    extends JFrame
{
    public MainFrame()
    {
        super();
        setBackground(new Color(53, 66, 90));
        final JComponent newContentPane = new ScrollPanel();
        setContentPane(newContentPane);
    }
}
