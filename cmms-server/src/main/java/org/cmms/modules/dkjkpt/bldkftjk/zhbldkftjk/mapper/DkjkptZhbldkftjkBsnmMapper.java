package org.cmms.modules.dkjkpt.bldkftjk.zhbldkftjk.mapper;

import org.cmms.modules.dkjkpt.bldkftjk.zhbldkftjk.entity.DkjkptZhbldkftjkBsnm;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 支行不良贷款反弹监控比上年末
 * @Author: cmms
 * @Date:   2019-09-11
 * @Version: V1.0
 */
public interface DkjkptZhbldkftjkBsnmMapper extends BaseMapper<DkjkptZhbldkftjkBsnm> {

    public void initData(String tjyf);

}
