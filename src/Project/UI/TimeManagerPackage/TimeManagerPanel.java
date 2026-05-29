package Project.UI.TimeManagerPackage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TimeManagerPanel {

    JPanel panel;

    JPanel infoPanel;
    JPanel attributesPanel;

    TimeManager timeManager;

    Font font;

    JLabel timeSinceStartLabel;
    JLabel simulatedTimeLabel;

    JLabel dtLabel;
    JLabel speedLabel;

    public TimeManagerPanel(TimeManager timeManager) {
        this.timeManager = timeManager;
        init();
    }

    public void init() {
        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        font = new Font("Arial", Font.PLAIN, 20);

        panel.setBackground(Color.WHITE);

        // TOP PANEL

        infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(2, 1));
        infoPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        timeSinceStartLabel = new JLabel("Time Since Start: " + timeManager.getTimeSinceStart());

        simulatedTimeLabel = new JLabel("Simulation Time: " + timeManager.getSimulatedTime());

        timeSinceStartLabel.setFont(font);
        simulatedTimeLabel.setFont(font);

        infoPanel.add(timeSinceStartLabel);
        infoPanel.add(simulatedTimeLabel);

        //---------------------------------
        // BOTTOM PANEL
        //---------------------------------

        attributesPanel = new JPanel();
        attributesPanel.setLayout(new GridLayout(2, 1));
        attributesPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        dtLabel = new JLabel("Delta Time: " + timeManager.getDt());

        speedLabel = new JLabel("Simulation Speed: " + timeManager.getSpeed());

        dtLabel.setFont(font);
        speedLabel.setFont(font);

        attributesPanel.add(dtLabel);
        attributesPanel.add(speedLabel);

        //---------------------------------
        // ADD TO MAIN PANEL
        //---------------------------------

        panel.add(infoPanel, BorderLayout.NORTH);
        panel.add(attributesPanel, BorderLayout.SOUTH);
    }

    public JPanel getRoot() {
        return panel;
    }

    public void updateLabels() {
        timeSinceStartLabel.setText("Time Since Start: " + timeManager.getTimeSinceStart());

        simulatedTimeLabel.setText("Simulation Time: " + timeManager.getSimulatedTime());

        dtLabel.setText("Delta Time: " + timeManager.getDt());

        speedLabel.setText("Simulation Speed: " + timeManager.getSpeed());
    }
}