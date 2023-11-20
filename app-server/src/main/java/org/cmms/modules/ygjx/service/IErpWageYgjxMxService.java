package org.cmms.modules.ygjx.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.ygjx.entity.ErpWageYgjxMx;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.ygjx.entity.ErpWageYgjxMxVO;

/**
 * @Description: 员工绩效明细
 * @Author: jeecg-boot
 * @Date:   2022-02-28
 * @Version: V1.0
 */
public interface IErpWageYgjxMxService extends IService<ErpWageYgjxMx> {

    IPage<ErpWageYgjxMxVO> getList(Page page,String yggh, String zblb,String date);
    IPage<ErpWageYgjxMxVO> getListV3(Page page,String yggh, String zblb,String date);
    IPage<ErpWageYgjxMxVO> getListTy(Page page,String yggh, String zblb,String date);
    IPage<ErpWageYgjxMxVO> getJhList(Page page,String yggh, String zblb,String date);
}
