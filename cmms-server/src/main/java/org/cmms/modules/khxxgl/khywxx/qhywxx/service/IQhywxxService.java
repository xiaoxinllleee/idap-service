package org.cmms.modules.khxxgl.khywxx.qhywxx.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhxq;
import org.cmms.modules.khxxgl.khywxx.qhywxx.entity.Qhywxx;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 全行业务信息
 * @Author: jeecg-boot
 * @Date:   2021-11-29
 * @Version: V1.0
 */
public interface IQhywxxService extends IService<Qhywxx> {

    IPage getYwsjBySjrq(Page page,String rq,String wgbh,String type, String jqlx,String qydm);

    IPage<Nhxq> getWktYwsjBySjrq(Page page,String rq,String wgbh,String type, String jqlx);

    List<Qhywxx> getListYwsjBySjrq(String rq,String wgbh,String type, String jqlx);

    List<Nhxq> getListWktYwsjBySjrq(String rq,String wgbh,String type, String jqlx);

    List<Nhxq> getSbkWktListByWgbh(String wgbh);

    IPage<Nhxq> getSbkWktByWgbh(Page page, String wgbh);
}
