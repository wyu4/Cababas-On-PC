package AppClasses.LoadingScreen;

import DataTypes.FloatCoordinate;
import GUIClasses.AccurateUIComponents.AccurateFrame;
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
        runtime.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

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

        bgContent = new BackgroundContent();
        setContentPane(bgContent);

        refresh();
    }

    /**
     * Refreshes some of the component's attributes.
     */
    private void refresh() {
        FloatCoordinate screenSize = new FloatCoordinate(Toolkit.getDefaultToolkit().getScreenSize());
        setAnchorPoint(0.5f, 0.5f);
        setLocation(screenSize.multiply(0.5f));
        setSize(screenSize.multiply(0.2f));

        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        bgContent.actionPerformed(e);
        refresh();
    }
}

class BackgroundContent extends AccuratePanel implements ActionListener {
    public BackgroundContent() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

class CababasEmblem extends AccurateLabel {

}