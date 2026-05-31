package Project.UI;

import Project.Math.Space;
import Project.Math.SpaceObject;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SpaceObjectPanel {

    private JPanel panel;
    private SpaceObject spaceObject;
    private Space space;
    private Font font;

    private JLabel positionValue;
    private JLabel speedValue;
    private JLabel velocityValue;
    private JLabel radiusValue;
    private JLabel distanceValue;

    private JTextField nameField;
    private JTextField massField;

    private JComboBox<SpaceObject> objectCombo;

    public SpaceObjectPanel(Space space) {
        this.space = space;
        init();
    }

    private void init() {
        panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);

        font = new Font("Arial", Font.PLAIN, 20);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4, 6, 4, 6);
        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.weighty = 0;

        int y = 0;

        // STATIC LABELS
        positionValue = createRow("Position:", y++, c);
        speedValue = createRow("Speed:", y++, c);
        velocityValue = createRow("Velocity:", y++, c);
        radiusValue = createRow("Radius:", y++, c);

        // NAME FIELD
        nameField = new JTextField();
        nameField.setFont(font);

        JButton setNameBtn = new JButton("Set");
        setNameBtn.setFont(font);

        setNameBtn.addActionListener(e -> {
            if (spaceObject != null) {
                spaceObject.setName(nameField.getText());
                refresh(); // Explicitly refresh stats after manual change
            }
        });

        addInputRow("Name:", nameField, setNameBtn, y++, c);

        // MASS FIELD
        massField = new JTextField();
        massField.setFont(font);

        JButton setMassBtn = new JButton("Set");
        setMassBtn.setFont(font);

        setMassBtn.addActionListener(e -> {
            if (spaceObject != null) {
                try {
                    spaceObject.setMass(Double.parseDouble(massField.getText()));
                    refresh(); // Explicitly refresh stats after manual change
                } catch (Exception ignored) {}
            }
        });

        addInputRow("Mass:", massField, setMassBtn, y++, c);

        // COMBO BOX
        objectCombo = new JComboBox<>();
        objectCombo.setFont(font);
        setComboObjects(space.getSpaceObjects());

        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.setFont(font);

        objectCombo.addActionListener(e -> updateDistance());

        addInputRow("Target:", objectCombo, refreshBtn, y++, c);

        // DISTANCE LABEL
        distanceValue = new JLabel("-");
        distanceValue.setFont(font);

        addLabelRow("Distance:", distanceValue, y++, c);

        // SPACER
        c.gridx = 0;
        c.gridy = y;
        c.gridwidth = 3;
        c.weighty = 1.0;
        panel.add(new JLabel(), c);
    }

    public JPanel getRoot() {
        return panel;
    }

    /**
     * Call this ONLY when switching to a completely different SpaceObject.
     */
    public void setSpaceObject(SpaceObject spaceObject) {
        this.spaceObject = spaceObject;

        if (spaceObject == null) {
            panel.setVisible(false);
            return;
        }

        panel.setVisible(true);

        // Unconditionally populate fields because a new object was selected
        nameField.setText(spaceObject.getName());
        massField.setText(String.valueOf(spaceObject.getMass()));

        // Call refresh to update the dynamic coordinate labels
        refresh();
    }

    /**
     * Call this repeatedly inside your simulation/render loop.
     * It updates dynamic values without touching or overwriting user input fields.
     */
    public void refresh() {
        if (spaceObject == null) return;

        positionValue.setText(spaceObject.getPositionString());
        speedValue.setText(spaceObject.getSpeedString());
        velocityValue.setText(spaceObject.getVelocityString());
        radiusValue.setText(spaceObject.getRadiusString());

        updateDistance();
    }

    public void setComboObjects(List<SpaceObject> objects) {
        objectCombo.removeAllItems();
        for (SpaceObject o : objects) {
            objectCombo.addItem(o);
        }
    }

    private void updateDistance() {
        if (spaceObject == null) return;

        SpaceObject target = (SpaceObject) objectCombo.getSelectedItem();
        if (target == null) {
            distanceValue.setText("-");
            return;
        }

        double dx = spaceObject.getPosition().getX() - target.getPosition().getX();
        double dy = spaceObject.getPosition().getY() - target.getPosition().getY();

        double dist = Math.sqrt(dx * dx + dy * dy);

        final double KM_THRESHOLD = 1e3;
        final double AU_THRESHOLD = 1.496e11;

        if (dist >= AU_THRESHOLD / 1000.0) {
            distanceValue.setText(String.format("%.2f AU", dist / AU_THRESHOLD));
        } else if (dist >= KM_THRESHOLD) {
            distanceValue.setText(String.format("%.2f KM", dist / KM_THRESHOLD));
        } else {
            distanceValue.setText(String.format("%.2f m", dist));
        }
    }

    private JLabel createRow(String label, int y, GridBagConstraints c) {
        JLabel l1 = new JLabel(label);
        l1.setFont(font);

        JLabel l2 = new JLabel("-");
        l2.setFont(font);

        c.gridy = y;
        c.gridx = 0;
        c.weightx = 0;
        panel.add(l1, c);

        c.gridx = 1;
        c.weightx = 1;
        panel.add(l2, c);

        return l2;
    }

    private void addInputRow(String label, JComponent input, JButton button, int y, GridBagConstraints c) {
        JLabel l1 = new JLabel(label);
        l1.setFont(font);

        c.gridy = y;
        c.gridx = 0;
        c.weightx = 0;
        panel.add(l1, c);

        c.gridx = 1;
        c.weightx = 1;
        panel.add(input, c);

        c.gridx = 2;
        c.weightx = 0;
        panel.add(button, c);
    }

    private void addLabelRow(String label, JLabel value, int y, GridBagConstraints c) {
        JLabel l1 = new JLabel(label);
        l1.setFont(font);

        value.setFont(font);

        c.gridy = y;
        c.gridx = 0;
        c.weightx = 0;
        panel.add(l1, c);

        c.gridx = 1;
        c.weightx = 1;
        panel.add(value, c);
    }
}