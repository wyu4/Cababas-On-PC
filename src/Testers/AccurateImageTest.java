package Testers;

import AppClasses.ResourceClasses.ResourceEnum;
import AppClasses.ResourceClasses.ResourcesManager;
import GUIClasses.AccurateUIComponents.AccurateFrame;
import GUIClasses.AccurateUIComponents.AccurateImageIcon;
import GUIClasses.AccurateUIComponents.AccurateLabel;
import GUIClasses.AccurateUIComponents.AccuratePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AccurateImageTest {
    public AccurateImageTest() throws InterruptedException {
        System.out.println("Creating UI...");

        AccurateFrame frame = new AccurateFrame("TestFrame");
        frame.setDefaultCloseOperation(AccurateFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setBackground(Color.BLACK);
        frame.setSize(200, 200);
        frame.setLocation(50, 50);

        AccurateLabel label = new AccurateLabel("TestLabel");
        label.setBackground(Color.WHITE);
        label.setSize(200, 100);
        label.setLocation(100, 100);

        BufferedImage img = ResourcesManager.getAsBufferedImage(ResourceEnum.Cababas_PNG);
        AccurateImageIcon accImg = new AccurateImageIcon(img);
        accImg.setMirrored(true);

        label.setIcon(accImg);

        frame.add(label);
        frame.setVisible(true);
        frame.revalidate();

        Thread.sleep(1000);

        System.out.println("Anchor point changed...");
        label.setAnchorPoint(1f, 1f);
        frame.repaint();

        Thread.sleep(2000);

        accImg.setMirrored(false);

        Thread.sleep(2000);

        System.out.println("Closing frame and (hopefully) terminating program...");
        frame.closeFrame();

        System.exit(0); // In case frame closing does not exit out.
    }

    public static void main(String[] args) {
        try {
            new AccurateImageTest();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
