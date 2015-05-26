package tableview;

import tablemodel.StudentModel;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class StudentTableModel extends AbstractTableModel {
    private List<StudentModel> studentListBuffer;
    private int numberRecordsOnPage;
    private int indexPage;

    public StudentTableModel(List<StudentModel> studentListBuffer){
        setStudentListBuffer(studentListBuffer);
    }

    @Override
    public int getRowCount() {
        return studentListBuffer.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex != 0){
            return studentListBuffer.get(rowIndex).getField(columnIndex);
        } else {
            return numberRecordsOnPage*(indexPage-1)+rowIndex+1;
        }
    }

    @Override
    public String getColumnName(int column) {
        String columnName[] ={
            "№",
            "ФИО студента",
            "Дата рождения",
            "Футбольная команда",
            "Факультет",
            "Состав",
            "Позиция"
        };
        return columnName[column];
    }

    public void setStudentListBuffer(List<StudentModel> studentListBuffer){
        this.studentListBuffer = studentListBuffer;
    }
    public List<StudentModel> getStudentListBuffer(){
        return studentListBuffer;
    }

    public int getNumberRecordsOnPage() {
        return numberRecordsOnPage;
    }

    public void setNumberRecordsOnPage(int numberRecordsOnPage) {
        this.numberRecordsOnPage = numberRecordsOnPage;
    }

    public int getIndexPage() {
        return indexPage;
    }

    public void setIndexPage(int indexPage) {
        this.indexPage = indexPage;
    }
}
