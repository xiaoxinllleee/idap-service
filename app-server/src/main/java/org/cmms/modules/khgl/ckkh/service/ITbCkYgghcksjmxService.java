package org.cmms.modules.khgl.ckkh.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.ckkh.entity.*;
import org.cmms.modules.khgl.dkkh.entity.DkxtjcVO;

import java.util.List;

/**
 * @Description: 员工管户数据明细
 * @Author: jeecg-boot
 * @Date:   2022-03-04
 * @Version: V1.0
 */
public interface ITbCkYgghcksjmxService extends IService<TbCkYgghcksjmx> {

    CkkhjbVO getByYggh(String yggh);
    CkkhGyVO getByZjhm(String zjhm);

    List<String> getZjhms(int rownumStart,int rownumEnd,String yggh,String grp,String custName);

    //员工管户数
    Integer getGhzs(String yggh);
    //员工管户数 带条件 对公对私
    Integer getGhzs(String yggh,String grp,String custName);

    //证件号码最近到期
    TbCkYgghcksjmx minEndDate(String zjhm);

    //根据身份证获取对公对私类型
    List<String> getAcctGrpByZjhm(String zjhm,String yggh);

    //存款排名
    List<CkkhRankVO> getRankList(int rownumStart, int rownumEnd, String yggh, String pxType);


    //较上日
    IPage<TbCkYgghcksjmxVo> getJsr(Page page, String ghr, String qmyeS, String qmyeE,String qmTable, String zrTable);
    //较月初
    IPage<TbCkYgghcksjmxVo> getJyc(Page page, String ghr, String qmyeS, String qmyeE,String qmTable);
    //较季初
    IPage<TbCkYgghcksjmxVo> getJjc(Page page, String ghr, String qmyeS, String qmyeE,String qmTable,String jcTable);
    //较年初
    IPage<TbCkYgghcksjmxVo> getJnc(Page page, String ghr, String qmyeS, String qmyeE,String qmTable);

    String getCkCpxx(String zjhm);
    List<QhckphVO> getCkRank();

    IPage<CkkhCardVO> getClckkh(Page page,String tzr,
                                String grp,String custName);
}
