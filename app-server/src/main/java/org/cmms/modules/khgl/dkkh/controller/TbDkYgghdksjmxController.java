package org.cmms.modules.khgl.dkkh.controller;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.*;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.handler.IFillRuleHandler;
import org.cmms.common.util.encryption.RSAEncryptUtil;
import org.cmms.config.RequestDataHelper;
import org.cmms.modules.appbase.datatime.service.IBasDataJobDaysService;
import org.cmms.modules.khgl.dkkh.entity.*;
import org.cmms.modules.khgl.dkkh.service.IAppDkkhGzListService;
import org.cmms.modules.khgl.dkkh.service.IKhgxglDkkhghlsbService;
import org.cmms.modules.khgl.dkkh.service.ITbDkYgbsdksjmxService;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.khgl.dkkh.service.ITbDkYgghdksjmxService;
import org.cmms.modules.khgxgl.entity.KhgxglDkkhxxgl;
import org.cmms.modules.khgxgl.entity.KhgxglKhzlglGrkh;
import org.cmms.modules.khgxgl.entity.KhgxglKhzlglQykh;
import org.cmms.modules.khgxgl.service.IKhgxglDkkhxxglService;
import org.cmms.modules.khgxgl.service.IKhgxglKhzlglGrkhService;

import org.cmms.modules.khgxgl.service.IKhgxglKhzlglQykhService;
import org.cmms.modules.system.service.ISysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 员工管户贷款数据明细
 * @Author: jeecg-boot
 * @Date: 2022-03-09
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "员工管户贷款数据明细")
@RestController
@RequestMapping("/dkkh/tbDkYgghdksjmx")
public class TbDkYgghdksjmxController extends JeecgController<TbDkYgghdksjmx, ITbDkYgghdksjmxService> {

    public static String TB_DK_YGGHDKSJMX_TABLENAME = "TB_DK_YGGHDKSJMX";
    public static String TB_DK_YGBSDKSJMX_TABLENAME = "TB_DK_YGBSDKSJMX";
    @Autowired
    private IKhgxglKhzlglGrkhService khgxglKhzlglGrkhService;
    @Autowired
    private IKhgxglKhzlglQykhService khgxglKhzlglQykhService;
    @Autowired
    IBasDataJobDaysService basDataJobDaysService;
    @Autowired
    ITbDkYgbsdksjmxService tbDkYgbsdksjmxService;
    @Autowired
    private ISysDictService dictService;
    @Autowired
    private IKhgxglDkkhxxglService khgxglDkkhxxglService;
    @Autowired
    private IAppDkkhGzListService appDkkhGzListService;
    @Autowired
    IKhgxglDkkhghlsbService khgxglDkkhghlsbService;

