import javax.swing.*;
import java.awt.*;

public class MainFrame {

    private JFrame frame;

    public MainFrame() {
        frame = new JFrame("The Only Place that hasn't been Corrupted by CAPITALISM!");
    }

    public void init(){
        this.frame.setSize(1000, 1000);
        this.frame.setLayout(new BorderLayout());
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);
        this.frame.setResizable(false);

        this.frame.setVisible(true);
    }
}
