package tableview;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.io.IOException;
import java.net.Socket;

public class EnterHostNameDialog {
    private Socket socket;

    public EnterHostNameDialog(){
        JFrame jfMainWin = new JFrame("Table student");

        jfMainWin.setLayout(new BorderLayout());

        jfMainWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfMainWin.setSize(200, 200);
        jfMainWin.setLocationRelativeTo(null);
        String nameHost;
        socket = null;
        boolean flag = false;

        while (true){
            try {
                nameHost = JOptionPane.showInputDialog("Enter name of host");
                socket = new Socket(nameHost, 4569);
                flag = true;
            } catch (IOException e) {
                JOptionPane.showMessageDialog(jfMainWin,"No connected to host");
                e.printStackTrace();
            }

            if (flag){
                break;
            }
        }

        new MainWindow(jfMainWin,socket);
    }
}
