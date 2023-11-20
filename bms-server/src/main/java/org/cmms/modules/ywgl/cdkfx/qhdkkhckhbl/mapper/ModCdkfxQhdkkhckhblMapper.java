package org.cmms.modules.ywgl.cdkfx.qhdkkhckhbl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.ywgl.cdkfx.qhdkkhckhbl.entity.ModCdkfxQhdkkhckhbl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 全行贷款客户存款回报率
 * @Author: jeecg-boot
 * @Date:   2021-07-02
 * @Version: V1.0
 */
public interface ModCdkfxQhdkkhckhblMapper extends BaseMapper<ModCdkfxQhdkkhckhbl> {
    void pDkfxDkkhckhbl(String tjyf);
}
