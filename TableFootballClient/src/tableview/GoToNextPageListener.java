package tableview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToNextPageListener implements ActionListener {
    private ChangeTablePanel changeTablePanel;

    public GoToNextPageListener(ChangeTablePanel changeTablePanel){
        this.changeTablePanel = changeTablePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StudentTableView tableView = changeTablePanel.getTableView();
        int currentPageIndex = tableView.getIndexPage();
        if (currentPageIndex < tableView.getNumberPage()){
            tableView.goToPage(currentPageIndex+1);
            changeTablePanel.getCurrentPageIndex().setText(String.valueOf(currentPageIndex+1));
        }
    }
}
