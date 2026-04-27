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

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        paintSpaceObjects(g2d);
    }

    private void paintSpaceObjects(Graphics2D g2d){
        for (SpaceObject o : space.getSpaceObjects()){
            g2d.setColor(o.getColor());
            g2d.fillOval((int) o.getPosition().getX(), (int) o.getPosition().getX(), (int) o.getRadius(), (int) o.getRadius());
        }
    }
}
