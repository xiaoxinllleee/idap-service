package org.cmms.modules.xddagl.dkdagl.xfdkhtsjgl.controller;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.UUIDGenerator;
import org.cmms.common.util.oConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.dictcache.IDictValueQuery;
import org.cmms.modules.xddagl.dkdagl.cldkhtsjgl.controller.CldkhtsjglController;
import org.cmms.modules.xddagl.dkdagl.cldkhtsjgl.entity.CldkhtsjglFjxx;
import org.cmms.modules.xddagl.dkdagl.cldkhtsjgl.service.ICldkhtsjglFjxxService;
import org.cmms.modules.xddagl.dkdagl.xddaglxlh.entity.XddaglXlh;
import org.cmms.modules.xddagl.dkdagl.xddaglxlh.service.IXddaglXlhService;
import org.cmms.modules.xddagl.dkdagl.xfdkhtsjgl.entity.Xfdkhtsjgl;
import org.cmms.modules.xddagl.dkdagl.xfdkhtsjgl.entity.XfdkhtsjglFjVo;
import org.cmms.modules.xddagl.dkdagl.xfdkhtsjgl.entity.XfdkhtsjglVO;
import org.cmms.modules.xddagl.dkdagl.xfdkhtsjgl.service.IXfdkhtsjglService;
import org.cmms.modules.xddagl.dkdagl.xfdkhtsjgl.verify.XfdkhtsjglImportVerify;
import org.cmms.modules.xddagl.dkdaglfjxx.entity.DkdaglFjxx;
import org.cmms.modules.xddagl.dkdaglfjxx.service.IDkdaglFjxxService;
import org.cmms.modules.xddagl.xtgl.xddaglcsgl.service.IXddaglcsglService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 新放贷款合同数据管理
 * @Author: jeecg-boot
 * @Date:   2022-01-07
 * @Version: V1.0
 */
@Slf4j
@Api(tags="新放贷款合同数据管理")
@RestController
@RequestMapping("/xfdkhtsjgl/xfdkhtsjgl")
public class XfdkhtsjglController extends JeecgController<Xfdkhtsjgl, IXfdkhtsjglService> {
	@Autowired
	private IXfdkhtsjglService xfdkhtsjglService;
	@Autowired
	private XfdkhtsjglImportVerify xfdkhtsjglImportVerify;
	@Autowired
    private ICldkhtsjglFjxxService cldkhtsjglFjxxService;
	@Autowired
	private IDkdaglFjxxService dkdaglFjxxService;
	@Value(value = "${common.path.upload}")
    private String uploadpath;
	@Autowired
    IDictValueQuery iDictValueQuery;
	@Autowired
    private IXddaglXlhService xddaglXlhService;
	 @Autowired
	 private IXddaglcsglService iXddaglcsglService;
	private static final String STR_FORMAT = "000000";

