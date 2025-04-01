
package ru.mzpo.forms;

public class Basket {
    public static String name = "Supertester_Basket_MZPO";
    public static String email = "guv@guv.ebay";
   public static String tel = "1300000002";


    public static String getName() {
        return name;
    }

    public static String getEmail() {
        return email;
    }

    public static String getTel() {

        return tel;
    }


    public Basket() {
        this.name = name;
        this.email = email;
        this.tel = tel;
           }
}
