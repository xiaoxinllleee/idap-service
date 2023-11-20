package org.cmms.modules.khlc.khfagl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.khlc.khfagl.entity.PmaAScheme;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.system.entity.DpJdrwgl;

/**
 * @Description: 考核方案基础信息表
 * @Author: jeecg-boot
 * @Date:   2021-01-29
 * @Version: V1.0
 */
public interface IPmaASchemeService extends IService<PmaAScheme> {
    public IPage<PmaAScheme> getSchenmeByJdId(Page<PmaAScheme> page, String jdid, String famc);

}
