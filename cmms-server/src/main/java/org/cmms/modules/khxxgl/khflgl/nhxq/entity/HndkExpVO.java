package org.cmms.modules.khxxgl.khflgl.nhxq.entity;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import lombok.Data;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.cmms.common.enums.QydmEnums;
import org.cmms.modules.khgl.grkhgl.entity.Khhmcxx;
import org.cmms.modules.khgl.nh.entity.Nhbkbpy;
import org.cmms.modules.pad.nhxxgl.entity.CamsZcsxNhcjxxPad;
import org.cmms.modules.pad.nhxxgl.entity.KhglNhhzxxgl;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.xdgl.grkhpjsx.utils.ELoandDictUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @Date 2022/4/20
 * @Created by eran
 */
@Data
public class HndkExpVO {
    //客户姓名
    private String row;
    //客户身份证号
    private String row2;
    //额度
    private String row3;
    //基点
    private String row4 = "267.5";
    //授信到期日
    private String row5 ="36";
    //客户分群
    private String row6 = "04-城乡居民";
    //客户经理工号
    private String row7;
    //调查责任人一工号
    private String row8;
    private String row9;
    //调查责任人二工号
    private String row10;
    private String row11;
    //管理责任人工号
    private String row12;
    private String row13;
    //审查责任人工号
    private String row14;
    private String row15;
    //最终审批责任人工号
    private String row16;
    private String row17;
    //第一责任人工号
    private String row18;
    private String row19;
    //民族
    //private	String	row20 = "1-汉族";
    private String row20 ="1-汉族";
    private String row21 = "01-农户";
    private String row22 = "11-一般农户";
    private String row23;
    private String row24 = "2-无";
    private String row25 = "1-好";
    private String row26 = "4-其他";
    private String row27;
    private String row28;
    private String row29 = "1";
    private String row30 = "1-好";
    private String row31 = "4-10年以上";
    private String row32 = "1-自置";
    private String row33 = "70-初中";
    private String row34;
    private String row35;
    private String row36;
    //蓝山行政区划代码
    //private	String	row37 = "431127";
    private String row37;
    //湖南省永州市蓝山县邮编
    //private	String	row38 = "425800";
    private String row38;
    private String row39 = "2-否";
    ;
    private String row40 = "1-是";
    private String row41;
    private String row42="3-一般员工";
    //private	String	row42 = "未知";
    private String row43="A0111-稻谷种植";
    //private	String	row43 = "9-未知";
    private String row44 = "1-是";
    ;
    private String row45 = "1-需求旺盛";
    private String row46 = "1-强烈";
    private String row47="5-农、林、牧、渔、水利业生产人员";
    private String row48="3-初级";
    private String row49 = "2-3-5年";

    //从业年限
    private String row50 = "4-好";
    //综合评价标志
    private String row51;
    //个体工商户名称
    private String row52;
    //统一社会信用代码
    private String row53;
    //家庭年收入
    private String row54;
    //个人年输入
    private String row55 = "1-是";
    //循环标志
    private String row56 = "1-1级";
    //户籍地址
    private String row57;
    //户籍所在
    private String row58;
    //通讯地址邮编
    private String row59;
    //就业状况
    private String row60="99-未知";
    //单位性质
    private String row61="99-未知";
    //最高学位
    private String row62="0-其他";
    private String row63="2-否";


