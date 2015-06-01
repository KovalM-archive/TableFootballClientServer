package file;

//import tablemodel.TableModel;
//import tableview.TableView;

import tablemodel.StudentModel;
import tableview.StudentTableModel;
import tableview.TablePanel;
import tableview.ChangeTablePanel;
import tableview.StudentTableView;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class NewTableListener implements ActionListener {
    private JTabbedPane tableTab;
    private JToolBar jtbMain;
    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public NewTableListener(JTabbedPane tableTab,JToolBar jtbMain,ObjectInputStream inputStream, ObjectOutputStream outputStream){
        this.tableTab = tableTab;
        this.jtbMain = jtbMain;
        this.outputStream = outputStream;
        this.inputStream = inputStream;
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
            tableTab.add("No name",mainPage);
            tableTab.setSelectedComponent(mainPage);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
