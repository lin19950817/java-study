package org.lzn;

import org.apache.commons.beanutils.BeanUtils;
import org.lzn.entity.Register;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Map;

/**
 * 获取表单数据
 *
 * @author LinZhenNan lin_hehe@qq.com 2019/11/10 14:27
 */
public class ThirdHttpServletRequest extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 告诉服务器要使用什么编码。注：浏览器使用的是什么编码传过来的就是什么编码
        req.setCharacterEncoding("UTF-8");

        // 显示指定的表单数据
//        showDesignativeFormData(req);

        // 显示所有表单数据
//        showAllFormData(req);

        try {
            // 获得表单数据并封装
//            showFormDataOfEncapsulation(req);

            // 使用框架封装表单数据
            showFormDataForFrame(req);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    //
    // 私有方法
    // ------------------------------------------------------------------------------
    /**
     * 显示所有表单数据
     *
     * @param req 请求
     * @author LinZhenNan lin_hehe@qq.com 2019-11-10 18:32
     */
    private void showAllFormData(HttpServletRequest req) {
        // 获取所有表单的 name
        Enumeration names = req.getParameterNames();

        // 遍历获取所有表单 name对应的值
        while (names.hasMoreElements()) {
            String name = (String)names.nextElement();

            // 获取表单 name对应的所有值
            String[] values = req.getParameterValues(name);
            System.out.println(name + " : ");
            for (int i = 0; values != null && i < values.length; i++) {
                System.out.println("\t" + values[i]);
            }
        }

    }
    
    /**
     * 使用框架封装表单数据
     *
     * @param req 请求
     * @author LinZhenNan lin_hehe@qq.com 2019-11-11 0:27
     */
    private void showFormDataForFrame(HttpServletRequest req) throws InvocationTargetException, IllegalAccessException {
        Register r = new Register();
        System.out.println("封装前：" + r);
        BeanUtils.populate(r, req.getParameterMap());
        System.out.println("封装后：" + r);
    }

    /**
     * 获取表单数据并封装
     *
     * @param req 请求
     * @author LinZhenNan lin_hehe@qq.com 2019-11-10 19:02
     */
    private void showFormDataOfEncapsulation(HttpServletRequest req) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        Register r = new Register();
        System.out.println("封装数据前：" + r);

        // 获取表单数据
        Map<String, String[]> map = req.getParameterMap();

        for (Map.Entry<String, String[]> m : map.entrySet()) {
            String name = m.getKey();
            String[] value = m.getValue();

            // 创建属性描述器
            PropertyDescriptor pd = new PropertyDescriptor(name, Register.class);
            // 得到 setter属性
            Method setter = pd.getWriteMethod();

            if (1 == value.length) {
                // 给一个值的变量赋值
                setter.invoke(r, value[0]);
            } else {
                // jdk 1.4版本会将 value拆开再调用 invoke可变参数的 api，所以装箱避免拆开
                // 给复选框赋值
                setter.invoke(r, (Object)value);
            }
        }
        System.out.println("封装数据后：" + r);
    }


    /**
     * 显示指定的表单数据
     *
     * @param req 请求
     * @author LinZhenNan lin_hehe@qq.com 2019-11-10 14:42
     */
    private void showDesignativeFormData(HttpServletRequest req) {
        // 根据表单中的 name属性的名，获取 value属性的值
        String userName = req.getParameter("userName");
        String pwd = req.getParameter("pwd");
        String sex = req.getParameter("sex");
        String[] hobbys = req.getParameterValues("hobby");
        String city = req.getParameter("city");

        // 显示
        System.out.println("userName : " + userName);
        System.out.println("pwd : " + pwd);
        System.out.println("sex : " + sex);
        System.out.println("hobby : ");
        for (int i = 0; hobbys != null && i < hobbys.length; i++) {
            System.out.println("\t" + hobbys[i]);
        }
        System.out.println("city : " + city);
    }
}
