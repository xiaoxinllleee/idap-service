package org.cmms.modules.xddaglxt.dkdagl.xddascjdtj.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.xddaglxt.dkdagl.xddascjdtj.entity.Xddascjdtj;

/**
 * @Description: 信贷档案上传进度统计
 * @Author: jeecg-boot
 * @Date:   2021-12-01
 * @Version: V1.0
 */
@DS("eweb")
public interface IXddascjdtjService extends IService<Xddascjdtj> {
    void pXddascjdtj();
}
