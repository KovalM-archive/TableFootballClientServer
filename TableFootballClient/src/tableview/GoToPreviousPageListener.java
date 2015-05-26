package tableview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToPreviousPageListener implements ActionListener {
    private ChangeTablePanel changeTablePanel;

    public GoToPreviousPageListener(ChangeTablePanel changeTablePanel){
        this.changeTablePanel = changeTablePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StudentTableView tableView = changeTablePanel.getTableView();
        int currentPageIndex = tableView.getIndexPage();
        if (currentPageIndex > 1){
            tableView.goToPage(currentPageIndex-1);
            changeTablePanel.getCurrentPageIndex().setText(String.valueOf(currentPageIndex-1));
        }
    }
}
