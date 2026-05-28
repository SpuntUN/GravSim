import javax.swing.*;
import java.awt.*;

public class SpaceObjectPanel {
    private JPanel panel;
    private SpaceObject spaceObject;


    public SpaceObjectPanel(){
        init();
    }

    public void init(){
        panel = new JPanel();
        this.panel.setLayout(new BorderLayout());
        this.panel.setBackground(Color.WHITE);

    }

    public JPanel getRoot(){
        return panel;
    }

    public void setSpaceObject(SpaceObject spaceObject) {
        this.spaceObject = spaceObject;
        if (spaceObject == null){
            this.panel.setVisible(false);
            return;
        }




        this.panel.setVisible(true);
    }
}
