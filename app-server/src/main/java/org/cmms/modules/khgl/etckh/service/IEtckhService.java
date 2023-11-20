package org.cmms.modules.khgl.etckh.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.khgl.etckh.entity.BdxxbVO;
import org.cmms.modules.khgl.etckh.entity.Etckh;
import org.cmms.modules.khgl.etckh.entity.SbxxVO;

/**
 * @Description: ETC绑定信息表
 * @Author: jeecg-boot
 * @Date:   2021-12-15
 * @Version: V1.0
 */
@DS("sjxf")
public interface IEtckhService extends IService<Etckh> {
    IPage<BdxxbVO> getKhxxList(Page page, String username, String khmc);

    IPage<SbxxVO> getSbxxList(Page page, String zjhm);
}
