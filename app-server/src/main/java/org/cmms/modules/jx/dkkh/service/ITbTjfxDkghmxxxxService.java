package org.cmms.modules.jx.dkkh.service;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.jx.dkkh.entity.TbTjfxDkghmxxxx;
import org.cmms.modules.jx.dkkh.entity.TbTjfxDkghmxxxxBnblmxVO;
import org.cmms.modules.jx.dkkh.mapper.TbTjfxDkghmxxxxMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @Description: 贷款管户明细信息
 * @Author: jeecg-boot
 * @Date:   2021-05-25
 * @Version: V1.0
 */
@DS("eweb")
public interface ITbTjfxDkghmxxxxService extends IService<TbTjfxDkghmxxxx> {

    Map getListOfInstitutionalLoans(Page page, String zzbz);

    IPage getPagMapAssist(Page page,Map<String, Object> map);

    IPage<TbTjfxDkghmxxxxBnblmxVO> getPageByYggh(Page page, String yggh);
}
