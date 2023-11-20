package org.cmms.modules.system.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.system.mapper.ToolMapper;
import org.cmms.modules.system.service.ToolService;
import org.cmms.modules.system.vo.TableComments;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description: 
 * @Author: jeecg-boot
 * @Date:   2021-06-21
 * @Version: V1.0
 */
@Service
public class ToolServiceImpl extends ServiceImpl<ToolMapper, TableComments> implements ToolService {
    @Override
    @DS("ads")
    public List<TableComments> getTableAll(String tableName) {
        return baseMapper.getTableAll(tableName);
    }

    @Override
    public String getTableComments(String tableName) {
        return baseMapper.getTableComments(tableName);
    }

    @DS("#ds")
    public Object getResultDynamic(String tableName, String result, List list, String ds) {
        return baseMapper.getResultDynamic(tableName, result, list);
    }

    @DS("#ds")
    public List<Map<String, Object>> getTableData(String tableName, Map condition, Map sort, String ds) {
        return baseMapper.getTableData(tableName, condition, sort);
    }

    @DS("#ds")
    public void execInsertSql(@Param("sqlStr") String sql, String ds) {
        baseMapper.execInsertSql(sql);
    }
}
