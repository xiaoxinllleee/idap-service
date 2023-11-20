package org.cmms.modules.ywgl.dqdk.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.ywgl.dqdk.entity.AppDqdkVO;
import org.cmms.modules.ywgl.dqdk.entity.AppTjfxDkdqkh;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 到期贷款
 * @Author: jeecg-boot
 * @Date:   2022-03-11
 * @Version: V1.0
 */
public interface IAppTjfxDkdqkhService extends IService<AppTjfxDkdqkh> {
    IPage<AppDqdkVO> getListByPage(Page page, @Param("dqrq") String dqrq
            , @Param("dkye") String dkye, @Param("ye") String ye
            , @Param("khlx") String khlx, @Param("yggh")String yggh);
}
