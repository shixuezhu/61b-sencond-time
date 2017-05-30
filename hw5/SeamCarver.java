/**
 * Created by mwang on 5/29/17.
 */
import edu.princeton.cs.algs4.Picture;
import java.awt.Color;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class SeamCarver {
    public Picture picture;

    public SeamCarver(Picture picture) {
        this.picture = picture;
    }

    public Picture picture() {
        return picture;
    }

    public int width() {
        return picture.width();
    }

    public int height() {
        return picture.height();
    }

    public double energy(int x, int y) {
        Color left, right, up, down;
        left = picture.get(getLeft(x), y);
        right = picture.get(getRight(x), y);
        up = picture.get(x, getUp(y));
        down = picture.get(x, getDown(y));

        int Rx = left.getRed() - right.getRed();
        int Gx = left.getGreen() - right.getGreen();
        int Bx = left.getBlue() - right.getBlue();

        int Ry = up.getRed() - down.getRed();
        int Gy = up.getGreen() - down.getGreen();
        int By = up.getBlue() - down.getBlue();

        return Rx * Rx + Gx * Gx + Bx * Bx + Ry * Ry + Gy * Gy + By * By;
    }

    private int getLeft(int x) {
        if (x == 0) {
            return width() - 1;
        }
        return x - 1;
    }

    private int getRight(int x) {
        if (x == width() - 1) {
            return 0;
        }
        return x + 1;
    }

    private int getUp(int y) {
        if (y == 0) {
            return height() - 1;
        }
        return y - 1;
    }

    private int getDown(int y) {
        if (y == height() - 1) {
            return 0;
        }
        return y + 1;
    }

    public void findEachVerticalSeam(int index, double[][] energy, Map<Double, int[]> energyPath) {
        int height = picture.height();
        int[] path = new int[height];
        path[0] = index;
        double totalEnergy = energy[0][index];

        int heightIndex = 1;
        int minIndex = index;
        while (heightIndex < height) {
            double left = energy[heightIndex][getLeft(minIndex)];
            double mid = energy[heightIndex][minIndex];
            double right = energy[heightIndex][getRight(minIndex)];

            double minEnergy = Math.min(Math.min(left, mid), right);
            if (minEnergy == left) {
                minIndex = getLeft(minIndex);
            }
            else if (minEnergy == right) {
                minIndex = getRight(minIndex);
            }

            path[heightIndex] = minIndex;
            totalEnergy += minEnergy;

            heightIndex += 1;
        }
        energyPath.put(totalEnergy, path);
    }


    public int[] findVerticalSeam() {
        int width = picture.width();
        int height = picture.height();
        double[][] energy = new double[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                energy[i][j] = energy(j, i);
            }
        }

        Map<Double, int[]> energyPath = new TreeMap<>();
        for (int i = 0; i < width; i++) {
            findEachVerticalSeam(i, energy, energyPath);
        }

        double minEnergy = Collections.min(energyPath.keySet());
        return energyPath.get(minEnergy);
    }

    public void findEachHorizontalSeam(int index, double[][] energy, Map<Double, int[]> energyPath) {

        int width = picture.width();
        int[] path = new int[width];
        path[0] = index;
        double totalEnergy = energy[index][0];

        int widthIndex = 1;
        int minIndex = index;
        while (widthIndex < width) {
            double up = energy[getUp(minIndex)][widthIndex];
            double mid = energy[minIndex][widthIndex];
            double down = energy[getDown(minIndex)][widthIndex];

            double minEnergy = Math.min(Math.min(up, mid), down);
            if (minEnergy == up) {
                minIndex = getUp(minIndex);
            }
            else if (minEnergy == down) {
                minIndex = getDown(minIndex);
            }

            path[widthIndex] = minIndex;
            totalEnergy += minEnergy;

            widthIndex += 1;
        }
        energyPath.put(totalEnergy, path);
    }

    public int[] findHorizontalSeam() {
        int width = picture.width();
        int height = picture.height();
        double[][] energy = new double[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                energy[i][j] = energy(j, i);
            }
        }

        Map<Double, int[]> energyPath = new TreeMap<>();
        for (int i = 0; i < height; i++) {
            findEachHorizontalSeam(i, energy, energyPath);
        }

        double minEnergy = Collections.min(energyPath.keySet());
        return energyPath.get(minEnergy);

    }

    private boolean isValidSeam(int[] seam) {
        for (int i = 1; i < seam.length; i++) {
            if (Math.abs(seam[i] - seam[i -1 ]) > 1) {
                return false;
            }
        }
        return true;
    }

    public void removeHorizontalSeam(int[] seam) {
        if (seam.length != width() || !isValidSeam(seam)) {
            throw new IllegalArgumentException("wrong horizontal");
        }

        SeamRemover.removeHorizontalSeam(picture(), findHorizontalSeam());
    }

    public void removeVerticalSeam(int[] seam) {
        if (seam.length != height() || !isValidSeam(seam)) {
            throw new IllegalArgumentException("wrong vertical");
        }

        SeamRemover.removeHorizontalSeam(picture(), findVerticalSeam());
    }

}
