package org.cmms.modules.khxxgl.khflgl.nhxq.entity;

import lombok.Data;
import org.cmms.modules.khgl.nh.entity.Fjgl;
import org.cmms.modules.khgl.nh.entity.Nhbkbpy;
import org.cmms.modules.pad.nhxxgl.entity.*;
import org.cmms.modules.pad.pyxx.entity.Pyfjxx;
import org.cmms.modules.pad.shxxgl.entity.Xyk;

import java.util.List;

/**
 * @Date 2022/2/16
 * @Created by eran
 */
@Data
public class NhxqPersonVO {

    //农户详情
    private Nhxq nhxq;
    //附件
    private List<Fjgl> fjgls;
    //家庭信息
    List<vKhglNhhzxxgl> vKhglNhhzxxgls;
    //户主信息
    KhglNhhzxxgl khglNhhzxxgl;
    //家庭信息
    List<Nhxq> nhxqs;
    //附件信息
    List<KhglNhhzzllb> khglNhhzzllbs;
    //房产信息
    List<CamsZcsxNhfcxxPad> camsZcsxNhfcxxPads;
    //农户评级授信
    CamsZcsxNhpjsxxxPad camsZcsxNhpjsxxxPads;
    //存贷款业务信息
    List<KhglYwhywwlxxPad> cdkywxxList;
    //贷款数据明细
    List<KhywxxDksjmxPad> dksjmxList;
    //手机银行
    List<KhywxxSjyhPad> sjyhList;
    //etc
    List<KhywxxEtc> etcList;
    //信用卡
    List<Xyk> xykList;
    //背靠背评议
    List<Nhbkbpy> nhbkbpies;
    //评议附件
    List<Pyfjxx> pyfjxxes;
}
