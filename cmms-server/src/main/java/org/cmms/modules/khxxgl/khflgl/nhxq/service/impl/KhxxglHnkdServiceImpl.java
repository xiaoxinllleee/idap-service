package org.cmms.modules.khxxgl.khflgl.nhxq.service.impl;

import cn.hutool.core.util.RandomUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.system.util.JavaToXmlUtil;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.RedisUtil;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.*;
import org.cmms.modules.khxxgl.khflgl.nhxq.mapper.KhxxglHnkdMapper;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.IKhxxglHnkdService;
import org.cmms.modules.system.entity.SysUser;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 惠农快贷
 * @Author: jeecg-boot
 * @Date:   2022-01-07
 * @Version: V1.0
 */
@Service
public class KhxxglHnkdServiceImpl extends ServiceImpl<KhxxglHnkdMapper, KhxxglHnkd> implements IKhxxglHnkdService {
    @Autowired
    ISysUserService sysUserService;
    @Autowired
    RedisUtil redisUtil;

    @Autowired
    private ISysDictService sysDictService;
    @Override
    public void updateHongMingDan() {
        baseMapper.updateHongMingDan();
    }

    @Override
    public String hnkdMessage(KhxxglHnkd khxxglHnkd) {
        LoginUser principal = (LoginUser)SecurityUtils.getSubject().getPrincipal();
        String qydm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + principal.getUsername());
        FXED0205 fxed0205 = new FXED0205();
        FXED0205Header header = new FXED0205Header();
        FXED0205Request request = new FXED0205Request();

        SlSHnkdVO slSHnkdVO = new SlSHnkdVO();
        if (StringUtils.isNotBlank(khxxglHnkd.getKhmc()))
            slSHnkdVO.setCustName(khxxglHnkd.getKhmc());
        if (StringUtils.isNotBlank(khxxglHnkd.getZjhm()))
            slSHnkdVO.setCertNo(khxxglHnkd.getZjhm());
        //授信额度 * 10000
        if (khxxglHnkd.getSxed() != null){
            BigDecimal sxed = khxxglHnkd.getSxed();
            slSHnkdVO.setCreditValue(sxed.toString());
        }

        if (khxxglHnkd.getJtnsr() != null){
            slSHnkdVO.setFamEcnmIncmAmt(khxxglHnkd.getJtnsr().toString());
        }
        if (khxxglHnkd.getGrnsr() != null){
            slSHnkdVO.setPrsnlEcnmIncmAmt(khxxglHnkd.getGrnsr().toString());
        }

