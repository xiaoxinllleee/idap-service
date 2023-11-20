package org.cmms.modules.ywgl.djkyw.djkkhzr.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.ywgl.djkyw.djkkhzr.entity.Djkkhzr;

/**
 * @Description: 贷记卡考核责任
 * @Author: jeecg-boot
 * @Date:   2021-12-06
 * @Version: V1.0
 */
public interface DjkkhzrMapper extends BaseMapper<Djkkhzr> {

    /**
     * 贷记卡考核责任新增、编辑、删除时调用存储更新贷款责任人
     * @param tjrq 统计日期
     */
    void CallPkgUpdateDkzrr(@Param("tjrq") String tjrq);

}
