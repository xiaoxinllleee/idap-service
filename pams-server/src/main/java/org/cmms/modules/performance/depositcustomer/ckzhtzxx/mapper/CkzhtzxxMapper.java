package org.cmms.modules.performance.depositcustomer.ckzhtzxx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.performance.depositcustomer.ckzhtzxx.entity.Ckzhtzxx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 存款账户拓展信息
 * @Author: jeecg-boot
 * @Date:   2023-03-30
 * @Version: V1.0
 */
public interface CkzhtzxxMapper extends BaseMapper<Ckzhtzxx> {
    public void extract();
}