        if (khxxglHnkd.getJd() != null){
            slSHnkdVO.setAppRate(khxxglHnkd.getJd().toString());
        }
        if (StringUtils.isNotBlank(khxxglHnkd.getKhfq())){
            slSHnkdVO.setCustSort(khxxglHnkd.getKhfq());
        }
        if (StringUtils.isNotBlank(khxxglHnkd.getKhjl())){
            slSHnkdVO.setCustManagerId(khxxglHnkd.getKhjl());
        }
        if (StringUtils.isNotBlank(khxxglHnkd.getMz())){
            slSHnkdVO.setNationality(khxxglHnkd.getMz());
        }
        if (StringUtils.isNotBlank(khxxglHnkd.getKhlx1())){
            slSHnkdVO.setCstTp1(khxxglHnkd.getKhlx1());
        }
        if (StringUtils.isNotBlank(khxxglHnkd.getKhlx2())){
            slSHnkdVO.setCstTp2(khxxglHnkd.getKhlx2());
        }
        if (StringUtils.isNotBlank(khxxglHnkd.getSsxz())){
            System.out.println(khxxglHnkd.getSsxz());
            String s = sysDictService.queryDictTextByKey(HnkdConstant.hnkd_xz + qydm, khxxglHnkd.getSsxz());
            System.out.println(s);
            if (StringUtils.isNotBlank(s))
                slSHnkdVO.setTwnNm(s);
        }
        if (StringUtils.isNotBlank(khxxglHnkd.getYwzn())){
            slSHnkdVO.setChlInd(khxxglHnkd.getYwzn());
        }
        if (StringUtils.isNotBlank(khxxglHnkd.getJkzk())){
            slSHnkdVO.setHltSt(khxxglHnkd.getJkzk());
        }
        if (StringUtils.isNotBlank(khxxglHnkd.getHyzk())){
            slSHnkdVO.setMrrgSt(khxxglHnkd.getHyzk());
        }
        if (StringUtils.isNotBlank(khxxglHnkd.getPoxm())){
            slSHnkdVO.setAuthorizeCustName(khxxglHnkd.getPoxm());
        }
        if (StringUtils.isNotBlank(khxxglHnkd.getPozjhm())){
            slSHnkdVO.setAuthorizeCertType(khxxglHnkd.getPozjhm());
        }
        if (khxxglHnkd.getJtrk() != null){
            slSHnkdVO.setFamNum(khxxglHnkd.getJtrk()+"");
        }
        if (StringUtils.isNotBlank(khxxglHnkd.getLdnl())){
            slSHnkdVO.setLbrSt(khxxglHnkd.getLdnl());
        }
        if (StringUtils.isNotBlank(khxxglHnkd.getJznx())){
            slSHnkdVO.setRsdnYrTm(khxxglHnkd.getJznx());
        }
        if (StringUtils.isNotBlank(khxxglHnkd.getJzzk())){
            slSHnkdVO.setRsdnSt(khxxglHnkd.getJzzk());
        }
        if (StringUtils.isNotBlank(khxxglHnkd.getZgxl())){
            slSHnkdVO.setHighEdctDgrCd(khxxglHnkd.getZgxl());
        }
        if (StringUtils.isNotBlank(khxxglHnkd.getCzdz())){
            slSHnkdVO.setComAdr(khxxglHnkd.getCzdz());
        }
        if (StringUtils.isNotBlank(khxxglHnkd.getTxdz())){
            slSHnkdVO.setCtcAdr(khxxglHnkd.getTxdz());
        }
        if (StringUtils.isNotBlank(khxxglHnkd.getXzqhdm())){
            slSHnkdVO.setAdmnDivCd(khxxglHnkd.getXzqhdm());
        }
        if (StringUtils.isNotBlank(khxxglHnkd.getZdyzbm())){
            slSHnkdVO.setPstNo(khxxglHnkd.getZdyzbm());
        }
        if (StringUtils.isNotBlank(khxxglHnkd.getSjhm())){
            slSHnkdVO.setMblNo(khxxglHnkd.getSjhm());
        }
        if (StringUtils.isNotBlank(khxxglHnkd.getSfhz())){
            slSHnkdVO.setHsHldrInd(khxxglHnkd.getSfhz());
        }
        if (StringUtils.isNotBlank(khxxglHnkd.getSffsdx())){
            slSHnkdVO.setMsgNtcInd(khxxglHnkd.getSffsdx());
        }
        if (StringUtils.isNotBlank(khxxglHnkd.getXgzdw())){
            slSHnkdVO.setWrkUnitNm(khxxglHnkd.getXgzdw());
        }
        if (StringUtils.isNotBlank(khxxglHnkd.getXdrzw())){
            slSHnkdVO.setDutyCd(khxxglHnkd.getXdrzw());
        }
        if (StringUtils.isNotBlank(khxxglHnkd.getHy())){
            slSHnkdVO.setIdyClTp(khxxglHnkd.getHy());
        }else {
            int i1 = RandomUtil.randomInt(0, 6);
            if (i1 == 0) {
                slSHnkdVO.setIdyClTp("A0111");
            } else if (i1 == 2) {
                slSHnkdVO.setIdyClTp("A0143");
            } else if (i1 == 3) {
                slSHnkdVO.setIdyClTp("A0159");
            } else if (i1 == 4) {
                slSHnkdVO.setIdyClTp("A0122");
            } else if (i1 == 5) {
                slSHnkdVO.setIdyClTp("A0313");
            } else {
                slSHnkdVO.setIdyClTp("A0412");
            }
        }
        if (StringUtils.isNotBlank(khxxglHnkd.getJyqk())){
            slSHnkdVO.setOprStRglrInd(khxxglHnkd.getJyqk());
        }
        if (StringUtils.isNotBlank(khxxglHnkd.getScxu())){
            slSHnkdVO.setPdMktRqmSt(khxxglHnkd.getScxu());
        }
        if (StringUtils.isNotBlank(khxxglHnkd.getHkyy())){
            slSHnkdVO.setRepyWillTp(khxxglHnkd.getHkyy());
        }
        if (StringUtils.isNotBlank(khxxglHnkd.getZy())){
            slSHnkdVO.setOcpCd(khxxglHnkd.getZy());
        }
        if (StringUtils.isNotBlank(khxxglHnkd.getZc())){
            slSHnkdVO.setProfTtlCd(khxxglHnkd.getZc());
        }
        if (StringUtils.isNotBlank(khxxglHnkd.getCynx())){
            slSHnkdVO.setWrkYrTm(khxxglHnkd.getCynx());
        }
        if (StringUtils.isNotBlank(khxxglHnkd.getZhpj())){
            slSHnkdVO.setCmprhEvlInd(khxxglHnkd.getZhpj());
        }

