package org.cmms.common.util;


import org.apache.poi.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class BeanUtil {
    /**
     * @author zml2015
     * @Email zhengmingliang911@gmail.com
     * @Time 2017年2月14日 下午5:14:25
     * @Description <p>获取到对象中属性为null的属性名  </P>
     * @param source 要拷贝的对象
     * @return
     */
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null)
                emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    /**
     * @author zml2015
     * @Email zhengmingliang911@gmail.com
     * @Time 2017年2月14日 下午5:15:30
     * @Description <p> 拷贝非空对象属性值 </P>
     * @param source 源对象
     * @param target 目标对象
     */
    public static void copyPropertiesIgnoreNull(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    /**
     * 对象属性拷贝-只拷贝为空的字段
     * @param source 源对象
     * @param target 目标对象
     * @throws IllegalAccessException
     */
    public static void copyPropertiesIsNull(Object source,Object target) throws IllegalAccessException {
        //获取源对象的所有字段
        Field[] sourceFields = source.getClass().getDeclaredFields();
        //获取目标对象的所有字段
        Field[] targetFields = target.getClass().getDeclaredFields();
        //遍历
        for (int i=0;i<sourceFields.length;i++){
            for (int j=0;j<targetFields.length;j++){
                //根据字段名称去匹配，有相同的字段名称进行复制值
                if (sourceFields[i].getName().equals(targetFields[j].getName())){
                    //开启权限
                    sourceFields[i].setAccessible(true);
                    targetFields[j].setAccessible(true);
                    //判断目标对象里面的字段为null时
                    if ("null".equals(String.valueOf(targetFields[j].get(target)))){
                        //给目标对象字段赋值
                        targetFields[j].set(target,sourceFields[i].get(source));
                        //赋值后跳出当前循环
                        break;
                    }
                    //关闭权限
                    sourceFields[i].setAccessible(false);
                    targetFields[j].setAccessible(false);
                }
            }
        }
    }
}
