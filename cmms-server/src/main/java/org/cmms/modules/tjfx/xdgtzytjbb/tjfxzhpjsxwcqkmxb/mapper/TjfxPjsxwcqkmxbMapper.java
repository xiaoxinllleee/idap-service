package org.cmms.modules.tjfx.xdgtzytjbb.tjfxzhpjsxwcqkmxb.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.xdgtzytjbb.tjfxzhpjsxwcqkmxb.entity.TjfxPjsxwcqkmxb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 评级授信完成情况明表
 * @Author: jeecg-boot
 * @Date:   2020-02-17
 * @Version: V1.0
 */
public interface TjfxPjsxwcqkmxbMapper extends BaseMapper<TjfxPjsxwcqkmxb> {

    public List<TjfxPjsxwcqkmxb> querysjzfhs (@Param("ksrq") Date ksrq,@Param("jsrq") Date jsrq ,@Param("xzc") String xzc,@Param("qfbs") String qfbs,@Param("code") String code);

    public List<TjfxPjsxwcqkmxb> querysjpjhs (@Param("ksrq") Date ksrq,@Param("jsrq") Date jsrq ,@Param("xzc") String xzc,@Param("qfbs") String qfbs);

}
