package util;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import view.TankBox;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/*
 * 从文件读取数据
 * @Author: 谢宇弢
 * */
public class DataController {

    //将document对象写入xml,内部函数,无需在外部使用
    public static void saveDocument(Document document,File dataFile) throws TransformerException {
        TransformerFactory factory=TransformerFactory.newInstance();
        Transformer transformer=factory.newTransformer();
        StringWriter writer=new StringWriter();
        transformer.transform(new DOMSource(document),new StreamResult(dataFile));//将document中的值写入file文件中，自动完成file文件的实体
    }

    //重新生成xml,money为0,内部函数（如果有删档需要也可以调用它）
    public static void createNewXml() throws TransformerException, ParserConfigurationException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder    documentBuilder = documentBuilderFactory.newDocumentBuilder();

        Document document = documentBuilder.newDocument();

        Element data = document.createElement("data");

        Element tankList = document.createElement("tankList");
        data.appendChild(tankList);

        //生成坦克编号列表
        for(int i = 1;i <= 8;i++){
            Element tank = document.createElement("tank");
            tank.setAttribute("id",i+"");
            if(i==1)tank.setTextContent("true");
            else tank.setTextContent("false");
            //如果要存储更多数据留作扩展

            tankList.appendChild(tank);
        }

        //生成钱
        Element money = document.createElement("money");
        money.setTextContent("0");
        data.appendChild(money);
        //生成原石
        Element stone = document.createElement("stone");
        stone.setTextContent("0");
        data.appendChild(stone);
        //生成选择
        Element choose = document.createElement("choose");
        choose.setTextContent("1");
        data.appendChild(choose);

        document.appendChild(data);

