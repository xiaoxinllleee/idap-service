package org.cmms.modules.khgl.dkkh.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.khgl.dkkh.entity.AppDkkhGzList;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 贷款客户关注列表
 * @Author: jeecg-boot
 * @Date:   2022-03-10
 * @Version: V1.0
 */
public interface IAppDkkhGzListService extends IService<AppDkkhGzList> {

    boolean isGz(String zjhm,String yggh);

    IPage getZjhms(Page page, String yggh, String custType, String wjfl,String zjhm);
}