    @AutoLog(value = "员工管户贷款数据明细-分页列表查询")
    @ApiOperation(value = "员工管户贷款数据明细-分页列表查询", notes = "员工管户贷款数据明细-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(String custType, String wjfl, int indexTab, String zjhm,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        Date maxExtDay = basDataJobDaysService.getMaxExtDay();
        String yyMMdd = DateUtil.format(maxExtDay, "yyMMdd");
        Map<String, Object> requestData = new HashMap<>();
        requestData.put(TB_DK_YGGHDKSJMX_TABLENAME, TB_DK_YGGHDKSJMX_TABLENAME + "_" + yyMMdd);
        requestData.put(TB_DK_YGBSDKSJMX_TABLENAME, TB_DK_YGBSDKSJMX_TABLENAME + "_" + yyMMdd);
        RequestDataHelper.setRequestData(requestData);

        Page<String> page = new Page<String>(pageNo, pageSize);

        IPage<String> zjhms2 = null;
        if (indexTab == 1) {
            zjhms2 = service.getZjhms2(page, getWorkNo(), custType, wjfl, zjhm);
        } else if (indexTab == 2) {
            zjhms2 = appDkkhGzListService.getZjhms(page, getWorkNo(), custType, wjfl, zjhm);
        }
        List<String> zjhms = zjhms2.getRecords();

        List<DkkhVO> result = new ArrayList<>();
        if (CollUtil.isNotEmpty(zjhms)) {
            for (int i = 0; i < zjhms.size(); i++) {
                DkkhVO dkkhVO = new DkkhVO();
                zjhm = zjhms.get(i);
                dkkhVO.setZjhm(zjhm);
                dkkhVO.setZjhmjm(zjhm);

                boolean gz = appDkkhGzListService.isGz(zjhm, getWorkNo());
                if (gz)
                    dkkhVO.setIsGz("1");


                //查出此证件号码在管户表中的所有字段进行操作 需要统计出 贷款金额 贷款余额 最大的贷款类型 最近发放 最近到期 贷款类型 不良余额
                LambdaQueryWrapper<TbDkYgghdksjmx> lambdaQueryWrapper = new LambdaQueryWrapper<>();
                lambdaQueryWrapper.eq(TbDkYgghdksjmx::getZjhm, zjhm);
                List<TbDkYgghdksjmx> list = service.list(lambdaQueryWrapper);
                if (CollUtil.isNotEmpty(list)) {
                    for (int j = 0; j < list.size(); j++) {
                        TbDkYgghdksjmx tbDkYgghdksjmx = list.get(j);

                        if (StringUtils.isNotBlank(tbDkYgghdksjmx.getCustType())) {
                            dkkhVO.appendDklx(tbDkYgghdksjmx.getCustType());
                        }

                        if (StringUtils.isBlank(dkkhVO.getJgdm())) {
                            if (StringUtils.isNotBlank(tbDkYgghdksjmx.getJgdm())) {
                                dkkhVO.setJgdm(tbDkYgghdksjmx.getJgdm());
                                String s = dictService.queryTableDictTextByKey("HR_BAS_ORGANIZATION", "zzjc", "ywjgdm", tbDkYgghdksjmx.getJgdm());
                                dkkhVO.setJgdmVal(s);
                            }
                        }


                        if (StringUtils.isNotBlank(tbDkYgghdksjmx.getFiveClassType())) {

                            if (tbDkYgghdksjmx.getDkye() != null) {
                                dkkhVO.maxFiveClassType(tbDkYgghdksjmx.getFiveClassType(), tbDkYgghdksjmx.getDkye());
                            } else {
                                dkkhVO.maxFiveClassType(tbDkYgghdksjmx.getFiveClassType(), new BigDecimal(0));
                            }

                            //字典 五级分类
                            String wjflbz = dictService.queryDictTextByKey("wjflbz", tbDkYgghdksjmx.getFiveClassType());
                            dkkhVO.setFiveClassTypeVal(wjflbz);
                        }


                        if (tbDkYgghdksjmx.getDkje() != null)
                            dkkhVO.hjdkje(tbDkYgghdksjmx.getDkje());

                        if (tbDkYgghdksjmx.getDkye() != null)
                            dkkhVO.hjdkye(tbDkYgghdksjmx.getDkye());

                        if (tbDkYgghdksjmx.getFfrq() != null)
                            dkkhVO.maxFfrq(tbDkYgghdksjmx.getFfrq());

                        if (tbDkYgghdksjmx.getDqrq() != null)
                            dkkhVO.maxDqrq(tbDkYgghdksjmx.getDqrq());

                        if (StringUtils.isNotBlank(tbDkYgghdksjmx.getCustName()))
                            dkkhVO.setKhmc(tbDkYgghdksjmx.getCustName());

                    }

                }

                //查贷款类型
                String allCpxxByZjhm = khgxglDkkhxxglService.getAllCpxxByZjhm(zjhm);
                dkkhVO.setCpxx(allCpxxByZjhm);

                //根据贷款类型查询
                if (StringUtils.isNotBlank(dkkhVO.getDklx())) {
                    if ("个人".equals(dkkhVO.getDklx())) {
                        LambdaQueryWrapper<KhgxglKhzlglGrkh> khgxglKhzlglGrkhLambdaQueryWrapper = new LambdaQueryWrapper<>();
                        khgxglKhzlglGrkhLambdaQueryWrapper.eq(KhgxglKhzlglGrkh::getZjhm, zjhm);
                        if (StringUtils.isNotBlank(getRedisUserJgdm())){
                            khgxglKhzlglGrkhLambdaQueryWrapper.eq(KhgxglKhzlglGrkh::getJgdm,getRedisUserJgdm());
                        }
                        List<KhgxglKhzlglGrkh> khgxglKhzlglGrkhs = khgxglKhzlglGrkhService.list(khgxglKhzlglGrkhLambdaQueryWrapper);
                        if (CollUtil.isNotEmpty(khgxglKhzlglGrkhs)) {
                            KhgxglKhzlglGrkh khgxglKhzlglGrkh = khgxglKhzlglGrkhs.get(0);
                            dkkhVO.setKhgxglKhzlglGrkh(khgxglKhzlglGrkh);
                            if (StringUtils.isNotBlank(khgxglKhzlglGrkh.getSjhm())) {
                                dkkhVO.setSjhm(khgxglKhzlglGrkh.getSjhm());
                                dkkhVO.setSjhmrsa(khgxglKhzlglGrkh.getSjhm());
                            }
                            if (StringUtils.isNotBlank(khgxglKhzlglGrkh.getZz())) {
                                dkkhVO.setZz(khgxglKhzlglGrkh.getZz());
                            }
                            if (StringUtils.isNotBlank(khgxglKhzlglGrkh.getCustId())) {
                                dkkhVO.setCustId(khgxglKhzlglGrkh.getCustId());
                            }
                        }
                    }
                    if ("对公".equals(dkkhVO.getDklx())) {
                        LambdaQueryWrapper<KhgxglKhzlglQykh> khgxglKhzlglGrkhLambdaQueryWrapper = new LambdaQueryWrapper<>();
                        khgxglKhzlglGrkhLambdaQueryWrapper.eq(KhgxglKhzlglQykh::getZjhm, zjhm);
                        List<KhgxglKhzlglQykh> list1 = khgxglKhzlglQykhService.list(khgxglKhzlglGrkhLambdaQueryWrapper);
                        if (CollUtil.isNotEmpty(list1)){
                            KhgxglKhzlglQykh khgxglKhzlglQykh = list1.get(0);
                            dkkhVO.setKhgxglKhzlglQykh(khgxglKhzlglQykh);
                            if (StringUtils.isNotBlank(khgxglKhzlglQykh.getFrlxfs())) {
                                dkkhVO.setSjhm(khgxglKhzlglQykh.getFrlxfs());
                            }
                            if (StringUtils.isNotBlank(khgxglKhzlglQykh.getTxdz())) {
                                dkkhVO.setZz(khgxglKhzlglQykh.getTxdz());
                            }
                            if (StringUtils.isNotBlank(khgxglKhzlglQykh.getCustId())) {
                                dkkhVO.setCustId(khgxglKhzlglQykh.getCustId());
                            }
                        }
                    }
                }

                dkkhVO.jssex();
                result.add(dkkhVO);
            }

        }
        return Result.ok(result);
    }




