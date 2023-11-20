package org.cmms.modules.xdgl.pjsxspjl.service;

import org.cmms.modules.xdgl.grkhpjsx.entity.FamerExportWord;
import org.cmms.modules.xdgl.pjsxspjl.entity.CamsZcsxGrpjsxxxSpjl;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.xdgl.pjsxspjl.entity.LyNewNsVO;

import java.util.List;

/**
 * @Description: 评级授信审批记录
 * @Author: jeecg-boot
 * @Date:   2020-08-05
 * @Version: V1.0
 */
public interface ICamsZcsxGrpjsxxxSpjlService extends IService<CamsZcsxGrpjsxxxSpjl> {

    FamerExportWord getWord(String zjhm,String hhbm);

    int updateFp(String zjhm,String dj,Double je,String name);

    List<LyNewNsVO> getByWgbh(String wgbh);
    List<LyNewNsVO> getBySszh(String zzbz);

    String getRequestBody(CamsZcsxGrpjsxxxSpjl camsZcsxGrpjsxxxSpjl);
}
