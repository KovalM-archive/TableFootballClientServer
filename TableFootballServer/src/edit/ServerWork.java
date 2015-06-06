package edit;

import tablemodel.StudentModel;
import tablemodel.StudentsList;
import window.ServerWorkPanel;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

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
            SearchStudentTerms searchStudentTerms = null;
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
                } else if (command.equals(CommandConst.REMOVE)){
                    studentsListBuffer.findRemoveStudents(searchStudentTerms);
                } else if (command.equals(CommandConst.END_FIND_REMOVE)){
                    studentsList = studentsListBuffer;
                } else if (command.equals(CommandConst.GET_FIND_TERMS)){
                    searchStudentTerms = (SearchStudentTerms)inputStream.readObject();
                    studentsList.setStudentList(studentsListBuffer.findStudents(searchStudentTerms));
                } else if (command.equals(CommandConst.SAVE)){
                    String nameFile = (String) inputStream.readObject();
                    new XMLFile(nameFile,studentsList).writeFile();
                } else if (command.equals(CommandConst.GET_FILE_LIST)){
                    File[] files = new File(CommandConst.DIRICTORY).listFiles();
                    List<String> filesName = new ArrayList<String>();
                    for (File currentFile : files) {
                        if (currentFile.getName().contains(".xml")) {
                            filesName.add(currentFile.getName());
                        }
                    }
                    outputStream.writeObject(filesName);
                } else if (command.equals(CommandConst.OPEN)){
                    String nameFile = (String) inputStream.readObject();
                    new XMLFile(nameFile,studentsList).readFile();
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
          e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
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
