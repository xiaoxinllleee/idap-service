package org.cmms.modules.word.entity;

import lombok.Data;
import org.cmms.common.constant.WordConstant;

import java.io.Serializable;

/**
 * 个人授信申请表
 * */
@Data
public class GrsxsqbDTO{
    //主调查人
    private String zdcr_;
    private String khmc_;
    private String sszh_;
    //借款人名称
    private String khmc;
    private String dz;
    private String sjhm;
    private String zjhm;
    private String xb;
    private String ssjhm;
    private String khyh1;
    private String khyh2;
    private String zh1;
    private String zh2;
    private String drye1;
    private String drye2;
    //现有贷款
    private String xydk;
    //对外担保
    private String dwdb;
    //担保对象
    private String dbdx;
    //其他用信
    private String qtyx1;
    private String qtyx2;
    private String qtyx3;
    private String qtyx4;
    //家庭成员情况
    private String fkhmc1;
    private String fkhmc2;
    private String fkhmc3;

    private String yjkrgx1;
    private String yjkrgx2;
    private String yjkrgx3;

    private String fzjhm1;
    private String fzjhm2;
    private String fzjhm3;

    private String fdz1;
    private String fdz2;
    private String fdz3;
    //申请授信总额
    private String sqsxze;
    private String qx;
    //贷款金额及用途 ☑
    private String yt_;

    //福农卡
    private String fnk_;
    private String fnkk = WordConstant.FANGKUANG;

    //便民卡
    private String bmk_;
    private String bmkk = WordConstant.FANGKUANG;

    //担保金额
    private String dbje_;
    private String dbjek = WordConstant.FANGKUANG;
    //还款来源及计划
    private String hklyjjh;
    //申请借款方式
    private String sqjkfs;

    //抵押 保证 质押 信用
    private String dyk = WordConstant.FANGKUANG;
    private String bzk = WordConstant.FANGKUANG;
    private String zyk = WordConstant.FANGKUANG;
    private String xyk = WordConstant.FANGKUANG;

    /**
     * 个人贷款面谈记录
     * */
    //借款
    private String mtjk_;
    private String mtqx_;
    private String mtyt_;
    private String mtsr_;
    //private String mtxx1 = WordConstant.IS_YES;
    //private String mtxx2 = WordConstant.IS_YES;
    //private String mtxx3 = WordConstant.IS_YES;
    //private String mtxx4 = WordConstant.IS_YES;
    //private String mtxx5 = WordConstant.IS_YES;




    public void setDbjeAndDbjek(String dbje) {
        this.dbje_ = dbje;
        this.dbjek = WordConstant.GOUXUAN;
    }


    public void setBmkAdnBmkk(String bmk) {
        this.bmk_ = bmk;
        this.bmkk = WordConstant.GOUXUAN;
    }

    public void setFnkAdnFnkk(String fnk) {

        this.fnk_ = fnk;
        this.fnkk = WordConstant.GOUXUAN;
    }

    //湖南浏阳农村商业银行抵押房地产评估报告单
    private String sszh;
    private String fwssqr1;
    private String lxdh1;
    private String fcyjkrgx1;
    private String fwzlwz1;
    private String fwssqr2;
    private String lxdh2;
    private String fcyjkrgx2;
    private String fwzlwz2;
    private String fwssqr3;
    private String lxdh3;
    private String fcyjkrgx3;
    private String fwzlwz3;
    private String fwssqr4;
    private String lxdh4;
    private String fcyjkrgx4;
    private String fwzlwz4;

    private String tdzh1;
    private String tdlx1;
    private String tdyt1;
    private String syqmj1;
    private String zzrq1;
    private String tdzh2;
    private String tdlx2;
    private String tdyt2;
    private String syqmj2;
    private String zzrq2;
    private String tdzh3;
    private String tdlx3;
    private String tdyt3;
    private String syqmj3;
    private String zzrq3;
    private String tdzh4;
    private String tdlx4;
    private String tdyt4;
    private String syqmj4;
    private String zzrq4;

    private String fczh1;
    private String zcs1;
    private String fcyt1;
    private String jzmj1;
    private String jcsj1;
    private String fczh2;
    private String zcs2;
    private String fcyt2;
    private String jzmj2;
    private String jcsj2;
    private String fczh3;
    private String zcs3;
    private String fcyt3;
    private String jzmj3;
    private String jcsj3;
    private String fczh4;
    private String zcs4;
    private String fcyt4;
    private String jzmj4;
    private String jcsj4;

