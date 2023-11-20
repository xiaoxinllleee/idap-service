package org.cmms.modules.khgl.qtzrr.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.khgl.qtzrr.entity.Qtzrrfcxx;

/**
 * @Description: 其他自然人房产信息
 * @Author: cmms
 * @Date:   2019-11-29
 * @Version: V1.0
 */
public interface QtzrrfcxxMapper extends BaseMapper<Qtzrrfcxx> {

    public List<Qtzrrfcxx> selectByMainId(String zjhm);

    public Boolean deleteByMainId(String zjhm);

    public Qtzrrfcxx selectFcjz(String hhbm);
}
