package org.cmms.modules.khgl.etckh.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdcardUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.checkerframework.checker.units.qual.K;
import org.cmms.common.api.vo.Result;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.appbase.vo.CkkhjbVO;
import org.cmms.modules.khgl.etckh.entity.BdxxbVO;
import org.cmms.modules.khgl.etckh.entity.Etckh;
import org.cmms.modules.khgl.etckh.entity.SbxxVO;
import org.cmms.modules.khgl.etckh.service.IEtckhService;
import org.cmms.modules.khgxgl.entity.KhgxglKhzlglGrkh;
import org.cmms.modules.khgxgl.entity.KhgxglKhzlglQykh;
import org.cmms.modules.khgxgl.service.IKhgxglKhzlglGrkhService;
import org.cmms.modules.khgxgl.service.IKhgxglKhzlglQykhService;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.entity.SysUser;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.cmms.modules.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Date 2022/2/23
 * @Created by eran
 */
@RestController
@RequestMapping("/app/khgl/etckh")
public class EtckhController extends JeecgController<Etckh, IEtckhService>{
    @Autowired
    private IEtckhService iEtckhService;
    @Autowired
    private IHrBasOrganizationService hrBasOrganizationService;
    @Autowired
    private IKhgxglKhzlglGrkhService khgxglKhzlglGrkhService;
    @Autowired
    private IKhgxglKhzlglQykhService khgxglKhzlglQykhService;

    @Autowired
    ISysUserService sysUserService;

