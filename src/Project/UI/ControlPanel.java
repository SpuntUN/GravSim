package Project.UI;

import Project.UI.TimeManagerPackage.TimeManager;
import Project.UI.TimeManagerPackage.TimeManagerPanel;

import javax.swing.*;
import java.awt.*;
import java.sql.Time;

public class ControlPanel {
    private JPanel panel;
    private TimeManagerPanel timeManagerPanel;
    private ShipPanel shipPanel;

    public ControlPanel(TimeManager timeManager){
        timeManagerPanel = new TimeManagerPanel(timeManager);
        shipPanel = new ShipPanel();
        init();
    }

    public void init() {

        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        panel.add(timeManagerPanel.getRoot(), BorderLayout.WEST);
        panel.add(shipPanel.getRoot(), BorderLayout.EAST);


        panel.setBackground(Color.WHITE);
    }

    public void updateComponents(){
        timeManagerPanel.updateLabels();
    }

    public JPanel getRoot(){
        return panel;
    }


}
