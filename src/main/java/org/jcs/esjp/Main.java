package org.jcs.esjp;

import org.jcs.esjp.ui.MainWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Main
{
    /**
     * Logger for this class.
     */
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(final String[] args)
    {
        Main.LOG.info("Startup");

        javax.swing.SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                MainWindow.createAndShowGUI();
            }
        });

        Main.LOG.info("Terminated");
    }

}