    /**
     * 分页列表查询
     *
     * @param
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "ETC绑定信息表-分页列表查询")
    @ApiOperation(value="ETC绑定信息表-分页列表查询", notes="ETC绑定信息表-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Etckh etckh,
                                   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                   Integer type, String namecn,
                                   HttpServletRequest req) {
        QueryWrapper<Etckh> queryWrapper = QueryGenerator.initQueryWrapper(etckh, req.getParameterMap());
        if (StringUtils.isNotBlank(namecn)){
            queryWrapper.like("khmc",namecn);
        }
        if (1==type){
            queryWrapper.eq("operno",getRealname());
        }
        IPage pageList=org.cmms.common.utils.PageUtil.toPage(IEtckhService.class,iEtckhService,pageNo,pageSize,queryWrapper);
        return Result.ok(pageList);
    }


    @GetMapping(value = "/getKhxxList")
    public Result<?> queryPageList2(BdxxbVO bdxxbVO,
                                    @RequestParam(name = "pageNo",defaultValue = "1") Integer pageNo,
                                    @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                    Integer type, String namecn,
                                    HttpServletRequest req){
        String username ="";
        if (1 == type){
            //username = getLoginUser().
            //现场数据是柜员号 查柜员号
            SysUser sys_user = sysUserService.getUserByName(getUsername());
            if (sys_user != null && org.apache.commons.lang3.StringUtils.isNotBlank(sys_user.getGyh()))
                username = sys_user.getGyh();
            System.out.println("===etc==="+username);
        }
        Page<String> page = new Page<>(pageNo, pageSize);
        IPage<BdxxbVO> khxxList = service.getKhxxList(page, username, namecn);

        if (CollUtil.isNotEmpty(khxxList.getRecords())){
            for (int i=0;i<khxxList.getRecords().size();i++){
                String zjhm = khxxList.getRecords().get(i).getZjhm();
                if (IdcardUtil.isValidCard(zjhm)){
                    int sex = IdcardUtil.getGenderByIdCard(zjhm);
                    khxxList.getRecords().get(i).setXb(sex);

                    QueryWrapper<KhgxglKhzlglGrkh> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("zjhm", zjhm);
                    if (StringUtils.isNotBlank(getRedisUserJgdm())){
                        queryWrapper.eq("jgdm",getRedisUserJgdm());
                    }
                    queryWrapper.orderByDesc("zzkhrq");
                    List<KhgxglKhzlglGrkh> list = khgxglKhzlglGrkhService.list(queryWrapper);
                    if (CollUtil.isNotEmpty(list)) {
                        KhgxglKhzlglGrkh khgxglKhzlglGrkh = list.get(0);
                        if (org.apache.commons.lang3.StringUtils.isNotBlank(khgxglKhzlglGrkh.getCustId()))
                            khxxList.getRecords().get(i).setCustId(khgxglKhzlglGrkh.getCustId());
                        if (khgxglKhzlglGrkh.getCsrq() != null)
                            khxxList.getRecords().get(i).setCsrq(khgxglKhzlglGrkh.getCsrq());
                        if (org.apache.commons.lang3.StringUtils.isNotBlank(khgxglKhzlglGrkh.getZz()))
                            khxxList.getRecords().get(i).setZz(khgxglKhzlglGrkh.getZz());
                        if (org.apache.commons.lang3.StringUtils.isNotBlank(khgxglKhzlglGrkh.getDzyx()))
                            khxxList.getRecords().get(i).setDzyx(khgxglKhzlglGrkh.getDzyx());
                    }
                }else {
                    QueryWrapper<KhgxglKhzlglQykh> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("zjhm", zjhm);
                    queryWrapper.orderByDesc("zzkhrq");
                    List<KhgxglKhzlglQykh> list = khgxglKhzlglQykhService.list(queryWrapper);
                    if (CollUtil.isNotEmpty(list)){
                        KhgxglKhzlglQykh khgxglKhzlglQykh = list.get(0);
                        if (StringUtils.isNotBlank(khgxglKhzlglQykh.getCustId()))
                            khxxList.getRecords().get(i).setCustId(khgxglKhzlglQykh.getCustId());

                        khxxList.getRecords().get(i).setFrxb(1);
                        if (StringUtils.isNotBlank(khgxglKhzlglQykh.getZjhm())){
                             String zjhm1 = khgxglKhzlglQykh.getZjhm();
                             if (IdcardUtil.isValidCard(zjhm1)){
                                 int genderByIdCard = IdcardUtil.getGenderByIdCard(zjhm1);
                                 khxxList.getRecords().get(i).setFrxb(genderByIdCard);
                             }

                             QueryWrapper<KhgxglKhzlglGrkh> queryWrapper2 = new QueryWrapper<>();
                             queryWrapper2.eq("zjhm",khgxglKhzlglQykh.getZjhm());
                             queryWrapper2.orderByDesc("zzkhrq");
                            if (org.apache.commons.lang3.StringUtils.isNotBlank(getRedisUserJgdm())){
                                queryWrapper2.eq("jgdm",getRedisUserJgdm());
                            }
                             List<KhgxglKhzlglGrkh> list1 = khgxglKhzlglGrkhService.list(queryWrapper2);
                             if (CollUtil.isNotEmpty(list1)){
                                 KhgxglKhzlglGrkh khgxglKhzlglGrkh = list1.get(0);
                                 if (StringUtils.isNotBlank(khgxglKhzlglGrkh.getSjhm()))
                                     khxxList.getRecords().get(i).setFrlxfs(khgxglKhzlglGrkh.getSjhm());
                                 if (khgxglKhzlglGrkh.getCsrq() != null)
                                     khxxList.getRecords().get(i).setFrcsrq(khgxglKhzlglGrkh.getCsrq());
                                 if (StringUtils.isNotBlank(khgxglKhzlglGrkh.getZz()))
                                     khxxList.getRecords().get(i).setFrlxdz(khgxglKhzlglGrkh.getZz());
                                 if (StringUtils.isNotBlank(khgxglKhzlglGrkh.getDzyx()))
                                     khxxList.getRecords().get(i).setFrdzyx(khgxglKhzlglGrkh.getDzyx());
                             }

                        }
                        if (StringUtils.isNotBlank(khgxglKhzlglQykh.getFrdb()))
                            khxxList.getRecords().get(i).setFrdb(khgxglKhzlglQykh.getFrdb());
                        if (StringUtils.isNotBlank(khgxglKhzlglQykh.getTxdz()))
                            khxxList.getRecords().get(i).setZz(khgxglKhzlglQykh.getTxdz());

                    }
                    khxxList.getRecords().get(i).setXb(2);
                }
            }
        }
        return Result.ok(khxxList);
    }



    /**
     * 设备信息
     */
    @GetMapping(value = "/getSbxxList")
    public Result<?> getSbxxList(SbxxVO sbxxVO,
                                 @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                 @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                 String zjhm,
                                 HttpServletRequest req){
        Page<String> page = new Page<>(pageNo,pageSize);
        IPage<SbxxVO> SbxxList = service.getSbxxList(page,zjhm);
        return Result.ok(SbxxList);
    }



    @RequestMapping("/list")
    public Result<?> list(@RequestBody Page page){
        System.out.println(page);
        IPage<HrBasOrganization> result = hrBasOrganizationService.page(page);
        return Result.ok(result);
    }

    @RequestMapping("/getCkkhjbInfo")
    public Result<?> getCkkhjbInfo(){
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        //怎么

        String username = sysUser.getUsername();

        CkkhjbVO ckkhjbVO = new CkkhjbVO();
        //todo
        ckkhjbVO.setQmghs1(2000);

        return Result.ok(ckkhjbVO);
    }
}
