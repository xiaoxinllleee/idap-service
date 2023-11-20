package org.cmms.modules.tjfx.zhcdksjmx.service;

import org.cmms.modules.tjfx.zhcdksjmx.entity.Zhcksjmx;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description: 支行存款数据明细
 * @Author: cmms
 * @Date:   2019-09-17
 * @Version: V1.0
 */
public interface IZhcksjmxService extends IService<Zhcksjmx> {
    List<Map> getzhjynck();
    List<Map> getzhdqhqck();
    List<Map> getzhdgdsck();
    List<Map> getzhanlqjck(Date tjyf);
    List<Map> getzhdhckqj(Date tjyf);
    List<Map> getjynck(String jgdm);
    List<Map> getdqhqck(String jgdm);
    List<Map> getdgdsck(String jgdm);
    List<Map> getanlqjck(Date tjyf,String jgdm);
    List<Map> getdhckqj(Date tjyf,String jgdm);
    List<Map> getckpm(Date tjyf);
    List<Map> getckwclpm(Date tjyf);
    List<Map> getdkpm(Date tjyf);
    List<Map> getdkwclpm(Date tjyf);
    Date  getAnlqjcRq();
    Date  getZhdksjrq();
    Date  getZhcksjrq();
    List<Map> getzhjyndksj();
    List<Map> getjyndksj(String jgdm);
    List<Map> getzhwjfldk(Date tjyf);
    List<Map> getwjfldk(Date tjyf,String jgdm);
    List<Map> getzhanlqjdk(Date tjyf);
    List<Map> getanlqjdk(Date tjyf,String jgdm);
    Date  getAnlqjcRqDk();
    List<Map> getzhdhdkqj(Date tjyf);
    List<Map> getdhdkqj(Date tjyf,String jgdm);
    List<Map> getqhcksj(Date tjyf);
    List<Map> getzhcksj(Date tjyf,String jgdm);
    List<Map> getqhcdkxzs();
    List<Map> getcdkxzs(String jgdm);
    void callSysjmx();
}
