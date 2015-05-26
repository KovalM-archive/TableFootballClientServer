package file;

import javax.swing.JFileChooser;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenMenuListener implements ActionListener {
    private JTabbedPane tableTab;
    private JToolBar jtbMain;
    private JFileChooser jFileChooser;

    public OpenMenuListener(JTabbedPane tableTab,JToolBar jtbMain){
        this.tableTab = tableTab;
        this.jtbMain = jtbMain;
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
