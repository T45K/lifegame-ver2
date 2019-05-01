package lifegame;

import lifegame.button.NextButton;
import lifegame.button.UndoButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BoardModel implements ActionListener {
    private boolean[][] board;
    final private int col;
    final private int row;
    final private List<BoardListener> listeners;
    final private Deque<boolean[][]> boardHistory;
    final static private int MAX_STACK_SIZE = 30;

    public BoardModel(final int col, final int row) {
        this.col = col;
        this.row = row;
        this.board = new boolean[row][col];
        this.listeners = new ArrayList<>();
        this.boardHistory = new ArrayDeque<>(MAX_STACK_SIZE);
    }

    public void addListeners(final BoardListener listener) {
        this.listeners.add(listener);
    }

    private void fireUpdate() {
        for (final BoardListener listener : this.listeners) {
            listener.update(this);
        }
    }

    public boolean isAlive(final int x, final int y) {
        return isInField(x, y) && this.board[x][y];
    }

    private boolean isInField(final int x, final int y) {
        return x >= 0 && x < row && y >= 0 && y < col;
    }

    public void changeCellState(final int x, final int y) {
        if (this.boardHistory.size() == MAX_STACK_SIZE - 1) {
            boardHistory.removeFirst();
        }

        this.boardHistory.add(copyBoard(this.board));
        this.board[x][y] = !this.board[x][y];
        fireUpdate();
    }

    public int getCol() {
        return this.col;
    }

    public int getRow() {
        return this.row;
    }

    private boolean isAliveNext(final int x, final int y) {
        final int numOfAliveCellsAroundTargetCell = (isAlive(x - 1, y - 1) ? 1 : 0) + (isAlive(x - 1, y) ? 1 : 0)
                + (isAlive(x - 1, y + 1) ? 1 : 0) + (isAlive(x, y - 1) ? 1 : 0) + (isAlive(x, y + 1) ? 1 : 0)
                + (isAlive(x + 1, y - 1) ? 1 : 0) + (isAlive(x + 1, y) ? 1 : 0) + (isAlive(x + 1, y + 1) ? 1 : 0);
        if (isAlive(x, y)) {
            return numOfAliveCellsAroundTargetCell == 2 || numOfAliveCellsAroundTargetCell == 3;
        } else {
            return numOfAliveCellsAroundTargetCell == 3;
        }
    }

    private boolean[][] getNextBoard() {
        final boolean[][] nextBoard = new boolean[row][col];
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < col; y++) {
                nextBoard[x][y] = isAliveNext(x, y);
            }
        }
        return nextBoard;
    }

    public void next() {
        if (this.boardHistory.size() == MAX_STACK_SIZE - 1) {
            boardHistory.removeFirst();
        }

        this.boardHistory.addLast(copyBoard(this.board));
        this.board = getNextBoard();
        fireUpdate();
    }

    public void undo() {
        this.board = this.boardHistory.removeLast();
        fireUpdate();
    }

    public boolean isUndoable() {
        return this.boardHistory.size() != 0;
    }

    public int remainingUndoable() {
        return this.boardHistory.size();
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        if (e.getSource() instanceof NextButton) {
            next();
        } else if (e.getSource() instanceof UndoButton) {
            undo();
        }
    }

    private boolean[][] copyBoard(final boolean[][] board) {
        final boolean[][] copiedBoard = new boolean[row][col];
        for (int x = 0; x < row; x++) {
            if (col >= 0) {
                System.arraycopy(board[x], 0, copiedBoard[x], 0, col);
            }
        }

        return copiedBoard;
    }
}
