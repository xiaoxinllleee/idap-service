package org.cmms.modules.ckjkpt.ckzhgl.ckzhglgl.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.ckjkpt.ckzhgl.ckzhglgl.entity.CkjkptCkzhglxx;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.ckjkpt.ckzhgl.ckzhglgl.vo.HrBasStaffPostVO;
import org.cmms.modules.dklldj.lldjgl.glzhgl.entity.CbsInvmBase;

import java.util.List;

/**
 * @Description: 存款账号关联管理
 * @Author: jeecg-boot
 * @Date:   2021-10-18
 * @Version: V1.0
 */
@DS("ckjkpt")//ckjkpt
public interface ICkjkptCkzhglxxService extends IService<CkjkptCkzhglxx> {

    //拿到该列最大值id sql 里面已加1
    String getMaxId();

    Integer getCkzhglxxByckzh(String ckzh);

 /*   List<HrBasStaffPostVo> getListClaim(String ywjgdm, String rglx, String gwbz, String khjlbz,String yggh);
*/
    public void extract();

    // 判断这个存储是走哪一个

    public Integer judgeExtract();

    // 查看贷款主表最大日期
    String getMaxDataDate();

    //判断存款账号是否存在 日期可为空
    List<CbsInvmBase>  viewCkzhExit(@Param("b_date")String b_date, @Param("e_date")String e_date, @Param("ckzh")String ckzh);

    String getDkzh(String ckzh);

    List<HrBasStaffPostVO> empCheckFunc(String zzbz, String gwbz, String yggh);
}
