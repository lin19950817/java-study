import org.junit.Test;

import static org.junit.Assert.*;

/**
 * description
 *
 * @author LinZhenNan 2019/08/16 16:03
 */
public class FirstExperienceTest {

    FirstExperience test = new FirstExperience();
    @Test
    public void first() throws Exception {
        System.out.println("====================================================FirstExperienceTest first START====================================================");
        test.first();
        System.out.println("====================================================FirstExperienceTest first END====================================================");
    }

    @Test
    public void second() throws Exception {
        System.out.println("====================================================FirstExperienceTest second START====================================================");
        test.second();
        System.out.println("====================================================FirstExperienceTest second END====================================================");
    }

    @Test
    public void third() throws Exception {
        System.out.println("====================================================FirstExperienceTest third START====================================================");
        test.third();
        System.out.println("====================================================FirstExperienceTest third END====================================================");
    }
}