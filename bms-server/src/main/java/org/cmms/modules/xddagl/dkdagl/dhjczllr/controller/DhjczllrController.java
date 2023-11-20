package org.cmms.modules.xddagl.dkdagl.dhjczllr.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtils;
import org.cmms.modules.dictcache.IDictValueQuery;
import org.cmms.modules.dkjkpt.dhgl.dhjcbg.entity.VdkjkptDhdksjtz;
import org.cmms.modules.dkjkpt.dhgl.dhjcbg.service.IVdkjkptDhdksjtzService;
import org.cmms.modules.dkjkpt.dksjjk.dksjjktz.service.IDkjkptBndksjjktzService;
import org.cmms.modules.xddagl.dkdagl.dhjczllr.entity.Dhjczllr;
import org.cmms.modules.xddagl.dkdagl.dhjczllr.entity.DhjczllrVO;
import org.cmms.modules.xddagl.dkdagl.dhjczllr.entity.Dhjcbgfjxx;
import org.cmms.modules.xddagl.dkdagl.dhjczllr.service.IDhjczllrService;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.xddagl.dkdagl.dhjczllr.service.IDhjcbgfjxxService;

import org.cmms.modules.xddaglxt.dksjgl.dkhtsjgl.entity.HrBasStaffPostVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 贷后检查资料录入
 * @Author: jeecg-boot
 * @Date: 2022-01-18
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "贷后检查资料录入")
@RestController
@RequestMapping("/dhjczllr/dhjczllr")
public class DhjczllrController extends JeecgController<Dhjczllr, IDhjczllrService> {
    @Autowired
    private IDhjczllrService dhjczllrService;
    @Autowired
    private IDhjcbgfjxxService dkjkptDhjcbgfjxxService;
    @Autowired
    IDictValueQuery iDictValueQuery;
    @Autowired
    private IVdkjkptDhdksjtzService vdkjkptDhdksjtzService;

    @Value(value = "${common.path.upload}")
    private String uploadpath;
    @Autowired
    private IDkjkptBndksjjktzService dkjkptBndksjjktzService;
    /**
     * 分页列表查询
     *
     * @param dhjczllr
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "贷后检查资料录入-分页列表查询")
    @ApiOperation(value = "贷后检查资料录入-分页列表查询", notes = "贷后检查资料录入-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Dhjczllr dhjczllr,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<Dhjczllr> queryWrapper = QueryGenerator.initQueryWrapper(dhjczllr, req.getParameterMap());
        Page<Dhjczllr> page = new Page<Dhjczllr>(pageNo, pageSize);
        IPage<Dhjczllr> pageList = dhjczllrService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param
     * @return
     */
	/*@AutoLog(value = "贷后检查资料录入-添加")
	@ApiOperation(value="贷后检查资料录入-添加", notes="贷后检查资料录入-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Dhjczllr dhjczllr) {
		dhjczllrService.save(dhjczllr);
		return Result.ok("添加成功！");
	}*/
    @AutoLog(value = "贷后检查资料录入-添加")
    @ApiOperation(value = "贷后检查资料录入-添加", notes = "贷后检查资料录入-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody DhjczllrVO dhjczllrVO) {
        Dhjczllr dhjczllr = new Dhjczllr();
        BeanUtils.copyProperties(dhjczllrVO, dhjczllr);
        JSONArray fjxxs = dhjczllrVO.getImgdate();
        String fjnf = dhjczllrVO.getFjnf();
        String zllx = dhjczllrVO.getZllx();
        String fjsj = dhjczllrVO.getFjsj();
        String bz = dhjczllrVO.getBz();
        Dhjcbgfjxx wjxx = new Dhjcbgfjxx();
        if (fjxxs != null && fjxxs.size() > 0) {
            for (int i = 0; i < fjxxs.size(); i++) {
                if (fjxxs != null) {
                    String wllj = uploadpath + "/" + fjxxs.getJSONObject(i).getJSONObject("response").getString("message");
                    String fwlj =  "/" +fjxxs.getJSONObject(i).getJSONObject("response").getString("message");

                    wjxx.setFjnf(DateUtil.parse(fjnf));
                    wjxx.setWjid(Long.parseLong(iDictValueQuery.getSeqRateZxlldjbDjidNextval("SEQ_PUBLIC_ID.nextval")));
                    wjxx.setZjhm(dhjczllr.getZjhm());
                    wjxx.setFjlx(zllx);
                    wjxx.setFwlj(fwlj);
                    wjxx.setFjsjjd(fjsj);
                    wjxx.setWjlj(wllj);
                    wjxx.setBz(bz);
                    wjxx.setLrbz(1);
                    wjxx.setLrr(getUsername());
                    wjxx.setLrsj(new Timestamp(System.currentTimeMillis()));
                    dkjkptDhjcbgfjxxService.save(wjxx);
                }
            }
        }

        return Result.ok("添加成功！");
    }

