package Project.UI.TimeManagerPackage;

import javax.swing.*;
import java.awt.*;

public class TimeManagerPanel {
    JPanel panel;
    Font font;

    public void init(){
        panel = new JPanel();
        font = new Font("Arial", Font.PLAIN, 20);
        this.panel.setLayout(new GridBagLayout());



        this.panel.setBackground(Color.WHITE);

    }
}
