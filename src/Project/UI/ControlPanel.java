package Project.UI;

import Project.UI.TimeManagerPackage.TimeManagerPanel;
import Project.Math.SpaceCraft;

import javax.swing.*;
import java.awt.*;

public class ControlPanel {
    private JPanel panel;
    private TimeManagerPanel timeManagerPanel;
    private InstructionPanelManager instructionPanelManager;
    private SpaceCraft spaceCraft;

    private JLabel fuelLabel;
    private JLabel thrustLabel;

    public ControlPanel(TimeManagerPanel timeManagerPanel, SpaceCraft craft) {
        this.timeManagerPanel = timeManagerPanel;
        this.instructionPanelManager = new InstructionPanelManager(craft);
        this.spaceCraft = craft;

        init();
    }

    public void init() {
        panel = new JPanel(new BorderLayout());

        panel.add(timeManagerPanel.getRoot(), BorderLayout.WEST);
        panel.add(instructionPanelManager.getRoot(), BorderLayout.CENTER);

        JPanel telemetryPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        telemetryPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1), "Craft Status"));
        telemetryPanel.setPreferredSize(new Dimension(260, 0));
        telemetryPanel.setBackground(Color.WHITE);

        fuelLabel = new JLabel("Fuel: 0.00 L / 0.00 L");
        thrustLabel = new JLabel("Max Thrust: 0.00 N");

        Font displayFont = new Font("Arial", Font.BOLD, 16);
        fuelLabel.setFont(displayFont);
        thrustLabel.setFont(displayFont);

        telemetryPanel.add(fuelLabel);
        telemetryPanel.add(thrustLabel);

        panel.add(telemetryPanel, BorderLayout.EAST);
        panel.setBackground(Color.WHITE);
    }

    public void updateComponents() {
        timeManagerPanel.updateLabels();
        instructionPanelManager.refreshUI();

        if (spaceCraft != null) {
            fuelLabel.setText("Fuel: " + spaceCraft.getFuelString() + " / " + spaceCraft.getMaxFuelString());
            thrustLabel.setText("Max Thrust: " + spaceCraft.getMaxThrustString());

            if (spaceCraft.outOfFuel()) {
                fuelLabel.setForeground(Color.RED);
                fuelLabel.setText("OUT OF FUEL");
            } else {
                fuelLabel.setForeground(Color.BLACK);
            }
        }
    }

    public JPanel getRoot() {
        return panel;
    }
}