    public void hnkdInit(KhxxglHnkd khxxglHnkd,String qydm) {
        if (StringUtils.isNotBlank(khxxglHnkd.getKhmc()))
            this.row = khxxglHnkd.getKhmc();
        if (StringUtils.isNotBlank(khxxglHnkd.getZjhm()))
            this.row2 = khxxglHnkd.getZjhm();
        if (khxxglHnkd.getSxed() != null)
            this.row3 = khxxglHnkd.getSxed().toString();
        if (khxxglHnkd.getJd() != null)
            this.row4 = khxxglHnkd.getJd().toString();
        if (khxxglHnkd.getSxqx() != null)
            this.row5 = khxxglHnkd.getSxqx().toString();

        if (StringUtils.isNotBlank(khxxglHnkd.getKhfq())){
            this.row6 = khfq(khxxglHnkd.getKhfq());

//            if (QydmEnums.TIANYI.getQydmCode().equals(qydm)){
//                this.row63 = "1-是";
//            }
        }

        if(StringUtils.isNotBlank(khxxglHnkd.getXsm())){
            if ("1".equals(khxxglHnkd.getXsm())){
                this.row63 = "1-是";
            }else {
                this.row63 = "2-否";
            }
        }

        if (StringUtils.isNotBlank(khxxglHnkd.getKhjl()))
            this.row7 = khxxglHnkd.getKhjl();
        if (StringUtils.isNotBlank(khxxglHnkd.getDczrr1()))
            this.row8 = khxxglHnkd.getDczrr1();
        if (khxxglHnkd.getDczrr1bl() != null)
            this.row9 = khxxglHnkd.getDczrr1bl().toString();
        if (StringUtils.isNotBlank(khxxglHnkd.getDczrr2()))
            this.row10 = khxxglHnkd.getDczrr2();
        if (khxxglHnkd.getDczrr2bl() != null)
            this.row11 = khxxglHnkd.getDczrr2bl().toString();
        if (StringUtils.isNotBlank(khxxglHnkd.getGlzrr()))
            this.row12 = khxxglHnkd.getGlzrr();
        if (khxxglHnkd.getGlzrrbl() != null)
            this.row13 = khxxglHnkd.getGlzrrbl().toString();
        if (StringUtils.isNotBlank(khxxglHnkd.getSczrr()))
            this.row14 = khxxglHnkd.getSczrr();
        if (khxxglHnkd.getSczrrbl() != null)
            this.row15 = khxxglHnkd.getSczrrbl().toString();
        if (StringUtils.isNotBlank(khxxglHnkd.getZzspzrr()))
            this.row16 = khxxglHnkd.getZzspzrr();
        if (khxxglHnkd.getZzspzrrbl() != null)
            this.row17 = khxxglHnkd.getZzspzrrbl().toString();

        if (StringUtils.isNotBlank(khxxglHnkd.getDyzrr()))
            this.row18 = khxxglHnkd.getDyzrr();
        if (khxxglHnkd.getDyzrrbl() != null)
            this.row19 = khxxglHnkd.getDyzrrbl().toString();

        if (StringUtils.isNotBlank(khxxglHnkd.getSffsdx()))
            this.row40 = ELoandDictUtil.sfbz(khxxglHnkd.getSffsdx());
        if (StringUtils.isNotBlank(khxxglHnkd.getXgzdw())){
            this.row41 = khxxglHnkd.getXgzdw();
        }

        if (StringUtils.isNotBlank(khxxglHnkd.getHy())){
            //0132-麻类种植
//            String dict = getDict(HnkdConstant.hnkd_hy, khxxglHnkd.getHy());
//            //
//            if (StringUtils.isNotBlank(dict)){
//                String[] split = dict.split("-");
//                String hyflqz = ELoandDictUtil.hyflqz(split[0]);
//                this.row43 = hyflqz+split[1];
//            }
            String s = sysDictService.descValueText(HnkdConstant.hnkd_hy, khxxglHnkd.getHy());
            if (StringUtils.isNotBlank(s)){
                this.row43 = s;
            }
        }

        if (StringUtils.isNotBlank(khxxglHnkd.getJyqk()))
            this.row44 = ELoandDictUtil.sfbz(khxxglHnkd.getJyqk());
        if (StringUtils.isNotBlank(khxxglHnkd.getScxu())) {
            this.row45 = ELoandDictUtil.scxu(khxxglHnkd.getScxu());
        }
        if (StringUtils.isNotBlank(khxxglHnkd.getHkyy()))
            this.row46 = ELoandDictUtil.hkyy(khxxglHnkd.getHkyy());

        if (StringUtils.isNotBlank(khxxglHnkd.getZy())) {
            String dict = getDict(HnkdConstant.hnkd_zy, khxxglHnkd.getZy());
            if (StringUtils.isNotBlank(dict)){
                this.row47 = dict;
            }
        }

        if (StringUtils.isNotBlank(khxxglHnkd.getZc())) {
            String dict = getDict(HnkdConstant.hnkd_zc, khxxglHnkd.getZc());
            if (StringUtils.isNotBlank(dict)){
                this.row48 = dict;
            }
        }

        if (StringUtils.isNotBlank(khxxglHnkd.getPjbz())) {
            this.row50 = ELoandDictUtil.pjbz(khxxglHnkd.getPjbz());
        }

        if (StringUtils.isNotBlank(khxxglHnkd.getGtgshmc()))
            this.row51 = khxxglHnkd.getGtgshmc();
        if (StringUtils.isNotBlank(khxxglHnkd.getXydm()))
            this.row52 = khxxglHnkd.getXydm();

        if (khxxglHnkd.getJtnsr() != null) {
//            if (khxxglHnkd.getJtnsr().intValue() < 4) {
//                this.row53 = ELoandDictUtil.jtsr(khxxglHnkd.getJtnsr().toString());
//                this.row54 = ELoandDictUtil.jtsr(khxxglHnkd.getJtnsr().toString());
//            }
            this.row53 = khxxglHnkd.getJtnsr().toString();
        }
        if (khxxglHnkd.getGrnsr() != null) {
            this.row54 = khxxglHnkd.getGrnsr().toString();
        }

        if (StringUtils.isNotBlank(khxxglHnkd.getXhbz()))
            this.row55 = ELoandDictUtil.sfbz(khxxglHnkd.getXhbz());
        if (StringUtils.isNotBlank(khxxglHnkd.getXydj()))
            this.row56 = ELoandDictUtil.xydj(khxxglHnkd.getXydj());
//        if (StringUtils.isNotBlank(khxxglHnkd.getSsxz())) {
//            this.row23 = ELoandDictUtil.getXzByQydm(khxxglHnkd.getSsxz(),qydm);
//        }

        if (StringUtils.isNotBlank(khxxglHnkd.getSsxz())){
           //this.row23 = sysDictService.queryDictTextByKey(HnkdConstant.hnkd_xz+qydm,khxxglHnkd.getSsxz());
            this.row23 = khxxglHnkd.getSsxz();
        }

        if (StringUtils.isNotBlank(khxxglHnkd.getPoxm()))
            this.row27 = khxxglHnkd.getPoxm();
        if (StringUtils.isNotBlank(khxxglHnkd.getPozjhm())) {
            this.row28 = khxxglHnkd.getPozjhm();
            this.row29 = "2";
            if (!QydmEnums.TIANYI.getQydmCode().equals(qydm)){
                this.row26 = "2-已婚";
            }
        } else {
            if (!QydmEnums.TIANYI.getQydmCode().equals(qydm)){
                this.row26 = "4-其他";
            }
        }

        if (QydmEnums.TIANYI.getQydmCode().equals(qydm) && StringUtils.isNotBlank(khxxglHnkd.getHyzk())){
            this.row26 = hyzk095(khxxglHnkd.getHyzk());
        }


        //        if (khxxglHnkd.getJtrk() != null)
//            this.row29 = khxxglHnkd.getJtrk().toString();
        if (StringUtils.isNotBlank(khxxglHnkd.getYwzn()))
            this.row24 = ELoandDictUtil.ywzn(khxxglHnkd.getYwzn());

        if (StringUtils.isNotBlank(khxxglHnkd.getHjszdxzqh()))
            this.row58 = khxxglHnkd.getHjszdxzqh();
        if (StringUtils.isNotBlank(khxxglHnkd.getTxdzyb()))
            this.row59 = khxxglHnkd.getTxdzyb();
//        if (StringUtils.isNotBlank(khxxglHnkd.getJyzk())){
//            this.row60 = ELoandDictUtil.jyzk(khxxglHnkd.getJyzk());
//        }
        if (StringUtils.isNotBlank(khxxglHnkd.getJyzk())) {
            String dict = getDict(HnkdConstant.hnkd_jyzk, khxxglHnkd.getJyzk());
            if (StringUtils.isNotBlank(dict)){
                this.row60 = dict;
            }
        }

        if (StringUtils.isNotBlank(khxxglHnkd.getDwxz()))
            this.row61 = ELoandDictUtil.dwxz(khxxglHnkd.getDwxz());
        if (StringUtils.isNotBlank(khxxglHnkd.getZgxw()))
            this.row62 = ELoandDictUtil.zgxw(khxxglHnkd.getZgxw());
        if (StringUtils.isNotBlank(khxxglHnkd.getHjdz()))
            this.row57 = khxxglHnkd.getHjdz();
    }

