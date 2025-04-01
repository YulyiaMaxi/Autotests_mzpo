
package ru.education.forms;

public class BasketEDU {
    public static String name = "Supertester_Basket_EDU";
    public static String name1 = "Supertester_Basket_Apply_EDU";
    public static String surname = "testov";
    public static String patronimic = "testovivich";
    public static String email = "mu@kmu.com";
    public static String tel = "1500000031";

    public static String getName() {
        return name;
    }

    public static String getTel() {

        return tel;}
        public static String getEmail() {

            return email;
    }
    public static String getSurname() {

        return surname;
    }

    public static String getPatronimic() {

        return patronimic;

    }

    public static String getName1() {

        return name1;
    }

    public BasketEDU() {
        this.name = name;
        this.tel = tel;
        this.patronimic = patronimic;
        this.surname = surname;
        this.email = email;

    }
}

