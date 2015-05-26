package tableview;

import tablemodel.StudentModel;

import javax.swing.JTable;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class StudentTableView extends JTable {
    StudentTableModel tableModel;
    private int numberPage;
    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public StudentTableView(StudentTableModel tableModel,Socket socket){
        super(tableModel);
        this.tableModel = tableModel;
        this.socket = socket;

        try {
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        tableModel.setNumberRecordsOnPage(1);
        tableModel.setIndexPage(1);
        calculateNumberPage();
        goToPage(1);
    }

    public int getNumberPage() {
        calculateNumberPage();
        return numberPage;
    }

    public void setNumberPage(int numberPage) {
        this.numberPage = numberPage;
    }

    public void setNumberRecordsOnPage(int newNumber){
        tableModel.setNumberRecordsOnPage(newNumber);
    }

    public int getIndexPage() {
        return tableModel.getIndexPage();
    }

    public void goToPage(int newPageIndex){
        if (newPageIndex <= numberPage){
            List<StudentModel> studentListBuffer = new ArrayList<StudentModel>();

            int l = tableModel.getNumberRecordsOnPage()*(newPageIndex-1);
            int r = l + tableModel.getNumberRecordsOnPage();
            int countRecods = getCountRecord();
            for (int i = l+1; i <=r; i++) {
                if (i > countRecods){
                    break;
                }
                studentListBuffer.add(getStudentAtIndex(i-1));
            }

            StudentTableModel newTableModel = new StudentTableModel(studentListBuffer);
            newTableModel.setIndexPage(newPageIndex);
            newTableModel.setNumberRecordsOnPage(tableModel.getNumberRecordsOnPage());
            tableModel = newTableModel;
            this.setModel(newTableModel);
        }
    }

    public int getCountRecord(){
        int countRecord = 0;
        try {
            outputStream.writeObject("Get count records");
            boolean flag = false;
            while (true){
                try{
                    countRecord = (Integer)inputStream.readObject();
                    flag = true;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                if (flag){
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return countRecord;
    }

    private void calculateNumberPage(){
        int x = 0;
        int numberRecordsOnPage = tableModel.getNumberRecordsOnPage();
        int countRecord = getCountRecord();
        while (x*numberRecordsOnPage < countRecord) {
            x++;
        }
        numberPage = x;
    }

    public void addStudent(StudentModel currentStudent){
        try {
            outputStream.writeObject("Add student");
            outputStream.writeObject(currentStudent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        calculateNumberPage();
        goToPage(tableModel.getIndexPage());
    }

    public StudentModel getStudentAtIndex(int x){
        int countRecods = getCountRecord();
        StudentModel currentStudent = null;

        if (x>=0 && x < countRecods){
            try {
                outputStream.writeObject("Get student at index");
                outputStream.writeObject(x);
                boolean flag = false;
                while (true){
                    try{
                        currentStudent = (StudentModel)inputStream.readObject();
                        flag = true;
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    if (flag){
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return currentStudent;
        } else{
            return null;
        }
    }

    public void removeStudent(StudentModel oldStudent){
        try {
            outputStream.writeObject("Remove student");
            outputStream.writeObject(oldStudent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        calculateNumberPage();
        goToPage(1);
    }

    public List<StudentModel> getStudentList(){
        return null;
    }
}
