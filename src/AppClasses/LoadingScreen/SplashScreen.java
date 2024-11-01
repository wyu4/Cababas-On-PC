package AppClasses.LoadingScreen;

import AppClasses.ResourceClasses.ResourceEnum;
import AppClasses.ResourceClasses.ResourcesManager;
import DataTypes.FloatCoordinate;
import GUIClasses.AccurateUIComponents.AccurateFrame;
import GUIClasses.AccurateUIComponents.AccurateImageIcon;
import GUIClasses.AccurateUIComponents.AccurateLabel;
import GUIClasses.AccurateUIComponents.AccuratePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SplashScreen implements ActionListener {
    private final Timer runtime;
    private volatile float progress;
    private final Background background;

    public SplashScreen() {
        progress = 0f;

        System.out.println("Creating splash screen...");
        background = new Background();

        background.setVisible(true);

        runtime = new Timer(1, this);
        runtime.addActionListener(background);
        runtime.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {}

    public static void main(String[] args) {
        System.out.println("Testing splash screen...");
        new SplashScreen();
    }
}

class Background extends AccurateFrame implements ActionListener {
    private final BackgroundContent bgContent;

    public Background() {
        setName("SplashScreenBackground");
        setDefaultCloseOperation(AccurateFrame.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setBackground(new Color(0, 0, 0));
        setAlwaysOnTop(true);

        bgContent = new BackgroundContent();
        setContentPane(bgContent);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        FloatCoordinate screenSize = new FloatCoordinate(Toolkit.getDefaultToolkit().getScreenSize());
        setAnchorPoint(0.5f, 0.5f);
        setLocation(screenSize.multiply(0.5f));
        setSize(screenSize.multiply(0.2f));

        bgContent.setSize(getAccurateSize());
        bgContent.actionPerformed(e);

        repaint();
    }
}

class BackgroundContent extends AccuratePanel implements ActionListener {
    private final AccurateLabel imageLabel;

    public BackgroundContent() {
        setName("SplashBackgroundContent");
        setBackground(Color.BLACK);

        imageLabel = new AccurateLabel("SplashBackgroundImageLabel");
        AccurateImageIcon img = new AccurateImageIcon(ResourcesManager.getAsBufferedImage(ResourceEnum.SplashBackground_PNG));
        img.setMode(AccurateImageIcon.PaintMode.RATIO_FILL);
        imageLabel.setIcon(img);

        add(imageLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        imageLabel.setLocation(0, 0);
        imageLabel.setSize(getSize());
    }
}