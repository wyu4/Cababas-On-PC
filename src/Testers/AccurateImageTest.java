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

    public AccurateImageTest() {
        System.out.println("Creating UI...");

        frame = new AccurateFrame("TestFrame");
        frame.setDefaultCloseOperation(AccurateFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setBackground(new Color(0, 0, 0, 155));
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

        label.setIcon(accImg);

        frame.add(label);
        frame.setVisible(true);
        frame.repaint();

        Timer runtime = new Timer(1, this);
        runtime.start();

        boolean loopMirror = true;
        while (loopMirror) {
            try {
                accImg.setMirrored(false, false);
                Thread.sleep(2000);
                accImg.setMirrored(true, false);
                Thread.sleep(2000);
                accImg.setMirrored(true, true);
                Thread.sleep(2000);
                accImg.setMirrored(false, true);
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                loopMirror = false;
            }
        }
    }

    public static void main(String[] args) {
        new AccurateImageTest();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Point mousePos = MouseInfo.getPointerInfo().getLocation();
        frame.setSize(
                Math.clamp(mousePos.x-100, 10, Integer.MAX_VALUE),
                Math.clamp(mousePos.y-100, 10, Integer.MAX_VALUE)
        );
        label.setSize(frame.getAccurateSize().multiply(0.25f));
        label.setLocation(
                frame.getAccurateSize().getX()*1,
                frame.getAccurateSize().getY()*0.5f
        );
        frame.repaint();
    }
}
