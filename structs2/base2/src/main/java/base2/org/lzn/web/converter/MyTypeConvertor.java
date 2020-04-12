package base2.org.lzn.web.converter;

import org.apache.struts2.util.StrutsTypeConverter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * 自定义类型转换器<br>
 *     目的：把 MM/dd/yyyy 格式的数据转成日期类型，把数据库中的本地日期格式，转成 MM/dd/yyyy 形式输出
 * 第一步：编写一个类，继承 StrutsTypeConverter，实现 convertFromString, convertToString
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/04/07 22:11
 */
public class MyTypeConvertor extends StrutsTypeConverter {

    private DateFormat format = new SimpleDateFormat("MM/dd/yyyy");

    /**
     * 把字符串数组中的数据转成日期类型
     *
     * @param context OGNL 的上下文对象
     * @param values 要转换的数据
     * @param toClass 目标类型
     * @author LinZhenNan lin_hehe@qq.com 2020-04-07 22:16
     * @return java.lang.Object
     */
    @Override
    public Object convertFromString(Map context, String[] values, Class toClass) {
        System.out.println("convertFromString");
        // 1. 先看看有没有数据
        if (values == null || values.length == 0) {
            return null;
        }
        // 2.取出数组中的第一个元素
        String date = values[0];
        // 3. 判断目标类型的字节码是不是日期类型
        if (toClass == Date.class) {
            try {
                // 4. 使用 DateFormat 进行转换，并且返回转换后的结果
                return format.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    /**
     * 把日期类型的数据转换成字符串
     *
     * @param context OGNL 的上下文对象
     * @param o 要转换的数据
     * @author LinZhenNan lin_hehe@qq.com 2020-04-07 22:17
     * @return java.lang.String
     */
    @Override
    public String convertToString(Map context, Object o) {
        System.out.println("convertToString");
        // 1. 判断 o 是不是日期类型
        if (o instanceof Date) {
            Date date = (Date)o;
            // 2. 是日期类型，使用转换器转成指定格式的字符串，并返回
            return format.format(date);
        }
        return null;
    }
}
