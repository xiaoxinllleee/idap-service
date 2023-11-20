package org.cmms.modules.xdgl.pjsxspjl.service.impl;

import cn.hutool.core.util.IdcardUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.units.qual.A;
import org.cmms.common.constant.DictConstant;
import org.cmms.modules.dictcache.IDictValueQuery;
import org.cmms.modules.khgl.grkhgl.entity.CamsZcsxGrpjsxxx;
import org.cmms.modules.khgl.grkhgl.entity.CamsZcsxGrpjsxxxBc;
import org.cmms.modules.khgl.grkhgl.entity.Khhmcxx;
import org.cmms.modules.khgl.grkhgl.mapper.CamsZcsxGrpjsxxxBcMapper;
import org.cmms.modules.khgl.grkhgl.mapper.CamsZcsxGrpjsxxxMapper;
import org.cmms.modules.khgl.grkhgl.mapper.KhhmcxxMapper;
import org.cmms.modules.khgl.grkhgl.service.IvKhglKrkhglService;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.SlSHnkdVO;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.xdgl.grkhpjsx.entity.FamerExportWord;
import org.cmms.modules.xdgl.grkhpjsx.entity.FamilyMember;
import org.cmms.modules.xdgl.pjsxspjl.entity.CamsZcsxGrpjsxxxSpjl;
import org.cmms.modules.xdgl.pjsxspjl.entity.LyNewNsVO;
import org.cmms.modules.xdgl.pjsxspjl.mapper.CamsZcsxGrpjsxxxSpjlMapper;
import org.cmms.modules.xdgl.pjsxspjl.service.ICamsZcsxGrpjsxxxSpjlService;
import org.cmms.modules.yxdygl.sjyxdygl.entity.Sjyxdygl;
import org.cmms.modules.yxdygl.sjyxdygl.mapper.SjyxdyglMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 评级授信审批记录
 * @Author: jeecg-boot
 * @Date:   2020-08-05
 * @Version: V1.0
 */
@Service
public class CamsZcsxGrpjsxxxSpjlServiceImpl extends ServiceImpl<CamsZcsxGrpjsxxxSpjlMapper, CamsZcsxGrpjsxxxSpjl> implements ICamsZcsxGrpjsxxxSpjlService {


