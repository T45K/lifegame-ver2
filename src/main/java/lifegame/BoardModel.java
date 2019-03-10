package lifegame;

import java.util.ArrayList;
import java.util.List;

public class BoardModel {
	final private boolean[][] cells;
	final private int col;
	final private int row;
	final private List<BoardListener> listeners;
	
	public BoardModel(final int col,final int row) {
		this.col = col;
		this.row = row;
		this.cells = new boolean[row][col];
		this.listeners = new ArrayList<>();
	}
	
	public void addListeners(final BoardListener listener) {
		this.listeners.add(listener);
	}
	
	private void fireUpdate() {
		for(final BoardListener listener : this.listeners) {
			listener.update(this);
		}
	}
	
	public void changeCellState(final int x,final int y) {
		this.cells[x][y] = !this.cells[x][y];
		fireUpdate();
	}

	public int getCol() {
		return this.col;
	}

	public int getRow() {
		return this.row;
	}
	
	public void next() {
		
	}
}
