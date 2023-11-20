package org.cmms.modules.khxxgl.khflgl.nhxq.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.NsImportVO;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.VNhPjsx;

/**
 * @Description: 农户评级授信视图
 * @Author: jeecg-boot
 * @Date:   2023-02-05
 * @Version: V1.0
 */
public interface VNhPjsxMapper extends BaseMapper<VNhPjsx> {

    void updatePjsx(@Param("dao") NsImportVO nsImportVO);
    public void updateCpzlll();
    void updateNh(@Param("dao") NsImportVO nsImportVO);
    public String getWorkNoByRole(@Param("role")String role, @Param("sszh") String sszh);
}
