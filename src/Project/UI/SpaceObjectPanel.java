package Project.UI;

import Project.Math.Space;
import Project.Math.SpaceObject;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Shows information about a selected SpaceObject.
 * Selected objects attributes can be changed.
 */

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
    private JLabel relativeName;

    private JTextField nameField;
    private JTextField massField;

    private JComboBox<SpaceObject> objectCombo;
    private JComboBox<SpaceObject> relativeCombo;

    public SpaceObjectPanel(Space space) {
        this.space = space;
        init();
    }


    private void init() {
        panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);

        Dimension lockedSize = new Dimension(400, 600);
        panel.setPreferredSize(lockedSize);
        panel.setMinimumSize(lockedSize);
        panel.setMaximumSize(lockedSize);

        font = new Font("Arial", Font.PLAIN, 20);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4, 6, 4, 6);
        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.weighty = 0;

        int y = 0;

        positionValue = createRow("Position:", y++, c);
        speedValue = createRow("Speed:", y++, c);
        velocityValue = createRow("Velocity:", y++, c);
        radiusValue = createRow("Radius:", y++, c);

        nameField = new JTextField();
        nameField.setFont(font);

        JButton setNameBtn = new JButton("Set");
        setNameBtn.setFont(font);

        setNameBtn.addActionListener(e -> {
            if (spaceObject != null) {
                spaceObject.setName(nameField.getText());
                refresh();
            }
        });

        addInputRow("Name:", nameField, setNameBtn, y++, c);

        massField = new JTextField();
        massField.setFont(font);

        JButton setMassBtn = new JButton("Set");
        setMassBtn.setFont(font);

        setMassBtn.addActionListener(e -> {
            if (spaceObject != null) {
                try {
                    spaceObject.setMass(Double.parseDouble(massField.getText()));
                    refresh();
                } catch (Exception ignored) {}
            }
        });

        addInputRow("Mass:", massField, setMassBtn, y++, c);

        objectCombo = new JComboBox<>();
        objectCombo.setFont(font);
        setComboObjects(space.getSpaceObjects());

        objectCombo.addActionListener(e -> updateDistance());

        addInputRow("Distance To:", objectCombo, null, y++, c);

        distanceValue = new JLabel("-");
        distanceValue.setFont(font);

        addLabelRow("Distance:", distanceValue, y++, c);

        relativeCombo = new JComboBox<>();
        relativeCombo.setFont(font);
        setRelativeComboObjects(space.getSpaceObjects());

        relativeCombo.addActionListener(e -> updateRelative());

        addInputRow("Orbit Relative To:", relativeCombo, null, y++, c);

        relativeName = new JLabel("-");
        relativeName.setFont(font);

        addLabelRow("Relative To:", relativeName, y++, c);

        c.gridx = 0;
        c.gridy = y;
        c.gridwidth = 3;
        c.weighty = 1.0;
        panel.add(new JLabel(), c);
    }

    public JPanel getRoot() {
        return panel;
    }

    public void setSpaceObject(SpaceObject spaceObject) {
        this.spaceObject = spaceObject;

        if (spaceObject == null) {
            panel.setVisible(false);
            return;
        }

        panel.setVisible(true);

        nameField.setText(spaceObject.getName());
        massField.setText(String.valueOf(spaceObject.getMass()));

        if (spaceObject.getOrbit() != null && spaceObject.getOrbit().getRelativeTo() != null) {
            relativeCombo.setSelectedItem(spaceObject.getOrbit().getRelativeTo());
        } else {
            relativeCombo.setSelectedIndex(-1);
        }

        if (objectCombo.getSelectedItem() == spaceObject) {
            objectCombo.setSelectedIndex(-1);
        }

        refresh();
    }

    public void refresh() {
        if (spaceObject == null) return;

        positionValue.setText(spaceObject.getPositionString());
        speedValue.setText(spaceObject.getSpeedString());
        velocityValue.setText(spaceObject.getVelocityString());
        radiusValue.setText(spaceObject.getRadiusString());

        updateDistance();
        updateRelative();
    }

    public void setComboObjects(List<SpaceObject> objects) {
        objectCombo.removeAllItems();
        for (SpaceObject o : objects) {
            objectCombo.addItem(o);
        }
    }

    public void setRelativeComboObjects(List<SpaceObject> objects) {
        relativeCombo.removeAllItems();
        for (SpaceObject o : objects) {
            relativeCombo.addItem(o);
        }
    }

    private void updateDistance() {
        if (spaceObject == null) return;

        SpaceObject target = (SpaceObject) objectCombo.getSelectedItem();
        if (target == null || target == spaceObject) {
            distanceValue.setText("-");
            return;
        }

        double dx = spaceObject.getPosition().getX() - target.getPosition().getX();
        double dy = spaceObject.getPosition().getY() - target.getPosition().getY();

        double dist = Math.sqrt(dx * dx + dy * dy);

        final double KM_THRESHOLD = 1e3;
        final double AU_THRESHOLD = 1.496e11;

        if (dist >= AU_THRESHOLD / 100.0) {
            distanceValue.setText(String.format("%.2f AU", dist / AU_THRESHOLD));
        } else if (dist >= KM_THRESHOLD) {
            distanceValue.setText(String.format("%.2f KM", dist / KM_THRESHOLD));
        } else {
            distanceValue.setText(String.format("%.2f m", dist));
        }
    }

    private void updateRelative() {
        if (spaceObject == null) return;

        SpaceObject target = (SpaceObject) relativeCombo.getSelectedItem();
        if (target == null) {
            relativeName.setText("-");
            return;
        }

        if (target == spaceObject) {
            relativeCombo.setSelectedIndex(-1);
            return;
        }

        if (spaceObject.getOrbit() == null) return;

        if (spaceObject.getOrbit().getRelativeTo() == target) {
            relativeName.setText(target.getName());
            return;
        }

        spaceObject.getOrbit().getPositions().clear();
        spaceObject.getOrbit().setRelativeTo(target);
        relativeName.setText(target.getName());
    }


    /**
     * Creates and adds a text row with a label and a default "-" value display.
     * @return The dynamic value label.
     */
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

    /**
     * Adds an interactive input row with an optional action button.
     */
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

        if (button != null) {
            c.gridx = 2;
            c.weightx = 0;
            panel.add(button, c);
        }
    }

    /**
     * Adds a display row using a pre-existing value label.
     */
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