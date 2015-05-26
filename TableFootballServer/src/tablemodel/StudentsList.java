package tablemodel;

import java.util.ArrayList;
import java.util.List;

public class StudentsList {
    private List<StudentModel> studentList;

    public StudentsList(){
        studentList = new ArrayList<StudentModel>();
    }

    public int getCountRecord(){
        return studentList.size();
    }

    public void addStudent(StudentModel currentStudent){
        studentList.add(currentStudent);
    }

    public StudentModel getStudentAtIndex(int x){
        if (x>=0 && x < studentList.size()){
            return studentList.get(x);
        } else{
            return null;
        }
    }

    public void removeStudent(StudentModel oldStudent){
        studentList.remove(oldStudent);
    }

    public List<StudentModel> getStudentList(){
        return studentList;
    }
}
