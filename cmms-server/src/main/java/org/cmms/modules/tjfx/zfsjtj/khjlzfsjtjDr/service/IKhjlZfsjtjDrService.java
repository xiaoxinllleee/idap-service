package org.cmms.modules.tjfx.zfsjtj.khjlzfsjtjDr.service;

import org.cmms.modules.tjfx.zfsjtj.khjlzfsjtjDr.entity.KhjlZfsjtjDr;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.tjfx.zfsjtj.khjlzfsjtjDr.entity.KhjlZfsjtjDrVo;

/**
 * @Description: 客户经理走访统计-当日
 * @Author: jeecg-boot
 * @Date:   2023-05-05
 * @Version: V1.0
 */
public interface IKhjlZfsjtjDrService extends IService<KhjlZfsjtjDr> {
    public void initKhjlZf(String yggh);
    public Integer getWxzfxx(String yggh);
    public KhjlZfsjtjDrVo getBthzfxx(String sszh);
    public KhjlZfsjtjDrVo getYxzfHj(String yggh);
}
