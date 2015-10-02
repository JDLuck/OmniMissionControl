package enb329.soccerbot;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class OmniDebugServer extends Thread {

	int port;
	ServerSocket serverSocket;
	boolean socketActive;
	DataOutputStream out;
	DataInputStream in;
	ObjectOutputStream oout;
	ObjectInputStream oin;
	
	ArrayList<OmniObject> OmniObjectArray;
	
	public OmniDebugServer(int port)
	{
		this.port = port;
		this.socketActive = false;
	}
	
	
	private boolean connect()
	{
		System.out.println("OmniDebugServer::connect()");
		try{
			serverSocket = new ServerSocket(this.port);
			serverSocket.setSoTimeout(10000);	
		} catch(Exception e){
			System.out.println(e);
			return false;
		}
		this.socketActive = true;
		return true;
	}
	
	public void run()
	{
		System.out.println("OmniDebugServer::run()");
		this.connect();

		String line;
			try{
				 System.out.println("OmniDebugServer::run() Waiting on port " +
				            serverSocket.getLocalPort() + "...");
				 
				            Socket server = serverSocket.accept();
				            System.out.println("OmniDebugServer::run() Connected to "
				                  + server.getRemoteSocketAddress());
				            				            
				            oout = new ObjectOutputStream(server.getOutputStream());
				    		while(this.socketActive)
				    		{
				    			System.out.println("OmniDebugServer::run() Sent data");
				    			oout.reset();
				    			oout.writeObject(OmniObjectArray);
				    			Thread.sleep(1000);
				    			double angle = OmniObjectArray.get(0).getAngle() + 1;
				    			OmniObjectArray.get(0).setAngle(angle);
				    		}
				            oout.close();
				            server.close();			
			} catch(Exception e) {
				System.out.println(e);
			}
		}
	
	public void setOmniObjectArray(ArrayList<OmniObject> OmniObjects)
	{
		this.OmniObjectArray = OmniObjects;
	}
}
