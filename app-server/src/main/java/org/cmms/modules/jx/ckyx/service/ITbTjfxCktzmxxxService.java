package org.cmms.modules.jx.ckyx.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.jx.ckyx.entity.TbTjfxCktzmxxx;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.jx.ckyx.entity.TbTjfxCktzmxxxVO;

/**
 * @Description: 存款拓展明细
 * @Author: jeecg-boot
 * @Date:   2021-05-31
 * @Version: V1.0
 */
@DS("eweb")
public interface ITbTjfxCktzmxxxService extends IService<TbTjfxCktzmxxx> {
    IPage<TbTjfxCktzmxxxVO> getListByPage(Page page,String yggh);
}