        saveDocument(document,new File("Data/data.xml"));
    }

    //若不存在则创建文件并初始化
    public static void createXmlIfNeed() throws IOException, ParserConfigurationException, TransformerException {
        String dataPath = "Data/data.xml";
        File dataFile = new File(dataPath);
        if(!(dataFile.exists())){
            dataFile.createNewFile();
            createNewXml();
        }
    }

    //获取所有坦克属性
    public static void TankList(ArrayList<Boolean> tanks) throws ParserConfigurationException, IOException, TransformerException, SAXException {
        createXmlIfNeed();

        DocumentBuilderFactory factory = new DocumentBuilderFactory() {
            @Override
            public DocumentBuilder newDocumentBuilder() throws ParserConfigurationException {
                return null;
            }

            @Override
            public void setAttribute(String name, Object value) throws IllegalArgumentException {

            }

            @Override
            public Object getAttribute(String name) throws IllegalArgumentException {
                return null;
            }

            @Override
            public void setFeature(String name, boolean value) throws ParserConfigurationException {

            }

            @Override
            public boolean getFeature(String name) throws ParserConfigurationException {
                return false;
            }
        }.newInstance();

        DocumentBuilder db = factory.newDocumentBuilder();

        Document document = db.parse(new File("Data/data.xml"));

        NodeList tankListNode = document.getElementsByTagName("tank");

        for(int i = 0;i < 8;i++){
            Element item = (Element)tankListNode.item(i);
            if(item.getTextContent().equals("true"))
                tanks.add(true);
            else
                tanks.add(false);
        }

    }

    //读取某个坦克属性，由于目前只存编号所以返回int(现在虽然没用但是留作扩展)
    public static int getTankById(int id) throws ParserConfigurationException, IOException, TransformerException, SAXException {
        createXmlIfNeed();

        DocumentBuilderFactory factory = new DocumentBuilderFactory() {
            @Override
            public DocumentBuilder newDocumentBuilder() throws ParserConfigurationException {
                return null;
            }

            @Override
            public void setAttribute(String name, Object value) throws IllegalArgumentException {

            }

            @Override
            public Object getAttribute(String name) throws IllegalArgumentException {
                return null;
            }

            @Override
            public void setFeature(String name, boolean value) throws ParserConfigurationException {

            }

            @Override
            public boolean getFeature(String name) throws ParserConfigurationException {
                return false;
            }
        }.newInstance();

        DocumentBuilder db = factory.newDocumentBuilder();

        Document document = db.parse(new File("Data/data.xml"));

        NodeList tankListNode = document.getElementsByTagName("tank");

        Element item = (Element)tankListNode.item(id - 1);

        return Integer.parseInt(item.getAttribute("id"));
    }

    //获得钱
    public static int getMoney() throws ParserConfigurationException, IOException, TransformerException, SAXException {
        createXmlIfNeed();

        DocumentBuilderFactory factory = new DocumentBuilderFactory() {
            @Override
            public DocumentBuilder newDocumentBuilder() throws ParserConfigurationException {
                return null;
            }

            @Override
            public void setAttribute(String name, Object value) throws IllegalArgumentException {

            }

            @Override
            public Object getAttribute(String name) throws IllegalArgumentException {
                return null;
            }

            @Override
            public void setFeature(String name, boolean value) throws ParserConfigurationException {

            }

            @Override
            public boolean getFeature(String name) throws ParserConfigurationException {
                return false;
            }
        }.newInstance();

        DocumentBuilder db = factory.newDocumentBuilder();

        Document document = db.parse(new File("Data/data.xml"));

        return (Integer.parseInt(document.getElementsByTagName("money").item(0).getTextContent()));
    }

    public static int getStone() throws ParserConfigurationException, IOException, TransformerException, SAXException {
        createXmlIfNeed();

        DocumentBuilderFactory factory = new DocumentBuilderFactory() {
            @Override
            public DocumentBuilder newDocumentBuilder() throws ParserConfigurationException {
                return null;
            }

            @Override
            public void setAttribute(String name, Object value) throws IllegalArgumentException {

            }

            @Override
            public Object getAttribute(String name) throws IllegalArgumentException {
                return null;
            }

            @Override
            public void setFeature(String name, boolean value) throws ParserConfigurationException {

            }

            @Override
            public boolean getFeature(String name) throws ParserConfigurationException {
                return false;
            }
        }.newInstance();

        DocumentBuilder db = factory.newDocumentBuilder();

        Document document = db.parse(new File("Data/data.xml"));

        return (Integer.parseInt(document.getElementsByTagName("stone").item(0).getTextContent()));
    }

    public static int getChoose() throws ParserConfigurationException, IOException, TransformerException, SAXException {
        createXmlIfNeed();

        DocumentBuilderFactory factory = new DocumentBuilderFactory() {
            @Override
            public DocumentBuilder newDocumentBuilder() throws ParserConfigurationException {
                return null;
            }

            @Override
            public void setAttribute(String name, Object value) throws IllegalArgumentException {

            }

            @Override
            public Object getAttribute(String name) throws IllegalArgumentException {
                return null;
            }

            @Override
            public void setFeature(String name, boolean value) throws ParserConfigurationException {

            }

            @Override
            public boolean getFeature(String name) throws ParserConfigurationException {
                return false;
            }
        }.newInstance();

        DocumentBuilder db = factory.newDocumentBuilder();

        Document document = db.parse(new File("Data/data.xml"));

        return (Integer.parseInt(document.getElementsByTagName("choose").item(0).getTextContent()));
    }

    //存入钱
    public static void saveMoney(int money) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        createXmlIfNeed();

        DocumentBuilderFactory factory = new DocumentBuilderFactory() {
            @Override
            public DocumentBuilder newDocumentBuilder() throws ParserConfigurationException {
                return null;
            }

            @Override
            public void setAttribute(String name, Object value) throws IllegalArgumentException {

            }

            @Override
            public Object getAttribute(String name) throws IllegalArgumentException {
                return null;
            }

            @Override
            public void setFeature(String name, boolean value) throws ParserConfigurationException {

            }

            @Override
            public boolean getFeature(String name) throws ParserConfigurationException {
                return false;
            }
        }.newInstance();

        DocumentBuilder db = factory.newDocumentBuilder();

        File dataFile = new File("Data/data.xml");

        Document document = db.parse(dataFile);

        document.getElementsByTagName("money").item(0).setTextContent(money + "");

        saveDocument(document,dataFile);
    }


    public static void saveStone(int stone) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        createXmlIfNeed();

        DocumentBuilderFactory factory = new DocumentBuilderFactory() {
            @Override
            public DocumentBuilder newDocumentBuilder() throws ParserConfigurationException {
                return null;
            }

            @Override
            public void setAttribute(String name, Object value) throws IllegalArgumentException {

            }

            @Override
            public Object getAttribute(String name) throws IllegalArgumentException {
                return null;
            }

            @Override
            public void setFeature(String name, boolean value) throws ParserConfigurationException {

            }

            @Override
            public boolean getFeature(String name) throws ParserConfigurationException {
                return false;
            }
        }.newInstance();

        DocumentBuilder db = factory.newDocumentBuilder();

        File dataFile = new File("Data/data.xml");

        Document document = db.parse(dataFile);

        document.getElementsByTagName("stone").item(0).setTextContent(stone + "");

        saveDocument(document,dataFile);
    }

    public static void saveTanks(List<TankBox> tanks) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        createXmlIfNeed();

        DocumentBuilderFactory factory = new DocumentBuilderFactory() {
            @Override
            public DocumentBuilder newDocumentBuilder() throws ParserConfigurationException {
                return null;
            }

            @Override
            public void setAttribute(String name, Object value) throws IllegalArgumentException {

            }

            @Override
            public Object getAttribute(String name) throws IllegalArgumentException {
                return null;
            }

            @Override
            public void setFeature(String name, boolean value) throws ParserConfigurationException {

            }

            @Override
            public boolean getFeature(String name) throws ParserConfigurationException {
                return false;
            }
        }.newInstance();

        DocumentBuilder db = factory.newDocumentBuilder();

        File dataFile = new File("Data/data.xml");

        Document document = db.parse(dataFile);

        NodeList tankListNode = document.getElementsByTagName("tank");

        for(int i = 0;i < 8;i++){
            Element item = (Element)tankListNode.item(i);
            if(tanks.get(i).isOwn())
                item.setTextContent("true");
            else
                item.setTextContent("false");
        }

        saveDocument(document,dataFile);
    }

    //存入选择
    public static void saveChoose(int choose) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        createXmlIfNeed();

        DocumentBuilderFactory factory = new DocumentBuilderFactory() {
            @Override
            public DocumentBuilder newDocumentBuilder() throws ParserConfigurationException {
                return null;
            }

            @Override
            public void setAttribute(String name, Object value) throws IllegalArgumentException {

            }

            @Override
            public Object getAttribute(String name) throws IllegalArgumentException {
                return null;
            }

            @Override
            public void setFeature(String name, boolean value) throws ParserConfigurationException {

            }

            @Override
            public boolean getFeature(String name) throws ParserConfigurationException {
                return false;
            }
        }.newInstance();

        DocumentBuilder db = factory.newDocumentBuilder();

        File dataFile = new File("Data/data.xml");

        Document document = db.parse(dataFile);

        document.getElementsByTagName("choose").item(0).setTextContent(choose + "");

        saveDocument(document,dataFile);
    }
}
