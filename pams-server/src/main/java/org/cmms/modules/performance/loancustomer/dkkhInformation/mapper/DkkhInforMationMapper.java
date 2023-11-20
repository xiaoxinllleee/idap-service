package org.cmms.modules.performance.loancustomer.dkkhInformation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.performance.loancustomer.dkkhInformation.entity.DkkhInforMation;

/**
 * @Description: 贷款客户综合信息
 * @Author: jeecg-boot
 * @Date:   2023-04-03
 * @Version: V1.0
 */
public interface DkkhInforMationMapper extends BaseMapper<DkkhInforMation> {
    public void extract();
}
