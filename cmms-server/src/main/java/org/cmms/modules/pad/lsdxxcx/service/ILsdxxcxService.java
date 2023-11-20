package org.cmms.modules.pad.lsdxxcx.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.pad.lsdxxcx.entity.Lsdxxcx;

/**
 * @Description: 流水贷信息查询
 * @Author: jeecg-boot
 * @Date:   2023-05-24
 * @Version: V1.0
 */
public interface ILsdxxcxService extends IService<Lsdxxcx> {

    IPage<Lsdxxcx> getMaxData(Page page, String khmc, String zjhm);
}