        if (StringUtils.isNotBlank(khxxglHnkd.getGtgshmc())){
            slSHnkdVO.setIdvMrchNm(khxxglHnkd.getGtgshmc());
        }
        if (StringUtils.isNotBlank(khxxglHnkd.getXydm())){
            slSHnkdVO.setSocCrCd(khxxglHnkd.getXydm());
        }
        if (StringUtils.isNotBlank(khxxglHnkd.getXhbz())){
            slSHnkdVO.setRvlvInd(khxxglHnkd.getXhbz());
        }
        if (StringUtils.isNotBlank(khxxglHnkd.getXydj())){
            slSHnkdVO.setCrLvl(khxxglHnkd.getXydj());
        }
        if (StringUtils.isNotBlank(khxxglHnkd.getHjdz())){
            slSHnkdVO.setHouseAdr(khxxglHnkd.getHjdz());
        }
        if (StringUtils.isNotBlank(khxxglHnkd.getHjyzbm())){
            slSHnkdVO.setDomcAreaAdr(khxxglHnkd.getHjyzbm());
        }
        if (StringUtils.isNotBlank(khxxglHnkd.getTxdzyb())){
            slSHnkdVO.setCtcAdrPstNo(khxxglHnkd.getTxdzyb());
        }
        if (StringUtils.isNotBlank(khxxglHnkd.getTxdzyb())){
            slSHnkdVO.setCtcAdrPstNo(khxxglHnkd.getTxdzyb());
        }
        //就业情况
        if (StringUtils.isNotBlank(khxxglHnkd.getJyzk())){
            slSHnkdVO.setEmpySt(khxxglHnkd.getJyzk());
        }

        if (StringUtils.isNotBlank(khxxglHnkd.getDwxz())){
            slSHnkdVO.setUnitCharTp(khxxglHnkd.getDwxz());
        }

        if (StringUtils.isNotBlank(khxxglHnkd.getZgxw())){
            slSHnkdVO.setHighDgrCd(khxxglHnkd.getZgxw());
        }

        List<ChrgPrsnArry> list = new ArrayList<>();
        ChrgPrsnArry arry = new ChrgPrsnArry();
        arry.setChrgPrsnTp("5");
        if (StringUtils.isNotBlank(khxxglHnkd.getDczrr1()))
            arry.setChrgPrsnIdntNo(khxxglHnkd.getDczrr1());
        if (khxxglHnkd.getDczrr1bl()!=null)
            arry.setChrgPrsnPct(khxxglHnkd.getDczrr1bl().toString());

        ChrgPrsnArry arry2 = new ChrgPrsnArry();
        arry2.setChrgPrsnTp("5");
        if (StringUtils.isNotBlank(khxxglHnkd.getDczrr2()))
            arry2.setChrgPrsnIdntNo(khxxglHnkd.getDczrr2());
        if (khxxglHnkd.getDczrr2bl() != null)
            arry2.setChrgPrsnPct(khxxglHnkd.getDczrr2bl().toString());

