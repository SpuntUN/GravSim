import javax.swing.*;
import java.awt.*;

public class SpacePanel extends JPanel{

    private Space space;
    private double scale = 1;

    public SpacePanel(Space space) {
        this.space = space;
        init();
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
            g2d.fillOval((int) (o.getPosition().getX()/scale), (int) (o.getPosition().getY()/scale), (int) (o.getRadius()/scale), (int) (o.getRadius()/scale));
            if (o.getName().equals("Sun")){
                System.out.println(o.getRadius());
                System.out.println(o.getRadius()/scale);
            }
        }
    }


    private void init(){
        this.setLayout(new BorderLayout());
        this.setBackground(Color.BLACK);

        this.addMouseWheelListener(e -> {
            int rotation = e.getWheelRotation();
            if (rotation < 0) {
                scale /= 1.1;
            } else {
               scale *= 1.1;
            }
        });
    }

}