    private String cc1;
    private String ccyt1;
    private String ccjzmj1;
    private String mpfdj1;
    private String gjz1;
    private String cc2;
    private String ccyt2;
    private String ccjzmj2;
    private String mpfdj2;
    private String gjz2;
    private String cc3;
    private String ccyt3;
    private String ccjzmj3;
    private String mpfdj3;
    private String gjz3;
    private String cc4;
    private String ccyt4;
    private String ccjzmj4;
    private String mpfdj4;
    private String gjz4;

    //共同借款人承诺书
    private String gtjkr_;
    private String gtsszh_;
    private String gtyt_;
    private String jk_;
    private String qx_;
    private String dbrlxdz_;
    private String zdqsr_;
    private String sjhm_;
    private String czhm_;
    private String dzyx_;
    private String wxh_;
    private String qtfs_;
    private String bhjbry_;

    //调查报告
    private String dcbg;

    //支行风险审查表
    private String sc11 = WordConstant.FANGKUANG;
    private String sc12 = WordConstant.FANGKUANG;
    private String sc13 = WordConstant.FANGKUANG;

    private String sc21 = WordConstant.FANGKUANG;
    private String sc22 = WordConstant.FANGKUANG;
    private String sc23 = WordConstant.FANGKUANG;
    private String sc24 = WordConstant.FANGKUANG;
    private String sc25 = WordConstant.FANGKUANG;
    private String sc26 = WordConstant.FANGKUANG;
    private String sc27 = WordConstant.FANGKUANG;

    private String sc31 = WordConstant.FANGKUANG;
    private String sc32 = WordConstant.FANGKUANG;

    private String sc41 = WordConstant.FANGKUANG;
    private String sc42 = WordConstant.FANGKUANG;
    private String sc43 = WordConstant.FANGKUANG;
    private String sc44 = WordConstant.FANGKUANG;
    private String sc45 = WordConstant.FANGKUANG;
    private String sc46 = WordConstant.FANGKUANG;
    private String sc47 = WordConstant.FANGKUANG;
    private String sc48 = WordConstant.FANGKUANG;
    private String sc49 = WordConstant.FANGKUANG;

    private String sc51 = WordConstant.FANGKUANG;
    private String sc52 = WordConstant.FANGKUANG;
    private String sc53 = WordConstant.FANGKUANG;
    private String sc54 = WordConstant.FANGKUANG;
    private String sc55 = WordConstant.FANGKUANG;
    private String sc56 = WordConstant.FANGKUANG;

    private String sc6;
    private String sc7;
    private String sc81 = WordConstant.FANGKUANG;
    private String sc82 = WordConstant.FANGKUANG;
    private String sc83 = WordConstant.FANGKUANG;


    //集体审批讨论记录及审批责任书


    private String cxbr = WordConstant.GOUXUAN;
    private String cxxm_;
    private String cxzjhm_;
    private String cxpo = WordConstant.FANGKUANG;
    private String cxpo_;
    public void setCxpoValue(String cxpo){
        this.cxpo = WordConstant.GOUXUAN;
        this.cxpo_ = cxpo;
    }



    private String cxpozjhm_;
    private String gs;

    private String pic1;
    private String pic2;
    private String pic3;
    private String pic4;
    private String pic5;
    private String pic6;
    private String pic7;
    private String pic8;
    private String pic9;
    private String pic10;
    private String pic11;
    private String pic12;
    private String pic13;
    private String pic14;
    private String pic15;
    private String pic16;
    private String pic17;
    private String pic18;


    private String spsj;
    private String spdd;
    private String spjlr;
    private String spsx;
    private String spdk;
    private String spbm;
    private String pfn;
    private String pqt;
    private String pyx;
    private String pdk;
    private String prq;
    private String knf;
    private String qtp;

    private String xsx;
    private String pdb;
    private String pdywjz;
    private String csry;
    private String sdjl;

    private String sxze;
    private String pqx;
    private String n;
    private String jd;
    private String dksx;
    private String pbmksx;
    private String pdbsx;
    private String pfnksx;
    private String pfjtj;

    private String dcryj;
    private String piczqz;
    private String spryj;
    private String picsqz;
    private String piccqz;
    private String picxqz;

    //调查范本
    private String mName;
    private String mHeader;
    private String mYi;
    private String mEr;
    private String mSan;
    private String mSi;
    private String mWu;
    private String mLiu;
    private String mQi;
    private String mzdcr;




}
