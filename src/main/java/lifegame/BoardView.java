package lifegame;

import javax.swing.*;
import java.awt.*;

public class BoardView extends JPanel {
    private final BoardModel model;
    private final int cols;
    private final int rows;

    public BoardView(final BoardModel model) {
        this.model = model;
        this.cols = model.getCol();
        this.rows = model.getRow();
    }

    @Override
    public void paint(final Graphics g) {
        final int cellSize = Math.min(getHeight() / cols, getWidth() / rows);
        final int colsStartPoint = calcViewPaintStartPoint(cellSize, cols, getHeight());
        final int rowsStartPoint = calcViewPaintStartPoint(cellSize, rows, getWidth());

        paintColLines(g, cellSize * rows, cellSize, rowsStartPoint, colsStartPoint);
        paintRowLines(g, cellSize * cols, cellSize, colsStartPoint, rowsStartPoint);
        paintCells(g, cellSize, rowsStartPoint, colsStartPoint);
    }

    private int calcViewPaintStartPoint(final int lineWidth, final int lines, final int width) {
        return (width - lines * lineWidth) / 2;
    }

    private void paintCells(final Graphics g, final int cellSize, final int rowsStartPoint, final int colsStartPoint) {
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                if (model.isAlive(x, y)) {
                    g.fillRect(rowsStartPoint + x * cellSize, colsStartPoint + y * cellSize, cellSize, cellSize);
                }
            }
        }
    }

    private void paintColLines(final Graphics g, final int lineLength, final int cellSize, final int offset, final int startPoint) {
        for (int i = 0; i < cols + 1; i++) {
            g.drawLine(offset, i * cellSize + startPoint, offset + lineLength, i * cellSize + startPoint);
        }
    }

    private void paintRowLines(final Graphics g, final int lineLength, final int cellSize, final int offset, final int startPoint) {
        for (int i = 0; i < rows + 1; i++) {
            g.drawLine(i * cellSize + startPoint, offset, i * cellSize + startPoint, offset + lineLength);
        }
    }
}
