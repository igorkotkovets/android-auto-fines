package com.kotkovets.fines.common.display;

/**
 * Created by igork on 5/25/16.
 */
public class DisplayDriverInfo {
    private String name;
    private String surname;
    private String patronymic;
    private String series;
    private String number;
    private Boolean progressBarVisible;
    private String fineResult;
    private int userId;


    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getSeries() {
        return series;
    }

    public String getNumber() {
        return number;
    }

    public String getFineResult() {
        return fineResult;
    }

    public int getUserId() {
        return userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setFineResult(String fineResult) {
        this.fineResult = fineResult;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Boolean getProgressBarVisible() {
        return progressBarVisible;
    }

    public void setProgressBarVisible(Boolean progressBarVisible) {
        this.progressBarVisible = progressBarVisible;
    }
}
