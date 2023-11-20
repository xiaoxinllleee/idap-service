package org.cmms.modules.performance.depositcustomer.ckkhxxgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.performance.depositcustomer.ckkhxxgl.vo.ClckkhxxImp;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 存量存款客户信息（数据导入临时表）
 * @Author: jeecg-boot
 * @Date:   2023-04-11
 * @Version: V1.0
 */
public interface ClckkhxxImpMapper extends BaseMapper<ClckkhxxImp> {

    void ClckkhxxImpDelete();

}
