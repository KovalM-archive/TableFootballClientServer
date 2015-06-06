package edit;

import tablemodel.StudentModel;

import java.io.Serializable;

public class SearchStudentTerms extends StudentModel implements Serializable {
    private boolean firstNameFlag;
    private boolean secondNameFlag;
    private boolean thirdNameFlag;
    private boolean dateBirthFlag;
    private boolean footballTeamNameFlag;
    private boolean facultyNameFlag;
    private boolean squadFlag;
    private boolean positionFlag;

    public SearchStudentTerms(){
        super();
        allFalse();
    }

    public boolean isFirstNameFlag() {
        return firstNameFlag;
    }

    public boolean isSecondNameFlag() {
        return secondNameFlag;
    }

    public boolean isThirdNameFlag() {
        return thirdNameFlag;
    }

    public boolean isDateBirthFlag() {
        return dateBirthFlag;
    }

    public boolean isFootballTeamNameFlag() {
        return footballTeamNameFlag;
    }

    public boolean isFacultyNameFlag() {
        return facultyNameFlag;
    }

    public boolean isSquadFlag() {
        return squadFlag;
    }

    public boolean isPositionFlag() {
        return positionFlag;
    }

    public void allFalse(){
        firstNameFlag = false;
        secondNameFlag = false;
        thirdNameFlag = false;
        dateBirthFlag = false;
        footballTeamNameFlag = false;
        facultyNameFlag = false;
        squadFlag = false;
        positionFlag = false;
    }

    public void setFirstNameFlag(boolean firstNameFlag) {
        this.firstNameFlag = firstNameFlag;
    }

    public void setSecondNameFlag(boolean secondNameFlag) {
        this.secondNameFlag = secondNameFlag;
    }

    public void setThirdNameFlag(boolean thirdNameFlag) {
        this.thirdNameFlag = thirdNameFlag;
    }

    public void setDateBirthFlag(boolean dateBirthFlag) {
        this.dateBirthFlag = dateBirthFlag;
    }

    public void setFootballTeamNameFlag(boolean footballTeamNameFlag) {
        this.footballTeamNameFlag = footballTeamNameFlag;
    }

    public void setFacultyNameFlag(boolean facultyNameFlag) {
        this.facultyNameFlag = facultyNameFlag;
    }

    public void setSquadFlag(boolean squadFlag) {
        this.squadFlag = squadFlag;
    }

    public void setPositionFlag(boolean positionFlag) {
        this.positionFlag = positionFlag;
    }
}
