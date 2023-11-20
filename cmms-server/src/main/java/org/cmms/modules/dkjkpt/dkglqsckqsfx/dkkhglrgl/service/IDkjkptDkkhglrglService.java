package org.cmms.modules.dkjkpt.dkglqsckqsfx.dkkhglrgl.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.dkglqsckqsfx.dkkhglrgl.entity.DkjkptDkkhglrgl;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @Description: 贷款客户关联人管理
 * @Author: cmms
 * @Date:   2019-10-11
 * @Version: V1.0
 */
@DS("dkjkpt")
public interface IDkjkptDkkhglrglService extends IService<DkjkptDkkhglrgl> {

    public DkjkptDkkhglrgl deleteByMainId(String jkrzjhm,String glrzjhm);

    public void extract();

    /*public DkjkptDkkhglrgl queryByZjhm (String jkrzjhm,String glrzjhm);*/

    public  DkjkptDkkhglrgl queryByZjhm (Map<String,String> sql);

}
