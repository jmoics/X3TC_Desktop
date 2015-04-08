package org.jcs.esjp.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JPanel;
import javax.swing.Scrollable;
import javax.swing.SwingConstants;

import org.jcs.esjp.model.Sector;
import org.jcs.esjp.ui.SearchDataFrame.ObjectPosition;

public class MapPanel
    extends JPanel
    implements Scrollable,
    MouseMotionListener,
    MouseListener
{
    private int x;
    private int y;
    private int maxX;
    private int maxY;
    private final int maxUnitIncrement = 30;
    private Map<Integer, Map<Integer, Sector>> matrix;

    public MapPanel()
    {
        super();
        setBackground(new Color(53, 66, 90));
        // Let the user scroll by dragging to outside the window.
        setAutoscrolls(true); // enable synthetic drag events
        //addMouseMotionListener(this);
        //addMouseListener(this);
        buildSectors();
    }

    protected void buildSectors()
    {
        buildMatrix();

        final GridLayout layout = new GridLayout(maxX, maxY, 10, 10);
        setLayout(layout);

        for (int i = 0; i < maxX; i++) {
            for (int j = 0; j < maxY; j++) {
                if (matrix.containsKey(i)) {
                    if (matrix.get(i).containsKey(j)) {
                        final SectorPanel secPan = new SectorPanel(matrix.get(i).get(j), false, null);
                        add(secPan);
                    } else {
                        final SectorPanel secPan = new SectorPanel(null, false, null);
                        add(secPan);
                    }
                } else {
                    final SectorPanel secPan = new SectorPanel(null, false, null);
                    add(secPan);
                }
            }
        }
    }

    protected void buildMatrix() {
        final Structure structure = new Structure();
        try {
            matrix = structure.init();
        } catch (final IOException e) {
            e.printStackTrace();
        }

        maxX = structure.getMaxX() + 1;
        maxY = structure.getMaxY() + 1;
    }

    protected void updateGalaxyMap(final ObjectPosition _objPos) {
        removeAll();
        final Map<Object, Integer> obj2pos = _objPos != null
                        ? _objPos.getObject2Position() : new HashMap<Object, Integer>();
        if (_objPos == null) {
            buildMatrix();
        }

        final Map<Integer, Set<Object>> position2Objects = new TreeMap<Integer, Set<Object>>();
        for (final Entry<Object, Integer> entry : obj2pos.entrySet()) {
            final Object object = entry.getKey();
            final Integer position = entry.getValue();
            if (position2Objects.containsKey(position)) {
                final Set<Object> list = position2Objects.get(position);
                list.add(object);
            } else {
                final Set<Object> list = new HashSet<Object>();
                list.add(object);
                position2Objects.put(position, list);
            }
        }

        for (int i = 0; i < maxX; i++) {
            for (int j = 0; j < maxY; j++) {
                final Integer curPos = i * getMaxY() + j;
                if (matrix.containsKey(i)) {
                    if (matrix.get(i).containsKey(j)) {
                        final SectorPanel secPan = new SectorPanel(matrix.get(i).get(j),
                                        _objPos != null ? !position2Objects.containsKey(curPos) : false,
                                        _objPos != null ? (position2Objects.containsKey(curPos)
                                                        ? position2Objects.get(curPos) : null) : null);
                        add(secPan);
                    } else {
                        final SectorPanel secPan = new SectorPanel(null, false, null);
                        add(secPan);
                    }
                } else {
                    final SectorPanel secPan = new SectorPanel(null, false, null);
                    add(secPan);
                }
            }
        }
        repaint();
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(maxX * 120, maxY * 85);
    }

    @Override
    public Dimension getPreferredScrollableViewportSize()
    {
        return null;
    }

    @Override
    public int getScrollableUnitIncrement(final Rectangle visibleRect,
                                          final int orientation,
                                          final int direction)
    {
        // Get the current position.
        int currentPosition = 0;
        if (orientation == SwingConstants.HORIZONTAL) {
            currentPosition = visibleRect.x;
        } else {
            currentPosition = visibleRect.y;
        }

        // Return the number of pixels between currentPosition
        // and the nearest tick mark in the indicated direction.
        if (direction < 0) {
            final int newPosition = currentPosition -
                            (currentPosition / maxUnitIncrement)
                            * maxUnitIncrement;
            return (newPosition == 0) ? maxUnitIncrement : newPosition;
        } else {
            return ((currentPosition / maxUnitIncrement) + 1)
                            * maxUnitIncrement
                            - currentPosition;
        }
    }

    @Override
    public int getScrollableBlockIncrement(final Rectangle visibleRect,
                                           final int orientation,
                                           final int direction)
    {
        if (orientation == SwingConstants.HORIZONTAL) {
            return visibleRect.width - maxUnitIncrement;
        } else {
            return visibleRect.height - maxUnitIncrement;
        }
    }

    @Override
    public boolean getScrollableTracksViewportWidth()
    {
        return false;
    }

    @Override
    public boolean getScrollableTracksViewportHeight()
    {
        return false;
    }

    @Override
    public void mouseDragged(final MouseEvent e)
    {
        /*e.translatePoint(e.getComponent().getLocation().x - x, e.getComponent().getLocation().y - y);
        setLocation(e.getX(), e.getY());*/
    }

    @Override
    public void mousePressed(final MouseEvent e)
    {
        /*x = e.getX();
        y = e.getY();*/
    }

    @Override
    public void mouseMoved(final MouseEvent e)
    {
    }

    @Override
    public void mouseClicked(final MouseEvent e)
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

    protected Map<Integer, Map<Integer, Sector>> getMatrix() {
        return this.matrix;
    }

    public int getMaxX()
    {
        return this.maxX;
    }

    public int getMaxY()
    {
        return this.maxY;
    }

}
