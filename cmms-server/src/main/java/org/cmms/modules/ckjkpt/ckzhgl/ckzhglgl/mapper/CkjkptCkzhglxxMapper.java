package org.cmms.modules.ckjkpt.ckzhgl.ckzhglgl.mapper;

import java.util.List;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.ckjkpt.ckzhgl.ckzhglgl.entity.CkjkptCkzhglxx;
import org.cmms.modules.ckjkpt.ckzhgl.ckzhglgl.vo.HrBasStaffPostVO;
import org.cmms.modules.dklldj.lldjgl.glzhgl.entity.CbsInvmBase;
import org.cmms.modules.hr.zzgl.gwxxgl.entity.HrBasOranizationVo;

/**
 * @Description: 存款账号关联管理
 * @Author: jeecg-boot
 * @Date:   2021-10-18
 * @Version: V1.0
 */
public interface CkjkptCkzhglxxMapper extends BaseMapper<CkjkptCkzhglxx> {

    String getMaxId();

    Integer getCkzhglxxByckzh(String ckzh);

   /*    List<HrBasStaffPostVo> getListClaim(@Param("ywjgdm")String ywjgdm, @Param("rglx")String rglx,
                                        @Param("gwbz")String gwbz, @Param("khjlbz")String khjlbz,
                                        @Param("yggh")String yggh);*/

    public void extract();

    public Integer judgeExtract();

    String getMaxDataDate();

    List<CbsInvmBase>  viewCkzhExit(@Param("b_date")String b_date,@Param("e_date")String e_date,@Param("ckzh")String ckzh);

    String getDkzh(String ckzh);

    List<HrBasStaffPostVO> empCheckFunc(String zzbz, String gwbz, String yggh);
}
