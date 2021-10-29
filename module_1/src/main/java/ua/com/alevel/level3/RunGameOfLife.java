package ua.com.alevel.level3;

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
