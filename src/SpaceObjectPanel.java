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
        font = new Font("Arial", Font.PLAIN, 16);
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
        addRow("Position:", String.valueOf(spaceObject.getPosition()), y++, c);
        addRow("Speed:", String.valueOf(Vector.normalize(spaceObject.getVelocity())), y++, c);
        addRow("Velocity:", String.valueOf(spaceObject.getVelocity()), y++, c);
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

        c.gridy = y;

        c.gridx = 0;

        panel.add(l1, c);

        c.gridx = 1;
        panel.add(l2, c);
    }

    public void refresh(){
        setSpaceObject(spaceObject);
    }

}
