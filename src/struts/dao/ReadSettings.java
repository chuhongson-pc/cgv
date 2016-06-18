package struts.dao;

import java.util.Enumeration;
import java.util.ResourceBundle;

public class ReadSettings {

	public String[] getDatabaseSettings() {
		String[] return_arr = new String[3];
//		try {
//			
//
//			
//			System.out.println("read settings");
//			File myClass = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getFile());
//			System.out.println("File="+myClass.getPath());
//			File fXmlFile = new File(myClass.getCanonicalPath().replaceFirst("classes\\struts\\dao\\ReadSettings.class", "")+"database-config.xml");
			
//			File f = new File(ReadSettings.class.getProtectionDomain()
//					.getCodeSource().getLocation().getPath());

//			String parent = f.getParent();
//			parent = parent.substring(0, parent.length() - 18);
//
//			System.out.println("path=" + fXmlFile.getPath());
//
//			//File fXmlFile = new File(parent + "database-config.xml");
//			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//			Document doc = dBuilder.parse(fXmlFile);
//
//			doc.getDocumentElement().normalize();
//			System.out.println("Root element :"+ doc.getDocumentElement().getNodeName());
//
//			NodeList nList = doc.getElementsByTagName("setting");
//
//			int total = nList.getLength();
//
//			System.out.println(total);
//
//			for (int temp = 0; temp < nList.getLength(); temp++) {
//				
//				Node nNode = nList.item(temp);
//				
//				System.out.println("\nCurrent Element :" + nNode.getNodeName());
//				
//				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
//					
//					Element eElement = (Element) nNode;
//					
//					return_arr[0] = eElement.getElementsByTagName("database").item(0).getTextContent();
//					
//					
//					return_arr[1] = eElement.getElementsByTagName("username").item(0).getTextContent();
//					
//					
//					return_arr[2] = eElement.getElementsByTagName("password").item(0).getTextContent();
//					
//					
//				}
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return_arr[0] = "jdbc:jtds:sqlserver://localhost:1433/CINEMA;";
//		return_arr[1] = "sa";
//		return_arr[2] = "123456";
		
		ResourceBundle rb = ResourceBundle.getBundle("struts.dao.config");
		Enumeration <String> keys = rb.getKeys();

		while (keys.hasMoreElements()) {
			String key_str = keys.nextElement();
			String value = rb.getString(key_str);
			if(key_str.equals("database")) return_arr[0] = value;
			else if (key_str.equals("username")) return_arr[1] = value;
			else return_arr[2] = value;
		}
		
		
		return return_arr;

	}
	

}
