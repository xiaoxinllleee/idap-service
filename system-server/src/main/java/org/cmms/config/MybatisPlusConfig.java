package org.cmms.config;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.DynamicTableNameInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

/**
 * 单数据源配置（jeecg.datasource.open = false时生效）
 * @Author zhoujf
 *
 */
@Configuration
@MapperScan(value={"org.cmms.modules.**.mapper*","org.cmms.modules.*.mapper", "org.cmms.modules.*.*.mapper", "org.cmms.modules.*.*.*.mapper"})
public class MybatisPlusConfig {
    public static ThreadLocal<String> myTableName = new ThreadLocal<>();

    public static ThreadLocal<String> myTableName2 = new ThreadLocal<>();

    public static ThreadLocal<String> myTableNameCkzhglxx = new ThreadLocal<>();

    /**
         *  分页插件
     */
    /*@Bean
    public PaginationInterceptor paginationInterceptor() {
        // 设置sql的limit为无限制，默认是500
        return new PaginationInterceptor().setLimit(-1);
    }*/
    
//    /**
//     * mybatis-plus SQL执行效率插件【生产环境可以关闭】
//     */
//    @Bean
//    public PerformanceInterceptor performanceInterceptor() {
//        return new PerformanceInterceptor();
//    }

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
        paginationInnerInterceptor.setMaxLimit(1000L);
        
        DynamicTableNameInnerInterceptor dynamicTableNameInnerInterceptor = new DynamicTableNameInnerInterceptor();
        dynamicTableNameInnerInterceptor.setTableNameHandler((sql, tableName) -> {
            // 获取参数方法  默认dateStr

            Map<String, Object> paramMap = RequestDataHelper.getRequestData();
            if (CollUtil.isNotEmpty(paramMap)){
                System.out.println("tableName===============>");
                System.out.println(tableName);
                if (paramMap.get(tableName) != null){
                    System.out.println("tableName2===============>");
                    System.out.println(paramMap.get(tableName).toString());
                    return paramMap.get(tableName).toString();
                }
            }
            return tableName ;
        });

        interceptor.addInnerInterceptor(dynamicTableNameInnerInterceptor);
        interceptor.addInnerInterceptor(paginationInnerInterceptor);
        return interceptor;
    }



   /* @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        //分页查询上限重新赋值
        paginationInterceptor.setLimit(100000);

        // 创建SQL解析器集合
        List<ISqlParser> sqlParserList = new ArrayList<>();

        // 动态表名SQL解析器
        DynamicTableNameParser dynamicTableNameParser = new DynamicTableNameParser();
        Map<String, ITableNameHandler> tableNameHandlerMap = new HashMap<>();
        // Map的key就是需要替换的原始表名
        tableNameHandlerMap.put("MID_DMPM_DKYEBMXQKTJ", new ITableNameHandler() {
            @Override
            public String dynamicTableName(MetaObject metaObject, String sql, String tableName) {
                return myTableName.get();
            }
        });
        tableNameHandlerMap.put("MID_DMPM_DKYEBMXQKTJ_V", new ITableNameHandler() {
            @Override
            public String dynamicTableName(MetaObject metaObject, String sql, String tableName) {
                return myTableName2.get();
            }
        });
        tableNameHandlerMap.put("CKJKPT_CKZHGLXX", new ITableNameHandler() {
            @Override
            public String dynamicTableName(MetaObject metaObject, String sql, String tableName) {
                return myTableNameCkzhglxx.get();
            }
        });
      *//*  tableNameHandlerMap.put("t_a_dc_check_xxx2", new ITableNameHandler() {
            @Override
            public String dynamicTableName(MetaObject metaObject, String sql, String tableName) {
                // 自定义表名规则，或者从配置文件、request上下文中读取
                // 假设这里的用户表根据年份来进行分表操作
                Date date = new Date();
                //DateTimeFormatter dateTimeFormatter = new DateTimeFormatter(LocalDateTime.now());
                String year = String.format("_%tY%tm", date,date);
                // 返回最后需要操作的表名，sys_user_2019
                return "t_a_dc_check_xxx2" + year;
            }
        });

        //增加一个销账月表表名过滤器
        tableNameHandlerMap.put("dt_xxx_item_month", new ITableNameHandler() {
            @Override
            public String dynamicTableName(MetaObject metaObject, String sql, String tableName) {
                return myTableName.get();//返回null不会替换 注意 多租户过滤会将它一块过滤不会替换@SqlParser(filter=true) 可不会替换
            }
        });*//*
        dynamicTableNameParser.setTableNameHandlerMap(tableNameHandlerMap);
        sqlParserList.add(dynamicTableNameParser);
        paginationInterceptor.setSqlParserList(sqlParserList);
        paginationInterceptor.setLimit(-1);
        return paginationInterceptor;
    }*/

}
