package org.cmms.modules.khlc.jczbgl.mapper;


import org.cmms.modules.khlc.jczbgl.entity.PmaFBaseIndexType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description: 基础指标类型表
 * @Author: jeecg-boot
 * @Date:   2021-01-18
 * @Version: V1.0
 */
public interface PmaFBaseIndexTypeMapper extends BaseMapper<PmaFBaseIndexType> {
    List<Integer> getAllChild(Integer parentId);
}
