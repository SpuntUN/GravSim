import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SpacePanel extends JPanel{

    private Space space;
    private Transformer transformer;
    Vector pressedPos = new Vector(0, 0);

    public SpacePanel(Space space) {
        this.space = space;
        transformer = new Transformer(1e9);
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
            g2d.fillOval((int) (pos.getX()), (int) (pos.getY()), (int) rad*2, (int) rad*2);
        }
    }


    private void init(){
        this.setLayout(new BorderLayout());
        this.setBackground(Color.BLACK);

        this.addMouseWheelListener(e -> {

            int rotation = e.getWheelRotation();
            if (rotation < 0) {
                transformer.scaleUp();
            } else {
                    transformer.scaleDown();
            }
        });


        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                pressedPos = new Vector(e.getX(), e.getY());
                System.out.println(pressedPos);
            }
        });

        this.addMouseMotionListener(new MouseAdapter() {

            @Override
            public void mouseDragged(MouseEvent e) {
                transformer.addOffset(new Vector(e.getX(), e.getY()));
                transformer.subtractOffset(pressedPos);
                pressedPos = new Vector(e.getX(), e.getY());
            }
        });


    }

    private void mouse(){

    }

}
