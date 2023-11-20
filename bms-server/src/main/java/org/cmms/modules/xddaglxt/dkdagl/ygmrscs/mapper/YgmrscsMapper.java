package org.cmms.modules.xddaglxt.dkdagl.ygmrscs.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.xddaglxt.dkdagl.ygmrscs.entity.Ygmrscs;

/**
 * @Description: 员工每日上传数
 * @Author: jeecg-boot
 * @Date:   2021-12-01
 * @Version: V1.0
 */
public interface YgmrscsMapper extends BaseMapper<Ygmrscs> {
    void pYgmrscs(@Param("tjrqBegin")String tjrqBegin, @Param("tjrqEnd")String tjrqEnd, @Param("username")String username);
}
