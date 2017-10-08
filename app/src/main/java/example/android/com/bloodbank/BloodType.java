package example.android.com.bloodbank;

import android.util.Log;

import java.io.Serializable;

/**
 * Created by default on 10/7/2017.
 */

public class BloodType implements Serializable {
    private char firstType;
    private char secType;
    private int RhFactor;
    private boolean AB;
    private final String BAD_CREATION = "Bad Creation";
    /*
        Constructor for non-type AB bloods

        @param first: Char A or B
        @param rhFactor: 0- positive, 1- negative
     */
    public BloodType(char first, int rhFactor) {
        this.firstType = first;
        this.RhFactor = rhFactor;
        this.AB = false;
    }

    public BloodType () {}
    /*
        Constructor for type AB blood type

        @param first - the first type. A or B
        @param second - the second type. Always B if type AB blood
        @param rhFactor - 0: positive 1: negative
     */
    public BloodType(boolean isAB, int rhFactor) {
        if (isAB) {
            this.firstType = 'A';
            this.secType = 'B';
            this.RhFactor = rhFactor;
            this.AB = true;
        }
        else {
            Log.d(BAD_CREATION, "Wrong use of method occured");
        }
    }

    /*
        Converts the rH integer (0 or 1) to a + or -
        @return: string value of + or -
     */
    private String convertRhToStr() {
        String rH = "";

        switch (this.RhFactor) {
            case 0:
                rH = "+";
                break;
            case 1:
                rH = "-";
                break;
            default:
                rH = "a";
        }

        return rH;
    }

    /*
        Returns the String version of the blood type

        @return: String version of bloodtype
    */

    public String getBloodType() {
        String bType = "";
        if (this.AB) {
            if (this.RhFactor == 0) {
                bType = "AB+";
            }
            else {
                bType = "AB-";
            }
        }
        else {
            bType = this.firstType + this.convertRhToStr();
        }
        return bType;
    }
}
