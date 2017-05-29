import lab14lib.Generator;

/**
 * Created by mwang on 5/29/17.
 */
public class AcceleratingSawToothGenerator implements Generator {
    private int period;
    private double increRate;
    private int state;
    public AcceleratingSawToothGenerator(int p, double i) {
        this.period = p;
        this.increRate = i;
        this.state = 0;
    }

    public double next() {
        state = (state + 1) % period;
        if (state == 0) {
            period *= 1.1;
        }

        double astate = state * 1.0 / period;
        return normalize(astate);
    }

    private double normalize(double init) {
        return init * 2 - 1;
    }
}
