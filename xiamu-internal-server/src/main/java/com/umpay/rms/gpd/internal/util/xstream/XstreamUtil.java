package com.umpay.rms.gpd.internal.util.xstream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 */
public class XstreamUtil {

    /**
     * 将bean转换为xml
     * @param obj 转换的bean
     * @return bean转换为xml
     */
    public static String objectToXml(Object obj) {

        XStream xStream = new XStream(new DomDriver("utf-8", new XmlFriendlyNameCoder("-_", "_")));
        xStream.processAnnotations(obj.getClass());
        return xStream.toXML(obj);

    }

    /**
     * 将xml转换为bean
     * @param xml 要转换为bean的xml
     * @param cls bean对应的Class
     * @return xml转换为bean
     */
    public static <T> T  xmlToObject(String xml, Class<T> cls){

        XStream xstream = new XStream(new DomDriver());
        xstream.processAnnotations(cls);
        xstream.ignoreUnknownElements();
        return (T)xstream.fromXML(xml);
    }

    public static Map<String, Object> getMapFromXML(String xmlString) throws ParserConfigurationException, IOException, SAXException {

        //这里用Dom的方式解析回包的最主要目的是防止API新增回包字段
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputStream is ;
        if (StringUtils.isNotBlank(xmlString)){
            is =  new ByteArrayInputStream(xmlString.getBytes("UTF-8"));
        }else {
            return null;
        }

        Document document = builder.parse(is);

        //获取到document里面的全部结点
        NodeList allNodes = document.getFirstChild().getChildNodes();
        Node node;
        Map<String, Object> map = new HashMap<>(allNodes.getLength());
        int i=0;
        while (i < allNodes.getLength()) {
            node = allNodes.item(i);
            if(node instanceof Element){
                map.put(node.getNodeName(),node.getTextContent());
            }
            i++;
        }
        return map;

    }

    private static XStream xstream=createXStream();

    private static XStream createXStream() {
        xstream = new XStream(new NoneEscapeAppDriver());
        xstream.autodetectAnnotations(true);
        return xstream;
    }
    /**
     * 在指定路径生成xml文件
     *
     * @param t
     * @param xmlPath
     * @param <T>
     * @throws IOException
     */
    public static <T> void toXML(T t, Path xmlPath) throws IOException {
        File file = new File(xmlPath.toUri());
        if (!file.exists()) {
            file.createNewFile();
        }
        xstream.processAnnotations(t.getClass());
        FileOutputStream fos = new FileOutputStream(xmlPath.toString());
        fos.write(xstream.toXML(t).getBytes());
        fos.close();
    }

    public static <T> T fromXML(Path xmlPath) throws MalformedURLException {
        File file = new File(xmlPath.toUri());
        return (T) xstream.fromXML(file);
    }

    /**
     * 生成xml字符串
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> String toXML(T t) {
        return xstream.toXML(t).replace("__","_");
    }

    public static <T> T fromXML(String xml) {
        return (T) xstream.fromXML(xml);
    }

    /**
     * 别名
     *
     * @param aliasMap 需要起别名的class，如果没有别名，Xstream会默认以Class的全限定名作为节点名称
     */
    public static void alias(Map<String, Class> aliasMap) {
        aliasMap.forEach((k, v) -> xstream.alias(k, v));
    }

    public static XStream getXstream() {
        return xstream;
    }
}
