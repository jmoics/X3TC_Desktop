package org.jcs.esjp.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;

import org.jcs.esjp.model.ObjectAbstract;
import org.jcs.esjp.model.ObjectPurchase;
import org.jcs.esjp.model.ObjectSale;
import org.jcs.esjp.model.Sector;
import org.jcs.esjp.model.StructureAbstract;
import org.jcs.esjp.model.StructureFactory;
import org.jcs.esjp.model.StructureFreeShip;
import org.jcs.esjp.model.StructureNormal;
import org.jcs.esjp.model.StructureOther;
import org.jcs.esjp.util.Settings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SectorDataFrame
    extends JFrame
    implements ActionListener, TreeSelectionListener
{
    private static final Logger LOG = LoggerFactory.getLogger(SectorDataFrame.class);

    final JPanel dataView;
    JTree tree;
    final Set<Object> setObjects;

    public SectorDataFrame(final SectorPanel _sectorPanel)
    {
        super(_sectorPanel.getSector().getName());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setObjects = _sectorPanel.getSetFindObjects();
        if (setObjects != null) {
            evaluateSetObjects();
        }

        // Place the button near the bottom of the window.
        final Container contentPane = getContentPane();

        dataView = new JPanel();
        dataView.setBackground(new Color(53, 66, 90));
        dataView.setLayout(new BorderLayout());
        //initHelp();

        final JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setLeftComponent(createDataPane(_sectorPanel.getSector()));
        splitPane.setRightComponent(dataView);

        contentPane.add(splitPane, BorderLayout.CENTER);
        contentPane.add(createButtonPane(), BorderLayout.PAGE_END);
    }

    @Override
    public void actionPerformed(final ActionEvent e)
    {
        setVisible(false);
        dispose();
    }

    protected JComponent createDataPane(final Sector _sector)
    {
        final DefaultMutableTreeNode top = new DefaultMutableTreeNode(_sector.getName());

        for (final StructureAbstract struct : _sector.getLstStruct()) {
            if (struct instanceof StructureNormal) {
                final DefaultMutableTreeNode structure = new DefaultMutableTreeNode(struct);
                top.add(structure);
                for (final ObjectSale object : ((StructureNormal) struct).getObjSale()) {
                    final DefaultMutableTreeNode obj = new DefaultMutableTreeNode(object);
                    structure.add(obj);
                }
            } else if (struct instanceof StructureFactory) {
                final DefaultMutableTreeNode structure = new DefaultMutableTreeNode(struct);
                top.add(structure);
                for (final ObjectSale object : ((StructureFactory) struct).getObjSale()) {
                    final DefaultMutableTreeNode obj = new DefaultMutableTreeNode(object);
                    structure.add(obj);
                    // cell.setImage("images/sale.png");
                }
                for (final ObjectPurchase object : ((StructureFactory) struct).getObjPurch()) {
                    final DefaultMutableTreeNode obj = new DefaultMutableTreeNode(object);
                    structure.add(obj);
                    // cell.setImage("images/purchase.png");
                }
            } else if (struct instanceof StructureOther) {

            } else if (struct instanceof StructureFreeShip) {
                final DefaultMutableTreeNode structure = new DefaultMutableTreeNode(struct);
                top.add(structure);
            }
        }

        tree = new JTree(top);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.setBackground(new Color(53, 66, 90));
        tree.setCellRenderer(new MyCellRenderer());
        //Listen for when the selection changes.
        tree.addTreeSelectionListener(this);

        final JScrollPane treeView = new JScrollPane(tree);
        final Dimension minimumSize = new Dimension(350, 700);
        treeView.setMinimumSize(minimumSize);
        treeView.setBackground(new Color(53, 66, 90));
        return treeView;
    }

    // Create the button that goes in the main window.
    protected JComponent createButtonPane()
    {
        final JButton button = new JButton("Cerrar Ventana");
        final Dimension size = button.getPreferredSize();
        button.setSize(size.width - 100, size.height - 100);
        button.addActionListener(this);

        // Center the button in a panel with some space around it.
        final JPanel pane = new JPanel(); // use default FlowLayout
        pane.setBackground(new Color(53, 66, 90));
        pane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        pane.add(button);

        return pane;
    }

    protected void evaluateSetObjects() {
        final Set<Object> toAdd = new HashSet<Object>();
        for (final Object object : setObjects) {
            if (object instanceof StructureAbstract) {
                toAdd.add(((StructureAbstract) object).getParent());
            } else if (object instanceof ObjectAbstract) {
                toAdd.add(((ObjectAbstract) object).getParent());
            }
        }

        for (final Object add : toAdd) {
            setObjects.add(add);
        }
    }

    public class MyCellRenderer
        extends DefaultTreeCellRenderer
    {

        @Override
        public Color getBackgroundNonSelectionColor()
        {
            return new Color(53, 66, 90);
        }

        @Override
        public Color getBackgroundSelectionColor()
        {
            return new Color(53, 66, 90);
        }

        @Override
        public Color getBackground()
        {
            return new Color(53, 66, 90);
        }

        @Override
        public Color getTextSelectionColor()
        {
            return Color.WHITE;
        }

        @Override
        public Color getTextNonSelectionColor()
        {
            return Color.WHITE;
        }

        @Override
        public Font getFont()
        {
            return new Font("Tahoma", Font.PLAIN, 10);
        }

        @Override
        public Component getTreeCellRendererComponent(final JTree tree,
                                                      final Object value,
                                                      final boolean sel,
                                                      final boolean expanded,
                                                      final boolean leaf,
                                                      final int row,
                                                      final boolean hasFocus)
        {
            super.getTreeCellRendererComponent(
                            tree, value, sel,
                            expanded, leaf, row,
                            hasFocus);
            final DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
            if (node.getUserObject() instanceof ObjectAbstract) {
                final ObjectAbstract obj = (ObjectAbstract) node.getUserObject();
                if (setObjects != null && setObjects.contains(obj)) {
                    setForeground(Color.YELLOW);
                    final DefaultMutableTreeNode nodeAux = (DefaultMutableTreeNode) node.getParent();
                    /*while (nodeAux != null) {
                        nodeAux.getUserObject()
                        nodeAux = nodeAux.getParent();
                    }*/
                }
                if (obj.getIconPath() != null) {
                    SectorDataFrame.LOG.debug("Path for '{}' is '{}'", obj.getName(), obj.getIconPath());
                    final URL imageURL = this.getClass().getClassLoader().getResource(obj.getIconPath());
                    setIcon(new ImageIcon(imageURL));
                } else {
                    SectorDataFrame.LOG.error("Icon for the ObjectAbstract '{}' was not found.", obj.getName());
                }
            } else if (node.getUserObject() instanceof StructureAbstract) {
                final StructureAbstract str = (StructureAbstract) node.getUserObject();
                if (setObjects!= null && setObjects.contains(str)) {
                    setForeground(Color.YELLOW);
                }
                if (str.getIconPath() != null) {
                    SectorDataFrame.LOG.debug("Path for '{}' is '{}'", str.getName(), str.getIconPath());
                    final URL imageURL = this.getClass().getClassLoader().getResource(str.getIconPath());
                    setIcon(new ImageIcon(imageURL));
                } else {
                    SectorDataFrame.LOG.error("Icon for the StructureAbstract '{}' was not found.", str.getName());
                }
            }

            return this;
        }
    }

    @Override
    public void valueChanged(final TreeSelectionEvent e)
    {
        final DecimalFormat formatter = new DecimalFormat();
        formatter.setMaximumFractionDigits(0);
        formatter.setParseBigDecimal(true);

        Properties prop = new Properties();
        prop = Settings.getProperties();
        final Object object = ((DefaultMutableTreeNode) ((JTree) e.getSource())
                        .getLastSelectedPathComponent()).getUserObject();
        if (object instanceof ObjectAbstract) {
            final JPanel northView = new JPanel();
            northView.setBackground(new Color(53, 66, 90));
            northView.setLayout(new GridLayout(4, 2));
            northView.removeAll();
            final Font font = new Font("Tahoma", Font.PLAIN, 10);
            JLabel label = new JLabel(prop.getProperty("org.jcs.esjp.ui.Price"));
            label.setFont(font);
            label.setForeground(new Color(255, 255, 255));
            northView.add(label);

            label = new JLabel(formatter.format(((ObjectAbstract) object).getPrice()));
            label.setFont(font);
            label.setForeground(new Color(255, 255, 255));
            northView.add(label);

            label = new JLabel(prop.getProperty("org.jcs.esjp.ui.Quantity"));
            label.setFont(font);
            label.setForeground(new Color(255, 255, 255));
            northView.add(label);

            label = new JLabel(formatter.format(((ObjectAbstract) object).getQuantity()));
            label.setFont(font);
            label.setForeground(new Color(255, 255, 255));
            northView.add(label);

            label = new JLabel(prop.getProperty("org.jcs.esjp.ui.FreeSpace"));
            label.setFont(font);
            label.setForeground(new Color(255, 255, 255));
            northView.add(label);

            label = new JLabel(formatter.format(((ObjectAbstract) object).getFreeSpace()));
            label.setFont(font);
            label.setForeground(new Color(255, 255, 255));
            northView.add(label);


            dataView.removeAll();
            dataView.add(northView, BorderLayout.NORTH);
            dataView.revalidate();
        } else if (object instanceof StructureAbstract) {
            final JPanel northView = new JPanel();
            northView.setBackground(new Color(53, 66, 90));
            northView.setLayout(new GridLayout(4, 2));
            northView.removeAll();
            final Font font = new Font("Tahoma", Font.PLAIN, 10);
            JLabel label = new JLabel(prop.getProperty("org.jcs.esjp.ui.Position"));
            label.setFont(font);
            label.setForeground(new Color(255, 255, 255));
            northView.add(label);

            final StringBuilder strBldr = new StringBuilder();
            strBldr.append("X=").append(formatter.format(((StructureAbstract) object).getPosX())).append("  ")
                    .append("Y=").append(formatter.format(((StructureAbstract) object).getPosY())).append("  ")
                    .append("Z=").append(formatter.format(((StructureAbstract) object).getPosZ()));

            label = new JLabel(strBldr.toString());
            label.setFont(font);
            label.setForeground(new Color(255, 255, 255));
            northView.add(label);

            dataView.removeAll();
            dataView.add(northView, BorderLayout.NORTH);
            dataView.revalidate();
        }
    }
}
