package org.cmms.modules.dkjkpt.tjcx.ygghdkxxtj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.dkjkpt.tjcx.ygghdkxxtj.entity.YgghdkxxtjNb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 员工管户贷款信息统计年报
 * @Author: jeecg-boot
 * @Date:   2022-09-27
 * @Version: V1.0
 */
public interface YgghdkxxtjNbMapper extends BaseMapper<YgghdkxxtjNb> {

    void InitData(String tjyf);
}
