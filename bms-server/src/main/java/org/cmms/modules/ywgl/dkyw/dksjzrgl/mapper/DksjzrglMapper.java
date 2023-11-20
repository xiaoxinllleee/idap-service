package org.cmms.modules.ywgl.dkyw.dksjzrgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.ywgl.dkyw.dksjzrgl.entity.Dksjzrgl;

/**
 * @Description: 贷款数据责任管理
 * @Author: jeecg-boot
 * @Date:   2021-09-24
 * @Version: V1.0
 */
public interface DksjzrglMapper extends BaseMapper<Dksjzrgl> {

    List<Dksjzrgl> deleteDkzh(@Param("dkzh") String dkzh ,@Param("tableName")String tableName);

  List<Dksjzrgl> saveTjyf(@Param("list") List list ,@Param("tableName") String tableName);
}
