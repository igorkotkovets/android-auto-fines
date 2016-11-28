package com.kotkovets.fines.common.model;

/**
 * Created by igork on 4/16/16.
 */
public class Driver {
    public static final int USER_ID_INVALID = 0;

    private String name;
    private String surname;
    private String patronymic;
    private String series;
    private String number;
    private int uniqueKey;

    public Driver(String name, String surname, String patronymic, String series, String number, int uniqKey) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.series = series;
        this.number = number;
        this.uniqueKey = uniqKey;
    }

    public Driver(String name, String surname, String patronymic, String series, String number) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.series = series;
        this.number = number;
        this.uniqueKey = hashCode(name, surname, patronymic, series, number);
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

    public int getUniqueKey() {
        return uniqueKey;
    }

    private int hashCode(String name, String surname, String patronymic, String series, String number) {
        int nameHash = (name != null)?name.hashCode():0;
        int surnameHash = (surname != null)?surname.hashCode():0;
        int patronymicHash = (patronymic != null)?patronymic.hashCode():0;
        int seriesHash = (series != null)?series.hashCode():0;
        int numberHash = (number != null)?number.hashCode():0;

        return nameHash + surnameHash + patronymicHash + seriesHash + numberHash;
    }
}
