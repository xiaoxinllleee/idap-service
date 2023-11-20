package org.cmms.modules.ygjx.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.handler.IFillRuleHandler;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.ygjx.entity.*;
import org.cmms.modules.ygjx.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Date 2022/3/2
 * @Created by eran
 */
@RestController
@RequestMapping("/jbxc")
public class BasicCommonController extends JeecgController<BasicWageAllowabces, IBasicWageAllowabcesService> {

    @Autowired
    private IBasicWageIssuanceService basicWageIssuanceService;
    @Autowired
    private IBasicWageLaborcompetitionService basicWageLaborcompetitionService;
    @Autowired
    private IBasicWageOtherService basicWageOtherService;
    @Autowired
    IBasicWageAllowabcesService basicWageAllowabcesService;
    @Autowired
    private IBasicWageWithholdingService basicWageWithholdingService;
    @Autowired
    private ISysDictService dictService;


    public static String QBGZ = "全部工资";
    public static String GWGZ = "岗位工资";
    public static String LDJS = "劳动竞赛";
    public static String JBT = "津补贴";
    public static String QT = "其他";
    public static String DKDJ = "代扣代缴";

    /**
     * @param date 只能查月初  每张表每个人只有一条数据（理论上）
     * @param type 全部a 岗位工资b 劳动竞赛c 津补贴d 其他e 代扣代缴f
     */
    @RequestMapping("/getCardByType")
    public Result<?> getJbxc(String date, String type) {

        System.out.println("===============>");
        System.out.println(date);
        System.out.println(type);
        DateTime dateTime = DateUtil.beginOfMonth(new Date());
        if (StringUtils.isNotBlank(date)) {
            date.replaceAll("/", "-");
            DateTime parse = DateUtil.parse(date);
            dateTime = DateUtil.beginOfMonth(parse);
        }
        String bMonStr = DateUtil.format(dateTime, DatePattern.NORM_DATE_PATTERN).replaceAll("-","");
        System.out.println(bMonStr);
        if (StringUtils.isBlank(type))
            type = "a";

        GzVO gzVO = new GzVO();
        List<GzVO> gzVOList = new ArrayList<>();

        String username = getUsername();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("yggh", username);
        queryWrapper.eq("tjyf", dateTime);

        if ("a".equals(type)) {
            gzVO.setTitle(QBGZ);
            BigDecimal qbgz = new BigDecimal(0);
            List<JbgzVO> jbgzVOList = new ArrayList<>();

            GzMxVO byYggh = service.getByYggh(username, bMonStr);
            JbgzVO gwgz = new JbgzVO();
            gwgz.setTitle(GWGZ);
            if (byYggh.getGwgz() != null) {
                gwgz.setExtra(byYggh.getGwgz());
                qbgz = qbgz.add(byYggh.getGwgz());
            }
            jbgzVOList.add(gwgz);

            JbgzVO ldjs = new JbgzVO();
            ldjs.setTitle(LDJS);
            if (byYggh.getLdjs() != null) {
                ldjs.setExtra(byYggh.getLdjs());
                qbgz = qbgz.add(byYggh.getLdjs());
            }
            jbgzVOList.add(ldjs);

            JbgzVO jbt = new JbgzVO();
            jbt.setTitle(JBT);
            if (byYggh.getJbt() != null) {
                jbt.setExtra(byYggh.getJbt());
                qbgz = qbgz.add(byYggh.getJbt());
            }
            jbgzVOList.add(jbt);

            JbgzVO qt = new JbgzVO();
            qt.setTitle(QT);
            if (byYggh.getQt() != null) {
                qt.setExtra(byYggh.getQt());
                qbgz = qbgz.add(byYggh.getQt());
            }
            jbgzVOList.add(qt);

            JbgzVO dkdj = new JbgzVO();
            dkdj.setTitle(DKDJ);
            if (byYggh.getDkdj() != null) {
                dkdj.setExtra(byYggh.getDkdj());
                qbgz = qbgz.add(byYggh.getDkdj());
            }
            jbgzVOList.add(dkdj);
            gzVO.setJbgzVOS(jbgzVOList);
            gzVO.setExtra(qbgz);
            return Result.ok(gzVO);
        }else if ("b".equals(type)){
            List<BasicWageIssuance> list = basicWageIssuanceService.list(queryWrapper);
            if (CollUtil.isNotEmpty(list)){
                for (int i = 0; i < list.size(); i++) {
                    GzVO jbgz = new GzVO();
                    List<JbgzVO> jbgzVOS = new ArrayList<>();

                    BasicWageIssuance basicWageIssuance = list.get(i);
                    if (basicWageIssuance.getTotalWage() != null)
                        jbgz.setExtra(basicWageIssuance.getTotalWage());
                    JbgzVO jbgzVO1 = new JbgzVO();
                    jbgzVO1.setTitle("基本保障工资");
                    if (basicWageIssuance.getBasicWage() != null)
                        jbgzVO1.setExtra(basicWageIssuance.getBasicWage());
                    JbgzVO jbgzVO2 = new JbgzVO();
                    jbgzVO2.setTitle("岗位工资");
                    if (basicWageIssuance.getPostWage() != null)
                        jbgzVO2.setExtra(basicWageIssuance.getPostWage());
                    JbgzVO jbgzVO3 = new JbgzVO();
                    jbgzVO3.setTitle("职称或学历津贴");
                    if (basicWageIssuance.getZcxlWage() != null)
                        jbgzVO3.setExtra(basicWageIssuance.getZcxlWage());
                    JbgzVO jbgzVO4 = new JbgzVO();
                    jbgzVO4.setTitle("工龄工资");
                    if (basicWageIssuance.getWorkingYearsWage() != null)
                        jbgzVO4.setExtra(basicWageIssuance.getWorkingYearsWage());
                    JbgzVO jbgzVO5 = new JbgzVO();
                    jbgzVO5.setTitle("地区差异补贴");
                    if (basicWageIssuance.getRegionalDiffSubsidy() != null)
                        jbgzVO5.setExtra(basicWageIssuance.getRegionalDiffSubsidy());
                    JbgzVO jbgzVO6 = new JbgzVO();
                    jbgzVO6.setTitle("卫生费");
                    if (basicWageIssuance.getHealthExpenses() != null)
                        jbgzVO6.setExtra(basicWageIssuance.getHealthExpenses());
                    JbgzVO jbgzVO7 = new JbgzVO();
                    jbgzVO7.setTitle("特殊岗位津贴");
                    if (basicWageIssuance.getSpecialPostAllowance() != null)
                        jbgzVO7.setExtra(basicWageIssuance.getSpecialPostAllowance());
                    JbgzVO jbgzVO8 = new JbgzVO();
                    jbgzVO8.setTitle("代缴税");
                    if (basicWageIssuance.getPayTax() != null)
                        jbgzVO8.setExtra(basicWageIssuance.getPayTax());

                    if (StringUtils.isNotBlank(basicWageIssuance.getJgdm())){
                        String s = dictService.queryTableDictTextByKey("HR_BAS_ORGANIZATION", "zzjc", "ywjgdm", basicWageIssuance.getJgdm());
                        jbgz.setTitle(s);
                    }
                    jbgzVOS.add(jbgzVO1);
                    jbgzVOS.add(jbgzVO2);
                    jbgzVOS.add(jbgzVO3);
                    jbgzVOS.add(jbgzVO4);
                    jbgzVOS.add(jbgzVO5);
                    jbgzVOS.add(jbgzVO6);
                    jbgzVOS.add(jbgzVO7);
                    jbgzVOS.add(jbgzVO8);
                    jbgz.setJbgzVOS(jbgzVOS);
                    gzVOList.add(jbgz);
                }

                return Result.ok(gzVOList);
            }
        }else if ("c".equals(type)){
            List<BasicWageLaborcompetition> list = basicWageLaborcompetitionService.list(queryWrapper);
            for (int i = 0; i < list.size(); i++) {
                GzVO jbgz = new GzVO();
                List<JbgzVO> jbgzVOS = new ArrayList<>();
                BasicWageLaborcompetition basicWageLaborcompetition = list.get(i);
                if (basicWageLaborcompetition.getTotalWage() != null){
                    jbgz.setExtra(basicWageLaborcompetition.getTotalWage());
                }
                if (StringUtils.isNotBlank(basicWageLaborcompetition.getJgdm())){
                    String s = dictService.queryTableDictTextByKey("HR_BAS_ORGANIZATION", "zzjc", "ywjgdm", basicWageLaborcompetition.getJgdm());
                    jbgz.setTitle(s);
                }
                JbgzVO jbgzVO1 = new JbgzVO();
                jbgzVO1.setTitle("业务部专项竞赛");
                if (basicWageLaborcompetition.getBusinessUnitWage() != null)
                    jbgzVO1.setExtra(basicWageLaborcompetition.getBusinessUnitWage());

                JbgzVO jbgzVO2 = new JbgzVO();
                jbgzVO2.setTitle("电子银行部专项竞赛");
                if (basicWageLaborcompetition.getInternetBankingWage() != null)
                    jbgzVO2.setExtra(basicWageLaborcompetition.getInternetBankingWage());

                JbgzVO jbgzVO3 = new JbgzVO();
                jbgzVO3.setTitle("风险管理部专项竞赛");
                if (basicWageLaborcompetition.getRiskManagementWage()!=null)
                    jbgzVO3.setExtra(basicWageLaborcompetition.getRiskManagementWage());
                JbgzVO jbgzVO4 = new JbgzVO();
                jbgzVO4.setTitle("运营管理部专项竞赛");
                if (basicWageLaborcompetition.getOperManagementWage() != null)
                    jbgzVO4.setExtra(basicWageLaborcompetition.getOperManagementWage());

                JbgzVO jbgzVO5 = new JbgzVO();
                jbgzVO5.setTitle("财务部专项考核");
                if (basicWageLaborcompetition.getFinanceDepartWage() != null){
                    jbgzVO5.setExtra(basicWageLaborcompetition.getFinanceDepartWage());
                }

                JbgzVO jbgzVO6 = new JbgzVO();
                jbgzVO6.setTitle("科技部专项考核");
                if (basicWageLaborcompetition.getScienceDepartWage() != null)
                    jbgzVO6.setExtra(basicWageLaborcompetition.getScienceDepartWage());

                JbgzVO jbgzVO7 = new JbgzVO();
                jbgzVO7.setTitle("保卫部专项考核");
                if (basicWageLaborcompetition.getSecurityDepartWage() != null)
                    jbgzVO7.setExtra(basicWageLaborcompetition.getSecurityDepartWage());

                JbgzVO jbgzVO8 = new JbgzVO();
                jbgzVO8.setTitle("代缴税");
                if (basicWageLaborcompetition.getPayTax() != null)
                    jbgzVO8.setExtra(basicWageLaborcompetition.getPayTax());


                jbgzVOS.add(jbgzVO1);
                jbgzVOS.add(jbgzVO2);
                jbgzVOS.add(jbgzVO3);
                jbgzVOS.add(jbgzVO4);
                jbgzVOS.add(jbgzVO5);
                jbgzVOS.add(jbgzVO6);
                jbgzVOS.add(jbgzVO7);
                jbgzVOS.add(jbgzVO8);
                jbgz.setJbgzVOS(jbgzVOS);
                gzVOList.add(jbgz);

            }
            return Result.ok(gzVOList);
        }else if ("d".equals(type)){
            List<BasicWageAllowabces> list = basicWageAllowabcesService.list(queryWrapper);
            for (int i = 0; i < list.size(); i++) {
                GzVO jbgz = new GzVO();
                List<JbgzVO> jbgzVOS = new ArrayList<>();
                BasicWageAllowabces basicWageAllowabces = list.get(i);
                if (basicWageAllowabces.getTotalWage() != null){
                    jbgz.setExtra(basicWageAllowabces.getTotalWage());
                }
                if (StringUtils.isNotBlank(basicWageAllowabces.getJgdm())){
                    String s = dictService.queryTableDictTextByKey("HR_BAS_ORGANIZATION", "zzjc", "ywjgdm", basicWageAllowabces.getJgdm());
                    jbgz.setTitle(s);
                }
                JbgzVO jbgzVO1 = new JbgzVO();
                jbgzVO1.setTitle("防暑降温费用");
                if (basicWageAllowabces.getFsjwAllowance() != null)
                    jbgzVO1.setExtra(basicWageAllowabces.getFsjwAllowance());

                JbgzVO jbgzVO2 = new JbgzVO();
                jbgzVO2.setTitle("退伍军人慰问");
                if (basicWageAllowabces.getVeteranAllowance() != null)
                    jbgzVO2.setExtra(basicWageAllowabces.getVeteranAllowance());

                JbgzVO jbgzVO3 = new JbgzVO();
                jbgzVO3.setTitle("残疾补助");
                if (basicWageAllowabces.getDisabilityAllowance()!=null)
                    jbgzVO3.setExtra(basicWageAllowabces.getDisabilityAllowance());
                JbgzVO jbgzVO4 = new JbgzVO();
                jbgzVO4.setTitle("荣誉奖金");
                if (basicWageAllowabces.getHonorBonus() != null)
                    jbgzVO4.setExtra(basicWageAllowabces.getHonorBonus());

                JbgzVO jbgzVO5 = new JbgzVO();
                jbgzVO5.setTitle("交通补助");
                if (basicWageAllowabces.getTransportAllowance() != null){
                    jbgzVO5.setExtra(basicWageAllowabces.getTransportAllowance());
                }

                JbgzVO jbgzVO6 = new JbgzVO();
                jbgzVO6.setTitle("其它津贴");
                if (basicWageAllowabces.getOtherAllowance() != null)
                    jbgzVO6.setExtra(basicWageAllowabces.getOtherAllowance());

                jbgzVOS.add(jbgzVO1);
                jbgzVOS.add(jbgzVO2);
                jbgzVOS.add(jbgzVO3);
                jbgzVOS.add(jbgzVO4);
                jbgzVOS.add(jbgzVO5);
                jbgzVOS.add(jbgzVO6);
                jbgz.setJbgzVOS(jbgzVOS);
                gzVOList.add(jbgz);

            }
            return Result.ok(gzVOList);
        } else if ("e".equals(type)){
            List<BasicWageOther> list = basicWageOtherService.list(queryWrapper);
            for (int i = 0; i < list.size(); i++) {
                GzVO jbgz = new GzVO();
                List<JbgzVO> jbgzVOS = new ArrayList<>();
                BasicWageOther basicWageOther = list.get(i);
                if (basicWageOther.getTotalWage() != null){
                    jbgz.setExtra(basicWageOther.getTotalWage());
                }
                if (StringUtils.isNotBlank(basicWageOther.getJgdm())){
                    String s = dictService.queryTableDictTextByKey("HR_BAS_ORGANIZATION", "zzjc", "ywjgdm", basicWageOther.getJgdm());
                    jbgz.setTitle(s);
                }
                JbgzVO jbgzVO1 = new JbgzVO();
                jbgzVO1.setTitle("加班补助");
                if (basicWageOther.getOvertimeAllowance() != null)
                    jbgzVO1.setExtra(basicWageOther.getOvertimeAllowance());

                JbgzVO jbgzVO2 = new JbgzVO();
                jbgzVO2.setTitle("其它奖励工资");
                if (basicWageOther.getOtherRewardsWage() != null)
                    jbgzVO2.setExtra(basicWageOther.getOtherRewardsWage());

                JbgzVO jbgzVO3 = new JbgzVO();
                jbgzVO3.setTitle("岗位模板考核");
                if (basicWageOther.getJobGoalAssessment()!=null)
                    jbgzVO3.setExtra(basicWageOther.getJobGoalAssessment());
                JbgzVO jbgzVO4 = new JbgzVO();
                jbgzVO4.setTitle("安全保证金");
                if (basicWageOther.getSecurityDeposit() != null)
                    jbgzVO4.setExtra(basicWageOther.getSecurityDeposit());

                JbgzVO jbgzVO5 = new JbgzVO();
                jbgzVO5.setTitle("代缴税");
                if (basicWageOther.getPayTax() != null){
                    jbgzVO5.setExtra(basicWageOther.getPayTax());
                }

                jbgzVOS.add(jbgzVO1);
                jbgzVOS.add(jbgzVO2);
                jbgzVOS.add(jbgzVO3);
                jbgzVOS.add(jbgzVO4);
                jbgzVOS.add(jbgzVO5);

                jbgz.setJbgzVOS(jbgzVOS);
                gzVOList.add(jbgz);

            }
            return Result.ok(gzVOList);
        }else if ("f".equals(type)){

            List<BasicWageWithholding> list = basicWageWithholdingService.list(queryWrapper);
            for (int i = 0; i < list.size(); i++) {
                GzVO jbgz = new GzVO();
                List<JbgzVO> jbgzVOS = new ArrayList<>();
                BasicWageWithholding basicWageWithholding = list.get(i);
                if (basicWageWithholding.getTotalWage() != null){
                    jbgz.setExtra(basicWageWithholding.getTotalWage());
                }
                if (StringUtils.isNotBlank(basicWageWithholding.getJgdm())){
                    String s = dictService.queryTableDictTextByKey("HR_BAS_ORGANIZATION", "zzjc", "ywjgdm", basicWageWithholding.getJgdm());
                    jbgz.setTitle(s);
                }
                JbgzVO jbgzVO1 = new JbgzVO();
                jbgzVO1.setTitle("基本养老保险");
                if (basicWageWithholding.getBasicRetirementIns() != null)
                    jbgzVO1.setExtra(basicWageWithholding.getBasicRetirementIns());

                JbgzVO jbgzVO2 = new JbgzVO();
                jbgzVO2.setTitle("基本医疗保险");
                if (basicWageWithholding.getBasicMedicalIns() != null)
                    jbgzVO2.setExtra(basicWageWithholding.getBasicMedicalIns());

                JbgzVO jbgzVO3 = new JbgzVO();
                jbgzVO3.setTitle("失业保险");
                if (basicWageWithholding.getUnemploymentIns()!=null)
                    jbgzVO3.setExtra(basicWageWithholding.getUnemploymentIns());
                JbgzVO jbgzVO4 = new JbgzVO();
                jbgzVO4.setTitle("住房公积金");
                if (basicWageWithholding.getHousingFund() != null)
                    jbgzVO4.setExtra(basicWageWithholding.getHousingFund());

                JbgzVO jbgzVO5 = new JbgzVO();
                jbgzVO5.setTitle("年金");
                if (basicWageWithholding.getAnnuity() != null){
                    jbgzVO5.setExtra(basicWageWithholding.getAnnuity());
                }

                JbgzVO jbgzVO6 = new JbgzVO();
                jbgzVO6.setTitle("扣工资还贷");
                if (basicWageWithholding.getSalaryDeduction() != null)
                    jbgzVO6.setExtra(basicWageWithholding.getSalaryDeduction());

                JbgzVO jbgzVO7 = new JbgzVO();
                jbgzVO7.setTitle("处分扣工资");
                if (basicWageWithholding.getWageDeduction() != null)
                    jbgzVO7.setExtra(basicWageWithholding.getWageDeduction());

                JbgzVO jbgzVO8 = new JbgzVO();
                jbgzVO8.setTitle("病假扣工资");
                if (basicWageWithholding.getSickleaveDeduction() != null)
                    jbgzVO8.setExtra(basicWageWithholding.getSickleaveDeduction());


                jbgzVOS.add(jbgzVO1);
                jbgzVOS.add(jbgzVO2);
                jbgzVOS.add(jbgzVO3);
                jbgzVOS.add(jbgzVO4);
                jbgzVOS.add(jbgzVO5);
                jbgzVOS.add(jbgzVO6);
                jbgzVOS.add(jbgzVO7);
                jbgzVOS.add(jbgzVO8);
                jbgz.setJbgzVOS(jbgzVOS);
                gzVOList.add(jbgz);

            }
            return Result.ok(gzVOList);

        }

        return Result.ok();
}


}
