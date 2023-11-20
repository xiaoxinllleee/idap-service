package org.cmms.modules.ywgl.dkyw.dksjzrgl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.ywgl.dkyw.dksjzrgl.entity.DksjzrglHistory;

import java.util.List;

/**
 * @Description: 贷款数据责任管理
 * @Author: jeecg-boot
 * @Date:   2021-09-24
 * @Version: V1.0
 */
public interface DksjzrglHistoryMapper extends BaseMapper<DksjzrglHistory> {

    List<DksjzrglHistory> deleteDkzh(@Param("dkzh") String dkzh, @Param("tableName") String tableName);

  List<DksjzrglHistory> saveTjyf(@Param("list") List list, @Param("tableName") String tableName);
}
