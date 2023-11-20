package org.cmms.modules.dkjkpt.bldkftjk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.dkjkpt.bldkftjk.entity.DkjkptBldkftjkBsnm;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 不良贷款反弹监控比上年末
 * @Author: cmms
 * @Date:   2019-09-11
 * @Version: V1.0
 */
public interface DkjkptBldkftjkBsnmMapper extends BaseMapper<DkjkptBldkftjkBsnm> {

    public void extract(String tjyf);
}
