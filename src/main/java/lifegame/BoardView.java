package lifegame;

import javax.swing.JPanel;
import java.awt.*;

public class BoardView extends JPanel{
    @Override
    public void paint(final Graphics g) {
        super.paint(g);

        g.drawLine(20, 30, 50, 30);
        g.fillRect(31,31,9,9);
    }
}
