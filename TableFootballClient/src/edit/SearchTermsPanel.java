package edit;

import com.toedter.calendar.JDateChooser;
import tablemodel.StudentModel;
import tableview.TablePanel;
import tableview.ChangeTablePanel;
import tableview.StudentTableView;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.GregorianCalendar;

public class SearchTermsPanel extends JPanel {
    private SearchStudentTerms currentStudent;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public SearchTermsPanel(final TablePanel tablePanel, final TablePanel tablePanelTable,
                            String textButton, ObjectInputStream inputStream, final ObjectOutputStream outputStream){
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        currentStudent = new SearchStudentTerms();
        currentStudent.allFalse();
        this.setLayout(new GridBagLayout());

        final JLabel enterFirstNameText = new JLabel("Имя:");
        final JTextField enterFirstName = new JTextField("",10);
        enterFirstName.setEnabled(false);
        enterFirstNameText.setEnabled(false);

        final JLabel enterSecondNameText = new JLabel("Фамилия:");
        final JTextField enterSecondName = new JTextField("",10);
        enterSecondName.setEnabled(false);
        enterSecondNameText.setEnabled(false);

        final JLabel enterThirdNameText = new JLabel("Отчество:");
        final JTextField enterThirdName = new JTextField("",10);
        enterThirdName.setEnabled(false);
        enterThirdNameText.setEnabled(false);

        final JLabel enterDateBirthText = new JLabel("Дата рождения:");
        final JDateChooser enterDateBirth = new JDateChooser();
        enterDateBirth.setEnabled(false);
        enterDateBirthText.setEnabled(false);

        final JLabel enterFootballTeamNameText = new JLabel("Футбольная команда:");
        final JTextField enterFootballTeamName = new JTextField("",10);
        enterFootballTeamName.setEnabled(false);
        enterFootballTeamNameText.setEnabled(false);

        final JLabel enterFacultyText = new JLabel("Факультет:");
        final JComboBox<String> enterFaculty = new JComboBox<String>(StudentsConst.FACULTY);
        enterFaculty.setEnabled(false);
        enterFacultyText.setEnabled(false);

        final JLabel enterSquadText = new JLabel("Состав:");
        final JComboBox<Integer> enterSquad = new JComboBox<Integer>(StudentsConst.SQUAD);
        enterSquad.setEnabled(false);
        enterSquadText.setEnabled(false);

        final JLabel enterPositionText = new JLabel("Позиция:");
        final JTextField enterPosition = new JTextField("",10);
        enterPosition.setEnabled(false);
        enterPositionText.setEnabled(false);

        JCheckBox enterFirstNameCheck = new JCheckBox();
        enterFirstNameCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentStudent.isFirstNameFlag()){
                    currentStudent.setFirstNameFlag(false);
                    enterFirstName.setEnabled(false);
                    enterFirstNameText.setEnabled(false);
                    enterFirstName.setText("");
                }else{
                    currentStudent.setFirstNameFlag(true);
                    enterFirstName.setEnabled(true);
                    enterFirstNameText.setEnabled(true);
                }
            }
        });

        JCheckBox enterSecondNameCheck = new JCheckBox();
        enterSecondNameCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentStudent.isSecondNameFlag()){
                    currentStudent.setSecondNameFlag(false);
                    enterSecondName.setEnabled(false);
                    enterSecondNameText.setEnabled(false);
                }else{
                    currentStudent.setSecondNameFlag(true);
                    enterSecondName.setEnabled(true);
                    enterSecondNameText.setEnabled(true);
                }
            }
        });

        JCheckBox enterThirdNameCheck = new JCheckBox();
        enterThirdNameCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentStudent.isThirdNameFlag()) {
                    currentStudent.setThirdNameFlag(false);
                    enterThirdName.setEnabled(false);
                    enterThirdNameText.setEnabled(false);
                } else {
                    currentStudent.setThirdNameFlag(true);
                    enterThirdName.setEnabled(true);
                    enterThirdNameText.setEnabled(true);
                }
            }
        });

        JCheckBox enterDateBirthCheck = new JCheckBox();
        enterDateBirthCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentStudent.isDateBirthFlag()) {
                    currentStudent.setDateBirthFlag(false);
                    enterDateBirth.setEnabled(false);
                    enterDateBirthText.setEnabled(false);
                } else {
                    currentStudent.setDateBirthFlag(true);
                    enterDateBirth.setEnabled(true);
                    enterDateBirthText.setEnabled(true);
                }
            }
        });
        JCheckBox enterFootballTeamNameCheck = new JCheckBox();
        enterFootballTeamNameCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentStudent.isFootballTeamNameFlag()) {
                    currentStudent.setFootballTeamNameFlag(false);
                    enterFootballTeamName.setEnabled(false);
                    enterFootballTeamNameText.setEnabled(false);
                } else {
                    currentStudent.setFootballTeamNameFlag(true);
                    enterFootballTeamName.setEnabled(true);
                    enterFootballTeamNameText.setEnabled(true);
                }
            }
        });
        JCheckBox enterFacultyNameCheck = new JCheckBox();
        enterFacultyNameCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentStudent.isFacultyNameFlag()) {
                    currentStudent.setFacultyNameFlag(false);
                    enterFaculty.setEnabled(false);
                    enterFacultyText.setEnabled(false);
                } else {
                    currentStudent.setFacultyNameFlag(true);
                    enterFaculty.setEnabled(true);
                    enterFacultyText.setEnabled(true);
                }
            }
        });
        JCheckBox enterSquadCheck = new JCheckBox();
        enterSquadCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentStudent.isSquadFlag()) {
                    currentStudent.setSquadFlag(false);
                    enterSquad.setEnabled(false);
                    enterSquadText.setEnabled(false);
                } else {
                    currentStudent.setSquadFlag(true);
                    enterSquad.setEnabled(true);
                    enterSquadText.setEnabled(true);
                }
            }
        });
        JCheckBox enterPositionCheck = new JCheckBox();
        enterPositionCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentStudent.isPositionFlag()) {
                    currentStudent.setPositionFlag(false);
                    enterPosition.setEnabled(false);
                    enterPositionText.setEnabled(false);
                } else {
                    currentStudent.setPositionFlag(true);
                    enterPosition.setEnabled(true);
                    enterPositionText.setEnabled(true);
                }
            }
        });

        JButton enterButton = new JButton(textButton);
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentStudent.isFirstNameFlag()){
                    currentStudent.setFirstName(enterFirstName.getText());
                }
                if (currentStudent.isSecondNameFlag()){
                    currentStudent.setSecondName(enterSecondName.getText());
                }
                if (currentStudent.isThirdNameFlag()){
                    currentStudent.setThirdName(enterThirdName.getText());
                }
                if (currentStudent.isDateBirthFlag()) {
                    currentStudent.setDateBirth(new GregorianCalendar(enterDateBirth.getDate().getYear() + 1900,
                            enterDateBirth.getDate().getMonth() + 1,
                            enterDateBirth.getDate().getDate()));
                }
                if (currentStudent.isFootballTeamNameFlag()){
                    currentStudent.setFootballTeamName(enterFootballTeamName.getText());
                }
                if (currentStudent.isFacultyNameFlag()){
                    currentStudent.setFacultyName((String)enterFaculty.getSelectedItem());
                }
                if (currentStudent.isSquadFlag()){
                    currentStudent.setSquad(enterSquad.getSelectedIndex()+1);
                }
                if (currentStudent.isPositionFlag()){
                    currentStudent.setPosition(Integer.parseInt(enterPosition.getText()));
                }
                try{
                    outputStream.writeObject("Get find terms");
                    outputStream.writeObject(currentStudent);
                    /*StudentTableView tableViewAnswer = tablePanel.getTableView();
                    ChangeTablePanel changeTablePanelAnswer = tablePanel.getChangeTablePanel();
                    StudentTableView tableView = tablePanelTable.getTableView();
                    ChangeTablePanel changeTablePanel = tablePanelTable.getChangeTablePanel();


                    if (e.getActionCommand().equals("Найти и удалить")){
                        int i=0;
                        while (i<tableView.getCountRecord()){
                            StudentModel student = tableView.getStudentAtIndex(i);
                            if (foundAgreement(student)){
                                tableView.removeStudent(student);
                                changeTablePanel.getAllRecord().setText(
                                    String.valueOf(tableView.getCountRecord()));
                                changeTablePanel.getAllPage().setText(
                                    String.valueOf(tableView.getNumberPage()));
                            } else{
                               i++;
                            }
                        }
                    }*/
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        add(enterFirstNameCheck,new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(
                1, 1, 1, 1), 0, 0));
        add(enterFirstNameText, new GridBagConstraints(1, 0, 1, 1, 0, 0,
                GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(
                1, 1, 1, 1), 0, 0));
        add(enterFirstName,new GridBagConstraints(2, 0, 1, 1, 0, 0,
                GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(
                1, 1, 1, 1), 0, 0));

        add(enterSecondNameCheck,new GridBagConstraints(0, 1, 1, 1, 0, 0,
                GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(
                1, 1, 1, 1), 0, 0));
        add(enterSecondNameText,new GridBagConstraints(1, 1, 1, 1, 0, 0,
                GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(
                1, 1, 1, 1), 0, 0));
        add(enterSecondName,new GridBagConstraints(2, 1, 1, 1, 0, 0,
                GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(
                1, 1, 1, 1), 0, 0));

        add(enterThirdNameCheck,new GridBagConstraints(0, 2, 1, 1, 0, 0,
                GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(
                1, 1, 1, 1), 0, 0));
        add(enterThirdNameText,new GridBagConstraints(1, 2, 1, 1, 0, 0,
                GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(
                1, 1, 1, 1), 0, 0));
        add(enterThirdName,new GridBagConstraints(2, 2, 1, 1, 0, 0,
                GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(
                1, 1, 1, 1), 0, 0));

        add(enterDateBirthCheck,new GridBagConstraints(0, 3, 1, 1, 0, 0,
                GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(
                1, 1, 1, 1), 0, 0));
        add(enterDateBirthText,new GridBagConstraints(1, 3, 1, 1, 0, 0,
                GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(
                1, 1, 1, 1), 0, 0));
        add(enterDateBirth,new GridBagConstraints(2, 3, 1, 1, 0, 0,
                GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(
                1, 1, 1, 1), 0, 0));

        add(enterFootballTeamNameCheck,new GridBagConstraints(0, 4, 1, 1, 0, 0,
                GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(
                1, 1, 1, 1), 0, 0));
        add(enterFootballTeamNameText,new GridBagConstraints(1, 4, 1, 1, 0, 0,
                GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(
                1, 1, 1, 1), 0, 0));
        add(enterFootballTeamName,new GridBagConstraints(2, 4, 1, 1, 0, 0,
                GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(
                1, 1, 1, 1), 0, 0));

        add(enterFacultyNameCheck,new GridBagConstraints(0, 5, 1, 1, 0, 0,
                GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(
                1, 1, 1, 1), 0, 0));
        add(enterFacultyText,new GridBagConstraints(1, 5, 1, 1, 0, 0,
                GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(
                1, 1, 1, 1), 0, 0));
        add(enterFaculty,new GridBagConstraints(2, 5, 1, 1, 0, 0,
                GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(
                1, 1, 1, 1), 0, 0));

        add(enterSquadCheck,new GridBagConstraints(0, 6, 1, 1, 0, 0,
                GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(
                1, 1, 1, 1), 0, 0));
        add(enterSquadText,new GridBagConstraints(1, 6, 1, 1, 0, 0,
                GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(
                1, 1, 1, 1), 0, 0));
        add(enterSquad,new GridBagConstraints(2, 6, 1, 1, 0, 0,
                GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(
                1, 1, 1, 1), 0, 0));

        add(enterPositionCheck,new GridBagConstraints(0, 7, 1, 1, 0, 0,
                GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(
                1, 1, 1, 1), 0, 0));
        add(enterPositionText,new GridBagConstraints(1, 7, 1, 1, 0, 0,
                GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(
                1, 1, 1, 1), 0, 0));
        add(enterPosition,new GridBagConstraints(2, 7, 1, 1, 0, 0,
                GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(
                1, 1, 1, 1), 0, 0));

        add(enterButton,new GridBagConstraints(1, 8, 1, 1, 0, 0,
                GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(
                1, 1, 1, 1), 0, 0));
    }

    private boolean foundAgreement(StudentModel student){
        boolean flag = false;
        if (currentStudent.isFirstNameFlag()){
            flag = flag | currentStudent.getFirstName().equals(student.getFirstName());
        }
        if (currentStudent.isSecondNameFlag()){
            flag = flag | currentStudent.getSecondName().equals(student.getSecondName());
        }
        if (currentStudent.isThirdNameFlag()){
            flag = flag | currentStudent.getThirdName().equals(student.getThirdName());
        }
        if (currentStudent.isDateBirthFlag()) {
            flag = flag | currentStudent.getDateBirth().equals(student.getDateBirth());
        }
        if (currentStudent.isFootballTeamNameFlag()){
            flag = flag | currentStudent.getFootballTeamName().equals(student.getFootballTeamName());
        }
        if (currentStudent.isFacultyNameFlag()){
            flag = flag | currentStudent.getFacultyName().equals(student.getFacultyName());
        }
        if (currentStudent.isSquadFlag()){
            flag = flag | (currentStudent.getSquad() == student.getSquad());
        }
        if (currentStudent.isPositionFlag()){
            flag = flag | (currentStudent.getPosition() == student.getPosition());
        }
        return flag;
    }
}
