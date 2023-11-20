package org.cmms.modules.tjfx.ipadsysj.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.enums.QybmEnum;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.*;
import org.cmms.modules.dklldj.lldjgl.glzhgl.entity.CbsInvmBase;
import org.cmms.modules.dklldj.lldjgl.glzhgl.service.ICbsInvmBaseService;
import org.cmms.modules.hr.yggl.ygxxgl.entity.Vhrbasstaffpost;
import org.cmms.modules.hr.yggl.ygxxgl.service.IVhrbasstaffpostService;
import org.cmms.modules.khgl.clkhxxgl.service.IClgrkhjbxxService;
import org.cmms.modules.khgl.clkhxxgl.service.IClkhxxglService;
import org.cmms.modules.khgl.khxx.entity.KhywxxSjyhPc;
import org.cmms.modules.khxxgl.clkhxx.service.IClkhglService;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.INhxqService;
import org.cmms.modules.khxxgl.khjbzl.service.IKhjbzlService;
import org.cmms.modules.khxxgl.khywxx.zhywxx.entity.KhxxglCksjmxZh;
import org.cmms.modules.khxxgl.khywxx.zhywxx.entity.KhxxglDksjmxZh;
import org.cmms.modules.khxxgl.khywxx.zhywxx.service.IKhxxglCksjmxZhService;
import org.cmms.modules.khxxgl.khywxx.zhywxx.service.IKhxxglDksjmxZhService;
import org.cmms.modules.pad.nhxxgl.entity.KhywxxSjyhPad;
import org.cmms.modules.pad.nhxxgl.service.IKhglNhhzxxglService;
import org.cmms.modules.sgtz.sjtb.service.IEtlSgtzSjtbService;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.entity.SysDic;
import org.cmms.modules.system.service.ISysDicService;
import org.cmms.modules.system.service.ISysLogService;
import org.cmms.modules.tjfx.ipadsysj.entity.KhglXzzllb;
import org.cmms.modules.tjfx.ipadsysj.entity.SysjQhzhCjpm;
import org.cmms.modules.tjfx.ipadsysj.entity.Zhyxzfpm;
import org.cmms.modules.tjfx.ipadsysj.entity.vTjfxSysj;
import org.cmms.modules.tjfx.ipadsysj.service.IKhglXzzllbService;
import org.cmms.modules.tjfx.ipadsysj.service.IvTjfxSysjService;
import org.cmms.modules.tjfx.jcsjgl.cssz.service.ITjfxCsszService;
import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbby.service.ITjfxZhbyService;
import org.cmms.modules.tjfx.zfsjmx.khjlsjmx.entity.ZfsjmxKhjl;
import org.cmms.modules.tjfx.zfsjtj.sxyxtj.nhsxmx.entity.Nhsxmx;
import org.cmms.modules.tjfx.zfsjtj.sxyxtj.nhsxmx.service.INhsxmxService;
import org.cmms.modules.tjfx.zfsjtj.sxyxtj.nhyxmx.entity.Nhyxmx;
import org.cmms.modules.tjfx.zfsjtj.sxyxtj.nhyxmx.service.INhyxmxService;
import org.cmms.modules.tjfx.zfsjtj.sxyxtj.shsxmx.entity.Shsxmx;
import org.cmms.modules.tjfx.zfsjtj.sxyxtj.shsxmx.service.IShsxmxService;
import org.cmms.modules.tjfx.zfsjtj.sxyxtj.shyxmx.entity.Shyxmx;
import org.cmms.modules.tjfx.zfsjtj.sxyxtj.shyxmx.service.IShyxmxService;
import org.cmms.modules.tjfx.zfsjtj.zfyxtj.nh.gr.service.IKhjlZfyxtjService;
import org.cmms.modules.util.EtlUtil;
import org.cmms.modules.yxdygl.pqzrrgl.service.ITjfxcsszService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description: 平板端首页数据
 * @Author: jeecg-boot
 * @Date: 2020-07-23
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "平板端首页数据")
@RestController
@RequestMapping("/tjfx/ipadsysj/vTjfxSysj")
public class vTjfxSysjController implements Job {
    @Autowired
    private IvTjfxSysjService vTjfxSysjService;
    @Autowired
    private ISysLogService logService;
    @Autowired
    private IKhglXzzllbService khglXzzllbService;
    @Autowired
    private ITjfxZhbyService tjfxZhbyService;
    @Autowired
    private ISysDicService sysDicService;
    @Autowired
    private IKhjlZfyxtjService khjlZfyxtjService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private SshUtil sshUtil;
    @Autowired
    private ICbsInvmBaseService cbsInvmBaseService;
    @Autowired
    private IClkhxxglService clkhxxglService;
    @Autowired
    private INhxqService nhxqService;
    @Autowired
    private IKhglNhhzxxglService khglNhhzxxglService;
    @Autowired
    private IEtlSgtzSjtbService etlSgtzSjtbService;

