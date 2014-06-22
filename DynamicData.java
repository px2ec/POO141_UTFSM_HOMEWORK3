import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.*;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JInternalFrame;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.data.time.FixedMillisecond;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class DynamicData extends JInternalFrame {
	Date t_start;
    /** The number of subplots. */
    public static final int SUBPLOT_COUNT = 3;
    
    /** The datasets. */
    private TimeSeriesCollection[] datasets;
    
    /** The most recent value added to series 1. */
    private double[] lastValue = new double[SUBPLOT_COUNT];

    /**
     * Constructs graphics.
     *
     * @param title  the frame title.
     */
    public DynamicData(final String title) {

        super(title);

        t_start = new Date();
        final CombinedDomainXYPlot plot = new CombinedDomainXYPlot(new DateAxis("Time"));
        this.datasets = new TimeSeriesCollection[SUBPLOT_COUNT];
        
        for (int i = 0; i < SUBPLOT_COUNT; i++) {
            this.lastValue[i] = 100.0;
            final TimeSeries series = new TimeSeries("Random " + i, FixedMillisecond.class);
            this.datasets[i] = new TimeSeriesCollection(series);
            final NumberAxis rangeAxis = new NumberAxis("Y" + i);
            rangeAxis.setAutoRangeIncludesZero(false);
            final XYPlot subplot = new XYPlot(
                    this.datasets[i], null, rangeAxis, new StandardXYItemRenderer()
            );
            subplot.setBackgroundPaint(Color.lightGray);
            subplot.setDomainGridlinePaint(Color.white);
            subplot.setRangeGridlinePaint(Color.white);
            plot.add(subplot);
        }

        final JFreeChart chart = new JFreeChart("Dynamic Data Demo 3", plot);
//        chart.getLegend().setAnchor(Legend.EAST);
        chart.setBorderPaint(Color.black);
        chart.setBorderVisible(true);
        chart.setBackgroundPaint(Color.white);
        
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
  //      plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 4, 4, 4, 4));
        final ValueAxis axis = plot.getDomainAxis();
        axis.setAutoRange(true);
        axis.setFixedAutoRange(30000.0);  // 30 seconds
        
        final JPanel content = new JPanel(new BorderLayout());

        final ChartPanel chartPanel = new ChartPanel(chart);
        content.add(chartPanel);

        // final JPanel buttonPanel = new JPanel(new FlowLayout());
        //         
        //         for (int i = 0; i < SUBPLOT_COUNT; i++) {
        //             final JButton button = new JButton("Series " + i);
        //             button.setActionCommand("ADD_DATA_" + i);
        //             button.addActionListener(this);
        //             buttonPanel.add(button);
        //         }
        //         final JButton buttonAll = new JButton("ALL");
        //         buttonAll.setActionCommand("ADD_ALL");
        //         buttonAll.addActionListener(this);
        //         buttonPanel.add(buttonAll);
        //         
        //         content.add(buttonPanel, BorderLayout.SOUTH);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 470));
        chartPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setContentPane(content);

    }


    /**
     * Handles a click on the button by adding new (random) data.
     *
     * @param e  the action event.
     */
	public void updateGraphics(double potential, double kinect, double total, double time) {
		Date now = new Date((long) (t_start.getTime() + time*1000));
		final FixedMillisecond t = new FixedMillisecond(now);
 		this.datasets[0].getSeries(0).addOrUpdate(t, potential);
		this.datasets[1].getSeries(0).addOrUpdate(t, kinect);
		this.datasets[2].getSeries(0).addOrUpdate(t, total);

    }
	
	public void reset(){
		this.datasets[0].getSeries(0).clear();
		this.datasets[1].getSeries(0).clear();
		this.datasets[2].getSeries(0).clear();
	}

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(final String[] args) {

        final DynamicData demo = new DynamicData("Dynamic Data Demo 3");
        demo.pack();
        //RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
        TimerTask task = new TimerTask() {

		        @Override
		        public void run() {
		            //demo.updateGraphics();
		        }
		};

		Timer timer = new Timer();
		timer.schedule(task, new Date(), 500);

    }

}