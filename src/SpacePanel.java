import javax.swing.*;
import java.awt.*;

public class SpacePanel {

    private JPanel panel;
    private Space space;

    public SpacePanel(Space space) {
        panel = new JPanel(new BorderLayout());
        this.space = space;
    }


    public JPanel getRoot() {
        return panel;
    }
}
