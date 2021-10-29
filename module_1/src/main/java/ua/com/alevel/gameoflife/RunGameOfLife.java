package ua.com.alevel.gameoflife;

import javax.swing.SwingUtilities;

public class RunGameOfLife {
    public void start() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainWindow();
            }
        });
    }
}
