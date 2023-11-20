package org.cmms.common.utils;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceProperties;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import lombok.extern.slf4j.Slf4j;
import net.sf.saxon.lib.Logger;
import org.cmms.common.util.SpringContextUtils;
import org.springframework.beans.factory.annotation.Value;
import org.apache.poi.ss.formula.functions.T;
import org.cmms.common.exception.XbootException;
import org.cmms.common.vo.PageVo;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @author Exrickx
 */
@Component
@Slf4j
public class PageUtil implements ApplicationContextAware {

    @Value("${com.etl.sfdsjpt}")
    private  String sfdsjpt;


    private static boolean sfdsjptValue;

    @PostConstruct
    public void init(){
        sfdsjptValue = Boolean.valueOf(this.sfdsjpt);
    }

    private final static String[] KEYWORDS = {"master", "truncate", "insert", "select",
            "delete", "update", "declare", "alter", "drop", "sleep"};

    /**
     * JPA分页封装
     * @param page
     * @return
     */
    public static Pageable initPage(PageVo page){

        Pageable pageable = null;
        int pageNumber = page.getPageNumber();
        int pageSize = page.getPageSize();
        String sort = page.getSort();
        String order = page.getOrder();

        if(pageNumber<1){
            pageNumber = 1;
        }
        if(pageSize<1){
            pageSize = 10;
        }
        if(pageSize>100){
            pageSize = 100;
        }
        if(StrUtil.isNotBlank(sort)) {
            Sort.Direction d;
            if(StrUtil.isBlank(order)) {
                d = Sort.Direction.DESC;
            } else {
                d = Sort.Direction.valueOf(order.toUpperCase());
            }
            Sort s = Sort.by(d, sort);
            pageable = PageRequest.of(pageNumber-1, pageSize, s);
        } else {
            pageable = PageRequest.of(pageNumber-1, pageSize);
        }
        return pageable;
    }

    /**
     * Mybatis-Plus分页封装
     * @param page
     * @return
     */
    public static Page initMpPage(PageVo page){

        Page p = null;
        int pageNumber = page.getPageNumber();
        int pageSize = page.getPageSize();
        String sort = page.getSort();
        String order = page.getOrder();

        SQLInject(sort);

        if(pageNumber<1){
            pageNumber = 1;
        }
        if(pageSize<1){
            pageSize = 10;
        }
        if(pageSize>100){
            pageSize = 100;
        }
        if(StrUtil.isNotBlank(sort)) {
            Boolean isAsc = false;
            if(StrUtil.isBlank(order)) {
                isAsc = false;
            } else {
                if("desc".equals(order.toLowerCase())){
                    isAsc = false;
                } else if("asc".equals(order.toLowerCase())){
                    isAsc = true;
                }
            }
            p = new Page(pageNumber, pageSize);
            if(isAsc){
                p.addOrder(OrderItem.asc(camel2Underline(sort)));
            } else {
                p.addOrder(OrderItem.desc(camel2Underline(sort)));
            }

        } else {
            p = new Page(pageNumber, pageSize);
        }
        return p;
    }

    /**
     * List 手动分页
     * @param page
     * @param list
     * @return
     */
    public static List listToPage(PageVo page, List list) {

        int pageNumber = page.getPageNumber() - 1;
        int pageSize = page.getPageSize();

        if(pageNumber<0){
            pageNumber = 0;
        }
        if(pageSize<1){
            pageSize = 10;
        }
        if(pageSize>100){
            pageSize = 100;
        }

        int fromIndex = pageNumber * pageSize;
        int toIndex = pageNumber * pageSize + pageSize;

        if(fromIndex > list.size()){
            return new ArrayList();
        } else if(toIndex >= list.size()) {
            return list.subList(fromIndex, list.size());
        } else {
            return list.subList(fromIndex, toIndex);
        }
    }


