package org.cmms.modules.ckjkpt.ckzhgl.dfpckzhgl.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.ckjkpt.ckzhgl.dfpckzhgl.entity.CkjkptDfpckzhVo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.hr.zzgl.gwxxgl.entity.HrBasStaffPostVo;

import java.util.List;

/**
 * @Description: 待分配存款帐号管理
 * @Author: jeecg-boot
 * @Date:   2021-07-13
 * @Version: V1.0
 */
@DS("ckjkpt")//ckjkpt
public interface ICkjkptDfpckzhVoService extends IService<CkjkptDfpckzhVo> {

    void delBykhzh(String khzh);
}
