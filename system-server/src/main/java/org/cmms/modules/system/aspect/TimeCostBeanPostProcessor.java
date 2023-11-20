package org.cmms.modules.system.aspect;

import com.google.common.collect.Maps;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Date 2022/5/27
 * @Created by eran
 */
//@Component
public class TimeCostBeanPostProcessor implements BeanPostProcessor {
    Map<String, Long> costMap = Maps.newConcurrentMap();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        costMap.put(beanName, System.currentTimeMillis());
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        try{
            long start = costMap.get(beanName);
            long cost  = System.currentTimeMillis() - start;
            if (cost > 0 && cost >= 100) {
                costMap.put(beanName, cost);
//                System.out.println("class: " + bean.getClass().getName()
//                        + "\tbean: " + beanName
//                        + "\ttime" + cost);
                System.out.println(" bean: "+beanName +" || 花费时间 "+ cost);

            }
            System.out.println("costMap=>"+costMap.size());
        }catch (Exception e){
            System.out.println(beanName+"出现了错误");
        }


        return bean;
    }
}
