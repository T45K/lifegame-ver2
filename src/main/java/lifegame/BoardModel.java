package lifegame;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BoardModel {
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

	public void changeCellState(final int x, final int y) {
		this.board[x][y] = !this.board[x][y];
		fireUpdate();
	}

	public int getCol() {
		return this.col;
	}

	public int getRow() {
		return this.row;
	}

	public void next() {
		final boolean[][] nextBoard = new boolean[row][col];

		if (this.boardHistory.size() > MAX_STACK_SIZE - 1) {
			throw new RuntimeException("invalid");
		} else if (this.boardHistory.size() == MAX_STACK_SIZE - 1) {
			boardHistory.removeFirst();
		}

		this.boardHistory.addLast(this.board.clone());
		this.board = nextBoard;
	}
	
	public void undo() {
		this.board = this.boardHistory.getLast();
	}
	
	public int isUndoable() {
		return this.boardHistory.size();
	}
}
