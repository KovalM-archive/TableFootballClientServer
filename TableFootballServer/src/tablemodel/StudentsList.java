package tablemodel;

import edit.SearchStudentTerms;

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

    public void setStudentList(List<StudentModel> newStudentList){
        studentList = newStudentList;
    }

    public List<StudentModel> findStudents(SearchStudentTerms searchStudentTerms){
        List<StudentModel> resultStudentList = new ArrayList<StudentModel>();
        int number = studentList.size();
        for (int i = 0; i < number; i++) {
            StudentModel student = studentList.get(i);
            if (foundAgreement(student,searchStudentTerms)){
                StudentModel newStudent = student.clone();
                resultStudentList.add(newStudent);
            }
        }
        return resultStudentList;
    }

    public void findRemoveStudents(SearchStudentTerms searchStudentTerms){
        int number = studentList.size();
        for (int i = 0; i < number; i++) {
            StudentModel student = studentList.get(i);
            if (foundAgreement(student,searchStudentTerms)){
                studentList.remove(student);
                i--;
                number--;
            }
        }
    }

    private boolean foundAgreement(StudentModel student,SearchStudentTerms currentStudent){
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
