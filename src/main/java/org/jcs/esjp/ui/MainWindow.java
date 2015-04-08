package org.jcs.esjp.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import javax.swing.JFrame;

public class MainWindow
    extends WindowAdapter
    implements ActionListener
{
    public MainWindow() {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //screenSize.setSize(1980, 1020);
    }

    @Override
    public void actionPerformed(final ActionEvent e)
    {
        // TODO Auto-generated method stub

    }

    public static void createAndShowGUI()
    {
        // Use the Java look and feel.
        /*try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (final Exception e) {
        }*/

        // Make sure we have nice window decorations.
        /*JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);*/

        // Instantiate the controlling class.
        final JFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setVisible(true);

        // Create and set up the content pane.
        //final MainWindow demo = new MainWindow();

        // Add components to it.
        /*final Container contentPane = frame.getContentPane();
        contentPane.add(demo.createOptionControls(), BorderLayout.CENTER);
        contentPane.add(demo.createButtonPane(), BorderLayout.PAGE_END);
        frame.getRootPane().setDefaultButton(defaultButton);*/

        // Display the window.
        //frame.setSize(3000, 3000);
        frame.setResizable(true);
        frame.pack();
        //frame.setLocationRelativeTo(null); // center it
        frame.setVisible(true);
    }

}
