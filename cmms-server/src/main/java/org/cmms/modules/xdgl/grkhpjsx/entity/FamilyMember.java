package org.cmms.modules.xdgl.grkhpjsx.entity;
/**
 * 家庭成员基础信息类
 *
 */

public class FamilyMember {
	/**姓名*/
	private String name;
	/**与户主关系*/
	private String relation;
	/**与户主关系（中文）*/
	private String relationVal;
	/**生日*/
	private String birthday;
	/**从事职业*/
	private String profession;
	/**从事职业中文*/
	private String professionVal;
	/**身份证号*/
	private String idn;
	/**电话号码*/
	private String mobile;

	public FamilyMember() {
		// TODO Auto-generated constructor stub
	}

	public FamilyMember(String name, String relation, String relationVal, String birthday, String profession,
                        String professionVal, String idn, String mobile) {
		super();
		this.name = name;
		this.relation = relation;
		this.relationVal = relationVal;
		this.birthday = birthday;
		this.profession = profession;
		this.professionVal = professionVal;
		this.idn = idn;
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getRelationVal() {
		return relationVal;
	}

	public void setRelationVal(String relationVal) {
		this.relationVal = relationVal;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getProfessionVal() {
		return professionVal;
	}

	public void setProfessionVal(String professionVal) {
		this.professionVal = professionVal;
	}

	public String getIdn() {
		return idn;
	}

	public void setIdn(String idn) {
		this.idn = idn;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	
}
