import org.junit.Test;

import static org.junit.Assert.*;

/**
 * description
 *
 * @author LinZhenNan 2019/08/19 15:17
 */
public class PreparedStatementExperienceTest {
    private PreparedStatementExperience test = new PreparedStatementExperience();

    @Test
    public void select() throws Exception {
        System.out.println("====================================================PreparedStatementExperienceTest select START====================================================");
        test.select();
        System.out.println("====================================================PreparedStatementExperienceTest select END====================================================");
    }
}