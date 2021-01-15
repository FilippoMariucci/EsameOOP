package Utilities;

public class Contatore {
    private int val;
    public Contatore() {
        val = 0;
    }
    public void reset() {
        val = 0;
    }
    public void inc() {
        val++;
    }
    public String toString() {
        return "Counter di valore " + val;
    }
}
