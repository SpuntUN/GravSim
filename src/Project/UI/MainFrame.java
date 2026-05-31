package Project.UI;

import Project.Math.*;
import Project.UI.TimeManagerPackage.TimeManager;
import Project.UI.TimeManagerPackage.TimeManagerPanel;

import javax.swing.*;
import java.awt.*;
import java.io.*;

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

        loadPlanetsFromCsv(space, "res/planets.csv");

        SpaceObject earth = space.getSpaceObject("earth");
        SpaceObject moon = space.getSpaceObject("moon");
        moon.getOrbit().setRelativeTo(earth);

        SpaceCraft ISS = new SpaceCraft(
                "ISS",
                5_000,
                new Vector(new Vector(earth.getPosition().getX() + earth.getRadius() + 400000, 0)),
                new Vector(0, earth.getVelocity().getY() + 7660),
                Color.WHITE,
                100,
                1_000_000,
                100_000.0
        );

        space.addSpaceObject(ISS);
        ISS.getOrbit().setRelativeTo(earth);
        ISS.addInstruction(new Instruction(100_000,0, 2.0, 100.0));
        ISS.addInstruction(new Instruction(100_000,90, 3600, 1000.0));


        spaceObjectPanel = new SpaceObjectPanel(space);
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



    private void loadPlanetsFromCsv(Space space, String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            reader.readLine(); // Header
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.strip();
                if (!line.isEmpty()) {
                    space.addSpaceObject(SpaceObject.fromCsv(line.split(",")));
                }
            }
        } catch (IOException e) {
            System.out.println("Error during planet loading: " + e.getMessage());
        }
    }
}
