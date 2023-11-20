package org.cmms.modules.rwzx.rwcj.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.dklldj.lldjgl.glzhgl.entity.CbsInvmBase;
import org.cmms.modules.khgl.dkkh.entity.CbsBormBase;
import org.cmms.modules.rwzx.rwcj.entity.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description: 基础拜访任务
 * @Author: jeecg-boot
 * @Date:   2023-04-01
 * @Version: V1.0
 */
public interface ITaskBfrwBaseService extends IService<TaskBfrwBase> {
    int selectCountByYxid(String yxid,String status);
    int selectCountByYxid(String yxid);
    Page<WdrwVO> getPageWdrw(Page page,String yggh);
    Page<WdrwVO> getPageRwtj(Page page,String yggh,String wgbh);
    List<ZfpxVO> getZpfxVOList(String yxid);

    int dkYYMM(String yymm);
    BigDecimal dkYYMM(String yymm,String wgbh,String yggh);
    int ckYYMM(String yymm);
    BigDecimal ckYYMM(String yymm,String wgbh,String yggh);
    int ckYYMM(String yymm,String type);

    Integer dqckdqs();
    Integer dqdkrs();

    Page<CbsInvmBase> getCkdq(Page page);
    Page<CbsBormBase> getDkdq(Page page);

    BigDecimal ckZhhj();
    BigDecimal dkZhhj();

    List<CbsInvmBase> getCKZHList();
    List<CbsBormBase> getDKZHList();

    Page<WdrwSearchResultVO> getWdrwSearchResultVOList(Page page,WdrwSearchVO wdrwSearchVO);
}
