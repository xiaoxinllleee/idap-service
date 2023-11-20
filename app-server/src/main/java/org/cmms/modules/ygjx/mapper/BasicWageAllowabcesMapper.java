package org.cmms.modules.ygjx.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.ygjx.entity.BasicWageAllowabces;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.ygjx.entity.GzMxVO;

/**
 * @Description: 津贴
 * @Author: jeecg-boot
 * @Date:   2022-03-02
 * @Version: V1.0
 */
public interface BasicWageAllowabcesMapper extends BaseMapper<BasicWageAllowabces> {

    GzMxVO getByYggh(@Param("yggh")String yggh,@Param("date")String date);

    BigDecimal getJbgz(@Param("yggh")String yggh,@Param("date")String date);
}
