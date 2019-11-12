package org.lzn;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import org.junit.Test;
import org.lzn.entity.Student;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 内省
 *
 * @author LinZhenNan lin.zhennan@hand-china.com 2019/11/11 15:23
 */
public class MyIntrospector {

    //
    // Introspector
    // ------------------------------------------------------------------------------

    @Test
    public void demo1() throws IntrospectionException {
        // 将 Student中的所有属性封装到 BeanInfo对象中
        BeanInfo beanInfo = Introspector.getBeanInfo(Student.class);
        // 获取属性描述器
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

        System.out.println(propertyDescriptors.length);

        // 显示
        for (PropertyDescriptor item : propertyDescriptors) {
            System.out.println(item.getName());
        }
    }

    @Test
    public void demo2() throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        Student student = new Student();
        // 得到指定的属性对象
        PropertyDescriptor propertyDescriptor = new PropertyDescriptor("name", Student.class);
        // 得到 setter方法：setName()
        Method setter = propertyDescriptor.getWriteMethod();
        setter.invoke(student, "hehe");

        // 得到 getter方法：getName()
        Method getter = propertyDescriptor.getReadMethod();
        System.out.println(getter.invoke(student, null));
    }

    //
    // BeanUtil
    // ------------------------------------------------------------------------------

    @Test
    public void demo3() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Student student = new Student();

        // 写入
        BeanUtils.setProperty(student, "name", "hehe");
        // 支持基本类型自动转换
        BeanUtils.setProperty(student, "age", "250");
        // 读取
        String name = BeanUtils.getProperty(student, "name");

        System.out.println(name);
        System.out.println(student);
    }

    @Test
    public void demo4() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Student student = new Student();
        // 注册类型转换器
        ConvertUtils.register(new DateLocaleConverter(), Date.class);
        BeanUtils.setProperty(student, "birthday", "2008-08-08");
        String birthday = BeanUtils.getProperty(student, "birthday");

        System.out.println(birthday);
    }

    @Test
    public void demo5() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Student student = new Student();
        // 注册类型转换器
        ConvertUtils.register(new Converter() {
            public Object convert(Class type, Object value) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                if (value instanceof String) {
                    String v = (String) value;
                    try {
                        return simpleDateFormat.parse(v);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }
                return null;
            }
        }, Date.class);
        BeanUtils.setProperty(student, "birthday", "2008-08-08");
        String birthday = BeanUtils.getProperty(student, "birthday");

        System.out.println(birthday);
    }

    @Test
    public void demo6() throws InvocationTargetException, IllegalAccessException {
        Map map = new HashMap(16);
        // key必须要与对象中的字段一致
        map.put("name", "hehe");
        map.put("age", "13");
        map.put("birthday", "2008-08-08");

        Student student = new Student();

        ConvertUtils.register(new DateLocaleConverter(), Date.class);
        BeanUtils.populate(student, map);

        System.out.println(student);
    }
}
