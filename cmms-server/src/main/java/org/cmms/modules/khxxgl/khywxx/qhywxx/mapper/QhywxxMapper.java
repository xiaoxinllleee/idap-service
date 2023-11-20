package org.cmms.modules.khxxgl.khywxx.qhywxx.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhxq;
import org.cmms.modules.khxxgl.khywxx.qhywxx.entity.Qhywxx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 全行业务信息
 * @Author: jeecg-boot
 * @Date:   2021-11-29
 * @Version: V1.0
 */
public interface QhywxxMapper extends BaseMapper<Qhywxx> {

    IPage getYwsjBySjrq(Page page,@Param("rq") String rq,@Param("wgbh") String wgbh,@Param("type") String type, @Param("jqlx") String jqlx,@Param("qydm")String qydm);
    IPage<Nhxq> getListWktYwsjBySjrq(Page page,@Param("rq") String rq,@Param("wgbh") String wgbh,@Param("type") String type, @Param("jqlx") String jqlx);
    List<Qhywxx> getListYwsjBySjrq(@Param("rq") String rq,@Param("wgbh") String wgbh,@Param("type") String type, @Param("jqlx") String jqlx);
    List<Nhxq> getListWktYwsjBySjrq(@Param("rq") String rq,@Param("wgbh") String wgbh,@Param("type") String type, @Param("jqlx") String jqlx);

    List<Nhxq> getSbkWktListByWgbh(String wgbh);
    IPage<Nhxq> getSbkWktListByWgbh(Page page, String wgbh);
}
