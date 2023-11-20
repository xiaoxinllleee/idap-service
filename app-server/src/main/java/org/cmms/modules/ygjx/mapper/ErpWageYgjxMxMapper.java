package org.cmms.modules.ygjx.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.ygjx.entity.ErpWageYgjxMx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.ygjx.entity.ErpWageYgjxMxVO;

/**
 * @Description: 员工绩效明细
 * @Author: jeecg-boot
 * @Date:   2022-02-28
 * @Version: V1.0
 */
public interface ErpWageYgjxMxMapper extends BaseMapper<ErpWageYgjxMx> {

    IPage<ErpWageYgjxMxVO> getList(Page page, @Param("yggh") String yggh,@Param("zblb") String zblb,@Param("gzrq") String rq);
    IPage<ErpWageYgjxMxVO> getListV3(Page page, @Param("yggh") String yggh,@Param("zblb") String zblb,@Param("gzrq") String rq);

    IPage<ErpWageYgjxMxVO> getListTy(Page page, @Param("yggh") String yggh,@Param("zblb") String zblb,@Param("gzrq") String rq);
    IPage<ErpWageYgjxMxVO> getJhList(Page page, @Param("yggh") String yggh,@Param("zblb") String zblb,@Param("gzrq") String rq);
}
