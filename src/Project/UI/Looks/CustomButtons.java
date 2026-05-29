package Project.UI.Looks;

import javax.swing.*;
import java.awt.*;

public class CustomButtons {
    public static void changeStyleSmall(JButton button, Color color){
        button.setBackground(color);
        button.setForeground(Color.WHITE);

        button.setFont(new Font("calibri", Font.PLAIN, 14));

        button.setFocusPainted(false);
        button.setBorderPainted(false);

        button.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }

    public static void changeStyleSmall(JButton button){
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);

        button.setFont(new Font("calibri", Font.PLAIN, 14));

        button.setFocusPainted(false);
        button.setBorderPainted(false);

        button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }
}
