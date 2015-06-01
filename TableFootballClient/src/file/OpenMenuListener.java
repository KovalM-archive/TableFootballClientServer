package file;

import javax.swing.JFileChooser;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class OpenMenuListener implements ActionListener {
    private JTabbedPane tableTab;
    private JToolBar jtbMain;
    private JFileChooser jFileChooser;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public OpenMenuListener(JTabbedPane tableTab,JToolBar jtbMain, ObjectInputStream inputStream, ObjectOutputStream outputStream){
        this.tableTab = tableTab;
        this.jtbMain = jtbMain;
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        jFileChooser = new JFileChooser();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int result = jFileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            if (jFileChooser.getSelectedFile().getName().contains(".xml")) {
                jtbMain.setVisible(true);
                //jtpVkladka.setTitleAt(jtpVkladka.getSelectedIndex(), jFileChooser.getSelectedFile().getName());
                new XMLFile(jFileChooser.getSelectedFile().getPath(), tableTab).readFile(jFileChooser.getSelectedFile().getName());
            }
        }
    }
}
