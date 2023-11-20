package org.cmms.modules.system.util;

import org.cmms.common.constant.CommonConstant;
import org.cmms.common.util.RedisUtil;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.entity.SysDepart;
import org.cmms.modules.system.model.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * <P>
 * 对应部门的表,处理并查找树级数据
 * <P>
 * 
 * @Author: Steve
 * @Date: 2019-01-22
 */
public class FindsDepartsChildrenUtil {

	//部门树信息-树结构
	//private static List<SysDepartTreeModel> sysDepartTreeList = new ArrayList<SysDepartTreeModel>();
	
	//部门树id-树结构
    //private static List<DepartIdModel> idList = new ArrayList<>();


    /**
     * queryTreeList的子方法 ====1=====
     * 该方法是s将SysDepart类型的list集合转换成SysDepartTreeModel类型的集合
     */
    public static List<SysDepartTreeModel> wrapTreeDataToTreeList(List<SysDepart> recordList) {
        // 在该方法每请求一次,都要对全局list集合进行一次清理
        //idList.clear();
    	List<DepartIdModel> idList = new ArrayList<DepartIdModel>();
        List<SysDepartTreeModel> records = new ArrayList<>();
        for (int i = 0; i < recordList.size(); i++) {
            SysDepart depart = recordList.get(i);
            records.add(new SysDepartTreeModel(depart));
        }
        List<SysDepartTreeModel> tree = findChildren(records, idList);

        setEmptyChildrenAsNull(tree);
        return tree;
    }

    public static List<HrBasOrganizationTreeModel> wrapTreeDataToTreeListHr(List<HrBasOrganization> recordList) {
        // 在该方法每请求一次,都要对全局list集合进行一次清理
        //idList.clear();
        List<DepartIdHrModel> idList = new ArrayList<DepartIdHrModel>();
        List<HrBasOrganizationTreeModel> records = new ArrayList<>();
        for (int i = 0; i < recordList.size(); i++) {
            HrBasOrganization hr = recordList.get(i);
            records.add(new HrBasOrganizationTreeModel(hr));
        }

        List<HrBasOrganizationTreeModel> tree = findChildrenHr(records, idList);

        setEmptyChildrenAsNullHr(tree);

        return tree;
    }
    /**
     * 获取 DepartIdModel
     * @param recordList
     * @return
     */
    public static List<DepartIdModel> wrapTreeDataToDepartIdTreeList(List<SysDepart> recordList) {
        // 在该方法每请求一次,都要对全局list集合进行一次清理
        //idList.clear();
        List<DepartIdModel> idList = new ArrayList<DepartIdModel>();
        List<SysDepartTreeModel> records = new ArrayList<>();
        for (int i = 0; i < recordList.size(); i++) {
            SysDepart depart = recordList.get(i);
            records.add(new SysDepartTreeModel(depart));
        }
        findChildren(records, idList);
        return idList;
    }
    public static List<DepartIdHrModel> wrapTreeDataToDepartIdTreeListHr(List<HrBasOrganization> recordList) {
        // 在该方法每请求一次,都要对全局list集合进行一次清理
        //idList.clear();
        List<DepartIdHrModel> idList = new ArrayList<DepartIdHrModel>();
        List<HrBasOrganizationTreeModel> records = new ArrayList<>();
        for (int i = 0; i < recordList.size(); i++) {
            HrBasOrganization depart = recordList.get(i);
            records.add(new HrBasOrganizationTreeModel(depart));
        }
        findChildrenHr(records, idList);
        return idList;
    }

    /**
     * queryTreeList的子方法 ====2=====
     * 该方法是找到并封装顶级父类的节点到TreeList集合
     */
    private static List<SysDepartTreeModel> findChildren(List<SysDepartTreeModel> recordList,
                                                         List<DepartIdModel> departIdList) {

        List<SysDepartTreeModel> treeList = new ArrayList<>();
        for (int i = 0; i < recordList.size(); i++) {
            SysDepartTreeModel branch = recordList.get(i);
            if (oConvertUtils.isEmpty(branch.getParentId())) {
                treeList.add(branch);
                DepartIdModel departIdModel = new DepartIdModel().convert(branch);
                departIdList.add(departIdModel);
            }
        }
        getGrandChildren(treeList,recordList,departIdList);
        
        //idList = departIdList;
        return treeList;
    }


