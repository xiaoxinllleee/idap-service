package org.cmms.modules.dkjkpt.dkglqsckqsfx.dkkhglrgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.dkjkpt.dkglqsckqsfx.dkkhglrgl.entity.DkjkptDkkhglrgl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.hibernate.mapping.Map;

/**
 * @Description: 贷款客户关联人管理
 * @Author: cmms
 * @Date:   2019-10-11
 * @Version: V1.0
 */
public interface DkjkptDkkhglrglMapper extends BaseMapper<DkjkptDkkhglrgl> {

    public DkjkptDkkhglrgl deleteByMainId(String jkrzjhm,String glrzjhm);

    public void extract();

    /*public DkjkptDkkhglrgl queryByZjhm (String jkrzjhm,String glrzjhm);*/

    public DkjkptDkkhglrgl queryByZjhm (java.util.Map<String,String> sql);

}
