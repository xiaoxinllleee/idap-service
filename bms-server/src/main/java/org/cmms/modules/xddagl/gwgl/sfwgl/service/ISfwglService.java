package org.cmms.modules.xddagl.gwgl.sfwgl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.xddagl.gwgl.sfwgl.entity.Sfwgl;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 收发文管理
 * @Author: jeecg-boot
 * @Date:   2022-01-08
 * @Version: V1.0
 */
public interface ISfwglService extends IService<Sfwgl> {

    public IPage<Sfwgl> getQuery(Page<Sfwgl> page, Sfwgl sfwgl, String zzbz);
}
