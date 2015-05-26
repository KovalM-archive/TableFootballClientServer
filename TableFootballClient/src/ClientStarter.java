import tableview.EnterHostNameDialog;

import javax.swing.SwingUtilities;

public class ClientStarter {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EnterHostNameDialog();
            }
        });
    }
}