	 /*@GetMapping(value = "/list")
	 public Result<?> queryPageList(String custType,String wjfl,int indexTab,String zjhm,
									@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									@RequestParam(name="pageSize", defaultValue="10") Integer pageSize
	 ) {
		 Date maxExtDay = basDataJobDaysService.getMaxExtDay();
		 String yyMMdd = DateUtil.format(maxExtDay, "yyMMdd");
		 Map<String, Object> requestData = new HashMap<>();
		 requestData.put(TB_DK_YGGHDKSJMX_TABLENAME,TB_DK_YGGHDKSJMX_TABLENAME+"_"+yyMMdd);
		 requestData.put(TB_DK_YGBSDKSJMX_TABLENAME,TB_DK_YGBSDKSJMX_TABLENAME+"_"+yyMMdd);
		 RequestDataHelper.setRequestData(requestData);

		 Page<String> page = new Page<String>(pageNo, pageSize);

		 IPage<String> zjhms2 = null;
		 if (indexTab == 1){
			 zjhms2 = service.getZjhms2(page, getWorkNo(), custType, wjfl,zjhm);
		 }else if (indexTab == 2){
			 zjhms2 = appDkkhGzListService.getZjhms(page,getWorkNo(),custType,wjfl,zjhm);
		 }
		 List<String> zjhms = zjhms2.getRecords();

		 List<DkkhVO> result = new ArrayList<>();
		 if (CollUtil.isNotEmpty(zjhms)){
			 for (int i = 0; i < zjhms.size(); i++) {
				 DkkhVO dkkhVO = new DkkhVO();
				 zjhm = zjhms.get(i);
				 dkkhVO.setZjhm(zjhm);
				 dkkhVO.jssex();
				 boolean gz = appDkkhGzListService.isGz(zjhm, getWorkNo());
				 if (gz)
					 dkkhVO.setIsGz("1");
				 //个人客户信息
				 LambdaQueryWrapper<KhgxglKhzlglGrkh> khgxglKhzlglGrkhLambdaQueryWrapper = new LambdaQueryWrapper<>();
				 khgxglKhzlglGrkhLambdaQueryWrapper.eq(KhgxglKhzlglGrkh::getZjhm,zjhm);
				 List<KhgxglKhzlglGrkh> khgxglKhzlglGrkhs = khgxglKhzlglGrkhService.list(khgxglKhzlglGrkhLambdaQueryWrapper);
				 if (CollUtil.isNotEmpty(khgxglKhzlglGrkhs)){
					 KhgxglKhzlglGrkh khgxglKhzlglGrkh = khgxglKhzlglGrkhs.get(0);
					 if (StringUtils.isNotBlank(khgxglKhzlglGrkh.getSjhm())){
						 dkkhVO.setSjhm(khgxglKhzlglGrkh.getSjhm());
					 }
					 if (StringUtils.isNotBlank(khgxglKhzlglGrkh.getZz())){
						 dkkhVO.setZz(khgxglKhzlglGrkh.getZz());
					 }
					 if (StringUtils.isNotBlank(khgxglKhzlglGrkh.getKhbh())){
						 dkkhVO.setKhbh(khgxglKhzlglGrkh.getKhbh());
					 }
					 if (StringUtils.isNotBlank(khgxglKhzlglGrkh.getCustId())){
						 dkkhVO.setCustId(khgxglKhzlglGrkh.getCustId());
					 }
				 }


				 //查出此证件号码在管户表中的所有字段进行操作 需要统计出 贷款金额 贷款余额 最大的贷款类型 最近发放 最近到期 贷款类型 不良余额
				 LambdaQueryWrapper<TbDkYgghdksjmx> lambdaQueryWrapper = new LambdaQueryWrapper<>();
				 lambdaQueryWrapper.eq(TbDkYgghdksjmx::getZjhm, zjhm);
				 List<TbDkYgghdksjmx> list = service.list(lambdaQueryWrapper);
				 if (CollUtil.isNotEmpty(list)){
					 List<TbDkYgghdksjmxVO> tbDkYgghdksjmxVOS = new ArrayList<>();
					 for (int j = 0; j < list.size(); j++) {
						 TbDkYgghdksjmx tbDkYgghdksjmx = list.get(j);
						 TbDkYgghdksjmxVO tbDkYgghdksjmxVO = new TbDkYgghdksjmxVO();
						 tbDkYgghdksjmxVO.setTbDkYgghdksjmx(tbDkYgghdksjmx);

						 if (StringUtils.isNotBlank(tbDkYgghdksjmx.getCustType())){
							 dkkhVO.appendDklx(tbDkYgghdksjmx.getCustType());
						 }

						 if (StringUtils.isNotBlank(tbDkYgghdksjmx.getFiveClassType())){

							 if (tbDkYgghdksjmx.getDkye() != null){
								 dkkhVO.maxFiveClassType(tbDkYgghdksjmx.getFiveClassType(),tbDkYgghdksjmx.getDkye());
							 }else {
								 dkkhVO.maxFiveClassType(tbDkYgghdksjmx.getFiveClassType(),new BigDecimal(0));
							 }

							 //字典 五级分类
							 String wjflbz = dictService.queryDictTextByKey("wjflbz", tbDkYgghdksjmx.getFiveClassType());
							 tbDkYgghdksjmxVO.setFiveClassTypeVal(wjflbz);
							 dkkhVO.setFiveClassTypeVal(wjflbz);
						 }


						 if (tbDkYgghdksjmx.getDkje() != null)
							 dkkhVO.hjdkje(tbDkYgghdksjmx.getDkje());

						 if (tbDkYgghdksjmx.getDkye() != null)
							 dkkhVO.hjdkye(tbDkYgghdksjmx.getDkye());

						 if (tbDkYgghdksjmx.getFfrq() != null)
							 dkkhVO.maxFfrq(tbDkYgghdksjmx.getFfrq());

						 if (tbDkYgghdksjmx.getDqrq() != null)
							 dkkhVO.maxDqrq(tbDkYgghdksjmx.getDqrq());

						 if (StringUtils.isNotBlank(tbDkYgghdksjmx.getCustName()))
							 dkkhVO.setKhmc(tbDkYgghdksjmx.getCustName());

						 //查包收人
						 if (StringUtils.isNotBlank(tbDkYgghdksjmx.getHth())){
							LambdaQueryWrapper<TbDkYgbsdksjmx> tbDkYgbsdksjmxLambdaQueryWrapper = new LambdaQueryWrapper<>();
							tbDkYgbsdksjmxLambdaQueryWrapper.eq(TbDkYgbsdksjmx::getHth,tbDkYgghdksjmx.getHth());
							List<TbDkYgbsdksjmx> tbDkYgbsdksjmxes = tbDkYgbsdksjmxService.list(tbDkYgbsdksjmxLambdaQueryWrapper);
							if (CollUtil.isNotEmpty(tbDkYgbsdksjmxes)){
								for (int k = 0; k < tbDkYgbsdksjmxes.size(); k++) {
									TbDkYgbsdksjmx tbDkYgbsdksjmx = tbDkYgbsdksjmxes.get(k);
									if (tbDkYgbsdksjmx.getYggh() != null){
										String s = dictService.queryTableDictTextByKey("hr_bas_staff", "ygxm", "yggh", tbDkYgbsdksjmx.getYggh());
										if (StringUtils.isNotBlank(s)){
											tbDkYgghdksjmxVO.appendGhr(s);
										}
									}
								}
							}


							 //查贷款类型
							 LambdaQueryWrapper<KhgxglDkkhxxgl> khgxglDkkhxxglLambdaQueryWrapper = new LambdaQueryWrapper<>();
							 khgxglDkkhxxglLambdaQueryWrapper.eq(KhgxglDkkhxxgl::getHth,tbDkYgghdksjmx.getHth());
							 List<KhgxglDkkhxxgl> khgxglDkkhxxgls = khgxglDkkhxxglService.list(khgxglDkkhxxglLambdaQueryWrapper);
							 if (CollUtil.isNotEmpty(khgxglDkkhxxgls)){
								 for (int k = 0; k < khgxglDkkhxxgls.size(); k++) {
									 KhgxglDkkhxxgl khgxglDkkhxxgl = khgxglDkkhxxgls.get(k);
									 if (khgxglDkkhxxgl.getCpxx() != null){
										 tbDkYgghdksjmxVO.appendDklx(khgxglDkkhxxgl.getCpxx());
									 }
								 }
							 }
						 }

						 tbDkYgghdksjmxVOS.add(tbDkYgghdksjmxVO);
					 }

					 dkkhVO.setTbDkYghhdksjmxList(tbDkYgghdksjmxVOS);
				 }

				 result.add(dkkhVO);
			 }

		 }
		 return Result.ok(result);
	 }*/


