package edit;

import tableview.TablePanel;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdditionListener implements ActionListener {
    JTabbedPane tableTab;
    JFrame mainWindow;
    JDialog additionStudentFrame;
    public AdditionListener(JFrame mainWindow,JTabbedPane tableTab){
        this.mainWindow = mainWindow;
        this.tableTab = tableTab;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Добавить")){
            TablePanel tablePanel = (TablePanel)tableTab.getSelectedComponent();
            additionStudentFrame = new JDialog(mainWindow,"Addition of student",false);
            additionStudentFrame.setSize(400, 500);
            additionStudentFrame.setLocationRelativeTo(tablePanel);
            additionStudentFrame.setVisible(true);
            additionStudentFrame.setLayout(new BorderLayout());

            AdditionDialogPanel additionDialogPanel = new AdditionDialogPanel(tablePanel);
            additionStudentFrame.add(additionDialogPanel,BorderLayout.NORTH);
        }
    }
}
