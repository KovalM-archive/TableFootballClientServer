package clientswork;

import window.ServerWorkPanel;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.BorderLayout;
import java.awt.ScrollPane;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientWaiter implements Runnable {
    private Socket socket;
    private Thread thread;

    public ClientWaiter() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        JFrame jfMainWin = new JFrame("Table student server");
        jfMainWin.setLayout(new BorderLayout());

        jfMainWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfMainWin.setSize(200, 200);
        jfMainWin.setLocationRelativeTo(null);

        ServerWorkPanel serverWorkPanel = new ServerWorkPanel();

        serverWorkPanel.setSize(jfMainWin.getWidth(), jfMainWin.getHeight());
        jfMainWin.add(new JScrollPane(serverWorkPanel), BorderLayout.CENTER);
        jfMainWin.setVisible(true);

        serverWorkPanel.getTextArea().append("Server start work\n");
        try {
            ServerSocket serverSocket = new ServerSocket(4569);
            while (true){
                socket = serverSocket.accept();
                new ServerWork(socket,serverWorkPanel);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}