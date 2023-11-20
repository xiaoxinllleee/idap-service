package org.cmms.modules.xyjlcx.xybg.xybgcx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.sjxf.qtxt.khxxst.grkhb.entity.Grkhb;
import org.cmms.modules.sjxf.qtxt.khxxst.khxxb.entity.Khxxb;
import org.cmms.modules.xyjlcx.xybg.cxjltz.entity.Cxjltz;
import org.cmms.modules.xyjlcx.xybg.qxmx.entity.Qxmx;
import org.cmms.modules.xyjlcx.xybg.xybgcx.vo.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface CreditReportQueryMapper extends BaseMapper<Cxjltz> {

    /*List<TmpCreditXytssjVO> getXytssjHive(@Param("zjhm") String zjhm);*/

    List<TmpCreditDksjVO> getDksjmxHive(@Param("zjhm") String zjhm);
    List<TmpCreditDksjVO> getDksjmxOracle(@Param("zjhm") String zjhm);
    List<TmpCreditDksjVO> getDksjmx(@Param("zjhm") String zjhm);

    /*List<TmpCreditDbxxVO> getDbxxHive();*/

    /*List<TmpCreditDklxshmxVO> getDklxshmxHive(@Param("zh") String zh,@Param("shbz") String shbz);*/

    void CreditInitExtract(@Param("zjhm") String zjhm);

    List<JbxxSfxx> getJbxxSfxx(@Param("zjhm") String zjhm);

    List<JbxxGlrxx> getJbxxGlrxx(@Param("zjhm") String zjhm,@Param("hhbm") String hhbm);

    List<TmpCreditDbxxVO> getdbxxOracle(@Param("zjhm") String zjhm);
    List<TmpCreditXytssjVO> getXytssjOracle(@Param("zjhm") String zjhm);
    void initZx();
    List<Qxmx> getqxmxOracle(@Param("zjhm") String zjhm);
}
