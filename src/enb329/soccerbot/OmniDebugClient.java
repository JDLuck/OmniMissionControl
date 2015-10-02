package enb329.soccerbot;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import org.jfree.ui.RefineryUtilities;

public class OmniDebugClient extends Thread {

	String host;
	int port;
	Socket socket;
	boolean socketActive;
	DataOutputStream out;
	DataInputStream in;
	ObjectOutputStream oout;
	ObjectInputStream oin;
	
	ArrayList<OmniObject> OmniObjectArray;
	OmniPlotPolar PlotPolar;
	
	public OmniDebugClient(String host, int port)
	{
		this.host = host;
		this.port = port;
		this.socketActive = false;
	}
	
	
	private boolean connect()
	{
		System.out.println("OmniDebugClient::connect()");
		try{
			socket = new Socket(this.host, this.port);
			socket.setSoTimeout(10000);
			oin = new ObjectInputStream(socket.getInputStream());
		} catch(Exception e){
			System.out.println(e);
			return false;
		}
		this.socketActive = true;
		return true;
	}
	
	public void run()
	{
		System.out.println("OmniDebugClient::run()");
		this.connect();

		PlotPolar = new OmniPlotPolar("OmniMissionControl - Vector Plot", "Detected Objects");
		PlotPolar.pack();
		RefineryUtilities.centerFrameOnScreen(PlotPolar);
		PlotPolar.setVisible(true);
		
		while(this.socketActive)
		{
			try{
			    while ((OmniObjectArray = (ArrayList<OmniObject>) oin.readObject()) != null) 
			    {
			    	System.out.println("OmniDebugClient::run() Got Data");
			    	PlotPolar.updateData(OmniObjectArray);
			        break;
			    }
			} catch(Exception e) {
				System.out.println(e);
			}
		}
	       
	}
	
	public ArrayList<OmniObject> getOmniObjectArray()
	{
		return OmniObjectArray;
	}
}
