package org.cmms.modules.dklldj.lldjgl.glzhgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.dklldj.lldjgl.glzhgl.entity.rateKhzhglxxb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.khgl.dkkh.entity.CbsBormBase;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-03-30
 * @Version: V1.0
 */
public interface rateKhzhglxxbMapper extends BaseMapper<rateKhzhglxxb> {

    public void extract(String zjhm);

}
