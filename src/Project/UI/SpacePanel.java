package Project.UI;

import Project.Math.Space;
import Project.Math.SpaceObject;
import Project.Math.Vector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SpacePanel extends JPanel{
    private Space space;
    private SpaceObjectPanel spaceObjectPanel;
    private Transformer transformer;
    Vector pressedPos;

    public SpacePanel(Space space, SpaceObjectPanel spaceObjectPanel) {
        this.space = space;
        this.spaceObjectPanel = spaceObjectPanel;
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
                if (spaceObjectPressed(mousePos) != null){
                    spaceObjectPanel.setSpaceObject(spaceObjectPressed(mousePos));
                }
                pressedPos = mousePos;
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    spaceObjectPanel.setSpaceObject(null);
                }
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

    private SpaceObject spaceObjectPressed(Vector mousePos){
        for (SpaceObject o : space.getSpaceObjects()){
            SpaceObject transformedObject = transformer.TransformNewSpaceObject(o);
            Vector pos = transformedObject.getPosition();
            double rad = transformedObject.getRadius();

            Vector difference = Vector.subtract(mousePos, pos);
            double x = difference.getX();
            double y = difference.getY();

            if (x <= rad && x >= -rad && y <= rad && y >= -rad){
                return o;
            }
        }
        return null;
    }

}