	/**
	 * 分页列表查询
	 *
	 * @param xfdkhtsjgl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "新放贷款合同数据管理-分页列表查询")
	@ApiOperation(value="新放贷款合同数据管理-分页列表查询", notes="新放贷款合同数据管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Xfdkhtsjgl xfdkhtsjgl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper wrapper=new QueryWrapper();
		wrapper.eq("csbm","00001");
		String qyrq = iXddaglcsglService.getOne(wrapper).getCsz();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date date=null;
		try {
			date = sdf.parse(qyrq);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		QueryWrapper<Xfdkhtsjgl> queryWrapper = QueryGenerator.initQueryWrapper(xfdkhtsjgl, req.getParameterMap());
		Page<Xfdkhtsjgl> page = new Page<Xfdkhtsjgl>(pageNo, pageSize);
		queryWrapper.ge("qyrq",date);
		IPage<Xfdkhtsjgl> pageList = xfdkhtsjglService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param xfdkhtsjgl
	 * @return
	 */
	@AutoLog(value = "新放贷款合同数据管理-添加")
	@ApiOperation(value="新放贷款合同数据管理-添加", notes="新放贷款合同数据管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Xfdkhtsjgl xfdkhtsjgl) {
		xfdkhtsjglService.save(xfdkhtsjgl);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "新放贷款合同数据管理-编辑")
	@ApiOperation(value="新放贷款合同数据管理-编辑", notes="新放贷款合同数据管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody XfdkhtsjglFjVo xfdkhtsjglfjvo) {
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        String xlhstr="";
        long sjc=System.currentTimeMillis();
        Xfdkhtsjgl xfdkhtsjgl = new Xfdkhtsjgl();
        BeanUtils.copyProperties(xfdkhtsjglfjvo,xfdkhtsjgl);
        JSONArray fjxxs = xfdkhtsjglfjvo.getImgdate();
        String dhglsj = xfdkhtsjglfjvo.getDhglsj();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        DkdaglFjxx fjxx = new DkdaglFjxx();
        if (fjxxs != null && fjxxs.size() > 0){
            for (int i = 0; i < fjxxs.size(); i++){
                String id = UUIDGenerator.generate();
                String fjname = (String) fjxxs.getJSONObject(i).get("name");
                String fjlx = fjname.split("_")[0];
                if ("1".equals(fjlx)){
                    QueryWrapper<DkdaglFjxx> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("hth",xfdkhtsjgl.getHth());
                    queryWrapper.eq("fjlx","1");
                    List<DkdaglFjxx> list = dkdaglFjxxService.list(queryWrapper);
                    if (list.size()>0){
                        return Result.error("已存在此合同的档案信息,请勿重复上传！");
                    }
                }
                String wllj = uploadpath + "/" + fjxxs.getJSONObject(i).getJSONObject("response").getString("message");
                String fwlj = "/" + fjxxs.getJSONObject(i).getJSONObject("response").getString("message");
                fjxx.setWjid(Long.parseLong(iDictValueQuery.getSeqRateZxlldjbDjidNextval("SEQ_PUBLIC_ID.nextval")));
                fjxx.setHth(xfdkhtsjgl.getHth());
                fjxx.setDkzl(xfdkhtsjgl.getDkpz());
                fjxx.setFjlx(fjlx);
                try {
                    fjxx.setDhglsj(sdf.parse(dhglsj));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                fjxx.setWjlj(wllj);
                fjxx.setFwlj(fwlj);
                fjxx.setLrbz(1);
                fjxx.setLrr(sysUser.getUsername());
                fjxx.setLrsj(new Timestamp(System.currentTimeMillis()));
				dkdaglFjxxService.save(fjxx);
                if ("1".equals(fjlx)){
                    xfdkhtsjgl.setSfscda("是");
                    QueryWrapper<XddaglXlh> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("jgdm",xfdkhtsjgl.getJgdm());
                    XddaglXlh xlh = xddaglXlhService.getOne(queryWrapper);
                    if (xlh == null){
                        xlhstr = haoAddOne("0");
                        XddaglXlh xlh1 = new XddaglXlh();
                        xlh1.setXlh(xlhstr);
                        xlh1.setJgdm(xfdkhtsjgl.getJgdm());
                        xddaglXlhService.save(xlh1);
                    }else {
                        xlhstr = xlh.getXlh();
                        xlh.setXlh(xlhstr);
                        xlh.setJgdm(xfdkhtsjgl.getJgdm());
                        xddaglXlhService.update(xlh,queryWrapper);
                    }
                    if (xfdkhtsjgl.getHth().lastIndexOf("-")>0){
                        xfdkhtsjgl.setDabh(xfdkhtsjgl.getJgdm()+"-"+xfdkhtsjgl.getHth().split("-")[2]+"-"+xlhstr);
                    }else {
                        xfdkhtsjgl.setDabh(xfdkhtsjgl.getJgdm()+"-"+xfdkhtsjgl.getHth().substring(10,14)+"-"+xlhstr);
                    }
                }
            }
        }
        QueryWrapper<Xfdkhtsjgl> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("hth",xfdkhtsjgl.getHth());
        Xfdkhtsjgl xfdkhtsjgl1 = xfdkhtsjglService.getOne(queryWrapper);
		if (StringUtils.isNotEmpty(xfdkhtsjgl.getSfscda())) {
			xfdkhtsjgl1.setSfscda(xfdkhtsjgl.getSfscda());
		}
		if(StringUtils.isNotEmpty(xfdkhtsjgl.getDabh())) {
			xfdkhtsjgl1.setDabh(xfdkhtsjgl.getDabh());
		}
        xfdkhtsjgl1.setDkzrr(xfdkhtsjgl.getDkzrr());
        xfdkhtsjgl1.setDkpz(xfdkhtsjgl.getDkpz());
        xfdkhtsjgl1.setDkpzbc(xfdkhtsjgl.getDkpzbc());
        xfdkhtsjgl1.setLxdh(xfdkhtsjgl.getLxdh());
        xfdkhtsjgl1.setLxdz(xfdkhtsjgl.getLxdz());
        xfdkhtsjgl1.setLrbz(2);
        xfdkhtsjgl1.setLrsj(new Date());
        xfdkhtsjgl1.setLrr(sysUser.getUsername());
        xfdkhtsjglService.update(xfdkhtsjgl1,queryWrapper);
        return Result.ok("操作成功!");
	}

     public static String haoAddOne(String liuShuiHao){
         Integer intHao = Integer.parseInt(liuShuiHao);
         intHao++;
         DecimalFormat df = new DecimalFormat(STR_FORMAT);
         return df.format(intHao);
     }

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "新放贷款合同数据管理-通过id删除")
	@ApiOperation(value="新放贷款合同数据管理-通过id删除", notes="新放贷款合同数据管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		xfdkhtsjglService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "新放贷款合同数据管理-批量删除")
	@ApiOperation(value="新放贷款合同数据管理-批量删除", notes="新放贷款合同数据管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.xfdkhtsjglService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "新放贷款合同数据管理-通过id查询")
	@ApiOperation(value="新放贷款合同数据管理-通过id查询", notes="新放贷款合同数据管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Xfdkhtsjgl xfdkhtsjgl = xfdkhtsjglService.getById(id);
		return Result.ok(xfdkhtsjgl);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param xfdkhtsjgl
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Xfdkhtsjgl xfdkhtsjgl) {
      return super.exportXls(request, xfdkhtsjgl, Xfdkhtsjgl.class, "新放贷款合同数据管理");
  }

	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		 ModelAndView modelAndView = new ModelAndView(new JeecgEntityExcelView());
		 // 导出文件名称
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "新放贷款合同数据管理导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, XfdkhtsjglVO.class);
		 ExportParams exportParams = new ExportParams("新放贷款合同数据管理导入模板", "模板信息");
		 modelAndView.addObject(NormalExcelConstants.PARAMS, exportParams);
		 modelAndView.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
		 return modelAndView;
	 }

  /**
   * 通过excel导入数据
   *
   * @param request
   * @param response
   * @return
   */
  @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
      return super.importExcelByTemplate(jsonObject, request, response, Xfdkhtsjgl.class,XfdkhtsjglVO.class, xfdkhtsjglImportVerify);
  }

}
