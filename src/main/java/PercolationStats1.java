import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats1 {

    // this private integer array holds the fraction of open sites in ith experiment on the N x N percolation grid
    private double[] openSites;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats1(int N, int T) {

        if(N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }

        this.openSites = new double[T];

        // perform T independent experiments on the N x N percolation grid
        for(int i = 0; i < T; ++i) {
            Percolation1 p = new Percolation1(N);
            int openSites = 0;
            while (!p.percolates()) {
                int row = StdRandom.uniform(1, N+1);
                int column = StdRandom.uniform(1, N+1);

                if(!p.isOpen(row, column)) {
                    ++openSites;
                    p.open(row, column);
                }
            }
            this.openSites[i] = ((double)openSites)/(N*N);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(this.openSites);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(this.openSites);
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - ((1.96*stddev())/Math.sqrt(this.openSites.length));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + ((1.96*stddev())/Math.sqrt(this.openSites.length));
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats1 ps = new PercolationStats1(N, T);
        System.out.println("mean                    = " + ps.mean());
        System.out.println("stddev                  = " + ps.stddev());
        System.out.println("95% confidence interval = " + ps.confidenceLo() + ", " + ps.confidenceHi());
    }
}
