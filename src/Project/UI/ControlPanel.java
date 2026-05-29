package Project.UI;

import Project.UI.TimeManagerPackage.TimeManager;
import Project.UI.TimeManagerPackage.TimeManagerPanel;

import javax.swing.*;
import java.awt.*;
import java.sql.Time;

public class ControlPanel {
    JPanel panel;
    TimeManagerPanel timeManagerPanel;

    public ControlPanel(TimeManagerPanel timeManagerPanel){
        this.timeManagerPanel = timeManagerPanel;
        init();
    }

    public void init() {

        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        panel.add(timeManagerPanel.getRoot(), BorderLayout.WEST);


        panel.setBackground(Color.WHITE);
    }

    public JPanel getRoot(){
        return panel;
    }


}
