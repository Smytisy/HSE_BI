import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import org.cicd.NumberProcessor;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PerformanceChart extends ApplicationFrame {

    public PerformanceChart(String applicationTitle, String chartTitle) {
        super(applicationTitle);
        JFreeChart xylineChart = ChartFactory.createXYLineChart(
                chartTitle,
                "Number of Elements",
                "Time (ms)",
                createDataset(),
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(xylineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        final XYPlot plot = xylineChart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        plot.setRenderer(renderer);
        setContentPane(chartPanel);
    }

    private XYSeriesCollection createDataset() {
        final XYSeries minSeries = new XYSeries("Min");
        final XYSeries maxSeries = new XYSeries("Max");
        final XYSeries sumSeries = new XYSeries("Sum");
        final XYSeries multSeries = new XYSeries("Mult");

        for (int n = 1000; n <= 10000000; n *= 2) {
            List<BigInteger> numbers = generateRandomNumbers(n);

            long startTime = System.currentTimeMillis();
            NumberProcessor._min(numbers);
            long endTime = System.currentTimeMillis();
            minSeries.add(n, endTime - startTime);

            startTime = System.currentTimeMillis();
            NumberProcessor._max(numbers);
            endTime = System.currentTimeMillis();
            maxSeries.add(n, endTime - startTime);

            startTime = System.currentTimeMillis();
            NumberProcessor._sum(numbers);
            endTime = System.currentTimeMillis();
            sumSeries.add(n, endTime - startTime);

//            startTime = System.currentTimeMillis();
//            NumberProcessor._mult(numbers);
//            endTime = System.currentTimeMillis();
//            multSeries.add(n, endTime - startTime);
        }

        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(minSeries);
        dataset.addSeries(maxSeries);
        dataset.addSeries(sumSeries);
        dataset.addSeries(multSeries);

        return dataset;
    }

    private List<BigInteger> generateRandomNumbers(int n) {
        List<BigInteger> numbers = new ArrayList<>();
        Random random = new Random();
        BigInteger max = new BigInteger("923372036854");
        for (int i = 0; i < n; i++) {
            BigInteger number = new BigInteger(max.bitLength(), random).mod(max);
            numbers.add(number);
        }
        return numbers;
    }

    public static void main(String[] args) {
        PerformanceChart chart = new PerformanceChart("Performance Chart", "Time vs Number of Elements");
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
    }
}