        ChrgPrsnArry arry3 = new ChrgPrsnArry();
        arry3.setChrgPrsnTp("1");
        if (StringUtils.isNotBlank(khxxglHnkd.getGlzrr()))
            arry3.setChrgPrsnIdntNo(khxxglHnkd.getGlzrr());
        if (khxxglHnkd.getGlzrrbl() != null)
            arry3.setChrgPrsnPct(khxxglHnkd.getGlzrrbl().toString());

        ChrgPrsnArry arry4 = new ChrgPrsnArry();
        arry4.setChrgPrsnTp("3");
        if (StringUtils.isNotBlank(khxxglHnkd.getSczrr()))
            arry4.setChrgPrsnIdntNo(khxxglHnkd.getSczrr());
        if (khxxglHnkd.getSczrrbl() != null)
            arry4.setChrgPrsnPct(khxxglHnkd.getSczrrbl().toString());

        ChrgPrsnArry arry5 = new ChrgPrsnArry();
        arry5.setChrgPrsnTp("41");
        if (StringUtils.isNotBlank(khxxglHnkd.getZzspzrr()))
            arry5.setChrgPrsnIdntNo(khxxglHnkd.getZzspzrr());
        if (khxxglHnkd.getZzspzrrbl() != null)
            arry5.setChrgPrsnPct(khxxglHnkd.getZzspzrrbl().toString());
        ChrgPrsnArry arry6 = new ChrgPrsnArry();
        arry6.setChrgPrsnTp("11");
        if (StringUtils.isNotBlank(khxxglHnkd.getDyzrr()))
            arry6.setChrgPrsnIdntNo(khxxglHnkd.getDyzrr());
        if (khxxglHnkd.getDyzrrbl() != null)
            arry6.setChrgPrsnPct(khxxglHnkd.getDyzrrbl().toString());
        list.add(arry);
        list.add(arry2);
        list.add(arry3);
        list.add(arry4);
        list.add(arry5);
        list.add(arry6);
        slSHnkdVO.setChrgPrsnArry(list);

        List<ApproavlTrailArry> trailArryList = new ArrayList<>();
        ApproavlTrailArry approavlTrailArry = new ApproavlTrailArry();
        if (StringUtils.isNotBlank(khxxglHnkd.getKhjl())){
            approavlTrailArry.setApproavlId(khxxglHnkd.getKhjl());
        }

        ApproavlTrailArry approavlTrailArry2 = new ApproavlTrailArry();
        if ( StringUtils.isNotBlank(khxxglHnkd.getSszh())){
            String sszh = khxxglHnkd.getSszh();
            Object o = redisUtil.get(sszh + "fxjl");
            if (o != null){
                approavlTrailArry2.setApproavlId(o.toString());
            }else {
                SysUser fxjl = sysUserService.findIdapUserByRoleIdAndSszh("fxjl", sszh);
                //可以缓存
                if (fxjl != null){
                    approavlTrailArry2.setApproavlId(fxjl.getWorkNo());
                    redisUtil.set(sszh+"fxjl",fxjl.getWorkNo());
                }
            }
        }
        ApproavlTrailArry approavlTrailArry3 = new ApproavlTrailArry();
        if ( StringUtils.isNotBlank(khxxglHnkd.getSszh())){
            String sszh = khxxglHnkd.getSszh();
            Object o = redisUtil.get(sszh + "ZHHZ");
            if (o != null){
                approavlTrailArry3.setApproavlId(o.toString());
            }else {
                SysUser zhhz = sysUserService.findIdapUserByRoleIdAndSszh("ZHHZ", sszh);
                //可以缓存
                if (zhhz != null){
                    approavlTrailArry3.setApproavlId(zhhz.getWorkNo());
                    redisUtil.set(sszh+"ZHHZ",zhhz.getWorkNo());
                }
            }
        }
        trailArryList.add(approavlTrailArry);
        trailArryList.add(approavlTrailArry2);
        trailArryList.add(approavlTrailArry3);

        slSHnkdVO.setApproavlTrailArry(trailArryList);
        request.setRequest(slSHnkdVO);
        fxed0205.setHeader(header);
        fxed0205.setBody(request);

        try {
            String s = JavaToXmlUtil.javaToxml(fxed0205);
            return s;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void updateResult(String id, String impResult) {
        baseMapper.updateResult(id,impResult);
    }
}
