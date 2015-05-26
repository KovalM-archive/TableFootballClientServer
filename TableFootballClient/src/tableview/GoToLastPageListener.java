package tableview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToLastPageListener implements ActionListener {
    private ChangeTablePanel changeTablePanel;

    public GoToLastPageListener(ChangeTablePanel changeTablePanel){
        this.changeTablePanel = changeTablePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StudentTableView tableView = changeTablePanel.getTableView();
        tableView.goToPage(tableView.getNumberPage());
        changeTablePanel.getCurrentPageIndex().setText(String.valueOf(tableView.getNumberPage()));
    }
}
