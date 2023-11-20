package org.cmms.modules.dkjkpt.bldkftjk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.dkjkpt.bldkftjk.entity.DkjkptBldkftjkBsr;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 不良贷款反弹监控比昨日
 * @Author: cmms
 * @Date:   2019-09-10
 * @Version: V1.0
 */
public interface DkjkptBldkftjkBsrMapper extends BaseMapper<DkjkptBldkftjkBsr> {

    public void extract(String tjyf);
}
