import javax.swing.*;
import java.awt.*;

public class SpaceObjectPanel {
    private JPanel panel;
    private SpaceObject spaceObject;
    private Font font;


    public SpaceObjectPanel(){
        init();
    }

    public void init(){
        panel = new JPanel();
        font = new Font("Arial", Font.PLAIN, 20);
        this.panel.setLayout(new GridBagLayout());



        this.panel.setBackground(Color.WHITE);

    }

    public JPanel getRoot(){
        return panel;
    }

    public void setSpaceObject(SpaceObject spaceObject) {
        this.spaceObject = spaceObject;

        this.panel.removeAll();

        if (spaceObject == null){
            this.panel.setVisible(false);
            return;
        }

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(2, 6, 2, 6);
        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill = GridBagConstraints.NONE;
        c.weightx = 1;
        c.weighty = 0;

        int y = 0;

        addRow("Name:", String.valueOf(spaceObject.getName()), y++, c);
        addRow("Massless:", String.valueOf(spaceObject.isMassless()), y++, c);
        addRow("Mass:", String.valueOf(spaceObject.getMass()), y++, c);
        addRow("Distance:", String.valueOf(spaceObject.getDistanceString()), y++, c);
        addRow("Position:", String.valueOf(spaceObject.getPositionString()), y++, c);
        addRow("Speed:", String.valueOf(spaceObject.getSpeedString()), y++, c);
        addRow("Velocity:", String.valueOf(spaceObject.getVelocityString()), y++, c);
        addRow("Force:", String.valueOf(spaceObject.getForce()), y++, c);
        addRow("Radius:", String.valueOf(spaceObject.getRadius()), y++, c);

        c.gridx = 0;
        c.gridy = y;
        c.gridwidth = 2;
        c.weighty = 1.0;
        panel.add(new JLabel(), c);

        this.panel.revalidate();
        this.panel.repaint();
        this.panel.setVisible(true);
    }

    private void addRow(String label, String value, int y, GridBagConstraints c) {

        JLabel l1 = new JLabel(label);
        l1.setFont(font);

        JLabel l2 = new JLabel(value);
        l2.setFont(font);

        // force wrapping / width control
        l2.setMaximumSize(new Dimension(300, Integer.MAX_VALUE));
        l2.setPreferredSize(new Dimension(300, l2.getPreferredSize().height));

        c.gridy = y;

        c.gridx = 0;
        c.weightx = 0;
        panel.add(l1, c);

        c.gridx = 1;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(l2, c);
    }

    public void refresh(){
        setSpaceObject(spaceObject);
    }

}
