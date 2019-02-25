package super1peng.demo01;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * @Author: super1peng
 * @Date: 2019/2/25 下午3:15
 * @Version 1.0
 */
public class TextJaxp {

    public static void main(String[] args) throws Exception {
//        listElement();
//        delSex();
//        modifySex();
        addSex();
    }

    // 遍历几点，把所有元素的名称打印出来
    public static void listElement() throws Exception {

        /*
         * 1、创建解析器工厂
         * 2、根据解析器工厂创建解析器
         * 3、解析xml，返回document
         *
         * ====使用递归实现=====
         * 4、得到根节点
         * 5、得到根节点子节点
         * 6、得到根节点子节点的子节点
         * */

        // 创建解析器工厂
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        // 创建解析器
        DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
        // 得到document对象
        Document document = documentBuilder.parse("src/person.xml");

//        NodeList list = document.getElementsByTagName("name");
//        for (int i = 0; i < list.getLength(); i++) {
//            Node item = list.item(i);
//            String textContent = item.getTextContent();
//            System.out.println(textContent);
//        }
        // 编写一个方法实现遍历操作
        list1(document);
    }

    private static void list1(Node node){

        if(node.getNodeType() == Node.ELEMENT_NODE){
            System.out.println(node.getNodeName());
        }
        // 得到一层子节点
        NodeList list = node.getChildNodes();
        for (int i=0; i<list.getLength(); i++){
            // 得到每一个节点
            Node node1 = list.item(i);
            //继续得到node1的子节点
            //node1.getChildNodes()
            list1(node1);
        }
    }

    // 删除<sex>nan</sex>节点
    public static void delSex() throws Exception {
        /*
         * 1、创建解析器工厂
         * 2、根据解析器工厂创建解析器
         * 3、解析xml，返回document
         *
         * 4、获取sex元素
         * 5、获取sex的父节点
         * 6、删除使用父节点删除 removeChild方法
         *
         * 7、回写xml
         * */

        // 创建工厂
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        // 创建解析器
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        // 得到document
        Document document = builder.parse("src/person.xml");
        // 得到sex元素
        Node sex1 = document.getElementsByTagName("sex").item(0);
        // 得到sex的父节点
        Node p1 = sex1.getParentNode();
        //删除操作
        p1.removeChild(sex1);
        // 回写操作
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.transform(new DOMSource(document), new StreamResult("src/person.xml"));
    }


    // 修改第一个p1下面的sex内容是nan
    public static void modifySex() throws Exception {
        /*
         * 1、创建解析器工厂
         * 2、根据解析器工厂创建解析器
         * 3、解析xml，返回document
         *
         * 4、得到sex item方法
         * 5、修改sex里面的值  setTextContent方法
         *
         * 6、回写xml
         * */

        // 创建解析器工厂
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        // 创建解析器
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document document = builder.parse("src/person.xml");
        // 得到sex
        Node sex1 = document.getElementsByTagName("sex").item(0);
        // 修改sex的值
        sex1.setTextContent("nan");
        // 进行会写操作
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.transform(new DOMSource(document), new StreamResult("src/person.xml"));
    }

    // 在第一个p1下面（末尾）添加<sex>nv<sex>
    public static void addSex() throws Exception {
        /*
         * 1、创建解析器工厂
         * 2、根据解析器工厂创建解析器
         * 3、解析xml，返回document
         *
         * 4、得到第一个p1
         * 	- 得到所有p1，使用item方法下标得到
         * 5、创建sex标签 createElement
         * 6、创建文本 createTextNode
         * 7、把文本添加到sex下面 appendChild
         * 8、把sex添加到第一个p1下面
         *
         * 9、回写xml
         * */
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document document = builder.parse("src/person.xml");
        // 得到所有的p1
        NodeList list = document.getElementsByTagName("p1");
        Node p1 = list.item(0);
        // 创建标签
        Element sex1 = document.createElement("sex");
        // 创建文本
        Text text1 = document.createTextNode("nv");
        // 将文本添加到sex1下面
        sex1.appendChild(text1);
        // 将sex1添加到p1下面
        p1.appendChild(sex1);
        // 回写
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.transform(new DOMSource(document), new StreamResult("src/person.xml"));
    }

    // 查询xml中第一个name元素的值
    public static void selectSin() throws Exception {
        /*
         * 1、创建解析器工厂
         * 2、根据解析器工厂创建解析器
         * 3、解析xml，返回document
         *
         * 4、得到所有name元素
         * 5、使用返回集合，里面方法 item，下标获取具体的元素
         * 6、得到具体的值，使用 getTextContent方法
         *
         * */
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document document = builder.parse("src/person.xml");
        Node name = document.getElementsByTagName("name").item(1);
        String textContent = name.getTextContent();
        System.out.println(textContent);
    }

    // 查询所有name元素的值
    public static void selectAll() throws Exception {
        /*
         * 1、创建解析器工厂
         * 2、根据解析器工厂创建解析器
         * 3、解析xml返回document
         *
         * 4、得到所有的name元素
         * 5、返回集合，遍历集合，得到每一个name元素
         * */
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document document = builder.parse("src/person.xml");
        NodeList list = document.getElementsByTagName("name");
        for (int i = 0; i < list.getLength(); i++) {
            Node item = list.item(i);
            String textContent = item.getTextContent();
            System.out.println(textContent);
        }

    }
}
