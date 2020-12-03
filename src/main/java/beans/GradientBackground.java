package beans;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

/**
 * @author Alexander Álvarez Marques
 * @date 2020-12-03
 * @version 0.1
 */
public class GradientBackground extends JPanel {

    private Color color1 = Color.GRAY;
    private Color color2 = Color.GRAY;
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth();
        int h = getHeight();
        GradientPaint gp = new GradientPaint(0, 0, color1, w, h, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
    }
    
    public void paintBackground(Color c1, Color c2) {
        color1 = c1;
        color2 = c2;
        repaint();
    }
}