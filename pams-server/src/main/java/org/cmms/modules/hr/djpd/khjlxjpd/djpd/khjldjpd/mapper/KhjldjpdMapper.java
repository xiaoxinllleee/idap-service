package org.cmms.modules.hr.djpd.khjlxjpd.djpd.khjldjpd.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.hr.djpd.khjlxjpd.djpd.khjldjpd.entity.Khjldjpd;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 客户经理等级评定
 * @Author: jeecg-boot
 * @Date:   2021-09-16
 * @Version: V1.0
 */
public interface KhjldjpdMapper extends BaseMapper<Khjldjpd> {
    void pKhjldjpd(@Param("pdzq")String pdzq, @Param("pdrq")String pdrq);
}
