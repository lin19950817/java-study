import org.junit.Assert;
import org.junit.Test;

/**
 * description
 *
 * @author LinZhenNan 2019/08/16 11:16
 */
public class CalculatorTest {

    @Test
    public void test01() {
        Calculator calculator = new Calculator();
        Assert.assertEquals(9, calculator.add(3, 6));
    }
    
    @Test
    public void test02() {
        Calculator calculator = new Calculator();
        /**
         * 最后参数为浮点值
         */
        Assert.assertEquals(15, calculator.quadruplicate(2), 1);
    }

}
