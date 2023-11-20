package org.cmms.modules.rwzx.rwcj.mapper;

import java.math.BigDecimal;
import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.dklldj.lldjgl.glzhgl.entity.CbsInvmBase;
import org.cmms.modules.khgl.dkkh.entity.CbsBormBase;
import org.cmms.modules.rwzx.rwcj.entity.*;

/**
 * @Description: 基础拜访任务
 * @Author: jeecg-boot
 * @Date:   2023-04-01
 * @Version: V1.0
 */
public interface TaskBfrwBaseMapper extends BaseMapper<TaskBfrwBase> {
    public int selectCountByYxid(@Param("yxid") String yxid,@Param("status")String status);

    Page<WdrwVO> getPageWdrw(Page page,@Param("yggh") String yggh);

    Page<WdrwVO> getPageRwtj(Page page,@Param("yggh") String yggh,@Param("wgbh")String wgbh);
    List<ZfpxVO> getZpfxVOList(String yxid);

    Integer dkYYMM(String yymm);
    BigDecimal dkYYMM2(@Param("yymm") String yymm,@Param("wgbh") String wgbh,@Param("yggh") String yggh);

    Integer ckYYMM(String yymm);
    BigDecimal ckYYMM2(@Param("yymm") String yymm,@Param("wgbh") String wgbh,@Param("yggh") String yggh);

    Integer ckYYMMType(@Param("yymm")String yymm,@Param("type")String type);

    Integer dqckdqs();
    Integer dqdkrs();

    Page<CbsInvmBase> getCkdq(Page page);
    Page<CbsBormBase> getDkdq(Page page);

    BigDecimal ckZhhj();
    BigDecimal dkZhhj();

    List<CbsInvmBase> getCKZHList();
    List<CbsBormBase> getDKZHList();

    Page<WdrwSearchResultVO> getWdrwSearchResultVOList(Page page,@Param("dao") WdrwSearchVO wdrwSearchVO);
}
