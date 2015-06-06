package file;

import tablemodel.StudentModel;
import tableview.ChangeTablePanel;
import tableview.StudentTableModel;
import tableview.StudentTableView;
import tableview.TablePanel;

import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class OpenFileButtonListener implements ActionListener {
    private ObjectOutputStream outputStream;
    private JList fileList;
    private JDialog enterFileName;
    private ObjectInputStream inputStream;
    private JTabbedPane tableTab;
    private JToolBar jtbMain;

    public OpenFileButtonListener(JDialog enterFileName, JList fileList, ObjectOutputStream outputStream,
                                  ObjectInputStream inputStream, JTabbedPane tableTab,JToolBar jtbMain){
        this.outputStream = outputStream;
        this.inputStream = inputStream;
        this.enterFileName = enterFileName;
        this.fileList = fileList;
        this.tableTab = tableTab;
        this.jtbMain = jtbMain;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            outputStream.writeObject("New table");
            jtbMain.setVisible(true);
            StudentTableModel tableModel = new StudentTableModel(new ArrayList<StudentModel>());
            StudentTableView tableView = new StudentTableView(tableModel,inputStream,outputStream);
            ChangeTablePanel changeTablePanel = new ChangeTablePanel(tableView);
            TablePanel mainPage = new TablePanel(tableView, changeTablePanel);

            mainPage.setLayout(new BorderLayout());
            JScrollPane scrollpane = new JScrollPane(tableView);
            mainPage.add(scrollpane, BorderLayout.CENTER);
            mainPage.add(changeTablePanel, BorderLayout.SOUTH);
            String nameFile = (String)fileList.getSelectedValue();
            tableTab.remove(tableTab.getSelectedComponent());
            tableTab.add(nameFile, mainPage);
            tableTab.setSelectedComponent(mainPage);

            outputStream.writeObject("Open");
            outputStream.writeObject(nameFile);

            tableView.calculateNumberPage();
            tableView.goToPage(1);
            changeTablePanel.getCurrentPageIndex().setText(String.valueOf(1));
            changeTablePanel.getAllRecord().setText(
                    String.valueOf(tableView.getCountRecord()));
            changeTablePanel.getAllPage().setText(
                    String.valueOf(tableView.getNumberPage()));
            enterFileName.dispose();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
