import lab14lib.Generator;

/**
 * Created by mwang on 5/29/17.
 */
public class SawToothGenerator implements Generator {
    private int period;
    private int state;

    public SawToothGenerator(int p) {
        this.period = p;
        this.state = 0;
    }

    public double next() {
        state = (state + 1) % period;

        double astate = state * 1.0 / this.period;

        return normalize(astate);
    }

    public double normalize(double aState) {
        return aState * 2 - 1;
    }

}
