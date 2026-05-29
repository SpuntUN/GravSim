package Project.UI;

import javax.swing.*;
import java.awt.*;

public class ShipPanel {
    JPanel panel;

    public ShipPanel(){
        init();
    }

    private void init(){
        this.panel = new JPanel();


        this.panel.setBackground(Color.WHITE);
    }

    public JPanel getRoot(){
        return panel;
    }
}
