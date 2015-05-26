package tableview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeNumberPageListener implements ActionListener {
    private ChangeTablePanel changeTablePanel;

    public ChangeNumberPageListener(ChangeTablePanel changeTablePanel){
        this.changeTablePanel = changeTablePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        changeTablePanel.getTableView().setNumberRecordsOnPage(changeTablePanel.getNumberPage().getSelectedIndex()+1);
        changeTablePanel.getTableView().goToPage(1);
        changeTablePanel.getAllPage().setText(String.valueOf(changeTablePanel.getTableView().getNumberPage()));
        changeTablePanel.getCurrentPageIndex().setText(String.valueOf(changeTablePanel.getTableView().getIndexPage()));
    }
}
