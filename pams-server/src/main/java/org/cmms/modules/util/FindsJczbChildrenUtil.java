package org.cmms.modules.util;

import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.khlc.jczbgl.entity.VpmaFBaseIndexType;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.entity.SysDepart;
import org.cmms.modules.system.model.*;

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
public class FindsJczbChildrenUtil {


    /**
     * queryTreeList的子方法 ====1=====
     * 该方法是s将SysDepart类型的list集合转换成SysDepartTreeModel类型的集合
     */
    public static List<JczbsTreeModel> wrapTreeDataToTreeList(List<VpmaFBaseIndexType> recordList) {
        // 在该方法每请求一次,都要对全局list集合进行一次清理
        //idList.clear();
    	List<JczbModel> idList = new ArrayList<JczbModel>();
        List<JczbsTreeModel> records = new ArrayList<>();
        for (int i = 0; i < recordList.size(); i++) {
            VpmaFBaseIndexType depart = recordList.get(i);
            records.add(new JczbsTreeModel(depart));
        }
        List<JczbsTreeModel> tree = findChildren(records, idList);

        setEmptyChildrenAsNull(tree);
        return tree;
    }


    /**
     * queryTreeList的子方法 ====2=====
     * 该方法是找到并封装顶级父类的节点到TreeList集合
     */
    private static List<JczbsTreeModel> findChildren(List<JczbsTreeModel> recordList,
                                                         List<JczbModel> departIdList) {

        List<JczbsTreeModel> treeList = new ArrayList<>();
        for (int i = 0; i < recordList.size(); i++) {
            JczbsTreeModel branch = recordList.get(i);
            if (oConvertUtils.isEmpty(branch.getPatentId())||branch.getPatentId().equals("0")) {
                treeList.add(branch);
                JczbModel departIdModel = new JczbModel().convert(branch);
                departIdList.add(departIdModel);
            }
        }
        getGrandChildren(treeList,recordList,departIdList);
        
        //idList = departIdList;
        return treeList;
    }


    /**
     * queryTreeList的子方法====3====
     *该方法是找到顶级父类下的所有子节点集合并封装到TreeList集合
     */
    private static void getGrandChildren(List<JczbsTreeModel> treeList,List<JczbsTreeModel> recordList,List<JczbModel> idList) {

        for (int i = 0; i < treeList.size(); i++) {
            JczbsTreeModel model = treeList.get(i);
            JczbModel idModel = idList.get(i);
            for (int i1 = 0; i1 < recordList.size(); i1++) {
                JczbsTreeModel m = recordList.get(i1);
                if (m.getPatentId()!=null && m.getPatentId().equals(model.getId())) {
                    model.getChildren().add(m);
                    JczbModel dim = new JczbModel().convert(m);
                    idModel.getChildren().add(dim);
                }
            }
            getGrandChildren(treeList.get(i).getChildren(), recordList, idList.get(i).getChildren());
        }

    }

    /**
     * queryTreeList的子方法 ====4====
     * 该方法是将子节点为空的List集合设置为Null值
     */
    private static void setEmptyChildrenAsNull(List<JczbsTreeModel> treeList) {

        for (int i = 0; i < treeList.size(); i++) {
            JczbsTreeModel model = treeList.get(i);
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

}
