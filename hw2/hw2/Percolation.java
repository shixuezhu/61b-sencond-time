package hw2;                       

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    WeightedQuickUnionUF allGrid;
    boolean[] openOrNot;
    public int N;
    public int openSite;
    public int topSite;
    public int bottomSite;

    public Percolation(int N){
        openSite = 0;
        this.N = N;
        topSite = N * N;
        bottomSite = N * N + 1;
        openOrNot = new boolean[N * N];
        allGrid = new WeightedQuickUnionUF(N * N + 2);
        for (int i = 0; i < N; i++) {
            allGrid.union(i, topSite);
        }
        for (int j = N * (N - 1); j < N * N; j++) {
            allGrid.union(j, bottomSite);
        }

    }
    public int xyTo1D(int row, int col) {
        return row * N + col;
    }

    public void open(int row, int col) {       // open the site (row, col) if it is not open already
        int index = xyTo1D(row, col);
        openOrNot[index] = true;
        openSite += 1;
        unionAllDirect(row, col);
    }

    public void unionAllDirect(int row, int col) {
        int cent = xyTo1D(row, col);
        if (row - 1 >= 0 && isOpen(row - 1, col)) {
            int up = xyTo1D(row - 1, col);
            allGrid.union(cent, up);
        }
        if (row + 1 < N && isOpen(row + 1, col)) {
            int down = xyTo1D(row + 1, col);
            allGrid.union(cent, down);
        }
        if (col - 1 >= 0 && isOpen(row, col - 1)) {
            int left = xyTo1D(row, col - 1);
            allGrid.union(cent, left);
        }
        if (col + 1 < N && isOpen(row, col + 1)) {
            int right = xyTo1D(row, col + 1);
            allGrid.union(cent, right);
        }
    }

    public boolean isOpen(int row, int col) {  // is the site (row, col) open?
        int index = xyTo1D(row, col);
        return openOrNot[index];
    }

    public boolean isFull(int row, int col) {  // is the site (row, col) full?
        int index = xyTo1D(row, col);
        return isOpen(row, col) && allGrid.connected(index, topSite);
    }

    public int numberOfOpenSites() {           // number of open sites
        return openSite;
    }

    public boolean percolates() {              // does the system percolate?
        return allGrid.connected(topSite, bottomSite);
    }

}                       
