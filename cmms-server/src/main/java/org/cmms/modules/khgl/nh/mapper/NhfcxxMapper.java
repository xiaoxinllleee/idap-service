package org.cmms.modules.khgl.nh.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.nh.entity.Nhfcxx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 农户房产信息
 * @Author: cmms
 * @Date:   2019-11-29
 * @Version: V1.0
 */
public interface NhfcxxMapper extends BaseMapper<Nhfcxx> {

    public List<Nhfcxx> selectByMainId(String zjhm);

    public Boolean deleteByMainId(String zjhm);

    public Nhfcxx selectFcjz(String hhbm);
}
