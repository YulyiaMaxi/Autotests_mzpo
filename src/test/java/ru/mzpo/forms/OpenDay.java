
package ru.mzpo.forms;

public class OpenDay {
    public static String name = "Supertester_OpenDay_MZPO";
    public static String surname = "1";
    public static String tel = "1400000002";
    public static String email = "open@open.ru";
    public static String checkbox;

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

    public OpenDay() {
        this.name = name;
        this.email = email;
        this.tel = tel;
        this.surname = surname;
    }
}
