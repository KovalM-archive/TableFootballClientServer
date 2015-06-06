package file;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.ListModel;
import java.awt.BorderLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Vector;

public class OpenMenuListener implements ActionListener {
    private JTabbedPane tableTab;
    private JToolBar jtbMain;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private JFrame mainWindow;

    public OpenMenuListener(JTabbedPane tableTab,JToolBar jtbMain, ObjectOutputStream outputStream,
                            ObjectInputStream inputStream,JFrame mainWindow){
        this.tableTab = tableTab;
        this.jtbMain = jtbMain;
        this.outputStream = outputStream;
        this.inputStream = inputStream;
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            outputStream.writeObject("Get file list");
            List<String> filesName = (List<String>)inputStream.readObject();
            int n = filesName.size();
            String[] files = new String[n];
            for (int i = 0; i <n; i++) {
                files[i] = filesName.get(i);
            }
            JDialog enterFileName = new JDialog(mainWindow,e.getActionCommand(),false);
            enterFileName.setSize(300, 300);
            enterFileName.setLocationRelativeTo(mainWindow);
            enterFileName.setVisible(true);
            enterFileName.setLayout(new BorderLayout());

            JList filesList = new JList(files);
            filesList.setLayoutOrientation(JList.VERTICAL);
            filesList.setVisibleRowCount(0);

            JButton enterName = new JButton("OK");
            enterFileName.add(new JScrollPane(filesList),BorderLayout.CENTER);
            enterFileName.add(enterName,BorderLayout.SOUTH);
            enterName.addActionListener(new OpenFileButtonListener(enterFileName,filesList,outputStream,
                                                                        inputStream,tableTab,jtbMain));
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }
}
