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
	 * @param title	 the frame title.
	 */
	public DynamicData(final String title) {

		super(title);

		t_start = new Date();
		final CombinedDomainXYPlot plot = new CombinedDomainXYPlot(new DateAxis("Time"));
		this.datasets = new TimeSeriesCollection[SUBPLOT_COUNT];
		String types[] = {"Potential", "Kinetic", "Total"};

		for (int i = 0; i < SUBPLOT_COUNT; i++) {
			this.lastValue[i] = 100.0;
			final TimeSeries series = new TimeSeries(types[i], FixedMillisecond.class);
			this.datasets[i] = new TimeSeriesCollection(series);
			final NumberAxis rangeAxis = new NumberAxis(types[i]);
			rangeAxis.setAutoRangeIncludesZero(false);
			final XYPlot subplot = new XYPlot(
					this.datasets[i], null, rangeAxis, new StandardXYItemRenderer()
			);
			subplot.setBackgroundPaint(Color.lightGray);
			subplot.setDomainGridlinePaint(Color.white);
			subplot.setRangeGridlinePaint(Color.white);
			plot.add(subplot);
		}

		final JFreeChart chart = new JFreeChart("Energy of system", plot);
		chart.setBorderPaint(Color.black);
		chart.setBorderVisible(true);
		chart.setBackgroundPaint(Color.white);
		
		plot.setBackgroundPaint(Color.lightGray);
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);
		final ValueAxis axis = plot.getDomainAxis();
		axis.setAutoRange(true);
		axis.setFixedAutoRange(30000.0);  // 30 seconds
		
		final JPanel content = new JPanel(new BorderLayout());

		final ChartPanel chartPanel = new ChartPanel(chart);
		content.add(chartPanel);

		chartPanel.setPreferredSize(new java.awt.Dimension(500, 470));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		setContentPane(content);

	}


	/**
	 * Update plots
	 *
	 * @param potential	 the actual potential energy.
	 * @param kinect	 the actual kinect energy.
	 * @param kinect	 the actual total energy.
	 * @param time	     the actual simulation time.
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

}