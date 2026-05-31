package Project.UI;

import Project.Math.*;
import Project.UI.TimeManagerPackage.TimeManager;
import Project.UI.TimeManagerPackage.TimeManagerPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame {

    private JFrame frame;
    private Space space;
    private SpacePanel spacePanel;
    private SpaceObjectPanel spaceObjectPanel;
    private ControlPanel controlPanel;
    private TimeManager timeManager;
    private TimeManagerPanel timeManagerPanel;


    public MainFrame() {
        frame = new JFrame("The Only Place that hasn't been Corrupted by CAPITALISM!");
        space = new Space();

        //BULLSHIT TE$STING START
        SpaceObject sun = new SpaceObject(
                "Sun",
                false,
                1.98847e30,
                new Vector(0, 0),
                new Vector(0, 0),
                6.957e8,
                Color.YELLOW

        );
        SpaceObject mercury = new SpaceObject(
                "Mercury",
                false,
                3.3011e23,
                new Vector(5.791e10, 0),
                new Vector(0, 47_360),
                2.4397e6,
                Color.ORANGE
        );
        SpaceObject earth = new SpaceObject(
                "Earth",
                false,
                5.972e24,
                new Vector(1.521e11, 0),
                new Vector(0, 29_290),
                6.371e6,
                Color.BLUE
        );
        SpaceObject moon = new SpaceObject(
                "Moon",
                false,
                7.349e22,
                new Vector(1.5251e11, 0),
                new Vector(0, 30_254),
                1.737e6,
                Color.GRAY
        );
        SpaceCraft ISS = new SpaceCraft(
                "ISS",
                5_000,
                new Vector(new Vector(earth.getPosition().getX() + earth.getRadius() + 400000, 0)),
                new Vector(0, earth.getVelocity().getY() + 7660),
                Color.WHITE,
                100,
                10_000_000,
                100_000.0
                );

        space.addSpaceObject(sun);
        space.addSpaceObject(mercury);
        space.addSpaceObject(earth);
        space.addSpaceObject(moon);
        moon.getOrbit().setRelativeTo(earth);
        space.addSpaceObject(ISS);
        ISS.getOrbit().setRelativeTo(earth);
        ISS.addInstruction(new Instruction(100_000,0, 2.0, 100.0));
        ISS.addInstruction(new Instruction(100_000,90, 3600, 1000.0));


        //BULLSHIT TESTING END

        spaceObjectPanel = new SpaceObjectPanel();
        spacePanel = new SpacePanel(space, spaceObjectPanel);
        timeManager = new TimeManager(1.0/100.0, 0);
        timeManagerPanel = new TimeManagerPanel(timeManager);
        controlPanel = new ControlPanel(timeManagerPanel, ISS);


    }

    public void init(){
        this.frame.setSize(1920, 1080);
        this.frame.setLayout(new BorderLayout());
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);
        this.frame.setResizable(false);

        spaceObjectPanel.setSpaceObject(new SpaceObject());
        matchPanelWidth(spaceObjectPanel.getRoot(), timeManagerPanel.getRoot());
        spaceObjectPanel.setSpaceObject(null);

        this.frame.add(spacePanel, BorderLayout.CENTER);
        this.frame.add(spaceObjectPanel.getRoot(), BorderLayout.WEST);
        this.frame.add(controlPanel.getRoot(), BorderLayout.SOUTH);




        this.frame.setVisible(true);

        timeManager.setLastTime(System.nanoTime());

        Timer timer = new Timer(8, e -> run());
        timer.start();
    }

    private void run(){

        timeManager.accumulate(System.nanoTime());
        while (timeManager.hasAccumulated()){
            space.update(timeManager.getDt());
            timeManager.reduceAccumulationByDt();
        }

        controlPanel.updateComponents();
        spaceObjectPanel.refresh();
        timeManagerPanel.updateLabels();
        spacePanel.repaint();
    }

    private void matchPanelWidth(JPanel source, JPanel target) {
        int width = source.getPreferredSize().width;

        Dimension targetPreferred = target.getPreferredSize();
        target.setPreferredSize(new Dimension(width, targetPreferred.height));
        target.setMinimumSize(new Dimension(width, targetPreferred.height));
        target.setMaximumSize(new Dimension(width, Integer.MAX_VALUE));
    }


}
