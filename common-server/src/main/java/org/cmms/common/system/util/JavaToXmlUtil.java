package org.cmms.common.system.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class JavaToXmlUtil {

    public static String javaToxml(Object o) throws Exception {
        // 获取JAXB的上下文环境，需要传入具体的 Java bean -> 这里使用Student
        JAXBContext context = JAXBContext.newInstance(o.getClass());
        // 创建 Marshaller 实例
        Marshaller marshaller = context.createMarshaller();
        // 设置转换参数 -> 这里举例是告诉序列化器是否格式化输出
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        // 构建输出环境 -> 这里使用标准输出，输出到控制台Console
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 将所需对象序列化 -> 该方法没有返回值
        marshaller.marshal(o, baos);
        
        return baos.toString("UTF-8");
    }

    public static Object xmlToJava(String s,Class c) throws Exception {
        JAXBContext context = JAXBContext.newInstance(c);
        // 创建 UnMarshaller 实例
        Unmarshaller unmarshaller = context.createUnmarshaller();
        // 加载需要转换的XML数据 -> 这里使用InputStream，还可以使用File，Reader等
        //InputStream stream = SimpleTest.class.getClassLoader().getResourceAsStream("lesson1.xml");
        InputStream stream = new ByteArrayInputStream(s.getBytes("utf-8"));
        // 将XML数据序列化 -> 该方法的返回值为Object基类，需要强转类型
        Object unmarshal = unmarshaller.unmarshal(stream);
        // 将结果打印到控制台
        return unmarshal;
    }

}