    public void init5(Nhxq nhxq,String qydm) {
        if (StringUtils.isNotBlank(nhxq.getKhmc()))
            this.row = nhxq.getKhmc();
        if (StringUtils.isNotBlank(nhxq.getKhfq())){
            this.row6 = khfq(nhxq.getKhfq());

//            if (QydmEnums.TIANYI.getQydmCode().equals(qydm)){
//                if ("1".equals(nhxq.getKhfq())){
//                    this.row63 = "1-是";
//                }
//            }
        }
        if (StringUtils.isNotBlank(nhxq.getMz())) {
            this.row20 = ELoandDictUtil.getMz(nhxq.getMz());
        }
        if (StringUtils.isNotBlank(nhxq.getKhlx1())) {
            this.row21 = khlx1(nhxq.getKhlx1());
        }
        if (StringUtils.isNotBlank(nhxq.getKhlx2())) {
            this.row22 = khlx2(nhxq.getKhlx2());
        }

//        if (QydmEnums.TIANYI.getQydmCode().equals(qydm) && StringUtils.isNotBlank(nhxq.getHyzk())){
//            this.row26 = hyzk(nhxq.getHyzk());
//        }

        if (StringUtils.isNotBlank(nhxq.getYwzn())) {
            this.row24 = ELoandDictUtil.ywzn(nhxq.getYwzn());
        }
        if (StringUtils.isNotBlank(nhxq.getLdnl())) {
            this.row30 = ELoandDictUtil.ldnl(nhxq.getLdnl());
        }
        if (StringUtils.isNotBlank(nhxq.getJznx())) {
            this.row31 = ELoandDictUtil.jznx(nhxq.getJznx());
        }
        if (StringUtils.isNotBlank(nhxq.getJzzt())) {
            this.row32 = ELoandDictUtil.jzzt(nhxq.getJzzt());
        }
        if (StringUtils.isNotBlank(nhxq.getZgxl()))
            this.row33 = ELoandDictUtil.zgxl(nhxq.getZgxl());
        if (StringUtils.isNotBlank(nhxq.getHjdz()))
            this.row34 = nhxq.getHjdz();
        if (StringUtils.isNotBlank(nhxq.getZz()))
            this.row35 = nhxq.getZz();
        if (StringUtils.isNotBlank(nhxq.getSjhm())) {
            this.row38 = nhxq.getSjhm();
        } else {
            if (StringUtils.isNotBlank(nhxq.getYlhm()))
                this.row38 = nhxq.getYlhm();
        }
        if (StringUtils.isNotBlank(nhxq.getSfhz()))
            this.row39 = ELoandDictUtil.sfbz(nhxq.getSfhz());
//        if (StringUtils.isNotBlank(nhxq.getGzdw()))
//            this.row41 = nhxq.getGzdw();

        if (StringUtils.isNotBlank(nhxq.getSjhm())){
            this.row40 = ELoandDictUtil.sfbz("1");
        }
//        else {
//            this.row40 = ELoandDictUtil.sfbz("2");
//        }

    }

