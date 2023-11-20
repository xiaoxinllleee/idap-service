package org.cmms.modules.jx.ckyj.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.jx.ckyj.entity.AppCkkhWatch;
import org.cmms.modules.jx.ckyj.entity.TbTjfxCkyjxx;
import org.cmms.modules.jx.ckyj.entity.TbTjfxCkyjxxVo;

/**
 * @Description:  * 员工关注客户关系表 app_ckkh_watch
 * @Author: jeecg-boot
 * @Date:   2021-06-01
 * @Version: V1.0
 */
@DS("eweb")
public interface AppCkkhWatchService extends IService<AppCkkhWatch> {


}
