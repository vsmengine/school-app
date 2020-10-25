package listners;

import javax.naming.InitialContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import dbHandlers.DatabaseManager;

public class SessionCntListnr implements HttpSessionListener {

	private static int totalActiveSessions;
	
	public static int getTotalActiveSession() {
		return totalActiveSessions;
	}

	public void sessionCreated(HttpSessionEvent arg0) {
		totalActiveSessions++;
		System.out.println("sessionCreated - add one session into counter");
		try {
	        String appConfigFile= (String) (new InitialContext()).lookup("java:comp/env/appConfigFile");
	        DatabaseManager.updateDbConnectionInfo(appConfigFile);
		}
		catch(Exception ex) {
			ex.printStackTrace(System.err);
		}
	}

	public void sessionDestroyed(HttpSessionEvent arg0) {
		totalActiveSessions--;
		System.out.println("sessionDestroyed - deduct one session from counter");
	}
		
}
