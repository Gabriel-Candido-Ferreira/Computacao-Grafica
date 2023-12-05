import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ZoomImageApp extends JFrame {
    private BufferedImage image;
    private double scale = 1.0;

    public ZoomImageApp() {
        super("Zoom Image App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            // Carregar a imagem de um arquivo (substitua "caminho/para/sua/imagem.jpg" pelo caminho real da sua imagem)
            image = ImageIO.read(new File("C://Users//gabri_knygjto//IdeaProjects//Zoom//zoom.jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Ao clicar, aumentar o fator de escala
                scale *= 1.2;
                repaint();
            }
        });

        setSize(800, 600);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Desenhar a imagem com o fator de escala atual
        int scaledWidth = (int) (image.getWidth() * scale);
        int scaledHeight = (int) (image.getHeight() * scale);

        g.drawImage(image, 0, 0, scaledWidth, scaledHeight, this);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ZoomImageApp());
    }
}
