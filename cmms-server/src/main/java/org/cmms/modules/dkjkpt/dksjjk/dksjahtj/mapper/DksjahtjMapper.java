package org.cmms.modules.dkjkpt.dksjjk.dksjahtj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.dkjkpt.dksjjk.dksjahtj.entity.Dksjahtj;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 贷款数据按户统计
 * @Author: Penghr
 * @Date:   2020-03-31
 * @Version: V1.0
 */
public interface DksjahtjMapper extends BaseMapper<Dksjahtj> {

    void InitData(String tjyf);

}
