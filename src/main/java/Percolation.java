import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // dimension of the grid
    private int N;
    // 0 - open, 1 - closed
    private int[] sites;
    //
    private WeightedQuickUnionUF wquf;

    // create N-by-N grid, with all sites blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException(String.format("N %d", N));
        }
        this.N = N;
        sites = new int[N * N + 2];
        wquf = new WeightedQuickUnionUF(N * N + 2);
        sites[0] = 1;
        sites[sites.length - 1] = 1;
    }

    // test client (optional)
    public static void main(String[] args) {

    }

    // open site (row row, column column) if it is not open already
    public void open(int row, int column) {
        if (!isValid(row, column)) {
            throw new IllegalArgumentException(String.format("Row %d, column %d", row, column));
        }

        if (isOpen(row, column)) {
            return;
        }

        final int index = getIndex(row, column);
        sites[index] = 1;

        if (row == 1 && column == 1) {
            wquf.union(index, 0);
        }

        if (row == N && column == N) {
            wquf.union(index, sites.length - 1);
        }

        //        if the left site is open - union them
        if (column > 1 && isOpen(row, column - 1)) {
            wquf.union(index, getIndex(row, column - 1));
        }
        //        if the top site is open - union them
        if (row > 1 && isOpen(row - 1, column)) {
            wquf.union(index, getIndex(row - 1, column));
        }
        //        if the right site is open - union them
        if (column < N && isOpen(row, column + 1)) {
            wquf.union(index, getIndex(row, column + 1));
        }
        //        if the bottom site is open - union them
        if (row < N && isOpen((row + 1), column)) {
            wquf.union(index, getIndex(row + 1, column));
        }
    }

    // is site (row row, column column) open?
    public boolean isOpen(int row, int column) {
        if (!isValid(row, column)) {
            throw new IllegalArgumentException(String.format("Row %d, column %d", row, column));
        }
        return sites[getIndex(row, column)] == 1;
    }

    // is site (row row, column column) full?
    public boolean isFull(int row, int column) {
        if (!isValid(row, column)) {
            throw new IndexOutOfBoundsException(String.format("Row %d, column %d", row, column));
        }

        return this.wquf.connected(0, getIndex(row, column));
    }

    // does the system percolate?
    public boolean percolates() {
        return wquf.connected(0, N * N + 1);
    }

    private boolean isValid(int row, int column) {
        return row > 0 && row <= N * N && column > 0 && column <= N * N;
    }

    private int getIndex(int row, int column) {
        return (row - 1) * N + column;
    }
}
