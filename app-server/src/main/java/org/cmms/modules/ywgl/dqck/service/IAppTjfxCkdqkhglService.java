package org.cmms.modules.ywgl.dqck.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.ywgl.dqck.entity.AppDqckVO;
import org.cmms.modules.ywgl.dqck.entity.AppTjfxCkdqkh;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 到期存款
 * @Author: jeecg-boot
 * @Date:   2022-03-10
 * @Version: V1.0
 */
public interface IAppTjfxCkdqkhglService extends IService<AppTjfxCkdqkh> {

    IPage<AppDqckVO> getAppList(Page page,String type,String yggh);
}
