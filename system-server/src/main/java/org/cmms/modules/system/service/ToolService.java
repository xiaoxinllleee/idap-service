package org.cmms.modules.system.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.system.vo.TableComments;

import java.util.List;
import java.util.Map;

/**
 * @Description: 
 * @Author: jeecg-boot
 * @Date:   2021-06-21
 * @Version: V1.0
 */
public interface ToolService extends IService<TableComments> {

    /**
     * 根据表名拿字段名,字段注释等
     * @param tableName
     * @return
     */
    List<TableComments> getTableAll(String tableName);

    /**
     * 根据表名拿表名注释
     * @param tableName
     * @return
     */
    String getTableComments(String tableName);

    Object getResultDynamic(String tableName, String result, List list, String ds);

    List<Map<String, Object>> getTableData(String tableName, Map condition, Map sort, String ds);

    void execInsertSql(@Param("sqlStr") String sql, String ds);


}
