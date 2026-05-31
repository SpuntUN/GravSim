package Project.UI;

import Project.Math.Orbit;
import Project.Math.Space;
import Project.Math.SpaceObject;
import Project.Math.Vector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;

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
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);


        for (SpaceObject o : space.getSpaceObjects()){
            System.out.println(o.getName());
            SpaceObject transformedObject = transformer.TransformNewSpaceObject(o);
            paintOrbit(g2d, transformedObject);
            paintSpaceObject(g2d , transformedObject);
        }

    }

    private void paintSpaceObject(Graphics2D g2d, SpaceObject o){
            Vector pos = o.getPosition();
            double rad = o.getRadius();

            g2d.setColor(o.getColor());
            java.awt.geom.Ellipse2D.Double oval = new java.awt.geom.Ellipse2D.Double(
                    pos.getX() - rad,
                    pos.getY() - rad,
                    rad * 2,
                    rad * 2
            );

            g2d.fill(oval);
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
                SpaceObject pressed = spaceObjectPressed(mousePos);
                if (pressed != null){
                    spaceObjectPanel.setSpaceObject(pressed);
                    transformer.setCenteredObject(pressed);
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

        // Procházení seznamu od posledního prvku k prvnímu
        for (int i = space.getSpaceObjects().size() - 1; i >= 0; i--) {
            SpaceObject o = space.getSpaceObjects().get(i);
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

    private void paintOrbit(Graphics2D g2d, SpaceObject spaceObject){
        LinkedList<Vector> positions = spaceObject.getOrbit().getPositions();
        Color color = spaceObject.getColor();

        g2d.setStroke(new BasicStroke(
                spaceObject.getMinimalScreenRadius()/2,
                BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_ROUND
        ));

        for (int i = 0; i < positions.size() - 1; i++) {
            Vector current = positions.get(i);
            Vector next = positions.get(i + 1);

            java.awt.geom.Line2D.Double line = new java.awt.geom.Line2D.Double(
                    current.getX(), current.getY(),
                    next.getX(), next.getY()
            );

            int alpha =(int) (255.0 * i / (positions.size() - 1));
            g2d.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha));
            g2d.draw(line);
        }
    }

}
