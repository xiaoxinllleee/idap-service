package org.cmms.modules.tjfx.xdgtzytjbb.zhtjbby.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbby.entity.TjfxZhbymxb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 支行表一明细表
 * @Author: jeecg-boot
 * @Date:   2020-02-11
 * @Version: V1.0
 */
public interface TjfxZhbymxbMapper extends BaseMapper<TjfxZhbymxb> {

    //public String queryTableDictTextByKey(@Param("ksrq") Date ksrq, @Param("jsrq") Date jsrq, @Param("xzc") String xzc, @Param("zkhjl") String zkhjl,@Param("khlx") String khlx);

    public List<TjfxZhbymxb> queryTableDictTextByKey(@Param("ksrq") Date ksrq, @Param("jsrq") Date jsrq, @Param("xzc") String xzc, @Param("zkhjl") String zkhjl, @Param("khlx") String khlx,@Param("code") String code,@Param("zdmc") String zdmc,@Param("qfbs")String qfbs);

    public List<TjfxZhbymxb>querykhzfmx(@Param("ksrq") Date ksrq, @Param("jsrq") Date jsrq, @Param("xzc") String xzc, @Param("zkhjl") String zkhjl, @Param("khlx") String khlx,@Param("code") String code,@Param("zdmc") String zdmc,@Param("qfbs")String qfbs);

    public List<TjfxZhbymxb>queryljkhzfmx(@Param("ksrq") Date ksrq, @Param("jsrq") Date jsrq,@Param("xzc") String xzc, @Param("zkhjl") String zkhjl, @Param("khlx") String khlx,@Param("code") String code,@Param("zdmc") String zdmc,@Param("qfbs")String qfbs);

    public List<TjfxZhbymxb>querypjsxjdmx(@Param("ksrq") Date ksrq, @Param("jsrq") Date jsrq, @Param("xzc") String xzc, @Param("zkhjl") String zkhjl,@Param("code") String code,@Param("zdmc") String zdmc,@Param("qfbs")String qfbs);

    public List<TjfxZhbymxb>queryljpjsxjdmx(@Param("ksrq") Date ksrq,@Param("jsrq") Date jsrq,@Param("xzc") String xzc, @Param("zkhjl") String zkhjl,@Param("code") String code,@Param("zdmc") String zdmc,@Param("qfbs")String qfbs);

    public List<TjfxZhbymxb>querypjsxjdyxjemx(@Param("ksrq") Date ksrq, @Param("jsrq") Date jsrq, @Param("xzc") String xzc, @Param("zkhjl") String zkhjl,@Param("code") String code,@Param("zdmc") String zdmc,@Param("qfbs")String qfbs);

    public List<TjfxZhbymxb>queryljpjsxjdyxjemx(@Param("ksrq") Date ksrq, @Param("jsrq") Date jsrq, @Param("xzc") String xzc, @Param("zkhjl") String zkhjl,@Param("code") String code,@Param("zdmc") String zdmc,@Param("qfbs")String qfbs);

}
