package Testers;

import AppClasses.ResourceClasses.ResourceEnum;
import AppClasses.ResourceClasses.ResourcesManager;
import GUIClasses.AccurateUIComponents.AccurateFrame;
import GUIClasses.AccurateUIComponents.AccurateImageIcon;
import GUIClasses.AccurateUIComponents.AccurateLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class AccurateImageTest implements ActionListener {
    private final AccurateFrame frame;
    private final AccurateLabel label;
    private final AccurateImageIcon accImg;

    public AccurateImageTest() throws InterruptedException {
        System.out.println("Creating UI...");

        frame = new AccurateFrame("TestFrame");
        frame.setDefaultCloseOperation(AccurateFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setBackground(Color.BLACK);
        frame.setSize(0, 0);
        frame.setLocation(0, 0);

        label = new AccurateLabel("TestLabel");
        label.setBackground(Color.WHITE);
        label.setSize(0, 0);
        label.setLocation(0, 0);
        label.setAnchorPoint(0.5f, 0.5f);

        BufferedImage img = ResourcesManager.getAsBufferedImage(ResourceEnum.Cababas_PNG);
        accImg = new AccurateImageIcon(img);
        accImg.setMode(AccurateImageIcon.PaintMode.STRETCH);
//        accImg.setMirrored(false, true);

        label.setIcon(accImg);

        frame.add(label);
        frame.setVisible(true);
        frame.repaint();

        Timer runtime = new Timer(1, this);
        runtime.start();
    }

    public static void main(String[] args) {
        try {
            new AccurateImageTest();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Point mousePos = MouseInfo.getPointerInfo().getLocation();
        frame.setSize(
                Math.clamp(mousePos.x-100, 10, Integer.MAX_VALUE),
                Math.clamp(mousePos.y-100, 10, Integer.MAX_VALUE)
        );
        label.setSize(frame.getSize());
        label.setLocation(frame.getAccurateSize().multiply(0.5f));
        frame.repaint();
    }
}
