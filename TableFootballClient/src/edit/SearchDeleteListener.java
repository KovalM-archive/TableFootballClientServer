package edit;

import tablemodel.StudentModel;
import tableview.ChangeTablePanel;
import tableview.StudentTableModel;
import tableview.StudentTableView;
import tableview.TablePanel;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class SearchDeleteListener implements ActionListener{
    private JTabbedPane tableTab;
    private JFrame mainWindow;
    private JDialog searchStudentFrame;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public SearchDeleteListener(JFrame mainWindow,JTabbedPane tableTab, ObjectInputStream inputStream, ObjectOutputStream outputStream){
        this.mainWindow = mainWindow;
        this.tableTab = tableTab;
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        searchStudentFrame = new JDialog(mainWindow,e.getActionCommand(),false);
        searchStudentFrame.setSize(500, 500);
        searchStudentFrame.setLocationRelativeTo(mainWindow);
        searchStudentFrame.setVisible(true);
        searchStudentFrame.setLayout(new BorderLayout());
        JLabel searchText;
        try {
            if (e.getActionCommand() == "Найти"){
                searchText = new JLabel("Найдено:");
            }else{
                searchText = new JLabel("Найдено и удалено:");
            }
            outputStream.writeObject("Find");

            StudentTableModel tableModel = new StudentTableModel(new ArrayList<StudentModel>());
            StudentTableView tableView = new StudentTableView(tableModel,inputStream,outputStream);

            ChangeTablePanel changeTablePanel = new ChangeTablePanel(tableView);
            TablePanel mainPage = new TablePanel(tableView, changeTablePanel);
            TablePanel tablePanel = (TablePanel)tableTab.getSelectedComponent();
            mainPage.setLayout(new BorderLayout());
            JScrollPane scrollpane = new JScrollPane(tableView);
            mainPage.add(scrollpane, BorderLayout.CENTER);
            mainPage.add(changeTablePanel, BorderLayout.SOUTH);

            SearchTermsPanel searchTermsPanel = new SearchTermsPanel(mainPage,e.getActionCommand(), outputStream);

            searchStudentFrame.add(searchTermsPanel,BorderLayout.SOUTH);
            searchStudentFrame.add(searchText,BorderLayout.NORTH);
            searchStudentFrame.add(mainPage,BorderLayout.CENTER);
            searchStudentFrame.addWindowListener(new CloseSearchDialogListener(outputStream, tablePanel));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
