package beans;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * @author Alexander Álvarez Marques
 * @date 2020-12-02
 * @version 0.1
 */
public class LoginImage extends JPanel {

    @Override
    public void paintComponent(Graphics g) {
        try {
            super.paintComponent(g);
            Image backgroundImage = ImageIO.read(new File("./images/user-login.png"));
            int w = getWidth();
            int h = getHeight();
            g.drawImage(backgroundImage, 0, 0, w, h, null);
        } catch (IOException ex) {
            Logger.getLogger(LoginImage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
