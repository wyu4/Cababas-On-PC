package Testers;

import GUIClasses.AccurateUIComponents.AccurateFrame;
import GUIClasses.AccurateUIComponents.AccuratePanel;

import java.awt.*;

public class AccurateContainerTest {
    public AccurateContainerTest() throws InterruptedException {
        System.out.println("Creating UI...");

        AccurateFrame frame = new AccurateFrame("TestFrame");
        frame.setDefaultCloseOperation(AccurateFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setBackground(Color.BLACK);
        frame.setSize(200, 200);
        frame.setLocation(50, 50);

        AccuratePanel panel = new AccuratePanel("TestPanel");
        panel.setBackground(Color.WHITE);
        panel.setSize(100, 100);
        panel.setLocation(100, 100);

        frame.add(panel);
        frame.setVisible(true);
        frame.revalidate();

        Thread.sleep(1000);

        System.out.println("Anchor point changed...");
        panel.setAnchorPoint(1f, 1f);
        frame.repaint();

        Thread.sleep(5000);

        System.out.println("Closing frame and (hopefully) terminating program...");
        frame.closeFrame();

        System.exit(0); // In case frame closing does not exit out.
    }

    public static void main(String[] args) {
        try {
            new AccurateContainerTest();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
