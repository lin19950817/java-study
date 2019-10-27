import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.util.List;

/**
 * description
 *
 * @author LinZhenNan 2019/08/22 11:17
 */
public class Dom4jExperience {


    public void experience() throws DocumentException {

        // 创建一个 xml解析对象
        SAXReader reader = new SAXReader();

        // 把 xml文档加载到 document对象中
        Document document = reader.read("src\\main\\resources\\Book.xml");

        // 获取根元素
        Element root = document.getRootElement();

        // 获取“书”节点
        Element bookNode = root.element("书");

        // 获取“书”节点下所有节点
        List list = root.elements();

        System.out.println(list);
    }

    public void loop() throws DocumentException {
        // 创建一个 xml解析对象
        SAXReader reader = new SAXReader();

        // 把 xml文档加载到 document对象中
        Document document = reader.read("src\\main\\resources\\Book.xml");

        // 根
        Element root = document.getRootElement();

        treeWalk(root);
    }

    private void treeWalk(Element element) {
        System.out.println(element.getName());
        for(int i = 0; i < element.nodeCount(); i++) {
            Node node = element.node(i);
            if (node instanceof Element) {
                treeWalk((Element) node);
            }
        }
    }
}
