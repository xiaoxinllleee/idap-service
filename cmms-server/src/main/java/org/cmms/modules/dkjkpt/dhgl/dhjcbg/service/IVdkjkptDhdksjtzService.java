package org.cmms.modules.dkjkpt.dhgl.dhjcbg.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.dkjkpt.dhgl.dhjcbg.entity.VdkjkptDhdksjtz;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 贷后检查报告
 * @Author: cmms
 * @Date:   2019-10-15
 * @Version: V1.0
 */
@DS("dkjkpt")/*eweb*/
public interface IVdkjkptDhdksjtzService extends IService<VdkjkptDhdksjtz> {


    List<VdkjkptDhdksjtz> getListClaim(String khmc,String zjhm);


}
