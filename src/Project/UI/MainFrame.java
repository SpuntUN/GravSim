package Project.UI;

import Project.Math.Space;
import Project.Math.SpaceObject;
import Project.Math.Vector;
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
    private ShipPanel shipPanel;


    public MainFrame() {
        frame = new JFrame("The Only Place that hasn't been Corrupted by CAPITALISM!");
        space = new Space();
        spaceObjectPanel = new SpaceObjectPanel();
        spacePanel = new SpacePanel(space, spaceObjectPanel);
        timeManager = new TimeManager(1.0/1.0, 86400);
        timeManagerPanel = new TimeManagerPanel(timeManager);
        shipPanel = new ShipPanel();
        controlPanel = new ControlPanel(timeManagerPanel, shipPanel);


        //BULLSHIT TE$STING
        SpaceObject sun = new SpaceObject(
                "Sun",
                false,
                1.98847e30,
                new Vector(0, 0),
                new Vector(0, 0),
                6.957e8,
                Color.YELLOW

        );
        SpaceObject earth = new SpaceObject(
                "Earth",
                false,
                5.972e24,
                new Vector(1.496e11, 0),
                new Vector(0, 29_780),
                6.371e6,
                Color.BLUE
        );
        SpaceObject moon = new SpaceObject(
                "Moon",
                false,
                7.349e22,
                new Vector(1.49984e11, 0),
                new Vector(0, 30_802),
                1.737e6,
                Color.GRAY
        );
        space.addSpaceObject(sun);
        space.addSpaceObject(earth);
        space.addSpaceObject(moon);
        spaceObjectPanel.setSpaceObject(earth);

    }

    public void init(){
        this.frame.setSize(1920, 1080);
        this.frame.setLayout(new BorderLayout());
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);
        this.frame.setResizable(false);

        matchPanelWidth(spaceObjectPanel.getRoot(), timeManagerPanel.getRoot());

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
