package javaLibrary;
import java.io.*;

public class Book implements Serializable{
    private int id;
    private String code;
    private String title;
    private String authorLastName;
    private int year;
    private int copiesCount;

    public Book(){}
    public Book(int Id,String code, String title, String authorLastName, int year, int copies) {
        this.id=Id;
        this.code = code;
        this.title = title;
        this.authorLastName = authorLastName;
        this.year = year;
        this.copiesCount = copies;
    }
    public Book(String code, String title, String authorLastName, int year, int copies) {
        this.code = code;
        this.title = title;
        this.authorLastName = authorLastName;
        this.year = year;
        this.copiesCount = copies;
    }
    public int getId() {
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code){
        this.code=code;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title){
        this.title=title;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }
    public void setAuthorLastName(String authorLastName){
        this.authorLastName=authorLastName;
    }

    public int getYear() {
        return year;
    }
    public void setYear(int Year){
        this.year=Year;
    }


    public int getCopies() {
        return copiesCount;
    }
    public void setCopies(int Copies){
        this.copiesCount=Copies;
    }

    @Override
    public String toString() {
        return "Book{" +
                "code='" + code + '\'' +
                ", title='" + title + '\'' +
                ", authorLastName='" + authorLastName + '\'' +
                ", year=" + year +
                ", copies=" + copiesCount +
                '}';
    }
}
