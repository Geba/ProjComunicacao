/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

/**
 *
 * @author Geeo
 */
public class Status {

    public static int nStatus = 8;
    //public static final int
    public static final int ONLINE = 2;
    public static final int HUNGRY = 1;
    public static final int SEXY = 0;
    public static final int SLEEPY = 3;
    public static final int IMPATIENT = 4;
    public static final int HAPPY = 5;
    public static final int SAD = 6;
    public static final int HULK = 7;

    public static String whichStatus(int status) {
        String s = "Vc esqueceu de me inicializar";
        switch (status) {
            case ONLINE:
                s = "Online";
                break;
            case HUNGRY:
                s = "Hungry";
                break;
            case SEXY:
                s = "Sexy";
                break;
            case SLEEPY:
                s = "Sleepy";
                break;
            case IMPATIENT:
                s = "Impatient";
                break;
            case HAPPY:
                s = "Happy";
                break;
            case SAD:
                s = "Sad";
                break;
            case HULK:
                s = "Hulk";
                break;
        }

        return s;
    }

    public static int getNumberOfStatus() {
        return nStatus;
    }
}
