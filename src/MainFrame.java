import javax.swing.*;
import java.awt.*;

public class MainFrame {

    private JFrame frame;
    private Space space;
    private SpacePanel spacePanel;

    private double dt = 1.0/1.0;
    private double speed = 846000;
    private double accumulator = 0;
    private long lastTime;


    public MainFrame() {
        frame = new JFrame("The Only Place that hasn't been Corrupted by CAPITALISM!");
        space = new Space();
        spacePanel = new SpacePanel(space);

        //BULLSHIT TE$STING
        space.addSpaceObject(new SpaceObject(
                "Sun",
                false,
                1.989e30,
                new Vector(6.9634e8, 6.9634e8),
                new Vector(0, 0),
                6.9634e8
        ));
        space.addSpaceObject(new SpaceObject(
                "Earth",
                false,
                5.972e24,
                new Vector(1.496e11, 0),
                new Vector(0, 29_780),
                6.9634e8
        ));
    }

    public void init(){
        this.frame.setSize(1000, 1000);
        this.frame.setLayout(new BorderLayout());
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);
        this.frame.setResizable(false);

        this.frame.add(spacePanel, BorderLayout.CENTER);

        this.frame.setVisible(true);

        lastTime = System.nanoTime();

        Timer timer = new Timer(16, e -> run());
        timer.start();
    }

    private void run(){
        long currentTime = System.nanoTime();
        double frameTime = (currentTime-lastTime)/1_000_000_000.0;
        lastTime = currentTime;

        accumulator += frameTime*speed;

        while (accumulator >= dt){
            space.update(dt);
            accumulator -= dt;
        }

        spacePanel.repaint();
    }

}