    /**
     * List 手动分页
     * @return
     */
    public static IPage toPage(Class clas,IService service ,Integer pageNo,Integer pageSize, QueryWrapper queryWrapper,String ... orderColumn) {
        List<String> orderList=new ArrayList<>();
        for (int i=0;i<orderColumn.length;i++){
            orderList.add(orderColumn[i]);
        }

        IPage<T> page = new Page<T>(pageNo, pageSize);
        Long total = 0L;
        if (sfdsjptValue) {
            //DynamicDataSourceProperties properties = SpringUtil.getBean(DynamicDataSourceProperties.class);
            DynamicDataSourceProperties properties = SpringContextUtils.getBean(DynamicDataSourceProperties.class);
            String dsType = "";
            String dbType = "";
            try {
                DS[] annotationsByType = (DS[]) clas.getAnnotationsByType(DS.class);
                if (annotationsByType != null && annotationsByType.length > 0) {
                    for (DS f : annotationsByType) {
                        dsType = f.value();
                        log.info("当前使用数据源[DS]：" + f.value());
                    }
                    final Map<String, DataSourceProperty> datasource = properties.getDatasource();
                    DataSourceProperty dataSourceProperty = datasource.get(dsType);
                    if (dataSourceProperty.getDriverClassName().equals("org.apache.hive.jdbc.HiveDriver")) {
                        dbType = "hive";
                    } else if (dataSourceProperty.getDriverClassName().equals("com.cloudera.impala.jdbc.Driver")) {
                        dbType = "impala";
                    } else if (dataSourceProperty.getDriverClassName().equals("oracle.jdbc.driver.OracleDriver")) {
                        dbType = "oracle";
                    }
                } else {
                    dbType = "oracle";
                }
                log.info("当前使用[DbType]：" + dbType);
                try {
                    total = service.count(queryWrapper);
                } catch (Exception e) {
                    if (e.getMessage().indexOf("Error setting metadata result set") > 0) {
                        total = service.count(queryWrapper);
                    } else {
                        log.error("PageUtil-1-分页查询异常:" + e.getMessage());
                        e.printStackTrace();
                    }
                }
                if ("impala".equals(dbType)) {
                    queryWrapper.orderByDesc(orderList);
                    queryWrapper.last("limit " + pageSize + " offset " + (pageNo - 1) * 10);
                    IService service1 = service;
                    List<T> list = service1.list(queryWrapper);
                    page.setTotal(total);
                    page.setRecords(list);
                } else if ("hive".equals(dbType)) {
                    queryWrapper.orderByDesc(orderList);
                    queryWrapper.last("limit " + (pageNo - 1) * 10 + "," + pageSize);
                    List<T> list = service.list(queryWrapper);
                    page.setTotal(total);
                    page.setRecords(list);
                } else if ("oracle".equals(dbType)) {
                    queryWrapper.orderByDesc(orderList);
                    page = service.page(page, queryWrapper);
                }
            } catch (Exception e) {
                if (e.getMessage()!=null&&e.getMessage().indexOf("Error setting metadata result set") > 0) {
                    IService service1 = service;
                    List<T> list = service1.list(queryWrapper);
                    page.setTotal(total);
                    page.setRecords(list);
                } else {
                    log.error("PageUtil-2-分页查询异常:" + e.getMessage());
                    e.printStackTrace();
                }
            }

        } else {
            queryWrapper.orderByDesc(orderList);
            page = service.page(page, queryWrapper);
        }
        return page;
    }



    /**
     * 驼峰法转下划线
     */
    public static String camel2Underline(String str) {

        if (StrUtil.isBlank(str)) {
            return "";
        }
        if(str.length()==1){
            return str.toLowerCase();
        }
        StringBuffer sb = new StringBuffer();
        for(int i=1;i<str.length();i++){
            if(Character.isUpperCase(str.charAt(i))){
                sb.append("_"+Character.toLowerCase(str.charAt(i)));
            }else{
                sb.append(str.charAt(i));
            }
        }
        return (str.charAt(0)+sb.toString()).toLowerCase();
    }

    /**
     * 防Mybatis-Plus order by注入
     * @param param
     */
    public static void SQLInject(String param){

        if (StrUtil.isBlank(param)) {
            return;
        }

        // 转换成小写
        param = param.toLowerCase();
        // 判断是否包含非法字符
        for (String keyword : KEYWORDS) {
            if (param.contains(keyword)) {
                throw new XbootException(param + "包含非法字符");
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}
