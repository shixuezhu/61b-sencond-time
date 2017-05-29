import lab14lib.Generator;

/**
 * Created by mwang on 5/29/17.
 */
public class StrangeBitWiseGenerator implements Generator {
    private int period;
    private int state;

    public StrangeBitWiseGenerator(int periods) {
        this.period = periods;
        this.state = 0;
    }

    public double next() {
        state += 1;
        int weirdState = state & (state >>> 3) % period;

        double astate = weirdState * 1.0 / this.period;

        return normalize(astate);
    }

    public double normalize(double aState) {
        return aState * 2 - 1;
    }
}
