import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame(String title, int height, int weight) {
        DrawRect drawRect = new DrawRect();

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
        jPanel.add(drawRect);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400,400);
        add(jPanel);
        setVisible(true);
    }
}