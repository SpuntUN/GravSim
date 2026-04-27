import org.w3c.dom.ls.LSOutput;

public class Main {
    public static void main(String[] args) {
        SpaceObject sun = new SpaceObject(
                "Sun",
                false,
                1.989e30,
                new Vector(0, 0),
                new Vector(0, 0),
                6.9634e8
        );

        SpaceObject earth = new SpaceObject(
                "Earth",
                false,
                5.972e24,
                new Vector(1.496e11, 0),
                new Vector(0, 29_780),
                6.371e6
        );


        MainFrame frame = new MainFrame();
        frame.init();

    }


}