    @Autowired
    KhhmcxxMapper khhmcxxMapper;
    @Autowired
    IDictValueQuery iDictValueQuery;
    @Autowired
    CamsZcsxGrpjsxxxMapper camsZcsxGrpjsxxxMapper;
    @Autowired
    CamsZcsxGrpjsxxxSpjlMapper camsZcsxGrpjsxxxSpjlMapper;
    @Autowired
    IvKhglKrkhglService ivKhglKrkhglService;
    @Autowired
    CamsZcsxGrpjsxxxBcMapper camsZcsxGrpjsxxxBcMapper;
    @Autowired
    ISysDictService sysDictService;
    @Autowired
    SjyxdyglMapper sjyxdyglMapper;
    //根据评级授信审批记录来获得农户信用评定信息采集表
    @Override
    public FamerExportWord getWord(String zjhm, String hhbm) {


        //固定资产
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("SPID",zjhm);
        //CamsZcsxGrpjsxxx camsZcsxGrpjsxxx = camsZcsxGrpjsxxxMapper.selectOne(queryWrapper);
        CamsZcsxGrpjsxxxSpjl camsZcsxGrpjsxxx = camsZcsxGrpjsxxxSpjlMapper.selectOne(queryWrapper);

        FamerExportWord famerExportWord = new FamerExportWord();


        if (camsZcsxGrpjsxxx != null){


            if (StringUtils.isNoneBlank(camsZcsxGrpjsxxx.getZjhm())){
                zjhm = camsZcsxGrpjsxxx.getZjhm();
                //客户花名册 客户采集信息 客户补充信息
                QueryWrapper jt = new QueryWrapper();
                jt.eq("HHBM",hhbm);
                List<Khhmcxx> list = khhmcxxMapper.selectList(jt);
                List<FamilyMember> familyMembers = new ArrayList<>();
                if (list != null && list.size() > 0){
                    for (int i = 0; i < list.size(); i++) {
                        Khhmcxx khhmcxx = list.get(i);
                        if (khhmcxx.getZjhm().equals(zjhm)){//户主
                            //根据三级营销单元编号获取123级中文
                            if (StringUtils.isNoneBlank(khhmcxx.getKhmc()))
                                famerExportWord.setName(khhmcxx.getKhmc());
                            if (StringUtils.isNoneBlank(khhmcxx.getXb())){
                                if (khhmcxx.getXb().equals(DictConstant.SEX_MAN)){
                                    famerExportWord.setSexVal(DictConstant.SEX_MAN_VAL);
                                }else {
                                    famerExportWord.setSexVal(DictConstant.SEX_WOMAN_VAL);
                                }
                                famerExportWord.setBirthday(iDictValueQuery.getYearMonthValue(zjhm));
                                famerExportWord.setIdn(zjhm);
                                if (StringUtils.isNoneBlank(khhmcxx.getHyzk()))
                                    famerExportWord.setMarriageVal(iDictValueQuery.getHyzkValue(khhmcxx.getHyzk()));
                                if (StringUtils.isNoneBlank(khhmcxx.getCszy()))
                                    famerExportWord.setProfessionVal(iDictValueQuery.getHyzkValue(khhmcxx.getCszy()));
                                if (StringUtils.isNoneBlank(khhmcxx.getLxfs()))
                                    famerExportWord.setMobile(khhmcxx.getLxfs());
                            }
                        }else {//非户主
                            FamilyMember familyMember = new FamilyMember();
                            if (StringUtils.isNoneBlank(khhmcxx.getKhmc()))
                                familyMember.setName(khhmcxx.getKhmc());
                            if (StringUtils.isNoneBlank(khhmcxx.getYhzgx())){
                                familyMember.setRelationVal(iDictValueQuery.getYzhgxValue(khhmcxx.getYhzgx()));
                            }
                            familyMember.setBirthday(iDictValueQuery.getYearMonthValue(khhmcxx.getZjhm()));
                            if (StringUtils.isNoneBlank(khhmcxx.getCszy()))
                                familyMember.setProfessionVal(iDictValueQuery.getCszyValue(khhmcxx.getCszy()));
                            familyMember.setIdn(khhmcxx.getZjhm());
                            if (StringUtils.isNoneBlank(khhmcxx.getLxfs()))
                                familyMember.setMobile(khhmcxx.getLxfs());
                            familyMembers.add(familyMember);
                        }
                    }
                }
                famerExportWord.setFamilyMember(familyMembers);
            }




            if (camsZcsxGrpjsxxx.getGdzcZfts() != null)
                famerExportWord.setHouseCount(camsZcsxGrpjsxxx.getGdzcZfts().toString());
            if (camsZcsxGrpjsxxx.getGdzcZfmj() != null)
                famerExportWord.setHouseArea(camsZcsxGrpjsxxx.getGdzcZfmj().toString());
            if (camsZcsxGrpjsxxx.getGdzcZfjz() != null)
                famerExportWord.setHousePrice(camsZcsxGrpjsxxx.getGdzcZfjz().toString());
            if (camsZcsxGrpjsxxx.getGdzcQt() != null)
                famerExportWord.setCapOther(camsZcsxGrpjsxxx.getGdzcQt().toString());
            if (camsZcsxGrpjsxxx.getGdzcHj() != null)
                famerExportWord.setCapTotal(camsZcsxGrpjsxxx.getGdzcHj().toString());

            if (camsZcsxGrpjsxxx.getLdzcXjjwhck() != null)
                famerExportWord.setCashDeposit(camsZcsxGrpjsxxx.getLdzcXjjwhck().toString());
            if (camsZcsxGrpjsxxx.getLdzcQt() != null)
                famerExportWord.setCircOther(camsZcsxGrpjsxxx.getLdzcQt().toString());
            if (camsZcsxGrpjsxxx.getLdzcYsk() != null)
                famerExportWord.setReceivable(camsZcsxGrpjsxxx.getLdzcYsk().toString());
            if (camsZcsxGrpjsxxx.getLdzcSfthkh() != null)
                famerExportWord.setIsOpen(camsZcsxGrpjsxxx.getLdzcSfthkh());
            if (StringUtils.isNoneBlank(camsZcsxGrpjsxxx.getLdzcSfthyck()))
                famerExportWord.setIsSave(camsZcsxGrpjsxxx.getLdzcSfthyck());
            if (camsZcsxGrpjsxxx.getLdzcThckje() != null)
                famerExportWord.setSaveAmount(camsZcsxGrpjsxxx.getLdzcThckje().toString());
            if (camsZcsxGrpjsxxx.getLdzcHj() != null)
                famerExportWord.setCircleTotal(camsZcsxGrpjsxxx.getLdzcHj().toString());

            //负债
            if (camsZcsxGrpjsxxx.getFzWhjk() != null)
                famerExportWord.setLoanOwnBank(camsZcsxGrpjsxxx.getFzWhjk().toString());
            if (camsZcsxGrpjsxxx.getFzQtyhjk() != null)
                famerExportWord.setLoanOtherBank(camsZcsxGrpjsxxx.getFzQtyhjk().toString());
            if (camsZcsxGrpjsxxx.getFzSrjkhqtjk() != null)
                famerExportWord.setLoanOther(camsZcsxGrpjsxxx.getFzSrjkhqtjk().toString());
            if (camsZcsxGrpjsxxx.getFzYfk() != null)
                famerExportWord.setPayable(camsZcsxGrpjsxxx.getFzYfk().toString());
            if (camsZcsxGrpjsxxx.getFzQtfz() != null)
                famerExportWord.setOther(camsZcsxGrpjsxxx.getFzQtfz().toString());
            if (camsZcsxGrpjsxxx.getFzWtrdb() != null)
                famerExportWord.setOtherGuarantee(camsZcsxGrpjsxxx.getFzWtrdb().toString());
            if (camsZcsxGrpjsxxx.getFzHj() != null)
                famerExportWord.setDebtTotal(camsZcsxGrpjsxxx.getFzHj().toString());
            if (camsZcsxGrpjsxxx.getJtjzc() != null)
                famerExportWord.setNetAsset(camsZcsxGrpjsxxx.getJtjzc().toString());

            //收入
            if (camsZcsxGrpjsxxx.getSrZz() != null)
                famerExportWord.setCrop(camsZcsxGrpjsxxx.getSrZz().toString());
            if (camsZcsxGrpjsxxx.getSrYz() != null)
                famerExportWord.setBreed(camsZcsxGrpjsxxx.getSrYz().toString());
            if (camsZcsxGrpjsxxx.getSrLw() != null)
                famerExportWord.setLabour(camsZcsxGrpjsxxx.getSrLw().toString());
            if (camsZcsxGrpjsxxx.getSrGsy() != null)
                famerExportWord.setCommerce(camsZcsxGrpjsxxx.getSrGsy().toString());
            if (camsZcsxGrpjsxxx.getSrQtsr() != null)
                famerExportWord.setInOther(camsZcsxGrpjsxxx.getSrQtsr().toString());
            if (camsZcsxGrpjsxxx.getSrHj() != null)
                famerExportWord.setInTotal(camsZcsxGrpjsxxx.getSrHj().toString());

            //支出
            if (camsZcsxGrpjsxxx.getZcJy() != null)
                famerExportWord.setEducation(camsZcsxGrpjsxxx.getZcJy().toString());
            if (camsZcsxGrpjsxxx.getZcYl() != null)
                famerExportWord.setMedical(camsZcsxGrpjsxxx.getZcYl().toString());
            if (camsZcsxGrpjsxxx.getZcYlao() != null)
                famerExportWord.setPension(camsZcsxGrpjsxxx.getZcYlao().toString());
            if (camsZcsxGrpjsxxx.getZcSccb() != null)
                famerExportWord.setProducation(camsZcsxGrpjsxxx.getZcSccb().toString());
            if (camsZcsxGrpjsxxx.getZcRcsh() != null)
                famerExportWord.setLiving(camsZcsxGrpjsxxx.getZcRcsh().toString());
            if (camsZcsxGrpjsxxx.getZcQtzc() != null)
                famerExportWord.setOutOther(camsZcsxGrpjsxxx.getZcQtzc().toString());
            if (camsZcsxGrpjsxxx.getZcHj() != null)
                famerExportWord.setOutTotal(camsZcsxGrpjsxxx.getZcHj().toString());
            if (camsZcsxGrpjsxxx.getJtjsr() != null)
                famerExportWord.setNetIncome(camsZcsxGrpjsxxx.getJtjsr().toString());

            //我行情况
            QueryWrapper bc = new QueryWrapper();
            bc.eq("HHBM",hhbm);
            bc.eq("ZJHM",zjhm);
            CamsZcsxGrpjsxxxBc camsZcsxGrpjsxxxBc = camsZcsxGrpjsxxxBcMapper.selectOne(bc);
            if (camsZcsxGrpjsxxxBc == null)
                ivKhglKrkhglService.prepare(zjhm,hhbm);
            camsZcsxGrpjsxxxBc = camsZcsxGrpjsxxxBcMapper.selectOne(bc);
            if (camsZcsxGrpjsxxxBc != null){
                if (camsZcsxGrpjsxxxBc.getDhqye() != null)
                    famerExportWord.setRemain(camsZcsxGrpjsxxxBc.getDhqye().toString());
                if (camsZcsxGrpjsxxxBc.getCkrp() != null)
                    famerExportWord.setAvgday(camsZcsxGrpjsxxxBc.getCkrp().toString());
            }

            if (StringUtils.isNoneBlank(camsZcsxGrpjsxxx.getShsySfxsfz())){
                if (camsZcsxGrpjsxxx.getShsySfxsfz().equals("1")){
                    famerExportWord.setIsBreakLaw("有 √ 无口");
                }else {
                    famerExportWord.setIsBreakLaw("有口  无√");
                }
            }

            if (StringUtils.isNoneBlank(camsZcsxGrpjsxxx.getDhzpjPxpj())){
                if ("1".equals(camsZcsxGrpjsxxx.getDhzpjPxpj())) {
                    famerExportWord.setBehaviour("良好 √ 较好口 一般口 差口");
                }else if ("2".equals(camsZcsxGrpjsxxx.getDhzpjPxpj())) {
                    famerExportWord.setBehaviour("良好 口 较好√ 一般口 差口");
                }else if ("3".equals(camsZcsxGrpjsxxx.getDhzpjPxpj())) {
                    famerExportWord.setBehaviour("良好口 较好口 一般√ 差口");
                }else if ("4".equals(camsZcsxGrpjsxxx.getDhzpjPxpj())) {
                    famerExportWord.setBehaviour("良好 口 较好口 一般口 差√");
                }
            }

            if (StringUtils.isNoneBlank(camsZcsxGrpjsxxx.getDhzpjXypj())){
                if ("1".equals(camsZcsxGrpjsxxx.getDhzpjXypj())) {
                    famerExportWord.setCredit("良好 √ 较好口 一般口 差口");
                }else if ("2".equals(camsZcsxGrpjsxxx.getDhzpjXypj())) {
                    famerExportWord.setCredit("良好 口 较好√ 一般口 差口");
                }else if ("3".equals(camsZcsxGrpjsxxx.getDhzpjXypj())) {
                    famerExportWord.setCredit("良好口 较好口 一般√ 差口");
                }else if ("4".equals(camsZcsxGrpjsxxx.getDhzpjXypj())) {
                    famerExportWord.setCredit("良好 口 较好口 一般口 差√");
                }
            }

            if (StringUtils.isNoneBlank(camsZcsxGrpjsxxx.getXtpddj()))
                famerExportWord.setRateAi(iDictValueQuery.getPddjValue(camsZcsxGrpjsxxx.getXtpddj()));
            if (StringUtils.isNoneBlank(camsZcsxGrpjsxxx.getCpdj()))
                famerExportWord.setRateInit(iDictValueQuery.getPddjValue(camsZcsxGrpjsxxx.getCpdj()));
            if (StringUtils.isNoneBlank(camsZcsxGrpjsxxx.getZzpddj()))
                famerExportWord.setReRate(iDictValueQuery.getPddjValue(camsZcsxGrpjsxxx.getZzpddj()));
            if (camsZcsxGrpjsxxx.getZzsxed() != null)
                famerExportWord.setCreditFinal(camsZcsxGrpjsxxx.getZzsxed().toString());

            if (camsZcsxGrpjsxxx.getQydm() != null){
                QueryWrapper sj = new QueryWrapper();
                sj.eq("DYBH",camsZcsxGrpjsxxx.getQydm());
                Sjyxdygl sjyxdygl = sjyxdyglMapper.selectOne(sj);
                if (sjyxdygl != null){
                    if (StringUtils.isNoneBlank(sjyxdygl.getDymc()))
                        famerExportWord.setGroupVal(sjyxdygl.getDymc());
                    if (StringUtils.isNoneBlank(sjyxdygl.getEjyxdybh())){
                        famerExportWord.setVillageVal(iDictValueQuery.getejValue(sjyxdygl.getEjyxdybh()));
                    }
                    if (StringUtils.isNoneBlank(sjyxdygl.getYjyxdybh())){
                        famerExportWord.setTownVal(iDictValueQuery.getyjValue(sjyxdygl.getYjyxdybh()));
                    }
                }
            }


        }
        return famerExportWord;
    }

