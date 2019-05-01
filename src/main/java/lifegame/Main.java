package lifegame;

import lifegame.button.NextButton;
import lifegame.button.UndoButton;

import javax.swing.*;
import java.awt.*;

public class Main implements Runnable {
    final static private String WINDOW_TITLE = "lifegame";
    private static final String WELCOME_MESSAGE = "Welcome to lifegame!";
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

        final MyJPanel base = new MyJPanel();
        frame.setContentPane(base);
        base.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        frame.setMinimumSize(new Dimension(MINIMUM_WIDTH, MINIMUM_HEIGHT));

        final Label welcomeMessage = new Label(WELCOME_MESSAGE);
        final Label showCols = new Label("cols");
        final TextField inputCols = new TextField("10", 1);
        final Label showRows = new Label("rows");
        final TextField inputRows = new TextField("10", 1);
        final Label showUndoable = new Label("undoable");
        final TextField inputUndoable = new TextField("32", 1);

        final GridBagLayout gridBagLayout = new GridBagLayout();
        base.setLayout(gridBagLayout);
        gridBagLayout.setConstraints(welcomeMessage, addComponent(0, 0, 2));
        base.add(welcomeMessage);
        gridBagLayout.setConstraints(showCols, addComponent(0, 1, 1));
        base.add(showCols);
        gridBagLayout.setConstraints(inputCols, addComponent(1, 1, 1));
        base.add(inputCols);
        gridBagLayout.setConstraints(showRows, addComponent(0, 2, 1));
        base.add(showRows);
        gridBagLayout.setConstraints(inputRows, addComponent(1, 2, 1));
        base.add(inputRows);
        gridBagLayout.setConstraints(showUndoable, addComponent(0, 3, 1));
        base.add(showUndoable);
        gridBagLayout.setConstraints(inputUndoable, addComponent(1, 3, 1));
        base.add(inputUndoable);

        frame.pack();
        frame.setVisible(true);
    }

    private GridBagConstraints addComponent(final int x, final int y, final int width) {
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.gridx = x;
        gridBagConstraints.gridy = y;
        gridBagConstraints.gridwidth = width;
        gridBagConstraints.gridheight = 1;
        return gridBagConstraints;
    }

    private void lunchGameWindow() {
        final JFrame frame = new JFrame();
        frame.setTitle(WINDOW_TITLE);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        final MyJPanel base = new MyJPanel();
        frame.setContentPane(base);
        base.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        frame.setMinimumSize(new Dimension(MINIMUM_WIDTH, MINIMUM_HEIGHT));

        final BoardModel model = new BoardModel(10, 10);
        final BoardView view = new BoardView(model);
        model.addListeners(base);
        base.setLayout(new BorderLayout());
        base.add(view, BorderLayout.CENTER);

        final JPanel buttonPanel = new JPanel();
        base.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.setLayout(new FlowLayout());

        final NextButton nextButton = new NextButton();
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
