package Project.UI;

import Project.UI.TimeManagerPackage.TimeManagerPanel;
import Project.Math.SpaceCraft;

import javax.swing.*;
import java.awt.*;

public class ControlPanel {
    private JPanel panel;
    private TimeManagerPanel timeManagerPanel;
    private InstructionPanelManager instructionPanelManager;

    public ControlPanel(TimeManagerPanel timeManagerPanel, SpaceCraft craft) {

        this.timeManagerPanel = timeManagerPanel;
        this.instructionPanelManager = new InstructionPanelManager(craft);

        init();
    }

    public void init() {
        panel = new JPanel(new BorderLayout());

        panel.add(timeManagerPanel.getRoot(), BorderLayout.WEST);
        panel.add(instructionPanelManager.getRoot(), BorderLayout.CENTER);

        panel.setBackground(Color.WHITE);
    }

    public void updateComponents() {
        timeManagerPanel.updateLabels();
        instructionPanelManager.refreshUI();
    }

    public JPanel getRoot() {
        return panel;
    }
}