    @Override
    public int updateFp(String zjhm, String dj, Double je,String name) {
        return baseMapper.updateFp(zjhm,dj,je,name);
    }

    @Override
    public List<LyNewNsVO> getByWgbh(String wgbh) {
        return baseMapper.getByWgbh(wgbh);
    }

    @Override
    public List<LyNewNsVO> getBySszh(String zzbz) {
        return baseMapper.getBySszh(zzbz);
    }

    @Override
    public String getRequestBody(CamsZcsxGrpjsxxxSpjl camsZcsxGrpjsxxxSpjl) {
        SlSHnkdVO slSHnkdVO = new SlSHnkdVO();
        if (StringUtils.isNotBlank(camsZcsxGrpjsxxxSpjl.getKhmc())){
            slSHnkdVO.setCustName(camsZcsxGrpjsxxxSpjl.getKhmc());
        }

        if (StringUtils.isNotBlank(camsZcsxGrpjsxxxSpjl.getZjhm())){
            slSHnkdVO.setCertNo(camsZcsxGrpjsxxxSpjl.getZjhm());
        }

        if (camsZcsxGrpjsxxxSpjl.getZzsxed() != null){
            slSHnkdVO.setCreditValue(camsZcsxGrpjsxxxSpjl.getZzsxed().multiply(new BigDecimal(10000))+"");
        }

        //todo jd

        String sskhjl = null;
        if (StringUtils.isNotBlank(camsZcsxGrpjsxxxSpjl.getSskhjl())){
            slSHnkdVO.setCustManagerId(camsZcsxGrpjsxxxSpjl.getSskhjl());
        }

        //todo mz
        //todo 乡镇
        //todo


        return null;
    }
}
