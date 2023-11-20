package org.cmms.modules.khlc.khfagl.controller;

import java.util.*;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.hr.yggl.ygxxgl.entity.Vhrbasstaffpost;
import org.cmms.modules.hr.zzgl.gwxxgl.service.IHrBasPostService;
import org.cmms.modules.khlc.khfagl.entity.*;
import org.cmms.modules.khlc.khfagl.service.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.khlc.khfagl.vo.ObjPostVo;
import org.cmms.modules.khlc.zbljgl.entity.ErpBasSjxArea;
import org.cmms.modules.khlc.zbljgl.service.IErpBasSjxAreaService;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 考核方案机构关系表
 * @Author: jeecg-boot
 * @Date:   2021-02-19
 * @Version: V1.0
 */
@Slf4j
@Api(tags="考核方案机构关系表")
@RestController
@RequestMapping("/khfagl/pmaASchemeOrgRel")
public class PmaASchemeOrgRelController extends JeecgController<PmaASchemeOrgRel, IPmaASchemeOrgRelService> {

	 @Autowired
	 private IPmaASchemeService pmaASchemeService;

	 @Autowired
	 private IPmaASchemeOrgRelService pmaASchemeOrgRelService;

	 @Autowired
	 private IPmaASchemePostRelService pmaASchemePostRelService;

	 @Autowired
	 private IHrBasOrganizationService hrBasOrganizationService;

	 @Autowired
	 private IHrBasPostService hrBasPostService;
	 @Autowired

	 private IPmaASchemeEvlobjRelService pmaASchemeEvlobjRelService;

	 @Autowired
	 private IPmaAShemeIndexRelService pmaAShemeIndexRelService;

	 @Autowired
	 private IErpAssessAljcService erpAssessAljcService;

