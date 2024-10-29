package GUIClasses.AccurateUIComponents;

import javax.swing.*;
import java.awt.*;

public class AccurateImageIcon extends ImageIcon {
    private boolean mirrored;

    /**
     * Create a new AccurateImageIcon object.
     * @param image The image
     */
    public AccurateImageIcon(Image image) {
        super(image);
        mirrored = false;
    }

    /**
     * Resize the image.
     * @param width New width
     * @param height New height
     */
    public void resizeImage(int width, int height) {
        Image resized = getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        setImage(resized);
    }

    /**
     * Resize the image.
     * @param size New size
     */
    public void resizeImage(Dimension size) {
        resizeImage(size.width, size.height);
    }

    /**
     * Set the image's mirrored state
     * @param mirror {@code true} if the image should be mirrored, {@code false} if the image should not be mirrored.
     */
    public void setMirrored(boolean mirror) {
        mirrored = mirror;
    }

    /**
     * Get the image's mirrored state
     * @return {@code true} if the image should be mirrored, {@code false} if the image should not be mirrored.
     */
    public boolean getMirrored() {
        return mirrored;
    }

    @Override
    public synchronized void paintIcon(Component c, Graphics g, int x, int y) {
        setImageObserver(c);

        // Making the image render more smoothly
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        // Mirror the image if requested
        if (mirrored) {
            g2d.translate(c.getWidth(), 0);
            g2d.scale(-1, 1);
        }

        // Painting the image
        g2d.drawImage(getImage(), 0, 0, c.getWidth(), c.getHeight(), c);
    }
}