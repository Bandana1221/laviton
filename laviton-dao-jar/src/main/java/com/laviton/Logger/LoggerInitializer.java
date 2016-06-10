package com.laviton.Logger;

import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

import com.laviton.constants.LavitonConstants;


public class LoggerInitializer {



	private static Logger LOG = Logger.getLogger(LoggerInitializer.class);
	public static Boolean inputflag=true;
	public static void InitializeLogger() throws SocketException, IOException {
		FTPClient ftpClient = new FTPClient();
		String server = "54.169.84.76";
        int port = 21;
        String user = "suraj";
        String pass = "suraj";
        ftpClient.connect(server, port);
		 
        int replyCode = ftpClient.getReplyCode();
        if (!FTPReply.isPositiveCompletion(replyCode)) {
            System.out.println("Connect failed");
            return;
        }

        boolean success = ftpClient.login(user, pass);

        if (!success) {
            System.out.println("Could not login to the server");
        }
		  PropertyConfigurator.configure(LavitonConstants.LOG4J);
		  
		DOMConfigurator.configure(LavitonConstants.LOG4J_XML_PATH);
		LOG.debug("Initializing Log4j");
	
	
	}

	
	
	
	

}