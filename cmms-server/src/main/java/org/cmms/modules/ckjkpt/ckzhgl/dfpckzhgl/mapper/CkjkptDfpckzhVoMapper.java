package org.cmms.modules.ckjkpt.ckzhgl.dfpckzhgl.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.ckjkpt.ckzhgl.dfpckzhgl.entity.CkjkptDfpckzhVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.hr.zzgl.gwxxgl.entity.HrBasStaffPostVo;

import java.util.List;

/**
 * @Description: 待分配存款帐号管理
 * @Author: jeecg-boot
 * @Date:   2021-07-13
 * @Version: V1.0
 */
public interface CkjkptDfpckzhVoMapper extends BaseMapper<CkjkptDfpckzhVo> {
   void delBykhzh(String khzh);
}
