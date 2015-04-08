package org.jcs.esjp.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.util.Set;

import javax.swing.GrayFilter;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import org.jcs.esjp.model.Sector;

public class SectorPanel
    extends JPanel implements MouseInputListener
{
    private Image bgImage;
    private final Sector sector;
    private final Set<Object> setFindObjects;

    public SectorPanel(final Sector _sector,
                       final Boolean _isDisabled,
                       final Set<Object> _setObjects) {
        super();
        sector = _sector;
        setFindObjects = _setObjects;
        setOpaque(false);

        setSize(85, 85);
        setLayout(null);
        if (_sector != null && _sector.getName() != null) {
            final Image image = _sector.getRace().getImage();
            if (_isDisabled) {
                setBackgroundImage(GrayFilter.createDisabledImage(image));
            } else {
                setBackgroundImage(image);
            }

            final Font font = new Font("Tahoma", Font.BOLD, 8);

            final JLabel txtLb = new JLabel("<html>" + _sector.getName() + "</html>");
            final Insets insets = getInsets();
            final Dimension size = txtLb.getPreferredSize();
            txtLb.setBounds(5 + insets.left, 45 + insets.top, 80, size.height * 2);
            txtLb.setOpaque(false);
            txtLb.setForeground(new Color(255, 255, 255));
            txtLb.setFont(font);

            addMouseListener(this);
            add(txtLb);
            //setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        }
    }

    public Sector getSector()
    {
        return this.sector;
    }

    public void setBackgroundImage(final Image bgImage) {
        this.bgImage = bgImage;
       }

    @Override
    public void paint(final Graphics g)
    {
        // Pintamos la imagen de fondo...
        if (bgImage != null) {
            g.drawImage(bgImage, 0, 0, null);
        }
        // Y pintamos el resto de cosas que pueda tener el panel
        super.paint(g);
    }

    @Override
    public void mouseClicked(final MouseEvent e)
    {
        if (e.getClickCount() == 2) {
            final SectorDataFrame intFrame = new SectorDataFrame((SectorPanel) e.getSource());
            intFrame.setSize(new Dimension(800, 950));
            intFrame.setVisible(true);
        }
    }

    @Override
    public void mousePressed(final MouseEvent e)
    {
    }

    @Override
    public void mouseReleased(final MouseEvent e)
    {
    }

    @Override
    public void mouseEntered(final MouseEvent e)
    {
    }

    @Override
    public void mouseExited(final MouseEvent e)
    {
    }

    @Override
    public void mouseDragged(final MouseEvent e)
    {
    }

    @Override
    public void mouseMoved(final MouseEvent e)
    {
    }

    public Image getBgImage()
    {
        return bgImage;
    }

    public Set<Object> getSetFindObjects()
    {
        return setFindObjects;
    }

}
