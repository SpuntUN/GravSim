import javax.swing.*;
import java.awt.*;

public class MainFrame {

    private JFrame frame;
    private Space space;
    private SpacePanel spacePanel;
    private SpaceObjectPanel spaceObjectPanel;

    private double dt = 1.0/1.0;
    private double speed = 864000;
    private double accumulator = 0;
    private long lastTime;


    public MainFrame() {
        frame = new JFrame("The Only Place that hasn't been Corrupted by CAPITALISM!");
        space = new Space();
        spacePanel = new SpacePanel(space);
        spaceObjectPanel = new SpaceObjectPanel();

        //BULLSHIT TE$STING
        space.addSpaceObject(new SpaceObject(
                "Sun",
                false,
                1.989e30,
                new Vector(0, 0),
                new Vector(0, 0),
                6.9634e9,
                Color.YELLOW

        ));
        space.addSpaceObject(new SpaceObject(
                "Earth",
                false,
                5.972e24,
                new Vector(1.496e11, 0),
                new Vector(0, 29_780),
                6.9634e9,
                Color.BLUE
        ));
    }

    public void init(){
        this.frame.setSize(1920, 1080);
        this.frame.setLayout(new BorderLayout());
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);
        this.frame.setResizable(false);

        this.frame.add(spacePanel, BorderLayout.CENTER);

        JSplitPane splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                spaceObjectPanel.getRoot(),
                spacePanel
        );

        splitPane.setResizeWeight(0.2); // 20% left, 80% right
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(0.2);

        frame.setContentPane(splitPane);



        this.frame.setVisible(true);

        lastTime = System.nanoTime();

        Timer timer = new Timer(8, e -> run());
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
