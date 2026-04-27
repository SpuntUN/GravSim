import javax.swing.*;
import java.awt.*;

public class SpacePanel extends JPanel{

    private Space space;

    public SpacePanel(Space space) {
        this.space = space;
        this.setLayout(new BorderLayout());
        this.setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
