package org.cmms.modules.khgl.dkkh.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.dkkh.entity.DkxtjcVO;
import org.cmms.modules.khgl.dkkh.entity.HtlbVO;
import org.cmms.modules.khgl.dkkh.entity.TbDkYgghdksjmx;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 员工管户贷款数据明细
 * @Author: jeecg-boot
 * @Date:   2022-03-09
 * @Version: V1.0
 */
public interface ITbDkYgghdksjmxService extends IService<TbDkYgghdksjmx> {

    List<String> getZjhms(int rownumStart, int rownumEnd, String yggh, String custType, String wjfl);
    IPage<String> getZjhms2(Page page, String yggh, String custType, String wjfl,String zjhm);

    List<String> getCustTypeByZjhm(String zjhm,String yggh);

    IPage<DkxtjcVO> getDkxtjcList(Page page, String yggh,String custType, String type, String zjhm,
                                  String qmTable,String jcTable,String zrTable);
    List<HtlbVO> getHtlbsByZjhm(String zjhm);
}