    public void initCssz(Map<String, String> map) {
        if (CollUtil.isNotEmpty(map)) {
            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW4))) {
                this.row4 = map.get(HnkdConstant.ROW4);
            }
            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW5))) {
                this.row5 = map.get(HnkdConstant.ROW5);
            }
            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW6))) {
                this.row6 = map.get(HnkdConstant.ROW6);
            }
            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW7))) {
                this.row7 = map.get(HnkdConstant.ROW7);
            }
            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW8))) {
                this.row8 = map.get(HnkdConstant.ROW8);
            }
            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW9))) {
                this.row9 = map.get(HnkdConstant.ROW9);
            }
            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW10))) {
                this.row10 = map.get(HnkdConstant.ROW10);
            }
            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW11))) {
                this.row11 = map.get(HnkdConstant.ROW11);
            }
            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW12))) {
                this.row12 = map.get(HnkdConstant.ROW12);
            }
            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW13))) {
                this.row13 = map.get(HnkdConstant.ROW13);
            }
            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW14))) {
                this.row14 = map.get(HnkdConstant.ROW14);
            }
            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW15))) {
                this.row15 = map.get(HnkdConstant.ROW15);
            }
            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW16))) {
                this.row16 = map.get(HnkdConstant.ROW16);
            }
            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW17))) {
                this.row17 = map.get(HnkdConstant.ROW17);
            }
            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW18))) {
                this.row18 = map.get(HnkdConstant.ROW18);
            }
            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW19))) {
                this.row19 = map.get(HnkdConstant.ROW19);
            }
            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW20))) {
                this.row20 = map.get(HnkdConstant.ROW20);
            }
            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW21))) {
                this.row21 = map.get(HnkdConstant.ROW21);
            }
            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW22))) {
                this.row22 = map.get(HnkdConstant.ROW22);
            }
            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW23))) {
                this.row23 = map.get(HnkdConstant.ROW23);
            }
            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW24))) {
                this.row24 = map.get(HnkdConstant.ROW24);
            }
            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW25))) {
                this.row25 = map.get(HnkdConstant.ROW25);
            }
            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW26))) {
                this.row26 = map.get(HnkdConstant.ROW26);
            }
            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW27))) {
                this.row27 = map.get(HnkdConstant.ROW27);
            }
            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW28))) {
                this.row28 = map.get(HnkdConstant.ROW28);
            }
            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW29))) {
                this.row29 = map.get(HnkdConstant.ROW29);
            }
            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW30))) {
                this.row30 = map.get(HnkdConstant.ROW30);
            }
            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW31))) {
                this.row31 = map.get(HnkdConstant.ROW31);
            }
            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW32))) {
                this.row32 = map.get(HnkdConstant.ROW32);
            }
            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW33))) {
                this.row33 = map.get(HnkdConstant.ROW33);
            }
            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW34))) {
                this.row34 = map.get(HnkdConstant.ROW34);
            }
            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW35))) {
                this.row35 = map.get(HnkdConstant.ROW35);
            }
            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW36))) {
                this.row36 = map.get(HnkdConstant.ROW36);
            }
            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW37))) {
                this.row37 = map.get(HnkdConstant.ROW37);
            }
            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW38))) {
                this.row38 = map.get(HnkdConstant.ROW38);
            }
            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW39))) {
                this.row39 = map.get(HnkdConstant.ROW39);
            }
            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW40))) {
                this.row40 = map.get(HnkdConstant.ROW40);
            }
            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW41))) {
                this.row41 = map.get(HnkdConstant.ROW41);
            }
            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW42))) {
                this.row42 = map.get(HnkdConstant.ROW42);
            }
            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW43))) {
                this.row43 = map.get(HnkdConstant.ROW43);
            }
            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW44))) {
                this.row44 = map.get(HnkdConstant.ROW44);
            }
            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW45))) {
                this.row45 = map.get(HnkdConstant.ROW45);
            }
            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW46))) {
                this.row46 = map.get(HnkdConstant.ROW46);
            }
            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW47))) {
                this.row47 = map.get(HnkdConstant.ROW47);
            }
            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW48))) {
                this.row48 = map.get(HnkdConstant.ROW48);
            }
            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW49))) {
                this.row49 = map.get(HnkdConstant.ROW49);
            }
            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW50))) {
                this.row50 = map.get(HnkdConstant.ROW50);
            }
            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW51))) {
                this.row51 = map.get(HnkdConstant.ROW51);
            }
            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW52))) {
                this.row52 = map.get(HnkdConstant.ROW52);
            }
            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW53))) {
                this.row53 = map.get(HnkdConstant.ROW53);
            }
            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW54))) {
                this.row54 = map.get(HnkdConstant.ROW54);
            }
            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW55))) {
                this.row55 = map.get(HnkdConstant.ROW55);
            }
            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW56))) {
                this.row56 = map.get(HnkdConstant.ROW56);
            }

            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW57))) {
                this.row57 = map.get(HnkdConstant.ROW57);
            }
            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW58))) {
                this.row58 = map.get(HnkdConstant.ROW58);
            }
            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW59))) {
                this.row59 = map.get(HnkdConstant.ROW59);
            }
            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW60))) {
                this.row60 = map.get(HnkdConstant.ROW60);
            }
            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW61))) {
                this.row61 = map.get(HnkdConstant.ROW61);
            }
            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW62))) {
                this.row62 = map.get(HnkdConstant.ROW62);
            }
            if (StringUtils.isNotBlank(map.get(HnkdConstant.ROW63))) {
                this.row63 = map.get(HnkdConstant.ROW63);
            }
        }
    }


    public void setFzr(String username) {
        this.row7 = username;
        this.row8 = username;
        this.row12 = username;
        this.row14 = username;
        this.row16 = username;
        this.row18 = username;

    }

    public void initBkbpyList(List<Nhbkbpy> nhbkbpyList) {
        String ed = "";
        if (CollUtil.isNotEmpty(nhbkbpyList)) {
            int n = nhbkbpyList.size();
            this.row29 = n + "";
            BigDecimal bigDecimal = null;
            for (int i = 0; i < n; i++) {
                Nhbkbpy nhbkbpy = nhbkbpyList.get(i);
                //算额度
                if (nhbkbpy.getJcmxcs() != null) {
                    if (bigDecimal == null) {
                        bigDecimal = nhbkbpy.getJcmxcs();
                    } else {
                        if (bigDecimal.compareTo(nhbkbpy.getJcmxcs()) == 1) {
                            bigDecimal = nhbkbpy.getJcmxcs();
                        }
                    }
                }
            }
        }
    }

    public String khfq(String khlx) {
        if ("1".equals(khlx))
            return "01-公职人员";
        if ("2".equals(khlx))
            return "02-个体工商户";
        if ("3".equals(khlx))
            return "03-私企人员";
        if ("4".equals(khlx))
            return "04-城乡居民";
        return "04-城乡居民";
    }

    public String khlx1(String khlx) {
        if ("1".equals(khlx))
            return "01-农户";
        if ("2".equals(khlx) || "5".equals(khlx))
            return "02-非农个体工商户";
        return "03-自然人";
    }

    public String khlx2(String khlx) {
        if ("1".equals(khlx))
            return "11-一般农户";
        if ("2".equals(khlx))
            return "12-农村个体工商户";
        if ("3".equals(khlx))
            return "13-失地农民";
        if ("4".equals(khlx))
            return "14-返乡农民工";
        if ("5".equals(khlx))
            return "15-残疾人";
        if ("6".equals(khlx))
            return "16-小微企业主";
        if ("7".equals(khlx))
            return "17-建档立卡贫困农户";
        if ("8".equals(khlx))
            return "19-其他";
        return "19-其他";
    }

    public String hyzk(String hyzk) {
        // 1wei 2yi 3li 4qita
//        if ("2".equals(hyzk))
//            return "1-未婚";
        if ("1".equals(hyzk))
            return "2-已婚";
//        if ("3".equals(hyzk))
//            return "3-离异";
        return "4-其他";
    }


    public String hyzk095(String hyzk) {
        if ("1".equals(hyzk))
            return "2-已婚";
        if ("2".equals(hyzk))
            return "1-未婚";
        if ("3".equals(hyzk))
            return "3-离异";
        return "4-其他";
    }

    public String zc(String zc) {
        // 1wei 2yi 3li 4qita
        if ("1".equals(zc))
            return "1-高级";
        if ("2".equals(zc))
            return "2-中级";
        if ("3".equals(zc))
            return "3-初级";
        if ("9".equals(zc))
            return "9-未知";
        return "0-无";
    }

    public String setCszy(String s) {
        if (StringUtils.isNotBlank(s)) {
            if (s.startsWith("0"))
                return "0-国家机关、党群组织、企业、事业单位负责人、公务员、本行员工";
            if (s.startsWith("1"))
                return "1-专业技术人员";
            if (s.startsWith("3"))
                return "3-办事人员和有关人员";
            if (s.startsWith("4"))
                return "4-商业、服务业人员";
            if (s.startsWith("5"))
                return "5-农、林、牧、渔、水利业生产人员";
            if (s.startsWith("6"))
                return "6-生产、运输设备操作人员及有关人员";
            if (s.startsWith("7"))
                return "x-军人";
            if (s.startsWith("8"))
                return "y-不便分类的其他从业人员";
            return "z-未知";
        }
        return "z";
    }
    private ISysDictService sysDictService;
    public void initOldHnkd(KhxxglHnkd hnkd,String qydm){
        if (StringUtils.isNotBlank(hnkd.getKhmc()))
            this.row = hnkd.getKhmc();

        if (StringUtils.isNotBlank(hnkd.getZjhm()))
            this.row2 = hnkd.getZjhm();

        if (hnkd.getSxed() != null)
            this.row3 = hnkd.getSxed().toString();

        if (hnkd.getJd() != null)
            this.row4 = hnkd.getJd().toString();

        if (hnkd.getSxqx() != null)
            this.row5 = hnkd.getSxqx()+"";

        if (StringUtils.isNotBlank(hnkd.getKhfq())){
            this.row6 = getDict(HnkdConstant.hnkd_khfq,hnkd.getKhfq());
        }

        if (StringUtils.isNotBlank(hnkd.getKhjl()))
            this.row7 = hnkd.getKhjl();

        if (StringUtils.isNotBlank(hnkd.getDczrr1()))
            this.row8 = hnkd.getDczrr1();

        if (hnkd.getDczrr1bl() != null)
            this.row9 = hnkd.getDczrr1bl().toString();

        if (StringUtils.isNotBlank(hnkd.getDczrr2()))
            this.row10 = hnkd.getDczrr2();

        if (hnkd.getDczrr2bl() != null)
            this.row11 = hnkd.getDczrr2bl().toString();

        if (StringUtils.isNotBlank(hnkd.getGlzrr()))
            this.row12 = hnkd.getGlzrr();

        if (hnkd.getGlzrrbl() != null)
            this.row13 = hnkd.getGlzrrbl().toString();

        if (StringUtils.isNotBlank(hnkd.getSczrr()))
            this.row14 = hnkd.getSczrr();

        if (hnkd.getSczrrbl() != null)
            this.row15 = hnkd.getSczrrbl().toString();

        if (StringUtils.isNotBlank(hnkd.getZzspzrr()))
            this.row16 = hnkd.getZzspzrr();

        if (hnkd.getZzspzrrbl() != null)
            this.row17 = hnkd.getZzspzrrbl().toString();

        if (StringUtils.isNotBlank(hnkd.getDyzrr()))
            this.row18 = hnkd.getDyzrr();

        if (hnkd.getDyzrrbl() != null)
            this.row19 = hnkd.getDyzrrbl().toString();

        if (StringUtils.isNotBlank(hnkd.getMz())){
            if (StringUtils.isNotBlank(ELoandDictUtil.getMz(hnkd.getMz())))
                this.row20 = ELoandDictUtil.getMz(hnkd.getMz());
        }

        if (StringUtils.isNotBlank(hnkd.getKhlx1())){
            this.row21 = getDict(HnkdConstant.hnkd_khlx1,hnkd.getKhlx1());
        }
        if (StringUtils.isNotBlank(hnkd.getKhlx2()))
            this.row22 = getDict(HnkdConstant.hnkd_khlx2,hnkd.getKhlx2());

        if (StringUtils.isNotBlank(hnkd.getSsxz())){
            String s = sysDictService.queryDictTextByKey(HnkdConstant.hnkd_xz + qydm, hnkd.getSsxz());
            if (StringUtils.isNotBlank(s))
                this.row23 = s;
        }

        if (StringUtils.isNotBlank(hnkd.getYwzn()))
            this.row24 = getDict(HnkdConstant.hnkd_ywzn,hnkd.getYwzn());

        if (StringUtils.isNotBlank(hnkd.getJkzk())){
            if (StringUtils.isNotBlank(getDict(HnkdConstant.hnkd_jkzk,hnkd.getJkzk()))){
                this.row25 = getDict(HnkdConstant.hnkd_jkzk,hnkd.getJkzk());
            }
        }

        if (StringUtils.isNotBlank(hnkd.getHyzk()))
            this.row26 = getDict(HnkdConstant.hnkd_hyzk,hnkd.getHyzk());

        if (StringUtils.isNotBlank(hnkd.getPoxm()))
            this.row27 = hnkd.getPoxm();

        if (StringUtils.isNotBlank(hnkd.getPozjhm())){
            this.row28 = hnkd.getPozjhm();
            this.row26 = "2-已婚";
        }

        if (hnkd.getJtrk() != null)
            this.row29 = hnkd.getJtrk().toString();

        if (StringUtils.isNotBlank(hnkd.getLdnl()))
            this.row30 = getDict(HnkdConstant.hnkd_ldnl,hnkd.getLdnl());

        if (StringUtils.isNotBlank(hnkd.getJznx()))
            this.row31 = getDict(HnkdConstant.hnkd_jznx,hnkd.getJznx());

        if (StringUtils.isNotBlank(hnkd.getJzzk())){
            if (StringUtils.isNotBlank(getDict(HnkdConstant.hnkd_jzzk,hnkd.getJzzk()))){
                this.row32 = getDict(HnkdConstant.hnkd_jzzk,hnkd.getJzzk());
            }
        }

        if (StringUtils.isNotBlank(hnkd.getZgxl())){
            if (StringUtils.isNotBlank(getDict(HnkdConstant.hnkd_jzzk,hnkd.getZgxl()))){
                this.row33 = getDict(HnkdConstant.hnkd_zgxl,hnkd.getZgxl());
            }
        }

        if (StringUtils.isNotBlank(hnkd.getCzdz()))
            this.row34 = hnkd.getCzdz();

        if (StringUtils.isNotBlank(hnkd.getTxdz()))
            this.row35 = hnkd.getTxdz();

        if (StringUtils.isNotBlank(hnkd.getXzqhdm()))
            this.row36 = hnkd.getXzqhdm();

        if (StringUtils.isNotBlank(hnkd.getZdyzbm()))
            this.row37 = hnkd.getZdyzbm();

        if (StringUtils.isNotBlank(hnkd.getSjhm()))
            this.row38 = hnkd.getSjhm();

        if (StringUtils.isNotBlank(hnkd.getSfhz()))
            this.row39 = getDict(HnkdConstant.hnkd_sfhz,hnkd.getSfhz());

        if (StringUtils.isNotBlank(hnkd.getSffsdx()))
            this.row40 = getDict(HnkdConstant.hnkd_sffsdx,hnkd.getSffsdx());

        if (StringUtils.isNotBlank(hnkd.getXgzdw()))
            this.row41 = hnkd.getXgzdw();

        if (StringUtils.isNotBlank(hnkd.getXdrzw()))
            this.row42 = getDict(HnkdConstant.hnkd_xdrzw,hnkd.getXdrzw());

        if (StringUtils.isNotBlank(hnkd.getHy())){
            this.row43 = getDict(HnkdConstant.hnkd_hy,hnkd.getHy());
        }else {
            int i1 = RandomUtil.randomInt(0, 6);
            if (i1 == 0) {
                this.row43="A0111-稻谷种植";
            } else if (i1 == 2) {
                this.row43="A0143-花卉种植";
            } else if (i1 == 3) {
                this.row43="A0159-其他水果种植";
            } else if (i1 == 4) {
                this.row43="A0122-油料种植";
            } else if (i1 == 5) {
                this.row43="A0313-猪的饲养";
            } else {
                this.row43="A0412-内陆养殖";
            }
        }

        if (StringUtils.isNotBlank(hnkd.getJyqk()))
            this.row44 = getDict(HnkdConstant.hnkd_jyqK,hnkd.getJyqk());

        if (StringUtils.isNotBlank(hnkd.getScxu()))
            this.row45 = getDict(HnkdConstant.hnkd_scxu,hnkd.getScxu());

        if (StringUtils.isNotBlank(hnkd.getHkyy()))
            this.row46 = getDict(HnkdConstant.hnkd_hkyy,hnkd.getHkyy());

        if (StringUtils.isNotBlank(hnkd.getZy()))
            this.row47 = getDict(HnkdConstant.hnkd_zy,hnkd.getZy());

        if (StringUtils.isNotBlank(hnkd.getZc()))
            this.row48 = getDict(HnkdConstant.hnkd_zc,hnkd.getZc());

        if (StringUtils.isNotBlank(hnkd.getCynx()))
            this.row49 = getDict(HnkdConstant.hnkd_cynx,hnkd.getCynx());

        if (StringUtils.isNotBlank(hnkd.getPjbz()))
            this.row50 = getDict(HnkdConstant.hnkd_pjbz,hnkd.getPjbz());

        if (StringUtils.isNotBlank(hnkd.getGtgshmc()))
            this.row51 = hnkd.getGtgshmc();

        if (StringUtils.isNotBlank(hnkd.getXydm()))
            this.row52 = hnkd.getXydm();

        if (hnkd.getJtnsr() != null)
            this.row53 = hnkd.getJtnsr().toString();

        if (hnkd.getGrnsr() != null)
            this.row54 = hnkd.getGrnsr().toString();

        if (StringUtils.isNotBlank(hnkd.getXhbz()))
            this.row55 = getDict(HnkdConstant.hnkd_xhbz,hnkd.getXhbz());

        if (StringUtils.isNotBlank(hnkd.getXydj()))
            this.row56 = getDict(HnkdConstant.hnkd_xydj,hnkd.getXydj());

        if (StringUtils.isNotBlank(hnkd.getHjdz()))
            this.row57 = hnkd.getHjdz();

        if (StringUtils.isNotBlank(hnkd.getHjszdxzqh()))
            this.row58 = hnkd.getHjszdxzqh();

        if (StringUtils.isNotBlank(hnkd.getTxdzyb()))
            this.row59 = hnkd.getTxdzyb();

        if (StringUtils.isNotBlank(hnkd.getJyzk()))
            this.row60 = getDict(HnkdConstant.hnkd_jyzk,hnkd.getJyzk());

        if (StringUtils.isNotBlank(hnkd.getDwxz()))
            this.row61 = getDict(HnkdConstant.hnkd_dwxz,hnkd.getDwxz());

        if (StringUtils.isNotBlank(hnkd.getZgxw()))
            this.row62 = getDict(HnkdConstant.hnkd_zgxw,hnkd.getZgxw());
    }

    public String getDict(String code,String key){
      String result = sysDictService.queryDictTextByKey(code, key);
      if (StringUtils.isNotBlank(result))
          return key+"-"+result;
      return  "";
    }
}
