package org.cmms.modules.xdgl.pjsxspjl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.xdgl.grkhpjsx.entity.Vgrpjsxspjl;
import org.cmms.modules.xdgl.pjsxspjl.entity.CamsZcsxGrpjsxxxSpjl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.xdgl.pjsxspjl.entity.LyNewNsVO;
import org.springframework.stereotype.Component;

/**
 * @Description: 评级授信审批记录
 * @Author: jeecg-boot
 * @Date:   2020-08-05
 * @Version: V1.0
 */
@Component
public interface CamsZcsxGrpjsxxxSpjlMapper extends BaseMapper<CamsZcsxGrpjsxxxSpjl> {
    public List<Vgrpjsxspjl> getListByZjhms(@Param("Zjhms")List<String> zjhms);
    public List<Vgrpjsxspjl> getListBySpids(@Param("Spids")List<String> Spids);

    public int updateFp (@Param("zjhm") String zjhm,@Param("dj") String dj,@Param("je") Double je,@Param("name") String name);
    public List<LyNewNsVO> getByWgbh(String wgbh);
    public List<LyNewNsVO> getBySszh(String zzbz);
}