    private static List<HrBasOrganizationTreeModel> findChildrenHr(List<HrBasOrganizationTreeModel> recordList,
                                                         List<DepartIdHrModel> departIdList) {

        List<HrBasOrganizationTreeModel> treeList = new ArrayList<>();
        for (int i = 0; i < recordList.size(); i++) {
            HrBasOrganizationTreeModel branch = recordList.get(i);
            if (branch.getSjzzbz().equals("0")) {
                treeList.add(branch);
                DepartIdHrModel departIdModel = new DepartIdHrModel().convert(branch);
                departIdList.add(departIdModel);
            }
        }
        getGrandChildrenHr(treeList,recordList,departIdList);

        //idList = departIdList;
        return treeList;
    }
    /**
     * queryTreeList的子方法====3====
     *该方法是找到顶级父类下的所有子节点集合并封装到TreeList集合
     */
    private static void getGrandChildren(List<SysDepartTreeModel> treeList,List<SysDepartTreeModel> recordList,List<DepartIdModel> idList) {

        for (int i = 0; i < treeList.size(); i++) {
            SysDepartTreeModel model = treeList.get(i);
            DepartIdModel idModel = idList.get(i);
            for (int i1 = 0; i1 < recordList.size(); i1++) {
                SysDepartTreeModel m = recordList.get(i1);
                if (m.getParentId()!=null && m.getParentId().equals(model.getId())) {
                    model.getChildren().add(m);
                    DepartIdModel dim = new DepartIdModel().convert(m);
                    idModel.getChildren().add(dim);
                }
            }
            getGrandChildren(treeList.get(i).getChildren(), recordList, idList.get(i).getChildren());
        }

    }
    private static void getGrandChildrenHr(List<HrBasOrganizationTreeModel> treeList,List<HrBasOrganizationTreeModel> recordList,List<DepartIdHrModel> idList) {
        for (int i = 0; i < treeList.size(); i++) {
            HrBasOrganizationTreeModel model = treeList.get(i);
            DepartIdHrModel idModel = idList.get(i);
            for (int i1 = 0; i1 < recordList.size(); i1++) {
                HrBasOrganizationTreeModel m = recordList.get(i1);
                if (m.getSjzzbz()!=null && m.getSjzzbz().equals(model.getZzbz())) {
                    model.getChildren().add(m);
                    DepartIdHrModel dim = new DepartIdHrModel().convert(m);
                    idModel.getChildren().add(dim);
                }
            }
            getGrandChildrenHr(treeList.get(i).getChildren(), recordList, idList.get(i).getChildren());
        }

    }

    /**
     * queryTreeList的子方法 ====4====
     * 该方法是将子节点为空的List集合设置为Null值
     */
    private static void setEmptyChildrenAsNull(List<SysDepartTreeModel> treeList) {

        for (int i = 0; i < treeList.size(); i++) {
            SysDepartTreeModel model = treeList.get(i);
            if (model.getChildren().size() == 0) {
                model.setChildren(null);
                model.setIsLeaf(true);
            }else{
                setEmptyChildrenAsNull(model.getChildren());
                model.setIsLeaf(false);
            }
        }
        // sysDepartTreeList = treeList;
    }
    /**
     * queryTreeList的子方法 ====4====
     * 该方法是将子节点为空的List集合设置为Null值
     */
    private static void setEmptyChildrenAsNullHr(List<HrBasOrganizationTreeModel> treeList) {

        for (int i = 0; i < treeList.size(); i++) {
            HrBasOrganizationTreeModel model = treeList.get(i);
            if (model.getChildren().size() == 0) {
                model.setChildren(null);
                model.setIsLeaf(true);
            }else{
                setEmptyChildrenAsNullHr(model.getChildren());
                model.setIsLeaf(false);
            }
        }
        // sysDepartTreeList = treeList;
    }
}
