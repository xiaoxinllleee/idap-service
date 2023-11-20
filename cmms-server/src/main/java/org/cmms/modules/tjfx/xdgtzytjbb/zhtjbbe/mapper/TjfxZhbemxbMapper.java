package org.cmms.modules.tjfx.xdgtzytjbb.zhtjbbe.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbbe.entity.TjfxZhbemxb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 支行表二明细表
 * @Author: jeecg-boot
 * @Date:   2020-02-14
 * @Version: V1.0
 */
public interface TjfxZhbemxbMapper extends BaseMapper<TjfxZhbemxb> {

    public List<Map<String,Object>> csqueryckyemx (@Param("tjyf") Date tjyf, @Param("zdmc") String zdmc,@Param("xzc") String xzc, @Param("code") String code,@Param("zkhjl") String zkhjl );

    public List<TjfxZhbemxb>queryckyemx(@Param("tjyf") Date tjyf, @Param("zdmc") String zdmc,@Param("xzc") String xzc, @Param("code") String code,@Param("zkhjl") String zkhjl,@Param("qfbs")String qfbs);

    public List<TjfxZhbemxb>queryclhsmx(@Param("tjyf") Date tjyf, @Param("zdmc") String zdmc,@Param("xzc") String xzc, @Param("code") String code,@Param("zkhjl") String zkhjl,@Param("code2") String code2,@Param("qfbs")String qfbs);

    public List<TjfxZhbemxb>queryzlhsmx(@Param("tjyf") Date tjyf, @Param("zdmc") String zdmc,@Param("xzc") String xzc, @Param("code") String code,@Param("zkhjl") String zkhjl,@Param("code2") String code2,@Param("qfbs")String qfbs);


    public List<TjfxZhbemxb>querycscqckyemx(@Param("tjyf") Date tjyf, @Param("zdmc") String zdmc,@Param("xzc") String xzc,@Param("qfbs")String qfbs);

    public List<TjfxZhbemxb>querycscqclhsmx(@Param("tjyf") Date tjyf, @Param("zdmc") String zdmc,@Param("xzc") String xzc, @Param("code") String code,@Param("qfbs")String qfbs);

    public List<TjfxZhbemxb>querycscqzlhsmx(@Param("tjyf") Date tjyf, @Param("zdmc") String zdmc,@Param("xzc") String xzc, @Param("code") String code,@Param("qfbs")String qfbs);

}
