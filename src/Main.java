import org.w3c.dom.ls.LSOutput;

public class Main {
    public static void main(String[] args) {
        SpaceObject sun = new SpaceObject(
                "Sun",
                false,
                1.98847e30,
                new Vector(0, 0),
                new Vector(0, 0)
        );

        SpaceObject earth = new SpaceObject(
                "Earth",
                false,
                5.97219e24,
                new Vector(1.496e11, 0),
                new Vector(0, 29_780)
        );


        MainFrame frame = new MainFrame();
        frame.init();

    }


}