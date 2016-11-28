package com.kotkovets.fines.common.display;

/**
 * Created by igork on 2/14/16.
 */
public class DriverDisplayItem {
    String name;
    String surname;
    String patronymic;
    String series;
    String number;
    int userId;

    public DriverDisplayItem(String name, String surname, String patronymic, String series, String number, int id) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.series = series;
        this.number = number;
        this.userId = id;
    }

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

    public int getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return name + " " + surname + " " + patronymic;
    }
}
