import clientswork.ClientWaiter;

import javax.swing.SwingUtilities;

public class ServerStarter {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClientWaiter();
            }
        });
    }
}
