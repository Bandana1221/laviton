/*import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.xml.parsers.DocumentBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.laviton.dao.MeterDetailsDao;
import com.laviton.dao.MeterLogsDao;
import com.laviton.dao.interfaces.MeterDetailsDaoInterface;
import com.laviton.model.MeterDetails;

import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.net.Authenticator;
import java.net.InetAddress;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLConnection;

public class XmlExtract {
	private static EJBContainer ejbContainer;

	private static Context ctx;
	private MeterDetails mt=new MeterDetails();
	
	 private static String getValue(String tag, Element element) {
		 NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
		 Node node = (Node) nodes.item(0);
		 return node.getNodeValue();
		 }

		 

	private static Document loadTestDocument(String url) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		return factory.newDocumentBuilder().parse(new URL(url).openStream());
	}

	public static class CustomAuthenticator extends Authenticator {

		// Called when password authorization is needed

		protected PasswordAuthentication getPasswordAuthentication() {

			// Get information about the request

			String prompt = getRequestingPrompt();

			String hostname = getRequestingHost();

			InetAddress ipaddr = getRequestingSite();

			int port = getRequestingPort();

			String username = "admin";

			String password = "admin";

			// Return the information (a data holder that is used by
			// Authenticator)

			return new PasswordAuthentication(username, password.toCharArray());

		}

		public static void main(String[] args) throws Exception {
			 
			MeterDetails mt=new MeterDetails();
			MeterDetailsDao meter=new MeterDetailsDao();
			Authenticator.setDefault(new CustomAuthenticator());

			URL url = new URL("http://mrsoluciones.ddns.net:8020/setup/devicexml.cgi?ADDRESS=1&TYPE=DATA");
			URLConnection conn = url.openConnection();

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(conn.getInputStream());

			doc.getDocumentElement().normalize();
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("devices");
			int length=nList.getLength();
			System.out.println(length);
			System.out.println("----------------------------");

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				System.out.println("\nCurrent Element :" + nNode.getNodeName());
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					try{
					Element eElement = (Element) nNode;
                     
					
					
					System.out.println("Address : " +getValue("address", eElement));
					mt.setMeter_address(getValue("address", eElement));

					System.out.println("Name : " + getValue("name", eElement));
					mt.setMeter_name(getValue("name", eElement));

					System.out.println("Dev class : " + getValue("class", eElement));
                    mt.setMeter_class(getValue("class", eElement));
					System.out.println("Status : " + getValue("status", eElement));
                     mt.setMeter_status(getValue("status", eElement));
					System.out.println("Type : " + getValue("type", eElement));
					mt.setMeter_type(getValue("type", eElement));
					mt.setMeter_numpoints(getValue("numpoints", eElement));
					
					System.out.println(eElement.getNodeName());
					mt.setMeter_negative_energy(meter_negative_energy);
					mt.setMeter_peak_demand(meter_peak_demand);
					mt.setMeter_positive_energy(meter_positive_energy);
					mt.setMeter_age(meter_age);
					meter.addmeterLogs(mt);
					}catch(Exception e)
					{
						System.out.println(e);
					}
					
				}
			}  System.out.println(doc); 
			TransformerFactory factory1 = TransformerFactory.newInstance();
			Transformer xform = factory1.newTransformer();
			xform.transform(new DOMSource(doc), new StreamResult(System.out));
			
		}
	}
}
*/