package org.cmms.modules.system.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.system.vo.TableComments;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ToolMapper extends BaseMapper<TableComments> {
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

    Object getResultDynamic(String tableName, String result, List list);

    List<Map<String, Object>> getTableData(String tableName, Map condition, Map sort);

    @Insert("${sqlStr}")
    void execInsertSql(@Param("sqlStr") String sql);
}