	 @Autowired
	 private IErpAssessPhjfkService erpAssessPhjfkService;
	 @Autowired
	 private IErpBasSjxAreaService erpBasSjxAreaService;
	 /**
	 * 分页列表查询
	 *
	 * @param pmaASchemeOrgRel
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "考核方案机构关系表-分页列表查询")
	@ApiOperation(value="考核方案机构关系表-分页列表查询", notes="考核方案机构关系表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(PmaASchemeOrgRel pmaASchemeOrgRel,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<PmaASchemeOrgRel> queryWrapper = QueryGenerator.initQueryWrapper(pmaASchemeOrgRel, req.getParameterMap());
		Page<PmaASchemeOrgRel> page = new Page<PmaASchemeOrgRel>(pageNo, pageSize);
		IPage<PmaASchemeOrgRel> pageList = pmaASchemeOrgRelService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param objPostVo
	 * @return
	 */
	@AutoLog(value = "考核方案机构关系表-添加")
	@ApiOperation(value="考核方案机构关系表-添加", notes="考核方案机构关系表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ObjPostVo objPostVo) {

		String [] orgs= objPostVo.getObjIds();
		String []  postIds =objPostVo.getPostIds();
		String []  zbids =objPostVo.getZbids();

		System.out.println(objPostVo.getSchemeId());
		QueryWrapper queryWrapper=new QueryWrapper();
		queryWrapper.eq("scheme_id",objPostVo.getSchemeId());

		PmaAScheme pmaAScheme =pmaASchemeService.getOne(queryWrapper);
		pmaAScheme.setSpeRuleType(objPostVo.getTsgz());
		pmaASchemeService.updateById(pmaAScheme);

		pmaASchemeEvlobjRelService.remove(queryWrapper);
		if(objPostVo.getEvlObjType().equals("3")){
			//保存特殊规则
			List<PmaASchemeEvlobjRel> pmaASchemeEvlobjRelList =new ArrayList<>();
			List<Vhrbasstaffpost>  khdxs=objPostVo.getKhdxs();
			for (Vhrbasstaffpost khdx : khdxs) {
				PmaASchemeEvlobjRel pmaASchemeEvlobjRel= new PmaASchemeEvlobjRel();
				pmaASchemeEvlobjRel.setSchemeId(objPostVo.getSchemeId());
				pmaASchemeEvlobjRel.setEvlObjType(objPostVo.getEvlObjType());
				pmaASchemeEvlobjRel.setZzbz(khdx.getZzbz());
				pmaASchemeEvlobjRel.setGwbz(khdx.getGwbz());
				if (StringUtils.isNotEmpty(khdx.getYggh())) {
					pmaASchemeEvlobjRel.setYggh(khdx.getYggh());
				}
				pmaASchemeEvlobjRelList.add(pmaASchemeEvlobjRel);
			}
			pmaASchemeEvlobjRelService.saveBatch(pmaASchemeEvlobjRelList);
		}else{
//			for (String orgId : orgs) {
//				PmaASchemeEvlobjRel pmaASchemeEvlobjRel= new PmaASchemeEvlobjRel();
//				pmaASchemeEvlobjRel.setSchemeId(objPostVo.getSchemeId());
//				pmaASchemeEvlobjRel.setEvlObjType(objPostVo.getEvlObjType());
//				pmaASchemeEvlobjRel.setEvlObjId(orgId);
//				pmaASchemeEvlobjRelService.save(pmaASchemeEvlobjRel);
//			}
	    }

		if(objPostVo.getKhcj().equals("3")){
			erpAssessAljcService.remove(queryWrapper);
		}else{
			erpAssessPhjfkService.remove(queryWrapper);
		}

		List<ErpAssessAljc> erpAssessAljcList =new ArrayList<>();
		List<ErpAssessPhjfk> erpAssessPhjfkList =new ArrayList<>();

		if(objPostVo.getEvlObjType().equals("3")){
			for (String orgId : orgs) {
				for (String postId : postIds) {
					for (String zbid : zbids) {
						if(objPostVo.getKhcj().equals("3")){//按量计酬
							ErpAssessAljc  assessAljc=new ErpAssessAljc();
							assessAljc.setSchemeId(objPostVo.getSchemeId());
							assessAljc.setZzbz(orgId);
							assessAljc.setGwbz(Integer.parseInt(postId));
							assessAljc.setZbid(zbid);
							QueryWrapper queryWrapper1=new QueryWrapper();
							queryWrapper1.eq("khfs",objPostVo.getKhcj());
							queryWrapper1.eq("zblx",objPostVo.getEvlObjType());
							queryWrapper1.eq("zbid",zbid);
							ErpBasSjxArea one = erpBasSjxAreaService.getOne(queryWrapper1);
							assessAljc.setZbwd(one.getZbwd());
							erpAssessAljcList.add(assessAljc);
						}else if(objPostVo.getKhcj().equals("1")) {
							ErpAssessPhjfk  erpAssessPhjfk=new ErpAssessPhjfk();
							erpAssessPhjfk.setSchemeId(objPostVo.getSchemeId());
							erpAssessPhjfk.setZzbz(orgId);
							erpAssessPhjfk.setGwbz(Integer.parseInt(postId));
							erpAssessPhjfk.setZbid(zbid);
							QueryWrapper queryWrapper1=new QueryWrapper();
							queryWrapper1.eq("khfs",objPostVo.getKhcj());
							queryWrapper1.eq("zblx",objPostVo.getEvlObjType());
							queryWrapper1.eq("zbid",zbid);
							ErpBasSjxArea one = erpBasSjxAreaService.getOne(queryWrapper1);
							erpAssessPhjfk.setZbwd(one.getZbwd());
							erpAssessPhjfkList.add(erpAssessPhjfk);
						}

					}
				}
			}
			erpAssessAljcService.saveBatch(erpAssessAljcList);
			erpAssessPhjfkService.saveBatch(erpAssessPhjfkList);
		}else{
			for (String orgId : orgs) {
				for (String zbid : zbids) {
					if(objPostVo.getKhcj().equals("3")){//按量计酬
						ErpAssessAljc  assessAljc=new ErpAssessAljc();
						assessAljc.setSchemeId(objPostVo.getSchemeId());
						assessAljc.setZzbz(orgId);
						assessAljc.setZbid(zbid);
						QueryWrapper queryWrapper1=new QueryWrapper();
						queryWrapper1.eq("khfs",objPostVo.getKhcj());
						queryWrapper1.eq("zblx",objPostVo.getEvlObjType());
						queryWrapper1.eq("zbid",zbid);
						ErpBasSjxArea one = erpBasSjxAreaService.getOne(queryWrapper1);
						assessAljc.setZbwd(one.getZbwd());
						erpAssessAljcList.add(assessAljc);
					}else if(objPostVo.getKhcj().equals("1")) {
						ErpAssessPhjfk  erpAssessPhjfk=new ErpAssessPhjfk();
						erpAssessPhjfk.setSchemeId(objPostVo.getSchemeId());
						erpAssessPhjfk.setZzbz(orgId);
						erpAssessPhjfk.setZbid(zbid);
						QueryWrapper queryWrapper1=new QueryWrapper();
						queryWrapper1.eq("khfs",objPostVo.getKhcj());
						queryWrapper1.eq("zblx",objPostVo.getEvlObjType());
						queryWrapper1.eq("zbid",zbid);
						ErpBasSjxArea one = erpBasSjxAreaService.getOne(queryWrapper1);
						erpAssessPhjfk.setZbwd(one.getZbwd());
						erpAssessPhjfkList.add(erpAssessPhjfk);
					}
				}
			}
			erpAssessAljcService.saveBatch(erpAssessAljcList);
			erpAssessPhjfkService.saveBatch(erpAssessPhjfkList);
		}

		/*QueryWrapper queryWrapper=new QueryWrapper();
		queryWrapper.eq("scheme_id",objPostVo.getSchemeId());
		pmaASchemeOrgRelService.remove(queryWrapper);
		PmaAScheme pmaAScheme =pmaASchemeService.getOne(queryWrapper);
		pmaAScheme.setSpeRuleType(objPostVo.getTsgz());
		pmaASchemeService.updateById(pmaAScheme);

		String [] orgs= objPostVo.getObjIds();
		List<PmaASchemeOrgRel> pmaASchemeOrgRelList= new ArrayList<>();
		for (String orgId : orgs) {
			PmaASchemeOrgRel pmaASchemeOrgRel= new PmaASchemeOrgRel();
			pmaASchemeOrgRel.setOrgId(orgId);
			pmaASchemeOrgRel.setSchemeId(objPostVo.getSchemeId());
			pmaASchemeOrgRel.setOrgName(hrBasOrganizationService.queryByZzbz(orgId).getZzjc());
			pmaASchemeOrgRelList.add(pmaASchemeOrgRel);
		}
		pmaASchemeOrgRelService.saveBatch(pmaASchemeOrgRelList);
		pmaASchemePostRelService.remove(queryWrapper);
		String []  postIds =objPostVo.getPostIds();
		List<PmaASchemePostRel> pmaASchemePostRelList= new ArrayList<>();
		for (String postId : postIds) {
			PmaASchemePostRel pmaASchemePostRel= new PmaASchemePostRel();
			pmaASchemePostRel.setPostId(postId);
			pmaASchemePostRel.setPostName(hrBasPostService.queryByGwbz(postId));
			pmaASchemePostRel.setSchemeId(objPostVo.getSchemeId());
			pmaASchemePostRelList.add(pmaASchemePostRel);
		}
		pmaASchemePostRelService.saveBatch(pmaASchemePostRelList);
		pmaASchemeEvlobjRelService.remove(queryWrapper);
		if(objPostVo.getEvlObjType().equals("3")){
			List<PmaASchemeEvlobjRel> pmaASchemeEvlobjRelList =new ArrayList<>();
			List<Vhrbasstaffpost>  khdxs=objPostVo.getKhdxs();
			for (Vhrbasstaffpost khdx : khdxs) {
				PmaASchemeEvlobjRel pmaASchemeEvlobjRel= new PmaASchemeEvlobjRel();
				pmaASchemeEvlobjRel.setSchemeId(objPostVo.getSchemeId());
				pmaASchemeEvlobjRel.setEvlObjType(objPostVo.getEvlObjType());
				pmaASchemeEvlobjRel.setEvlObjId(khdx.getYggh());
				pmaASchemeEvlobjRelList.add(pmaASchemeEvlobjRel);
			}
			pmaASchemeEvlobjRelService.saveBatch(pmaASchemeEvlobjRelList);
		}*//*else{
			for (String orgId : orgs) {
				PmaASchemeEvlobjRel pmaASchemeEvlobjRel= new PmaASchemeEvlobjRel();
				pmaASchemeEvlobjRel.setSchemeId(objPostVo.getSchemeId());
				pmaASchemeEvlobjRel.setEvlObjType(objPostVo.getEvlObjType());
				pmaASchemeEvlobjRel.setEvlObjId(orgId);
				pmaASchemeEvlobjRelService.save(pmaASchemeEvlobjRel);
			}
		}*//*
		String []  zbids =objPostVo.getZbids();
		pmaAShemeIndexRelService.remove(queryWrapper);
        List<PmaAShemeIndexRel> pmaAShemeIndexRelList=new ArrayList<>();
		for (String zbid : zbids) {
			PmaAShemeIndexRel pmaAShemeIndexRel= new PmaAShemeIndexRel();
			pmaAShemeIndexRel.setSchemeId(objPostVo.getSchemeId());
			pmaAShemeIndexRel.setKhfs(objPostVo.getEvlObjType());
			pmaAShemeIndexRel.setKhcj(objPostVo.getKhcj());
			pmaAShemeIndexRel.setZbid(zbid);
			pmaAShemeIndexRelList.add(pmaAShemeIndexRel);
		}
		pmaAShemeIndexRelService.saveBatch(pmaAShemeIndexRelList);*/
		return Result.ok("添加成功！");
	}



