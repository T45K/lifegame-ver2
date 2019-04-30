package lifegame;

import javax.swing.*;
import java.awt.*;

public class BoardView extends JPanel {
    private final int cols;
    private final int rows;

    public BoardView(final int cols, final int rows) {
        this.cols = cols;
        this.rows = rows;
    }

    @Override
    public void paint(final Graphics g) {
        final int cellSize = Math.min(getHeight() / cols, getWidth() / rows);
        final int colsStartPoint = calcViewPaintStartPoint(cellSize, cols, getHeight());
        final int rowsStartPoint = calcViewPaintStartPoint(cellSize, rows, getWidth());

        paintCols(g, cellSize * rows, cellSize, rowsStartPoint, colsStartPoint);
        paintRows(g, cellSize * cols, cellSize, colsStartPoint, rowsStartPoint);
    }

    private int calcViewPaintStartPoint(final int lineWidth, final int lines, final int width) {
        return (width - lines * lineWidth) / 2;
    }

    private void paintCols(final Graphics g, final int lineLength, final int cellSize, final int offset, final int startPoint) {
        for (int i = 0; i < cols + 1; i++) {
            g.drawLine(offset, i * cellSize + startPoint, offset + lineLength, i * cellSize + startPoint);
        }
    }

    private void paintRows(final Graphics g, final int lineLength, final int cellSize, final int offset, final int startPoint) {
        for (int i = 0; i < rows + 1; i++) {
            g.drawLine(i * cellSize + startPoint, offset, i * cellSize + startPoint, offset + lineLength);
        }
    }
}
