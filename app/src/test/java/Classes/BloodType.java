package Classes;

/**
 * Created by default on 10/7/2017.
 */

public class BloodType {
    private char firstType;
    private char secType;
    private int RhFactor;
    private boolean AB;

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
    /*
        Constructor for type AB blood type

        @param: first - the first type. A or B
        @param: second - the second type. Always B if type AB blood
        @param:rhFactor - 0: positive 1: negative
     */
    public BloodType(char first, char second, int rhFactor) {
        this.firstType = first;
        this.secType = second;
        this.RhFactor = rhFactor;
        this.AB = true;
    }
}
