package enb329.soccerbot;

import java.awt.Dimension;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.PolarChartPanel;
import org.jfree.chart.plot.PolarPlot;
import org.jfree.chart.renderer.DefaultPolarItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;


public class OmniPlotPolar extends ApplicationFrame {
    
	private String plotTitle;
	XYSeriesCollection data;
	PolarPlot plot;
	
    /**
     * Creates a new instance of the demo.
     * 
     * @param title  the frame title.
     */
    public OmniPlotPolar(final String title, final String plotTitle) {
        super(title);
        this.plotTitle = plotTitle;
        data = new XYSeriesCollection();
        final JFreeChart chart = createChart(data);
        final ChartPanel chartPanel = new PolarChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        chartPanel.setEnforceFileExtensions(false);
        setContentPane(chartPanel);
    }
    

    private JFreeChart createChart(final XYDataset dataset) {
        final JFreeChart chart = ChartFactory.createPolarChart(
            this.plotTitle, dataset, true, true, false
        ); 
        plot = (PolarPlot) chart.getPlot();
        final DefaultPolarItemRenderer renderer = (DefaultPolarItemRenderer) plot.getRenderer();
        renderer.setSeriesFilled(2, true);
        return chart;
    }
    
    public void updateData(ArrayList<OmniObject> OmniObjects)
    {   	
    	XYSeriesCollection newData = new XYSeriesCollection();
    	for(int i=0;i<OmniObjects.size();i++)
    	{
    		if(OmniObjects.get(i).isVisible()){
    			XYSeries series = new XYSeries(OmniObjects.get(i).getInfo());
    			series.add(0, 0);
    			series.add(OmniObjects.get(i).getAngle(), OmniObjects.get(i).getDistance());
    			newData.addSeries(series);
    		}
    	}
    	plot.setDataset(newData);
    }
}
