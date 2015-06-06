package edit;

import tableview.TablePanel;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class CloseSearchDialogListener implements WindowListener {
    private ObjectOutputStream outputStream;
    private TablePanel tablePanel;

    public CloseSearchDialogListener(ObjectOutputStream outputStream, TablePanel tablePanel){
        this.outputStream = outputStream;
        this.tablePanel = tablePanel;
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        try {
            outputStream.writeObject("End find or remove");
            tablePanel.getTableView().goToPage(1);
            tablePanel.getChangeTablePanel().getAllRecord().setText(
                    String.valueOf(tablePanel.getTableView().getCountRecord()));
            tablePanel.getChangeTablePanel().getAllPage().setText(
                    String.valueOf(tablePanel.getTableView().getNumberPage()));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
