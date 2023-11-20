package org.cmms.modules.tjfx.zfsjtj.pfrzfsjtj.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.tjfx.zfsjtj.pfrzfsjtj.entity.PfrZfMxVo;
import org.cmms.modules.tjfx.zfsjtj.pfrzfsjtj.entity.PfrZfsjtj;
import org.springframework.security.core.parameters.P;

import java.util.List;

/**
 * @Description: 陪访人走访数据统计
 * @Author: jeecg-boot
 * @Date:   2022-06-29
 * @Version: V1.0
 */
public interface IPfrZfsjtjService extends IService<PfrZfsjtj> {
    IPage<PfrZfMxVo> getPfrZfMxNhPage(Page page, String lx, String tjrq, String gwbz, String yggh,String weekFristDay);
    IPage<PfrZfMxVo> getPfrZfMxShPage(Page page, String lx, String tjrq, String gwbz, String yggh,String weekFristDay);
}
