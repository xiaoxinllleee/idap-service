package org.cmms.modules.ywgl.cdkfx.dkkhckhbl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.ywgl.cdkfx.dkkhckhbl.entity.ModCdkfxKhjldkkhckhblD;

/**
 * @Description: 贷款客户存款回报率
 * @Author: jeecg-boot
 * @Date:   2021-07-01
 * @Version: V1.0
 */
public interface ModCdkfxKhjldkkhckhblDMapper extends BaseMapper<ModCdkfxKhjldkkhckhblD> {
    void pDkfxDkkhckhbl(String tjyf);
}
