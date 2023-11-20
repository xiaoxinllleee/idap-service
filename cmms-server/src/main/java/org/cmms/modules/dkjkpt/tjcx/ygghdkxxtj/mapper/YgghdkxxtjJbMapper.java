package org.cmms.modules.dkjkpt.tjcx.ygghdkxxtj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.dkjkpt.tjcx.ygghdkxxtj.entity.YgghdkxxtjJb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 员工管户贷款信息统计季报
 * @Author: jeecg-boot
 * @Date:   2022-09-27
 * @Version: V1.0
 */
public interface YgghdkxxtjJbMapper extends BaseMapper<YgghdkxxtjJb> {

    void InitData(String tjyf);

}
