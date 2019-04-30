package lifegame.button;

import lifegame.BoardListener;
import lifegame.BoardModel;

import javax.swing.*;

public class UndoButton extends JButton implements BoardListener {
    @Override
    public void update(final BoardModel boardModel) {
        this.setEnabled(boardModel.isUndoable());
        this.setText("UNDO(remaining " + boardModel.remainingUndoable() + ")");
    }
}
