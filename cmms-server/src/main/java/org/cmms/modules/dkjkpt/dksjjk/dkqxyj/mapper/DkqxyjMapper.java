package org.cmms.modules.dkjkpt.dksjjk.dkqxyj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.dkjkpt.dksjjk.dkqxyj.entity.Dkqxyj;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 贷款欠息预警
 * @Author: jeecg-boot
 * @Date:   2022-09-15
 * @Version: V1.0
 */
public interface DkqxyjMapper extends BaseMapper<Dkqxyj> {

    public void initData(String userName);
}
