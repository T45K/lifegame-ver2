package lifegame;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

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

		final BoardView view = new BoardView();
		base.setLayout(new BorderLayout());
		base.add(view, BorderLayout.CENTER);

		frame.pack();
		frame.setVisible(true);
	}

}
