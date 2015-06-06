package file;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveMenuListener implements ActionListener {
    private ObjectOutputStream outputStream;
    private JFrame mainWindow;

    public SaveMenuListener(JFrame mainWindow, ObjectOutputStream outputStream) {
        this.mainWindow = mainWindow;
        this.outputStream = outputStream;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            outputStream.writeObject("Save");
            String  nameFile = JOptionPane.showInputDialog("Enter name of file");
            outputStream.writeObject(nameFile);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
