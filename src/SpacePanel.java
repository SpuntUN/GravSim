import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SpacePanel extends JPanel{
    private Space space;
    private Transformer transformer;
    Vector pressedPos;

    public SpacePanel(Space space) {
        this.space = space;
        transformer = new Transformer(1e9);
        pressedPos = new Vector();
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

            SpaceObject transformedObject = transformer.TransformNewSpaceObject(o);
            Vector pos = transformedObject.getPosition();
            double rad = transformedObject.getRadius();

            g2d.setColor(o.getColor());
            g2d.fillOval((int)(pos.getX() - rad), (int)(pos.getY() - rad), (int)(rad * 2), (int)(rad * 2));
        }
    }


    private void init(){
        this.setLayout(new BorderLayout());
        this.setBackground(Color.BLACK);

        this.addMouseWheelListener(e -> {

            int rotation = e.getWheelRotation();

            transformer.zoomAt(new Vector(e.getX(), e.getY()), rotation < 0);
            repaint();
        });


        this.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                Vector mousePos = new Vector(e.getX(), e.getY());
                pressedPos = mousePos;
            }
        });

        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Vector mousePos = new Vector(e.getX(), e.getY());
                transformer.addOffset(mousePos);
                transformer.subtractOffset(pressedPos);
                pressedPos = mousePos;
            }
        });


    }

}
