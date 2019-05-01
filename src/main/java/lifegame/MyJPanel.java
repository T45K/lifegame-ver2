package lifegame;

import javax.swing.*;

public class MyJPanel extends JPanel implements BoardListener {
    @Override
    public void update(final BoardModel boardModel) {
        this.repaint();
    }
}
