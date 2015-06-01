package file;

import javax.swing.JFileChooser;
import javax.swing.JTabbedPane;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveMenuListener implements ActionListener {
    private JTabbedPane tableTab;
    private JFileChooser jFileChooser;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public SaveMenuListener(JTabbedPane tableTab, ObjectInputStream inputStream, ObjectOutputStream outputStream) {
        this.tableTab = tableTab;
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        jFileChooser = new JFileChooser();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ( jFileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION ) {
            try {
                new XMLFile(jFileChooser.getSelectedFile().getPath(), tableTab).writeFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (TransformerException e1) {
                e1.printStackTrace();
            } catch (ParserConfigurationException e1) {
                e1.printStackTrace();
            }
        }
    }
}
