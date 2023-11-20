package org.cmms.modules.khgl.ckkh.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.ckkh.entity.AppCkkhGzList;
import org.cmms.modules.khgl.ckkh.entity.CkkhCardVO;

import java.util.List;

/**
 * @Description: app存款客户关注列表
 * @Author: jeecg-boot
 * @Date:   2022-03-08
 * @Version: V1.0
 */
public interface IAppCkkhGzListService extends IService<AppCkkhGzList> {

    Integer getGhzsByGrp(String yggh,String grp,String custName);

    List<String> getZjhms(int rownumStart, int rownumEnd, String yggh, String grp);

    boolean isGz(String zjhm,String yggh);

    IPage<CkkhCardVO> getClckkh(Page page, String tzr,
                                String grp, String custName);
}
