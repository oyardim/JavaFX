package sample;


import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class WetterLoad {

    private static WetterLoad instance;

    public WetterLoad() {
    }

    public static WetterLoad getInstance() {
        if (instance == null) {
            instance = new WetterLoad();
        }
        return instance;
    }

    public WetterInfo[] loads(String city) throws Exception {
        String uri = "https://api.openweathermap.org/data/2.5/forecast?q=" + city + "&mode=xml&appid=4afa0ea1543cc6b270c66322946fd0e2";
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document document = documentBuilder.parse(uri);
        NodeList times = document.getElementsByTagName("time");

        WetterInfo[] wetterInfos = new WetterInfo[times.getLength()];
        for (int x = 0; x < times.getLength(); x++) {
            Node time = times.item(x);
            NamedNodeMap timeAttributes = time.getAttributes();
            String timestamp = timeAttributes.getNamedItem("from").getNodeValue();
            NodeList children = time.getChildNodes();

            for (int y = 0; y < children.getLength(); y++) {
                Node child = children.item(y);
                if (child.getNodeName().equals("temperature")) {
                    String temperature = child.getAttributes().getNamedItem("value").getNodeValue();

                    wetterInfos[x] = new WetterInfo(timestamp, temperature);
                }
            }
        }
        return wetterInfos;
    }
}

