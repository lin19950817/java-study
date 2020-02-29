import org.lzn.service.AccountService;
import org.lzn.service.impl.AccountServiceImpl;
import org.lzn.service.impl.AccountServiceImpl2;

import java.math.BigDecimal;
import java.sql.SQLException;

/**
 * description
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/02/11 18:04
 */
public class Main {
    public static void main(String[] args) throws SQLException {
        AccountService service = new AccountServiceImpl();
        service.transfer("hehe", "haha", new BigDecimal(200));

        // ThreadLocal 的学习
        service = new AccountServiceImpl2();
        service.transfer("hehe", "haha", new BigDecimal(200));
    }
}
