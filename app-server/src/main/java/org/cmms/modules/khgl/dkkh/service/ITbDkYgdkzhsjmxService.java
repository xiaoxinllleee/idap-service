package org.cmms.modules.khgl.dkkh.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.khgl.dkkh.entity.TbDkYgdkzhsjmx;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 贷款员工管理综合数据明细
 * @Author: jeecg-boot
 * @Date:   2022-03-11
 * @Version: V1.0
 */
public interface ITbDkYgdkzhsjmxService extends IService<TbDkYgdkzhsjmx> {

    IPage<TbDkYgdkzhsjmx> getAppList(Page page,TbDkYgdkzhsjmx tbDkYgdkzhsjmx,String yggh);

    //CBS_BORM_BASE vouch_type
    String dbfs(String hth);
}
