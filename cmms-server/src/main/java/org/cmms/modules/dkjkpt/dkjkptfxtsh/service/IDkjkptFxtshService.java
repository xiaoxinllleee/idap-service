package org.cmms.modules.dkjkpt.dkjkptfxtsh.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.dkjkpt.dkjkptfxtsh.entity.DedkmxVo;
import org.cmms.modules.dkjkpt.dkjkptfxtsh.entity.DkjkptFxtsh;
import org.cmms.modules.dkjkpt.dkjkptfxtsh.entity.YqdkmxVo;

import java.util.List;

/**
 * @Description: 风险提示函
 * @Author: jeecg-boot
 * @Date:   2023-09-22
 * @Version: V1.0
 */
@DS("eweb")
public interface IDkjkptFxtshService extends IService<DkjkptFxtsh> {
    void init(String tjyf);

    List<DedkmxVo> dedk(String table, String jgdm, String tjyf);
    List<YqdkmxVo> yqdk(String table, String jgdm, String tjyf);
}
