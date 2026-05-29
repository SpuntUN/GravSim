package Project.UI.TimeManagerPackage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimeManagerPanel {

    private JPanel panel;

    private JPanel infoPanel;
    private JPanel attributesPanel;

    private TimeManager timeManager;

    private Font font;

    private JLabel timeSinceStartLabel;
    private JLabel simulatedTimeLabel;

    private JTextField dtField;
    private JTextField speedField;

    private JButton dtSetButton;
    private JButton speedSetButton;

    public TimeManagerPanel(TimeManager timeManager) {
        this.timeManager = timeManager;
        init();
    }

    public void init() {
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);

        font = new Font("Arial", Font.PLAIN, 20);

        infoPanelInit();
        attributePanelInit();

        panel.add(infoPanel, BorderLayout.NORTH);
        panel.add(attributesPanel, BorderLayout.SOUTH);
    }

    public JPanel getRoot() {
        return panel;
    }

    public void updateLabels() {
        timeSinceStartLabel.setText("Time Since Start: " + timeManager.getTimeString(timeManager.getTimeSinceStart()));

        simulatedTimeLabel.setText("Simulation Time: " + timeManager.getTimeString(timeManager.getSimulatedTime()));
    }

    private void infoPanelInit() {
        infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(2, 1));
        infoPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        timeSinceStartLabel = new JLabel("Time Since Start: " + timeManager.getTimeString(timeManager.getTimeSinceStart()));

        simulatedTimeLabel = new JLabel("Simulation Time: " + timeManager.getTimeString(timeManager.getSimulatedTime()));

        timeSinceStartLabel.setFont(font);
        simulatedTimeLabel.setFont(font);

        infoPanel.add(timeSinceStartLabel);
        infoPanel.add(simulatedTimeLabel);
    }

    public void attributePanelInit() {
        attributesPanel = new JPanel();
        attributesPanel.setLayout(new GridLayout(2, 3));
        attributesPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel dtLabel = new JLabel("Delta Time:");
        JLabel speedLabel = new JLabel("Speed:");

        dtField = new JTextField(String.valueOf(timeManager.getDt()));
        speedField = new JTextField(String.valueOf(timeManager.getSpeed()));

        dtSetButton = new JButton("Set");
        speedSetButton = new JButton("Set");

        dtLabel.setFont(font);
        speedLabel.setFont(font);
        dtField.setFont(font);
        speedField.setFont(font);
        dtSetButton.setFont(font);
        speedSetButton.setFont(font);

        dtSetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double value = Double.parseDouble(dtField.getText());
                    timeManager.setDt(value);
                } catch (NumberFormatException ex) {
                    dtField.setText(String.valueOf(timeManager.getDt()));
                }
            }
        });

        speedSetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double value = Double.parseDouble(speedField.getText());
                    timeManager.setSpeed(value);
                } catch (NumberFormatException ex) {
                    speedField.setText(String.valueOf(timeManager.getSpeed()));
                }
            }
        });

        attributesPanel.add(dtLabel);
        attributesPanel.add(dtField);
        attributesPanel.add(dtSetButton);

        attributesPanel.add(speedLabel);
        attributesPanel.add(speedField);
        attributesPanel.add(speedSetButton);
    }
}