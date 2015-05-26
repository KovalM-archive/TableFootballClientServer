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
import java.net.Socket;
import java.util.ArrayList;

public class SearchDeleteListener implements ActionListener{
    JTabbedPane tableTab;
    JFrame mainWindow;
    JDialog searchStudentFrame;

    public SearchDeleteListener(JFrame mainWindow,JTabbedPane tableTab){
        this.mainWindow = mainWindow;
        this.tableTab = tableTab;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        searchStudentFrame = new JDialog(mainWindow,e.getActionCommand(),false);
        searchStudentFrame.setSize(500, 500);
        searchStudentFrame.setLocationRelativeTo(mainWindow);
        searchStudentFrame.setVisible(true);
        searchStudentFrame.setLayout(new BorderLayout());

        StudentTableModel tableModel = new StudentTableModel(new ArrayList<StudentModel>());
        StudentTableView tableView = new StudentTableView(tableModel,new Socket());
        ChangeTablePanel changeTablePanel = new ChangeTablePanel(tableView);
        TablePanel mainPage = new TablePanel(tableView, changeTablePanel);
        TablePanel tablePanel = (TablePanel)tableTab.getSelectedComponent();
        mainPage.setLayout(new BorderLayout());
        JScrollPane scrollpane = new JScrollPane(tableView);
        mainPage.add(scrollpane, BorderLayout.CENTER);
        mainPage.add(changeTablePanel, BorderLayout.SOUTH);

        SearchTermsPanel searchTermsPanel = new SearchTermsPanel(mainPage, tablePanel,e.getActionCommand());
        JLabel searchText;
        if (e.getActionCommand() == "Найти"){
            searchText = new JLabel("Найдено:");
        }else{
            searchText = new JLabel("Найдено и удалено:");
        }

        searchStudentFrame.add(searchTermsPanel,BorderLayout.SOUTH);
        searchStudentFrame.add(searchText,BorderLayout.NORTH);
        searchStudentFrame.add(mainPage,BorderLayout.CENTER);
    }
}
