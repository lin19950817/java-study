import org.junit.Test;

import static org.junit.Assert.*;

/**
 * description
 *
 * @author LinZhenNan 2019/08/17 17:30
 */
public class ResultSetExperienceTest {
    ResultSetExperience test = new ResultSetExperience();

    @Test
    public void getObject() throws Exception {
        System.out.println("====================================================ResultSetExperienceTest getObject START====================================================");
        test.getObject();
        System.out.println("====================================================ResultSetExperienceTest getObject END====================================================");
    }

    @Test
    public void returnEntity() throws Exception {
        System.out.println("====================================================ResultSetExperienceTest returnEntity START====================================================");
        test.returnEntity();
        System.out.println("====================================================ResultSetExperienceTest returnEntity END====================================================");
    }

    @Test
    public void moveCursor() throws Exception {
        System.out.println("====================================================ResultSetExperienceTest moveCursor START====================================================");
        test.moveCursor();
        System.out.println("====================================================ResultSetExperienceTest moveCursor END====================================================");
    }
}