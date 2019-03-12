package lifegame;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class BoardHistory extends ArrayList<boolean[][]> {

	final private List<boolean[][]> boardHistory;
	final static private int MAX_SIZE = 30;

	public BoardHistory() {
		this.boardHistory = new ArrayList<>();
	}

	public boolean push(final boolean[][] board) {
		if (!this.boardHistory.add(board)) {
			return false;
		}
		if(this.boardHistory.size() > MAX_SIZE) {
			this.boardHistory.remove(0);
		}
		return true;
	}
	
	public boolean[][] pop(){
		final int latestBoardIndex = this.boardHistory.size() - 1;
		final boolean[][] latestBoard= this.boardHistory.get(latestBoardIndex);
		this.boardHistory.remove(latestBoardIndex);
		return latestBoard;
	}

}
