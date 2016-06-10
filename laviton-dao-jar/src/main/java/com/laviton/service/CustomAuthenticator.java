package com.laviton.service;

import java.io.IOException;
import java.net.Authenticator;
import java.net.InetAddress;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.laviton.dao.MeterDetailsDao;
import com.laviton.model.MeterDetails;

	
	public  class CustomAuthenticator extends Authenticator {
		private  String getValue(String tag, Element element) {
			NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
			Node node = (Node) nodes.item(0);
			return node.getNodeValue();
		}

		private  Document loadTestDocument(String url) throws Exception {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setNamespaceAware(true);
			return factory.newDocumentBuilder().parse(new URL(url).openStream());
		}

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

		public void addMeterDetails(String newurl) throws IOException, ParserConfigurationException, SAXException {
			MeterDetails mt = new MeterDetails();
			MeterDetailsDao meter = new MeterDetailsDao();
			Authenticator.setDefault(new CustomAuthenticator());

			URL url = new URL(newurl);
			URLConnection conn = url.openConnection();

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(conn.getInputStream());

			doc.getDocumentElement().normalize();
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("devices");
			int length = nList.getLength();
			System.out.println(length);
			System.out.println("----------------------------");

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				System.out.println("\nCurrent Element :" + nNode.getNodeName());
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					try {
						Element eElement = (Element) nNode;

						System.out.println("Address : " + getValue("address", eElement));
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
						NodeList nList2 = doc.getElementsByTagName("age");
						Node nNode2 = nList2.item(0);
						Element eElement2 = (Element) nNode2;
						System.out.println("\nCurrent Element :" + eElement2.getAttribute("units") + "\n");

						mt.setMeter_age(getValue("age", eElement) + eElement2.getAttribute("units"));
						NodeList nList1 = doc.getElementsByTagName("point");

						for (int temp1 = 0; temp1 < nList1.getLength(); temp1++) {
							Node nNode1 = nList1.item(temp1);
							System.out.println("\nCurrent Element :" + nNode1.getNodeName() + "\n");
							if (nNode1.getNodeType() == Node.ELEMENT_NODE) {

								Element eElement1 = (Element) nNode1;
								if (temp1 == 0) {
									System.out.println("Number: " + eElement1.getAttribute("number"));
									System.out.println("Point Name : " + eElement1.getAttribute("name"));
									System.out.println("Units : " + eElement1.getAttribute("units"));
									mt.setMeter_positive_energy(Float.parseFloat(eElement1.getAttribute("value")));
								}
								if (temp1 == 1) {
									System.out.println("Number: " + eElement1.getAttribute("number"));
									System.out.println("Point Name : " + eElement1.getAttribute("name"));
									System.out.println("Units : " + eElement1.getAttribute("units"));
									mt.setMeter_negative_energy(Float.parseFloat(eElement1.getAttribute("value")));
								}
								if (temp1 == 2) {
									System.out.println("Number: " + eElement1.getAttribute("number"));
									System.out.println("Point Name : " + eElement1.getAttribute("name"));
									System.out.println("Units : " + eElement1.getAttribute("units"));
									mt.setMeter_peak_demand(Float.parseFloat(eElement1.getAttribute("value")));
								}

							}

						}
						meter.addmeterLogs(mt);
					} catch (Exception e) {
						System.out.println(e);
					}

				}
			}
		}
	}

