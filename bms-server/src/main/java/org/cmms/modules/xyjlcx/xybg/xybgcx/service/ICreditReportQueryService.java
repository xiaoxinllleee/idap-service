package org.cmms.modules.xyjlcx.xybg.xybgcx.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.sjxf.qtxt.khxxst.grkhb.entity.Grkhb;
import org.cmms.modules.sjxf.qtxt.khxxst.khxxb.entity.Khxxb;
import org.cmms.modules.xyjlcx.xybg.cxjltz.entity.Cxjltz;
import org.cmms.modules.xyjlcx.xybg.qxmx.entity.Qxmx;
import org.cmms.modules.xyjlcx.xybg.xybgcx.vo.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@DS("zx") // zx
public interface ICreditReportQueryService extends IService<Cxjltz> {

    /**
     * 获取`信用提示数据`
     *
     * @param zjhm
     * @return
     */
    /*List<TmpCreditXytssjVO> getXytssjHive(String zjhm);*/

    /**
     * 获取`贷款数据`-impala
     *
     * @param zjhm
     * @return
     */
    List<TmpCreditDksjVO> getDksjmxHive(String zjhm);

    /**
     * 获取`贷款数据`-oracle
     *
     * @param zjhm
     * @return
     */
    List<TmpCreditDksjVO> getDksjmxOracle(@Param("zjhm") String zjhm);

    /**
     * 贷记卡数据明细
     *
     * @param zjhm
     * @return
     */
    List<TmpCreditDksjVO> getDksjmx(String zjhm);

    /**
     * 获取`担保信息`
     *
     * @return
     */
    /*List<TmpCreditDbxxVO> getDbxxHive();*/

    /**
     * 获取`贷款利息收回明细`
     *
     * @param zh
     * @param shbz
     * @return
     */
    /*@DS("preset")
    List<TmpCreditDklxshmxVO> getDklxshmxHive(String zh, String shbz);*/

    /**
     * Oracle 提取调用
     *
     * @param zjhm
     */
    @DS("eweb")
    void CreditInitExtract(@Param("zjhm") String zjhm);



    /**
     * 查询 客户身份信息
     *
     * @param zjhm 客户证件号码
     * @return
     */
    List<JbxxSfxx> getJbxxSfxx(@Param("zjhm") String zjhm);

    /**
     * 查询 客户关联人信息
     *
     * @param zjhm 客户证件号码
     * @param hhbm 客户户号编码
     * @return
     */
    List<JbxxGlrxx> getJbxxGlrxx(@Param("zjhm") String zjhm, @Param("hhbm") String hhbm);

    @DS("idap")
    void CreditInitExtractIdap(@Param("zjhm") String zjhm);

    /**
     * 担保品信息查询-oracle
     * @param zjhm 担保人证件号码
     * @return
     */
    List<TmpCreditDbxxVO> getdbxxOracle(@Param("zjhm") String zjhm);

    /**
     * 响应提示信息-oracle
     * @param zjhm
     * @return
     */
    List<TmpCreditXytssjVO> getXytssjOracle(@Param("zjhm") String zjhm);

    /**
     * 征信报告每日跑批
     */
    void initZx();

    /**
     * 征信报告-起息明细-oracle
     * @param zjhm
     * @return
     */
    List<Qxmx> getqxmxOracle(@Param("zjhm") String zjhm);
}
