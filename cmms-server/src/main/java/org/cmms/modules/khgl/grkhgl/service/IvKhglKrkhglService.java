package org.cmms.modules.khgl.grkhgl.service;

import org.cmms.modules.khgl.grkhgl.entity.*;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 个人客户
 * @Author: jeecg-boot
 * @Date:   2020-07-03
 * @Version: V1.0
 */
public interface IvKhglKrkhglService extends IService<vKhglKrkhgl> {


    /**
     * 添加一对多
     *
     */
    public void saveMain(ZcsxNhcjxx zcsxNhcjxx, KrkhglReceive krkhglReceive, List<Khhmcxx>khhmcxxList, List<CamsZcsxGrpjsxxx>camsZcsxGrpjsxxxList);


    public void saveMainPad(ZcsxNhcjxx zcsxNhcjxx, KrkhglReceive krkhglReceive, List<Khhmcxx>khhmcxxList, List<CamsZcsxGrpjsxxx>camsZcsxGrpjsxxxList);

    /**
     * 修改一对多
     *
     */
    public void updateMain(ZcsxNhcjxx zcsxNhcjxx,KrkhglReceive krkhglReceive, List<Khhmcxx>khhmcxxList, List<CamsZcsxGrpjsxxx>camsZcsxGrpjsxxxList);



    public void updateMainPad(ZcsxNhcjxx zcsxNhcjxx,KrkhglReceive krkhglReceive, List<Khhmcxx>khhmcxxList, List<CamsZcsxGrpjsxxx>camsZcsxGrpjsxxxList);

    /**
     * 删除一对多
     */
    public void delMain(String id);

    /**
     * 批量删除一对多
     */
    public void delBatchMain(Collection<? extends Serializable> idList);

    /**
     * 准备模型计算需要的数据
     * */
    public int prepare(String zjhm,String hhbm);

}
