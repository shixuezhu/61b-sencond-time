package hw2;

import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    private double[] x;
    private int T;
    public PercolationStats(int N, int T) {   // perform T independent experiments on an N-by-N grid
        if (N <= 0 || T <= 0) throw new IllegalArgumentException();
        this.T = T;

        for (int i = 0; i < T; i++) {
            Percolation p = new Percolation(N);
            int count = 0;

            int[] openSeq = StdRandom.permutation(N * N);
            while (! p.percolates()) {
                int toOpenX = openSeq[count] / N;
                int toOpenY = openSeq[count] % N;
                p.open(toOpenX, toOpenY);
                count += 1;
            }
            x[i] = count * 1.0 / (N * N);
        }

    }

    private double mean() {                   // sample mean of percolation threshold
        double sum = 0.0;
        for (double d: x) {
            sum += d;
        }
        return sum / T;
    }

    private double stddev() {                 // sample standard deviation of percolation threshold
        double sum = 0.0;
        double aveg = mean();
        for (double d: x) {
            sum += Math.pow(aveg - d, 2);
        }
        return Math.sqrt(sum / (T - 1));
    }

    public double confidenceLow() {          // low  endpoint of 95% confidence interval
        return mean() - 1.96 * stddev() / Math.sqrt(T);

    }

    public double confidenceHigh() {          // high endpoint of 95% confidence interval
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }


}                       
