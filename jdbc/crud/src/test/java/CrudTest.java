import org.junit.Test;

import static org.junit.Assert.*;

/**
 * description
 *
 * @author LinZhenNan 2019/08/16 17:11
 */
public class CrudTest {
    Crud test = new Crud();

    @Test
    public void insert() throws Exception {
        System.out.println("====================================================CrudTest insert START====================================================");
        test.insert();
        System.out.println("====================================================CrudTest insert END====================================================");
    }

    @Test
    public void update() throws Exception {
        System.out.println("====================================================CrudTest insert START====================================================");
        test.update();
        System.out.println("====================================================CrudTest insert END====================================================");
    }

    @Test
    public void selectByUtils() throws Exception {
        System.out.println("====================================================CrudTest selectByUtils START====================================================");
        test.selectByUtils();
        System.out.println("====================================================CrudTest selectByUtils END====================================================");
    }
}