    /**
     * 附件查询
     */
    @RequestMapping(value = "/queryFjxx",method = RequestMethod.GET)
    public Result<?> queryFjxx(@RequestParam(name = "zjhm",required = true)String zjhm){
        QueryWrapper<Dhjcbgfjxx> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("zjhm",zjhm);
        List<Dhjcbgfjxx> list = dkjkptDhjcbgfjxxService.list(queryWrapper);
        return Result.ok(list);
    }

    /**
     * 删除附件
     */
    @AutoLog(value = "贷款档案管理附件信息-通过id删除")
    @ApiOperation(value="贷款档案管理附件信息-通过id删除", notes="贷款档案管理附件信息-通过id删除")
    @DeleteMapping(value = "/deletefjxx")
    public Result<?> deletefjxx(@RequestParam(name="wjid",required=true) String wjid) {
        QueryWrapper<Dhjcbgfjxx> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("wjid",wjid);
        dkjkptDhjcbgfjxxService.remove(queryWrapper);
        return Result.ok("删除成功!");
    }


    /**
     * 查找带回
     */
    @AutoLog(value = "查找带回")
    @ApiOperation(value = "移交",notes = "贷款合同数据管理")
    @PostMapping(value = "/getListClaim")
    public Result<?> getListClaim(@RequestBody JSONObject jsonObject){
        System.out.println(jsonObject);
        String khmc = jsonObject.getString("khmc");
        String zjhm = jsonObject.getString("zjhm");
        List<VdkjkptDhdksjtz> list = vdkjkptDhdksjtzService.getListClaim(khmc,zjhm);
        return Result.ok(list);
    }


    /**
     * 编辑
     *
     * @param
     * @return
     */
    @AutoLog(value = "贷后检查资料录入-编辑")
    @ApiOperation(value = "贷后检查资料录入-编辑", notes = "贷后检查资料录入-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody DhjczllrVO dhjczllrVO) {
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        Dhjczllr dhjczllr = new Dhjczllr();
        BeanUtils.copyProperties(dhjczllrVO, dhjczllr);
        JSONArray fjxxs = dhjczllrVO.getImgdate();
        String fjnf = dhjczllrVO.getFjnf();
        String lx = dhjczllrVO.getZllx();
        String fjsj = dhjczllrVO.getFjsj();

        if (fjxxs != null && fjxxs.size() > 0) {
            for (int i = 0; i < fjxxs.size(); i++) {
                if (fjxxs != null) {
                    String fjname = (String) fjxxs.getJSONObject(i).get("name");
                    String fjlx = fjname.split("_")[0];
                    String wllj = uploadpath + "/" + fjxxs.getJSONObject(i).getJSONObject("response").getString("message");
                    String fwlj =  fjxxs.getJSONObject(i).getJSONObject("response").getString("message");
                    Dhjcbgfjxx wjxx = new Dhjcbgfjxx();

                    wjxx.setFjnf(DateUtil.parse(fjnf));
                    wjxx.setWjid(Long.parseLong(iDictValueQuery.getSeqRateZxlldjbDjidNextval("SEQ_PUBLIC_ID.nextval")));
                    wjxx.setZjhm(dhjczllr.getZjhm());
                    wjxx.setFjlx(lx);
                    wjxx.setFjsjjd(fjsj);
                    wjxx.setFwlj(fwlj);
                    wjxx.setWjlj(wllj);
                    wjxx.setBz(dhjczllrVO.getBz().trim());
                    wjxx.setLrbz(1);
                    wjxx.setLrr(sysUser.getUsername());
                    wjxx.setLrsj(new Timestamp(System.currentTimeMillis()));
                    dkjkptDhjcbgfjxxService.save(wjxx);
                }
            }
        }
        return Result.ok("操作成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "贷后检查资料录入-通过id删除")
    @ApiOperation(value = "贷后检查资料录入-通过id删除", notes = "贷后检查资料录入-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        dhjczllrService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "贷后检查资料录入-批量删除")
    @ApiOperation(value = "贷后检查资料录入-批量删除", notes = "贷后检查资料录入-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.dhjczllrService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "贷后检查资料录入-通过id查询")
    @ApiOperation(value = "贷后检查资料录入-通过id查询", notes = "贷后检查资料录入-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        Dhjczllr dhjczllr = dhjczllrService.getById(id);
        return Result.ok(dhjczllr);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param dhjczllr
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Dhjczllr dhjczllr) {
        return super.exportXls(request, dhjczllr, Dhjczllr.class, "贷后检查资料录入");
    }

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, Dhjczllr.class);
    }

}