    /**
     * 贷款形态监测
     * 对公 对私
     * 证件号码
     * 较年初上迁/下迁 1/2  季 3/4 月5/6 昨日 7/8
     */
    @RequestMapping("/dkxtjc")
    public Result<?> dkxtjc(String custType, String zjhm, String type, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {

        Date maxExtDay = basDataJobDaysService.getMaxExtDay();
        String yyMMdd = DateUtil.format(maxExtDay, "yyMMdd");
        String zr = DateUtil.format(DateUtil.offsetDay(maxExtDay, -1), "yyMMdd");
        final String jc = getJc(yyMMdd);

        String qmTable = TB_DK_YGGHDKSJMX_TABLENAME + "_" + yyMMdd;
        String zrTable = TB_DK_YGGHDKSJMX_TABLENAME + "_" + zr;
        String jcTable = TB_DK_YGGHDKSJMX_TABLENAME + "_" + jc;
        Page<DkxtjcVO> page = new Page<DkxtjcVO>(pageNo, pageSize);

        IPage<DkxtjcVO> dkxtjcList = service.getDkxtjcList(page, getWorkNo(), custType, type, zjhm, qmTable, jcTable, zrTable);
        return Result.ok(dkxtjcList);
    }


    public String getJc(String yyMMdd) {
        String yy = yyMMdd.substring(0, 2);
        String mm = yyMMdd.substring(2, 4);
        Integer integer = Integer.valueOf(mm);
        if (integer <= 3) {
            mm = "0101";
        } else if (integer > 3 && integer <= 6) {
            mm = "0401";
        } else if (integer > 6 && integer <= 9) {
            mm = "0701";
        } else if (integer > 9 && integer <= 12) {
            mm = "1001";
        }
        return yy + mm;
    }


    @RequestMapping("/getAllHt")
    public Result<?> getAllHt(String zjhm) {
        if (StringUtils.isNotBlank(zjhm) && zjhm.length() > 20)
            zjhm = RSAEncryptUtil.desEncrypt(zjhm.replaceAll(" ","+"));
        Date maxExtDay = basDataJobDaysService.getMaxExtDay();
        String yyMMdd = DateUtil.format(maxExtDay, "yyMMdd");
        Map<String, Object> requestData = new HashMap<>();
        requestData.put(TB_DK_YGGHDKSJMX_TABLENAME, TB_DK_YGGHDKSJMX_TABLENAME + "_" + yyMMdd);
        RequestDataHelper.setRequestData(requestData);

        List<HtlbVO> htlbsByZjhm = service.getHtlbsByZjhm(zjhm);
        for (int i = 0; i < htlbsByZjhm.size(); i++) {
            String hth = htlbsByZjhm.get(i).getHth();
            String bsrByHth = khgxglDkkhghlsbService.getBsrByHth(hth);
            htlbsByZjhm.get(i).setBsr(bsrByHth);
            String allCpxxByHth = khgxglDkkhxxglService.getAllCpxxByHth(hth);
            htlbsByZjhm.get(i).setCpxx(allCpxxByHth);
            htlbsByZjhm.get(i).formatDate();
        }

        return Result.ok(htlbsByZjhm);
    }


    @RequestMapping("/getbBnbl")
    public Result<?> getbBnbl(String zjhm) {
        if (StringUtils.isNotBlank(zjhm) && zjhm.length() > 20)
            zjhm = RSAEncryptUtil.desEncrypt(zjhm.replaceAll(" ","+"));

        Date maxExtDay = basDataJobDaysService.getMaxExtDay();
        String yyMMdd = DateUtil.format(maxExtDay, "yyMMdd");
        Map<String, Object> requestData = new HashMap<>();
        requestData.put(TB_DK_YGGHDKSJMX_TABLENAME, TB_DK_YGGHDKSJMX_TABLENAME + "_" + yyMMdd);
        RequestDataHelper.setRequestData(requestData);

        LambdaQueryWrapper<TbDkYgghdksjmx> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        ArrayList<Integer> list = new ArrayList();
        list.add(3);
        list.add(4);
        list.add(5);
        lambdaQueryWrapper.eq(TbDkYgghdksjmx::getZjhm, zjhm);
        lambdaQueryWrapper.in(TbDkYgghdksjmx::getFiveClassType, list);
        List<TbDkYgghdksjmx> result = service.list(lambdaQueryWrapper);
        List<BnblVO> bnblVOS = new ArrayList<>();
        if (CollUtil.isNotEmpty(result)) {
            for (int i = 0; i < result.size(); i++) {
                BnblVO bnblVO = new BnblVO();
                TbDkYgghdksjmx tbDkYgghdksjmx = result.get(i);
                bnblVO.init(tbDkYgghdksjmx);
                String ghlxByHth = khgxglDkkhghlsbService.getGhlxByHth(tbDkYgghdksjmx.getHth(), 2);
                String ghlxByHth2 = khgxglDkkhghlsbService.getGhlxByHth(tbDkYgghdksjmx.getHth(), 3);
                bnblVO.setGhr(ghlxByHth);
                bnblVO.setBsr(ghlxByHth2);
                bnblVOS.add(bnblVO);
            }
        }
        return Result.ok(bnblVOS);
    }
}
