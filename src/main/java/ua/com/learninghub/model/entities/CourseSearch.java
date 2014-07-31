package ua.com.learninghub.model.entities;

import java.util.List;

/**
 * Created by Max on 28.07.2014.
 */
public class CourseSearch {
    CourseSearchType searchType;
    CourseSortType sortType;

    private int idFrom;
    private int idTo;

    private String keywords;

    private int idSpeciality;
    private int idDiscipline;
    private int idSubject;


    public CourseSortType getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = CourseSortType.valueOf(sortType);
    }

    public CourseSearchType getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = CourseSearchType.valueOf(searchType);
    }

    public int getIdFrom() {
        return idFrom;
    }

    public void setIdFrom(int idFrom) {
        this.idFrom = idFrom;
    }

    public int getIdTo() {
        return idTo;
    }

    public void setIdTo(int idTo) {
        this.idTo = idTo;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public int getIdSpeciality() {
        return idSpeciality;
    }

    public void setIdSpeciality(int idSpeciality) {
        this.idSpeciality = idSpeciality;
    }

    public int getIdDiscipline() {
        return idDiscipline;
    }

    public void setIdDiscipline(int idDiscipline) {
        this.idDiscipline = idDiscipline;
    }

    public int getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(int idSubject) {
        this.idSubject = idSubject;
    }

    @Override
    public String toString() {
        return "CourseSearch{" +
                "idFrom=" + idFrom +
                ", idTo=" + idTo +
                ", keywords='" + keywords + '\'' +
                ", idSpeciality=" + idSpeciality +
                ", idDiscipline=" + idDiscipline +
                ", idSubject=" + idSubject +
                ", searchType=" + searchType +
                '}';
    }
}
