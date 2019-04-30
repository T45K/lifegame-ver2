package lifegame;

import lifegame.button.NextButton;
import lifegame.button.UndoButton;

import java.awt.*;

import javax.swing.*;

public class Main implements Runnable {
	final static private String WINDOW_TITLE = "lifegame";
	final static private int MINIMUM_WIDTH = 300;
	final static private int MINIMUM_HEIGHT = 200;
	final static private int DEFAULT_WIDTH = 400;
	final static private int DEFAULT_HEIGHT = 300;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Main());
	}

	public void run() {
		final JFrame frame = new JFrame();
		frame.setTitle(WINDOW_TITLE);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		final JPanel base = new JPanel();
		frame.setContentPane(base);
		base.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		frame.setMinimumSize(new Dimension(MINIMUM_WIDTH, MINIMUM_HEIGHT));

		final BoardModel model = new BoardModel(10, 10);
		final BoardView view = new BoardView(model);
		model.addListeners(view);
		base.setLayout(new BorderLayout());
		base.add(view, BorderLayout.CENTER);

		final JPanel buttonPanel = new JPanel();
		base.add(buttonPanel,BorderLayout.SOUTH);
		buttonPanel.setLayout(new FlowLayout());

		final JButton nextButton = new NextButton();
		nextButton.setText("NEXT");
		nextButton.addActionListener(model);
		buttonPanel.add(nextButton);

		final UndoButton undoButton = new UndoButton();
		model.addListeners(undoButton);
		undoButton.setText("UNDO(remaining 0)");
		undoButton.addActionListener(model);
		undoButton.setEnabled(false);
		buttonPanel.add(undoButton);

		frame.pack();
		frame.setVisible(true);
	}

}
