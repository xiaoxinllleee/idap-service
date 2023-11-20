package org.cmms.modules.khjg.pszbjg.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.khjg.pszbjg.entity.GzQueryVo;
import org.cmms.modules.khjg.pszbjg.entity.ParamFEvInRe;

/**
 * @Description: 派生指标结果
 * @Author: jeecg-boot
 * @Date:   2021-05-11
 * @Version: V1.0
 */
public interface IParamFEvInReService extends IService<ParamFEvInRe> {

    IPage<ParamFEvInRe> getGzjg(Page page,GzQueryVo gzQueryVo);
    IPage<ParamFEvInRe> getgzbyfa(Page page,GzQueryVo gzQueryVo);
    IPage<ParamFEvInRe> getgzbyjg(Page page,GzQueryVo gzQueryVo);
}
