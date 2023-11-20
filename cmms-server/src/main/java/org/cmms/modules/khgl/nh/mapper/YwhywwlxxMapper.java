package org.cmms.modules.khgl.nh.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.nh.entity.Ywhywwlxx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 与我行业务往来信息
 * @Author: jeecg-boot
 * @Date:   2020-04-02
 * @Version: V1.0
 */
public interface YwhywwlxxMapper extends BaseMapper<Ywhywwlxx> {

    public List<Ywhywwlxx> selectByMainId (String zjhm);

    public List<Ywhywwlxx> selectByHhbm (String hhbm);

    public  boolean  deleteByMainId( String zjhm);

    public String endDate(String zjhm);
    public String appMaturityDate(String zjhm);

}
