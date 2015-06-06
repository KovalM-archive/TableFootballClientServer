package tablemodel;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class StudentModel implements Cloneable,Serializable {
    private String firstName;
    private String secondName;
    private String thirdName;
    private GregorianCalendar dateBirth;
    private String footballTeamName;
    private String facultyName;
    private int squad;
    private int position;

    public StudentModel(){
        dateBirth = new GregorianCalendar();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public GregorianCalendar getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(GregorianCalendar dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getFootballTeamName() {
        return footballTeamName;
    }

    public void setFootballTeamName(String footballTeamName) {
        this.footballTeamName = footballTeamName;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public int getSquad() {
        return squad;
    }

    public void setSquad(int squad) {
        this.squad = squad;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Object getField(int columnIndex){
        if (columnIndex == 1){
            return secondName + " " + firstName + " " + thirdName;
        } else if (columnIndex == 2){
            return dateBirth.get(GregorianCalendar.DAY_OF_MONTH) + "."
                    + dateBirth.get(GregorianCalendar.MONTH) + "."
                    + dateBirth.get(GregorianCalendar.YEAR);
        } else if (columnIndex == 3){
            return footballTeamName;
        } else if (columnIndex == 4){
            return facultyName;
        } else if (columnIndex == 5){
           return squad;
        } else if (columnIndex == 6){
            return position;
        } else{
            return null;
        }
    }

    public StudentModel clone(){
        StudentModel newStudent = new StudentModel();
        newStudent.setFirstName(firstName);
        newStudent.setSecondName(secondName);
        newStudent.setThirdName(thirdName);
        newStudent.setDateBirth(dateBirth);
        newStudent.setFootballTeamName(footballTeamName);
        newStudent.setFacultyName(facultyName);
        newStudent.setSquad(squad);
        newStudent.setPosition(position);
        return newStudent;
    }
}
