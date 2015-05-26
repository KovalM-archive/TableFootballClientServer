package tableview;

import javax.swing.JPanel;

public class TablePanel extends JPanel {
    private StudentTableView tableView;
    private ChangeTablePanel changeTablePanel;
    public TablePanel(StudentTableView tableView, ChangeTablePanel changeTablePanel){
        this.tableView = tableView;
        this.changeTablePanel = changeTablePanel;
    }

    public StudentTableView getTableView() {
        return tableView;
    }

    public ChangeTablePanel getChangeTablePanel() {
        return changeTablePanel;
    }
}