	 /**
	  * 添加
	  *
	  * @param schemeId
	  * @return
	  */
	 @AutoLog(value = "考核方案机构关系表-添加")
	 @ApiOperation(value="考核方案机构关系表-添加", notes="考核方案机构关系表-添加")
	 @GetMapping(value = "/getOrgAndPost")
	 public Result<?> getOrgAndPost(@RequestParam(name="schemeId") String schemeId,@RequestParam(name="khcj") String khcj) {
		 QueryWrapper queryWrapper=new QueryWrapper();
		 queryWrapper.eq("scheme_id",schemeId);
		 List<String> orglist =new ArrayList<>();
		 List<Integer> postlist =new ArrayList<>();
		 List<String> zbidlist =new ArrayList<>();
		 if(khcj.equals("3")){
			 List<ErpAssessAljc> list = erpAssessAljcService.list(queryWrapper);
			 for(ErpAssessAljc erpAssessAljc:list){
				 orglist.add(erpAssessAljc.getZzbz());
				 postlist.add(erpAssessAljc.getGwbz());
				 zbidlist.add(erpAssessAljc.getZbid());
			 }
		 }else {
			 List<ErpAssessPhjfk> list = erpAssessPhjfkService.list(queryWrapper);
			 for(ErpAssessPhjfk erpAssessPhjfk:list){
				 orglist.add(erpAssessPhjfk.getZzbz());
				 postlist.add(erpAssessPhjfk.getGwbz());
				 zbidlist.add(erpAssessPhjfk.getZbid());
			 }
		 }

		 List<String> org = orglist.stream().distinct().collect(Collectors.toList());
		 List<Integer> post = postlist.stream().distinct().collect(Collectors.toList());
		 List<String> zbid = zbidlist.stream().distinct().collect(Collectors.toList());

		 JSONObject jsonObject=new JSONObject();
		 jsonObject.put("org",org);
		 jsonObject.put("post",post);
		 jsonObject.put("zbid",zbid);


		/* List<PmaASchemeOrgRel> pmaASchemeOrgRel=pmaASchemeOrgRelService.list(queryWrapper);
		 List<PmaASchemePostRel> pmaASchemePostRel=pmaASchemePostRelService.list(queryWrapper);
		 List<PmaAShemeIndexRel> pmaAShemeIndexRelList = pmaAShemeIndexRelService.list(queryWrapper);

		 int i=0;
		 int y=0;
		 int m=0;
		 String [] org= new String[pmaASchemeOrgRel.size()];
		 int [] post= new int[pmaASchemePostRel.size()];
		 for (PmaASchemeOrgRel orgs : pmaASchemeOrgRel) {
			 org[i]=orgs.getOrgId();
			 i++;
		 }
		 for (PmaASchemePostRel postId : pmaASchemePostRel) {
		 	System.out.println(postId.getPostId());
			 post[y]=Integer.parseInt(postId.getPostId());
			 y++;
		 }
		 String [] zbid= new String[pmaAShemeIndexRelList.size()];
		 for (PmaAShemeIndexRel zbids : pmaAShemeIndexRelList) {
			 zbid[m]=zbids.getZbid();
			 m++;
		 }

		 JSONObject jsonObject=new JSONObject();
		 jsonObject.put("org",org);
		 jsonObject.put("post",post);
		 jsonObject.put("zbid",zbid);*/
		 return Result.ok(jsonObject);
	 }

	
	/**
	 * 编辑
	 *
	 * @param pmaASchemeOrgRel
	 * @return
	 */
	@AutoLog(value = "考核方案机构关系表-编辑")
	@ApiOperation(value="考核方案机构关系表-编辑", notes="考核方案机构关系表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody PmaASchemeOrgRel pmaASchemeOrgRel) {
		pmaASchemeOrgRelService.updateById(pmaASchemeOrgRel);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "考核方案机构关系表-通过id删除")
	@ApiOperation(value="考核方案机构关系表-通过id删除", notes="考核方案机构关系表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		pmaASchemeOrgRelService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "考核方案机构关系表-批量删除")
	@ApiOperation(value="考核方案机构关系表-批量删除", notes="考核方案机构关系表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.pmaASchemeOrgRelService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "考核方案机构关系表-通过id查询")
	@ApiOperation(value="考核方案机构关系表-通过id查询", notes="考核方案机构关系表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		PmaASchemeOrgRel pmaASchemeOrgRel = pmaASchemeOrgRelService.getById(id);
		return Result.ok(pmaASchemeOrgRel);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param pmaASchemeOrgRel
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, PmaASchemeOrgRel pmaASchemeOrgRel) {
      return super.exportXls(request, pmaASchemeOrgRel, PmaASchemeOrgRel.class, "考核方案机构关系表");
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
      return super.importExcel(request, response, PmaASchemeOrgRel.class);
  }

}
