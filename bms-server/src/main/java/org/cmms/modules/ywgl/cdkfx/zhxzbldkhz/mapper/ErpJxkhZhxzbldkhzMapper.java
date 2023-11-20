package org.cmms.modules.ywgl.cdkfx.zhxzbldkhz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.ywgl.cdkfx.zhxzbldkhz.entity.ErpJxkhZhxzbldkhz;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 支行新增不良贷款汇总
 * @Author: jeecg-boot
 * @Date:   2021-06-24
 * @Version: V1.0
 */
public interface ErpJxkhZhxzbldkhzMapper extends BaseMapper<ErpJxkhZhxzbldkhz> {
    void pJxkhZhxzbldkhz(String tjyf);
}
