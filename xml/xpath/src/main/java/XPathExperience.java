import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.util.List;

/**
 * description
 *
 * @author LinZhenNan 2019/09/17 17:38
 */
public class XPathExperience {
    @Test
    public void experience() throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read("src\\main\\resources\\Book.xml");
        Node node = document.selectSingleNode("/书架/书[2]/书名");
        System.out.println(node.getText());
    }

    @Test
    public void experience2() throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read("src\\main\\resources\\Book.xml");
        List list = document.selectNodes("//*");
        for(int i = 0; i < list.size(); i++) {
            Node node = (Node) list.get(i);
            System.out.println(node.getName() + "\t" + node.getText());
        }
    }
}
