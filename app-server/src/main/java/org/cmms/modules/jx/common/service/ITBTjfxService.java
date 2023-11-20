package org.cmms.modules.jx.common.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.jx.common.entity.TbTjfxQhdksj;

import java.util.Map;

/**
 * @Description //TODO
 * @Date 2020/11/8 9:35
 * @Author huangwb
 **/
public interface ITBTjfxService {
    /**
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @Author huangwb
     * @Description //TODO 获取全行贷款数
     * @Date 2020/11/8 9:36
     * @Param []
     **/
    TbTjfxQhdksj getBankWideLoans();

}
