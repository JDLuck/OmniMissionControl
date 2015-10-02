package enb329.soccerbot;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JTextField;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.RefineryUtilities;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class OmniMissionControl {

	private JFrame frmOmnimission;
	private JTextField txtHost;
	JLabel labelInfo;
	JButton btnConnect;
	
	OmniDebugServer server;
	OmniDebugClient client;
	
	private ArrayList<OmniObject> OmniObjectArray;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OmniMissionControl window = new OmniMissionControl();
					window.frmOmnimission.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public OmniMissionControl() {	
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmOmnimission = new JFrame();
		frmOmnimission.setTitle("OmniMissionControl");
		frmOmnimission.setBounds(100, 100, 450, 350);
		frmOmnimission.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmOmnimission.getContentPane().setLayout(null);
		btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(createConnection()){
					labelInfo.setText("Connected.");
					btnConnect.setText("Disconnect");
					labelInfo.setForeground(Color.green);
					txtHost.setEnabled(false);
				}
			}
		});
		btnConnect.setBounds(280, 283, 117, 25);
		frmOmnimission.getContentPane().add(btnConnect);
		
		txtHost = new JTextField();
		txtHost.setText("localhost");
		txtHost.setBounds(60, 286, 208, 19);
		frmOmnimission.getContentPane().add(txtHost);
		txtHost.setColumns(10);
		
		JLabel labelHost = new JLabel("Host:");
		labelHost.setBounds(12, 288, 70, 15);
		frmOmnimission.getContentPane().add(labelHost);
		
		JLabel labelSplash = new JLabel("");
		labelSplash.setIcon(new ImageIcon(OmniMissionControl.class.getResource("/res/omnisplash.jpg")));
		labelSplash.setBounds(0, 0, 450, 260);
		frmOmnimission.getContentPane().add(labelSplash);
		
		JLabel labelStatus = new JLabel("Status:");
		labelStatus.setBounds(12, 306, 70, 15);
		frmOmnimission.getContentPane().add(labelStatus);
		
		labelInfo = new JLabel("Not Connected.");
		labelInfo.setBounds(91, 306, 177, 15);
		labelInfo.setForeground(Color.red);
		frmOmnimission.getContentPane().add(labelInfo);
	}
	
	private boolean createConnection()
	{
		System.out.println("MissionControl::CreateConnection()");
		try{
			server = new OmniDebugServer(5000);
			server.setOmniObjectArray(OmniObjectArray);
			server.start();
			client = new OmniDebugClient(txtHost.getText(), 5000);
			client.start();
		} catch(Exception e){
			System.out.println(e);
			return false;
		}
		return true;
	}
}
