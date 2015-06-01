package clientswork;

import tablemodel.StudentModel;
import tablemodel.StudentsList;
import window.ServerWorkPanel;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerWork extends Thread {
    private Socket socket;
    private ServerWorkPanel workPanel;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private String clientName;

    public ServerWork(Socket socket,ServerWorkPanel workPanel) throws IOException{
        this.socket = socket;
        this.workPanel = workPanel;
        clientName = socket.getInetAddress().getCanonicalHostName();

        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());
        outputStream.flush();
        start();
    }

    public void run() {
        try {
            StudentsList studentsList = new StudentsList();
            StudentsList studentsListBuffer = new StudentsList();
            Object searchStudentTerms;
            String command;
            workPanel.getTextArea().append(clientName + " Client connected\n");

            while (true) {
                command = (String) inputStream.readObject();
                workPanel.getTextArea().append(clientName + " " + command + "\n");
                if (command.equals(CommandConst.NEW_TABLE)) {
                    studentsList = new StudentsList();
                } else if (command.equals(CommandConst.ADD_STUDENT)) {
                    studentsList.addStudent((StudentModel) inputStream.readObject());
                } else if (command.equals(CommandConst.REMOVE_STUDENT)) {
                    studentsList.removeStudent((StudentModel) inputStream.readObject());
                } else if (command.equals(CommandConst.GET_COUNT_RECORDS)) {
                    outputStream.writeObject(studentsList.getCountRecord());
                } else if (command.equals(CommandConst.GET_STUDENT_AT_INDEX)) {
                    int num = (Integer) inputStream.readObject();
                    outputStream.writeObject(studentsList.getStudentAtIndex(num));
                } else if (command.equals(CommandConst.FIND)){
                    studentsListBuffer = studentsList;
                    studentsList = new StudentsList();
                }else if (command.equals(CommandConst.FIND_AND_REMOVE)){

                } else if (command.equals(CommandConst.END_FIND_REMOVE)){
                    studentsList = studentsListBuffer;
                } else if (command.equals(CommandConst.GET_FIND_TERMS)){
                    searchStudentTerms = inputStream.readObject();
                    studentsList.setStudentList(studentsListBuffer.findStudents((SearchStudentTerms)searchStudentTerms));
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
          e.printStackTrace();
        } finally {
            try {
                outputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
