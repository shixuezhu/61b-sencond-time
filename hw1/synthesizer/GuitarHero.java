package synthesizer;
import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mwang on 5/18/17.
 */
public class GuitarHero {
    private static final double CONCERT_A = 440.0;
    private static final double CONCERT_C = CONCERT_A * Math.pow(2, 3.0 / 12.0);

    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C */
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";


        Map<String, GuitarString> allString = new HashMap<>();
        for (int i = 0; i < keyboard.length(); i ++) {
            GuitarString temp = new GuitarString(440 * 2^((i - 24) / 12));
            allString.put(String.valueOf(keyboard.charAt(i)), temp);
        }

        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                allString.get(key).pluck();
            }

        /* compute the superposition of samples */
            double sample = 0.0;
            for (String s: allString.keySet()) {
                sample += (allString.get(s).sample());
            }

        /* play the sample on standard audio */
            StdAudio.play(sample);

        /* advance the simulation of each guitar string by one step */
            //stringA.tic();
            //stringC.tic();
            for (String s: allString.keySet()) {
                allString.get(s).tic();
            }
        }
    }
}

