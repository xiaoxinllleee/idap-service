package org.cmms.modules.ywgl.cdkfx.khjldkkhckhbl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.ywgl.cdkfx.khjldkkhckhbl.entity.ModCdkfxKhjldkkhckhbl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 客户经理贷款客户存款回报率
 * @Author: jeecg-boot
 * @Date:   2021-06-30
 * @Version: V1.0
 */
public interface ModCdkfxKhjldkkhckhblMapper extends BaseMapper<ModCdkfxKhjldkkhckhbl> {
    void pDkfxDkkhckhbl(String tjyf);

    /**
     * 根据名字获取员工工号
     * @param ygxm
     * @return
     */
    String getYgghByName(String ygxm);
}
