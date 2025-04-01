package ru.mzpo.forms;

public class TrialLesson {
    public static String name = "Supertester_TrialLesson_MZPO";
    public static String email = "moko@moko.ebay";
    public static String tel = "1900000002";
    public static String  surname= "TrialLesson";

    public static String getName() {
        return name;
    }

    public static String getEmail() {
        return email;
    }

    public static String getTel() {

        return tel;
    }

    public static String getSurname() {

        return surname;
    }

    public TrialLesson() {
        this.name = name;
        this.email = email;
        this.tel = tel;
        this.surname = surname;
    }
}