package org.jcs.esjp.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScrollPanel extends JPanel
{
    /**
     * Logger for this class.
     */
    private static final Logger LOG = LoggerFactory.getLogger(ScrollPanel.class);

    private final MapPanel mappan;

    public ScrollPanel() {
        super(new BorderLayout());
        setBackground(new Color(53, 66, 90));

        ScrollPanel.LOG.debug("Building Toolbar...");
        final JToolBar toolBar = new JToolBar("Buscar");
        toolBar.setBackground(new Color(53, 66, 90));
        toolBar.setBorderPainted(false);
        toolBar.add(makeNavigationButton());

        //Set up the scroll pane.
        mappan = new MapPanel();
        mappan.getComponents();

        final JScrollPane scrollPane = new JScrollPane(mappan);
        scrollPane.setPreferredSize(new Dimension(1920, 980));
        scrollPane.setViewportBorder(
                BorderFactory.createLineBorder(Color.black));

        //Put it in this panel.
        add(toolBar, BorderLayout.PAGE_START);
        add(scrollPane, BorderLayout.CENTER);
        //setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
    }

    protected JButton makeNavigationButton() {

        ScrollPanel.LOG.debug("Building Search Button in Toolbar");
        final URL imageURL = this.getClass().getClassLoader().getResource("images/data/search2.png");

        ScrollPanel.LOG.debug("Path for Search Button is '{}'", imageURL.getPath());

        final ImageIcon imgIcon = new ImageIcon(imageURL);

        final JButton button = new JButton();
        button.setActionCommand("BUSCAR");
        button.setToolTipText("Buscar");
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(final ActionEvent e)
            {
                ScrollPanel.LOG.debug("Search Button executing...");
                final SearchDataFrame intFrame = new SearchDataFrame(mappan);
                intFrame.setSize(new Dimension(800, 400));
                intFrame.setVisible(true);
            }
        });

        button.setIcon(imgIcon);
        ScrollPanel.LOG.debug("Adding image icon for Search Button, located in {}", imageURL.getPath());
        button.setFocusPainted(true);
        button.setBorder(BorderFactory.createEmptyBorder());

        return button;
    }
}