    @Value("${com.etl.etlName:数据下发ETL任务}")
    private String etlName;

    @Value("${com.etl.dagName:etl_day调度}")
    private String dagName;

    @Autowired
    private IKhxxglDksjmxZhService khxxglDksjmxZhService;
    @Autowired
    private IKhxxglCksjmxZhService khxxglCksjmxZhService;
    @Autowired
    private INhsxmxService nhsxmxService;
    @Autowired
    private IShsxmxService shsxmxService;
    @Autowired
    private INhyxmxService nhyxmxService;
    @Autowired
    private IShyxmxService shyxmxService;
    @Autowired
    private IClgrkhjbxxService clgrkhjbxxService;
    @Autowired
    private IKhjbzlService khjbzlService;
    @Autowired
    private IClkhglService clkhglService;
    @Autowired
    private IVhrbasstaffpostService vhrbasstaffpostService;

    @Autowired
    private ITjfxCsszService tjfxCsszService;
    /**
     * 分页列表查询
     *
     * @param vTjfxSysj
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "平板端首页数据-分页列表查询")
    @ApiOperation(value = "平板端首页数据-分页列表查询", notes = "平板端首页数据-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(vTjfxSysj vTjfxSysj,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<vTjfxSysj> queryWrapper = QueryGenerator.initQueryWrapper(vTjfxSysj, req.getParameterMap());
        Page<vTjfxSysj> page = new Page<vTjfxSysj>(pageNo, pageSize);
        IPage<vTjfxSysj> pageList = vTjfxSysjService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * ipadsysj
     * 分页列表宣传资料列表
     *
     * @param khglXzzllb
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @GetMapping(value = "/xczllist")
    public Result<?> queryxczlPageList(KhglXzzllb khglXzzllb,
                                       @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                       @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                       HttpServletRequest req) {
        QueryWrapper<KhglXzzllb> queryWrapper = QueryGenerator.initQueryWrapper(khglXzzllb, req.getParameterMap());
        Page<KhglXzzllb> page = new Page<KhglXzzllb>(pageNo, pageSize);
        IPage<KhglXzzllb> pageList = khglXzzllbService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param vTjfxSysj
     * @return
     */
    @AutoLog(value = "平板端首页数据-添加")
    @ApiOperation(value = "平板端首页数据-添加", notes = "平板端首页数据-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody vTjfxSysj vTjfxSysj) {
        vTjfxSysjService.save(vTjfxSysj);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param vTjfxSysj
     * @return
     */
    @AutoLog(value = "平板端首页数据-编辑")
    @ApiOperation(value = "平板端首页数据-编辑", notes = "平板端首页数据-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody vTjfxSysj vTjfxSysj) {
        vTjfxSysjService.updateById(vTjfxSysj);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "平板端首页数据-通过id删除")
    @ApiOperation(value = "平板端首页数据-通过id删除", notes = "平板端首页数据-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        vTjfxSysjService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "平板端首页数据-批量删除")
    @ApiOperation(value = "平板端首页数据-批量删除", notes = "平板端首页数据-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.vTjfxSysjService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "平板端首页数据-通过id查询")
    @ApiOperation(value = "平板端首页数据-通过id查询", notes = "平板端首页数据-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        vTjfxSysj vTjfxSysj = vTjfxSysjService.getById(id);
        return Result.ok(vTjfxSysj);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param vTjfxSysj
     */
  /*@RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, vTjfxSysj vTjfxSysj) {
      return super.exportXls(request, vTjfxSysj, vTjfxSysj.class, "平板端首页数据");
  }*/

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
  /*@RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      return super.importExcel(request, response, vTjfxSysj.class);
  }*/

    /**
     * 查询首页数据
     *
     * @param request
     * @param request
     * @return
     */
    @RequestMapping(value = "/querysysj", method = RequestMethod.GET)
    public Result<?> querysysj(HttpServletRequest request, HttpServletResponse response) {
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
//		 String zzbz = sysUser.getOrgCode();
        String khjl = vTjfxSysjService.queryGyh(sysUser.getUsername());
        JSONObject obj = new JSONObject();
        List<ZfsjmxKhjl> zfsjmxKhjlList = new ArrayList<>();
        List<SysjQhzhCjpm> sysjQhzhCjpmList = new ArrayList<>();
//		 String zzlb=logService.getzzlb(zzbz);

        Long byzfhs = 0l;
        Long ljzfhs = 0l;
        Long byysxje = 0l;
        Long ljysxje = 0l;
        Long shbyzfhs = 0l;
        Long shljzfhs = 0l;
        Long shbyysxed = 0l;
        Long shljysxed = 0l;

        //永兴查询支行或者全行数据，支行员工看本支行的数据，部门员工看全行的数据
        SysDic sysDic = sysDicService.queryByCode("101001");
        String qydm = sysDic.getValue();
        if ("350".equalsIgnoreCase(qydm)) {
            //获取员工所在组织类别
            HrBasOrganization zzxx = vTjfxSysjService.queryZzxxByYggh(sysUser.getWorkNo());
            if ("1".equalsIgnoreCase(zzxx.getZzlb()) || "3".equalsIgnoreCase(zzxx.getZzlb())) {
                //查询全行数据
                byzfhs = vTjfxSysjService.qhbyzfhs();
                ljzfhs = vTjfxSysjService.qhljzfhs();
                byysxje = vTjfxSysjService.qhdyysxed();
                ljysxje = vTjfxSysjService.qhljysxed();

                shbyzfhs = vTjfxSysjService.shqhbyzfhs();
                shljzfhs = vTjfxSysjService.shqhljzfhs();
                shbyysxed = vTjfxSysjService.shqhbyysxed();
                shljysxed = vTjfxSysjService.shqhljysxed();
            } else {
                //查询支行数据
                String zzbz = zzxx.getZzbz();
                byzfhs = vTjfxSysjService.zhbyzfhs(zzbz);
                ljzfhs = vTjfxSysjService.zhljzfhs(zzbz);
                byysxje = vTjfxSysjService.zhdyysxed(zzbz);
                ljysxje = vTjfxSysjService.zhljysxed(zzbz);

                shbyzfhs = vTjfxSysjService.shzhbyzfhs(zzbz);
                shljzfhs = vTjfxSysjService.shzhljzfhs(zzbz);
                shbyysxed = vTjfxSysjService.shzhbyysxed(zzbz);
                shljysxed = vTjfxSysjService.shzhljysxed(zzbz);
            }
        } else {
            //查询农户走访数据、评级授信数据
            byzfhs = vTjfxSysjService.grbyzfhs(khjl);
            ljzfhs = vTjfxSysjService.grljzfhs(khjl);
            byysxje = vTjfxSysjService.grdyysxed(khjl);
            ljysxje = vTjfxSysjService.grljysxed(khjl);

            //查询商户走访数据、评级授信数据
            shbyzfhs = vTjfxSysjService.shbyzfhs(khjl);
            shljzfhs = vTjfxSysjService.shljzfhs(khjl);
            shbyysxed = vTjfxSysjService.shbyysxed(khjl);
            shljysxed = vTjfxSysjService.shljysxed(khjl);
        }

        byzfhs = byzfhs == null ? 0 : byzfhs;
        ljzfhs = ljzfhs == null ? 0 : ljzfhs;
        byysxje = byysxje == null ? 0 : byysxje;
        ljysxje = ljysxje == null ? 0 : ljysxje;
        shbyzfhs = shbyzfhs == null ? 0 : shbyzfhs;
        shljzfhs = shljzfhs == null ? 0 : shljzfhs;
        shbyysxed = shbyysxed == null ? 0 : shbyysxed;
        shljysxed = shljysxed == null ? 0 : shljysxed;

        //查询农户走访排名
        zfsjmxKhjlList = vTjfxSysjService.queryQhCjpm();
        //查询综合走访排名
        sysjQhzhCjpmList = vTjfxSysjService.queryQhzhCjpm();

		 /*if(zzbz.equals("1")){
			 byzfhs = vTjfxSysjService.qhbyzfhs() == null ? 0 : vTjfxSysjService.qhbyzfhs();
			 ljzfhs = vTjfxSysjService.qhljzfhs()== null ? 0 : vTjfxSysjService.qhljzfhs() ;
			 byysxje = vTjfxSysjService.qhdyysxed() == null ? 0  :vTjfxSysjService.qhdyysxed();
			 ljysxje = vTjfxSysjService.qhljysxed() == null ? 0 :vTjfxSysjService.qhljysxed();
			 zfsjmxKhjlList = vTjfxSysjService.queryQhCjpm();
		 }else{
			 byzfhs = vTjfxSysjService.grbyzfhs(khjl) ==null ? 0 : vTjfxSysjService.grbyzfhs(khjl);
			 ljzfhs = vTjfxSysjService.grljzfhs(khjl) == null ? 0 :vTjfxSysjService.grljzfhs(khjl);
			 byysxje = vTjfxSysjService.grdyysxed(khjl) ==null ? 0 :vTjfxSysjService.grdyysxed(khjl);
			 ljysxje = vTjfxSysjService.grljysxed(khjl) == null ? 0 :vTjfxSysjService.grljysxed(khjl);
			 zfsjmxKhjlList = vTjfxSysjService.queryQhCjpm();
		 }*/
        for (ZfsjmxKhjl zfsjmxKhjl : zfsjmxKhjlList) {
            zfsjmxKhjl.setZkhjl(zfsjmxKhjl.getZkhjl() == null ? "" : tjfxZhbyService.queryTableDictTextByKey("HR_BAS_STAFF", "ygxm", "yggh", zfsjmxKhjl.getZkhjl()));
        }
        for (SysjQhzhCjpm sysjQhzhCjpm : sysjQhzhCjpmList) {
            sysjQhzhCjpm.setYggh(sysjQhzhCjpm.getYggh() == null ? "" : tjfxZhbyService.queryTableDictTextByKey("HR_BAS_STAFF", "ygxm", "yggh", sysjQhzhCjpm.getYggh()));
        }

        obj.put("byzfhs", byzfhs);
        obj.put("ljzfhs", ljzfhs);
        obj.put("byysxje", byysxje);
        obj.put("ljysxje", ljysxje);
        obj.put("shbyzfhs", shbyzfhs);
        obj.put("shljzfhs", shljzfhs);
        obj.put("shbyysxed", shbyysxed);
        obj.put("shljysxed", shljysxed);
        obj.put("zfsjmxKhjlList", zfsjmxKhjlList);
        obj.put("sysjQhzhCjpmList", sysjQhzhCjpmList);

        return Result.ok(obj);
    }

    @RequestMapping(value = "/getNhYxzfPm", method = RequestMethod.GET)
    public Result<?> getNhYxzfPm(HttpServletRequest request, HttpServletResponse response) {
        List<SysjQhzhCjpm> nhYxzfPmList = vTjfxSysjService.getYxzfpm("1","T");
        if (!nhYxzfPmList.isEmpty() && nhYxzfPmList.size() >= 10) {
            return Result.ok(nhYxzfPmList.subList(0, 10));
        }
        return Result.ok(nhYxzfPmList);
    }


    @RequestMapping(value = "/getNhYxzfPmByW", method = RequestMethod.GET)
    public Result<?> getNhYxzfPmByW(HttpServletRequest request, HttpServletResponse response) {
        List<SysjQhzhCjpm> nhYxzfPmList = vTjfxSysjService.getYxzfpm("1","W");
        if (!nhYxzfPmList.isEmpty() && nhYxzfPmList.size() >= 10) {
            return Result.ok(nhYxzfPmList.subList(0, 10));
        }
        return Result.ok(nhYxzfPmList);
    }

    @RequestMapping(value = "/gettsxx", method = RequestMethod.GET)
    public Result<?> gettsxx(HttpServletRequest request, HttpServletResponse response) {
        String value = tjfxCsszService.queryCszByCsbm("BDZFS");
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        List<SysjQhzhCjpm> nhYxzfPmListOne = vTjfxSysjService.getYxzfpmByYggh("1","W",sysUser.getWorkNo());
        JSONObject tsxx=new JSONObject();
        tsxx.put("bdzfs",value);
        if(!nhYxzfPmListOne.isEmpty()){
            Integer yxzfhs = nhYxzfPmListOne.get(0).getYxzfhs();
            tsxx.put("bdzfcs",Integer.valueOf(value)-(yxzfhs==null?0:yxzfhs));
        }else{
            tsxx.put("bdzfcs",Integer.valueOf(value));

        }
        List<SysjQhzhCjpm> nhYxzfPmList = vTjfxSysjService.getYxzfpm("1","W");
        if (!nhYxzfPmList.isEmpty()) {
           String yggh = nhYxzfPmList.get(0).getYggh();
            Vhrbasstaffpost vhrbasstaffpost = vhrbasstaffpostService.selectByYggh(yggh);
            if (vhrbasstaffpost != null) {
                tsxx.put("zgj",vhrbasstaffpost.getYgxm());
            }else{
                tsxx.put("zgj","");
            }
        }else{
            tsxx.put("zgj","");
        }



        return Result.ok(tsxx);
    }




    @RequestMapping(value = "/getNhYxzfPmByYYYY", method = RequestMethod.GET)
    public Result<?> getNhYxzfPmByYYYY(HttpServletRequest request, HttpServletResponse response) {
        List<SysjQhzhCjpm> nhYxzfPmList = vTjfxSysjService.getYxzfpm("1","YYYY");
        if (!nhYxzfPmList.isEmpty() && nhYxzfPmList.size() >= 10) {
            return Result.ok(nhYxzfPmList.subList(0, 10));
        }
        return Result.ok(nhYxzfPmList);
    }



    @RequestMapping(value = "/getShYxzfPm", method = RequestMethod.GET)
    public Result<?> getShYxzfPm(HttpServletRequest request, HttpServletResponse response) {
        List<SysjQhzhCjpm> shYxzfPmList = vTjfxSysjService.getYxzfpm("2","T");
        if (!shYxzfPmList.isEmpty() && shYxzfPmList.size() >= 10) {
            return Result.ok(shYxzfPmList.subList(0, 10));
        }
        return Result.ok(shYxzfPmList);
    }
    @RequestMapping(value = "/getShYxzfPmByW", method = RequestMethod.GET)
    public Result<?> getShYxzfPmByW(HttpServletRequest request, HttpServletResponse response) {
        List<SysjQhzhCjpm> shYxzfPmList = vTjfxSysjService.getYxzfpm("2","W");
        if (!shYxzfPmList.isEmpty() && shYxzfPmList.size() >= 10) {
            return Result.ok(shYxzfPmList.subList(0, 10));
        }
        return Result.ok(shYxzfPmList);
    }

    @RequestMapping(value = "/getShYxzfPmByYYYY", method = RequestMethod.GET)
    public Result<?> getShYxzfPmByYYYY(HttpServletRequest request, HttpServletResponse response) {
        List<SysjQhzhCjpm> shYxzfPmList = vTjfxSysjService.getYxzfpm("2","YYYY");
        if (!shYxzfPmList.isEmpty() && shYxzfPmList.size() >= 10) {
            return Result.ok(shYxzfPmList.subList(0, 10));
        }
        return Result.ok(shYxzfPmList);
    }


    @RequestMapping(value = "/getZHYxzfPmByW", method = RequestMethod.GET)
    public Result<?> getZHYxzfPmByW(HttpServletRequest request, HttpServletResponse response) {
        List<Zhyxzfpm> zhyxzfpmList = vTjfxSysjService.getZhYxzfpm("W");
        if (!zhyxzfpmList.isEmpty() && zhyxzfpmList.size() >= 10) {
            return Result.ok(zhyxzfpmList.subList(0, 10));
        }
        return Result.ok(zhyxzfpmList);
    }

    @RequestMapping(value = "/getZHYxzfPmByYYYY", method = RequestMethod.GET)
    public Result<?> getZHYxzfPmByYYYY(HttpServletRequest request, HttpServletResponse response) {
        List<Zhyxzfpm> zhyxzfpmList = vTjfxSysjService.getZhYxzfpm("YYYY");
        if (!zhyxzfpmList.isEmpty() && zhyxzfpmList.size() >= 10) {
            return Result.ok(zhyxzfpmList.subList(0, 10));
        }
        return Result.ok(zhyxzfpmList);
    }




    @RequestMapping(value = "/getYgYxzfsj", method = RequestMethod.GET)
    public Result<?> getYgYxzfsj(HttpServletRequest request, HttpServletResponse response) {
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        Map<String, Object> nhYxzfsj = vTjfxSysjService.getYgYxzfsj(sysUser.getWorkNo(), "1");
        Map<String, Object> shYxzfsj = vTjfxSysjService.getYgYxzfsj(sysUser.getWorkNo(), "2");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("nhYxzfsj", nhYxzfsj);
        jsonObject.put("shYxzfsj", shYxzfsj);
        return Result.ok(jsonObject);
    }

    @SneakyThrows
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Calendar cale = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        log.info(String.format("自动执行工作台数据提取1：" + DateUtils.getTimestamp()));
        String firstday;
        // 获取当前月的第一天
        cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        firstday = format.format(cale.getTime());
        String qybm = sysDicService.queryByCode("101001").getValue();
        if (StringUtils.isNotBlank(qybm) && QybmEnum.RUCHENG.getQybm().equals(qybm)) {
            nhxqService.init();
            //提取客户信息
            //需要同步到impala的表
            List<String> tableNameList = Stream.of(
                    "khxxgl_khxq_sh",
                    "loan_bwdk_sjmx",
                    "khgl_shglrxx",
                    "shgl_ywhywwlxx",
                    "khxxgl_qykhjbxx",
                    "loan_bwdk_sjmx",
                    "khxxgl_khxq_nh",
                    "khgl_nhhzxxgl",
                    "khxxgl_khjbzl",
                    "khxxgl_clkhxx",
                    "khxxgl_ywhywwlxx",
                    "khxxgl_ywhywwlxx_zh",
                    "khxxgl_grkhjbxx"
            ).collect(Collectors.toList());
            //同步oracle到impala
            tableNameList.forEach(item -> {
                EtlUtil.SHcallEtlRc(10, true, false, false, item, "idap");
            });
            EtlUtil.SHcallEtlRc(10, true, true, false, "hr_bas_organization", "idap");
            EtlUtil.SHcallEtlRc(10, true, true, false, "hr_bas_staff", "idap");

            //获取参数
            String csz_ckyrp = clkhxxglService.getCsz("CS0001");
            String csz_cknrp = clkhxxglService.getCsz("CS0002");
            String csz_dkyrp = clkhxxglService.getCsz("CS0003");
            String csz_dknrp = clkhxxglService.getCsz("CS0004");
            String zxrkrq = etlSgtzSjtbService.getZdrkrq(etlName == null ? "数据下发ETL任务" : etlName, dagName == null ? "etl_day调度" : dagName).replace("-","");
            System.out.println("----------------------------------最新入库日期:"+zxrkrq+"--------------------------------------");
            //调用python脚本
            //农户详细信息
//            System.out.println("---------------------------开始执行exec_nhxxxx-------------------");
//            sshUtil.execShell("docker exec kanas bash -c 'cd /root/trunk/src/ && source ./env_py2.7.sh && cd /root/trunk/src/tests/kanas/idap_test/ && python exec_nhxxxx.py'");
//            sshUtil.execShell("sh /home/exportdata/P_NHXXXX_EXPORT.sh");
//            sshUtil.execShell("su - oracle - /home/importdata/P_NHXXXX_IMPORT.sh");

            System.out.println("---------------------------开始执行exec_clgrkhxx-------------------");
            sshUtil.execShell("docker exec kanas bash -c 'cd /root/trunk/src/ && source ./env_py2.7.sh && cd /root/trunk/src/tests/kanas/idap_test/ && python exec_clgrkhxx.py'");
            sshUtil.execShell("sh /home/exportdata/P_CLGRKHXX_EXPORT.sh");
            sshUtil.execShell("su - oracle - /home/importdata/P_CLGRKHXX_IMPORT.sh");

            System.out.println("---------------------------开始执行exec_clqykhxx-------------------");
            sshUtil.execShell("docker exec kanas bash -c 'cd /root/trunk/src/ && source ./env_py2.7.sh && cd /root/trunk/src/tests/kanas/idap_test/ && python exec_clqykhxx.py'");
            sshUtil.execShell("sh /home/exportdata/P_CLQYKHXX_EXPORT.sh");
            sshUtil.execShell("su - oracle - /home/importdata/P_CLQYKHXX_IMPORT.sh");

            System.out.println("---------------------------开始执行exec_ywxxtq-------------------");
            QueryWrapper<KhxxglDksjmxZh> queryWrapper1=new QueryWrapper<>();
            queryWrapper1.apply("tjyf =to_date('"+zxrkrq+"','YYYY-MM-dd')");
            khxxglDksjmxZhService.remove(queryWrapper1);
            QueryWrapper<KhxxglCksjmxZh> queryWrapper2=new QueryWrapper<>();
            queryWrapper1.apply("tjyf =to_date('"+zxrkrq+"','YYYY-MM-dd')");
            khxxglCksjmxZhService.remove(queryWrapper2);
            clkhxxglService.delYwhxxwlDayBySjrq(zxrkrq);
            clkhxxglService.delYwhxxwlDayZhBySjrq(zxrkrq);
            String sb = "docker exec kanas bash -c 'cd /root/trunk/src/ && source ./env_py2.7.sh && cd /root/trunk/src/tests/kanas/idap_test/ && python exec_ywxxtq.py --fiscal_date " +
                    zxrkrq +
                    " --csz_ckyrp " +
                    csz_ckyrp +
                    " --csz_cknrp " +
                    csz_cknrp +
                    " --csz_dkyrp " +
                    csz_dkyrp +
                    " --csz_dknrp " +
                    csz_dknrp +
                    " --zxrkrq " +
                    zxrkrq +
                    "'";
            sshUtil.execShell(sb);
            System.out.println("---------------------------------------------------------"+sb);
            sshUtil.execShell("sh /home/exportdata/P_YWXXTQ_EXPORT.sh");
            sshUtil.execShell("su - oracle - /home/importdata/P_YWXXTQ_IMPORT.sh");
            System.out.println("同步完成P_YWXXTQ_IMPORT");

            System.out.println("---------------------------开始执行exec_khywxxdksjmx-------------------");
            //提取客户业务信息
            sshUtil.execShell("docker exec kanas bash -c 'cd /root/trunk/src/ && source ./env_py2.7.sh && cd /root/trunk/src/tests/kanas/idap_test/ && python exec_khywxxdksjmx.py'");
            sshUtil.execShell("sh /home/exportdata/P_KHYWXX_DKSJMX_EXPORT.sh");
            sshUtil.execShell("su - oracle - /home/importdata/P_KHYWXX_DKSJMX_IMPORT.sh");

            System.out.println("---------------------------开始执行exec_khywxxxgywtj-------------------");
            sshUtil.execShell("docker exec kanas bash -c 'cd /root/trunk/src/ && source ./env_py2.7.sh && cd /root/trunk/src/tests/kanas/idap_test/ && python exec_khywxxxgywtj.py --zxrkrq " + zxrkrq + "'");
            sshUtil.execShell("sh /home/exportdata/P_KHYWXX_XGYWTJ_EXPORT.sh");
            sshUtil.execShell("su - oracle - /home/importdata/P_KHYWXX_XGYWTJ_IMPORT.sh");

            System.out.println("---------------------------开始执行exec_shglrxx-------------------");
            //提取商户信息
            sshUtil.execShell("docker exec kanas bash -c 'cd /root/trunk/src/ && source ./env_py2.7.sh && cd /root/trunk/src/tests/kanas/idap_test/ && python exec_shglrxx.py --zxrkrq " + zxrkrq + "'");
            sshUtil.execShell("sh /home/exportdata/P_SHGLRXX_EXPORT.sh");
            sshUtil.execShell("su - oracle - /home/importdata/P_SHGLRXX_IMPORT.sh");

            System.out.println("---------------------------开始执行exec_shhmcxx-------------------");
            sshUtil.execShell("docker exec kanas bash -c 'cd /root/trunk/src/ && source ./env_py2.7.sh && cd /root/trunk/src/tests/kanas/idap_test/ && python exec_shhmcxx.py --zxrkrq " + zxrkrq + "'");
            sshUtil.execShell("sh /home/exportdata/P_SHHMCXX_EXPORT.sh");
            sshUtil.execShell("su - oracle - /home/importdata/P_SHHMCXX_IMPORT.sh");

            System.out.println("---------------------------开始执行exec_shywxxdksjmx-------------------");
            //提取商户业务信息
            sshUtil.execShell("docker exec kanas bash -c 'cd /root/trunk/src/ && source ./env_py2.7.sh && cd /root/trunk/src/tests/kanas/idap_test/ && python exec_shywxxdksjmx.py'");
            sshUtil.execShell("sh /home/exportdata/P_SHYWXX_DKSJMX_EXPORT.sh");
            sshUtil.execShell("su - oracle - /home/importdata/P_SHYWXX_DKSJMX_IMPORT.sh");

            System.out.println("---------------------------开始执行exec_shywxxxgywtj-------------------");
            sshUtil.execShell("docker exec kanas bash -c 'cd /root/trunk/src/ && source ./env_py2.7.sh && cd /root/trunk/src/tests/kanas/idap_test/ && python exec_shywxxxgywtj.py --zxrkrq " + zxrkrq + "'");
            sshUtil.execShell("sh /home/exportdata/P_SHYWXX_XGYWTJ_EXPORT.sh");
            sshUtil.execShell("su - oracle - /home/importdata/P_SHYWXX_XGYWTJ_IMPORT.sh");

            clkhxxglService.initKhxxRC(zxrkrq);
            vTjfxSysjService.initKhywxx();

            System.out.println("---------------------------开始执行exec_tjfxsxyxtj-------------------");
            //走访营销统计
            String endDate1 = etlSgtzSjtbService.getZdrkrq(etlName == null ? "数据下发ETL任务" : etlName, dagName == null ? "etl_day调度" : dagName).replace("-", "");
            List<String> tableNameList1 = Stream.of("khxxgl_khxq_sh", "tjfx_cssz", "khxxgl_khxq_nh").collect(Collectors.toList());
            //同步oracle到impala
            tableNameList1.forEach(item -> {
                EtlUtil.SHcallEtlRc(10, true, false, false, item, "idap");
            });
            QueryWrapper<Nhsxmx> queryWrapper = new QueryWrapper<Nhsxmx>();
            queryWrapper.apply("(tjrq =to_date('" + endDate1 + "','YYYY-MM-dd'))");
            nhsxmxService.remove(queryWrapper);
            QueryWrapper<Shsxmx> queryWrapper3 = new QueryWrapper<Shsxmx>();
            queryWrapper3.apply("(tjrq =to_date('" + endDate1 + "','YYYY-MM-dd'))");
            shsxmxService.remove(queryWrapper3);
            QueryWrapper<Nhyxmx> queryWrapper4 = new QueryWrapper<Nhyxmx>();
            queryWrapper4.apply("(tjrq =to_date('" + endDate1 + "','YYYY-MM-dd'))");
            nhyxmxService.remove(queryWrapper4);
            QueryWrapper<Shyxmx> queryWrapper5 = new QueryWrapper<Shyxmx>();
            queryWrapper5.apply(")tjrq =to_date('" + endDate1 + "','YYYY-MM-dd'))");
            shyxmxService.remove(queryWrapper5);
            //调用python脚本
            sshUtil.execShell("docker exec kanas bash -c 'cd /root/trunk/src/ && source ./env_py2.7.sh && cd /root/trunk/src/tests/kanas/idap_test/ && python exec_tjfxsxyxtj.py --fiscal_date " + endDate1 + "'");
            //同步impala到oracle
            sshUtil.execShell("sh /home/exportdata/P_TJFX_SXYXTJ_EXPORT.sh");
            sshUtil.execShell("su - oracle - /home/importdata/P_TJFX_SXYXTJ_IMPORT.sh");
            khjlZfyxtjService.syxyInit(endDate1);
            khjlZfyxtjService.init(DateUtil.format(new Date(), "yyyyMMdd"));
        } else {
            //提取客户信息
            vTjfxSysjService.initKhxx();
            log.info(String.format("自动执行工作台数据提取客户信息---end：" + DateUtils.getTimestamp()));
            //提取客户业务信息
            vTjfxSysjService.initKhywxx();
            log.info(String.format("自动执行工作台数据提取客户业务信息---end：" + DateUtils.getTimestamp()));
            //提取商户信息
            vTjfxSysjService.initShxx();
            log.info(String.format("自动执行工作台数据提取商户信息---end：" + DateUtils.getTimestamp()));
            //提取商户业务信息
            vTjfxSysjService.initShywxx();
            log.info(String.format("自动执行工作台数据提取商户业务信息---end：" + DateUtils.getTimestamp()));
            //走访营销统计
            khjlZfyxtjService.init(DateUtil.format(new Date(), "yyyyMMdd"));
            log.info(String.format("自动执行工作台数据提取走访营销统计---end：" + DateUtils.getTimestamp()));
            log.info(String.format("自动执行工作台数据提取2：" + DateUtils.getTimestamp()));
        }
    }
}
