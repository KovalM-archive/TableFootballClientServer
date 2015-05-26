package tableview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToFirstPageListener implements ActionListener {
    private ChangeTablePanel changeTablePanel;

    public GoToFirstPageListener(ChangeTablePanel changeTablePanel){
        this.changeTablePanel = changeTablePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StudentTableView tableView = changeTablePanel.getTableView();
        tableView.goToPage(1);
        changeTablePanel.getCurrentPageIndex().setText(String.valueOf(1));
    }
}
