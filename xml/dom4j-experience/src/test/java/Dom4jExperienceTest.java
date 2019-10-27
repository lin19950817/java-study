import org.dom4j.DocumentException;
import org.junit.Test;

/**
 * description
 *
 * @author LinZhenNan 2019/08/22 11:25
 */
public class Dom4jExperienceTest {

    private Dom4jExperience test = new Dom4jExperience();

    @Test
    public void experience() throws DocumentException {
        System.out.println("====================================================Dom4jExperienceTest experience START====================================================");
        test.experience();
        System.out.println("====================================================Dom4jExperienceTest experience END====================================================");
    }
}
