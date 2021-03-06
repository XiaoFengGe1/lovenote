package com.app.model;

// Generated 2016-3-21 11:16:01 by Hibernate Tools 4.3.1.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * User generated by hbm2java
 */
@Entity
@Table(name = "user", catalog = "lovenote")
public class User implements java.io.Serializable {

	private Integer id;
	private String name;
	private String password;
	private Date regtime;
	private Integer rank;
	private String email;
	private Long tel;
	private String sex;
	private Float money;
	/**
	 * md5加盐
	 */
	private String extendone;
	private String extendtwo;
	private Integer isdelete;
	private String pic;
	private String content;

	private Integer searchnum;
	private Integer indexnum;
	private String ip;
	private Long qq;
	private String weixin;
	private String weibo;
	private String qqcode;
	private String weixincode;
	private String weibocode;

	private Set<Review> reviews = new HashSet<Review>(0);

	public User() {
		this.extendone = "";
		this.extendtwo = "";
		this.money = (float) 0;
		this.rank = 0;
		this.tel = (long) 0;
		this.name = "";
		this.password = "";
		this.email = "";
		this.sex = "";
		this.isdelete = 0;
		this.regtime = new Date();
		this.pic = "";
		this.content = "";

		this.searchnum = 0;
		this.indexnum = 0;
		this.ip = "";
		this.qq = (long) 0;
		this.weixin = "";
		this.weibo = "";
		this.qqcode = "";
		this.weixincode = "";
		this.weibocode = "";
	}

	public User(Integer id) {
		this.id = id;
	}

	public User(String name) {
		this.name = name;
	}

	public User(String name, String password, Date regtime, Integer rank,
			String email, Long tel, String sex, Float money, String extendone,
			String extendtwo, Integer isdelete, String pic, String content,
			Integer searchnum, Integer indexnum, String ip, Long qq,
			String weixin, String weibo, String qqcode, String weixincode,
			String weibocode, Set<Review> reviews) {
		this.name = name;
		this.password = password;
		this.regtime = regtime;
		this.rank = rank;
		this.email = email;
		this.tel = tel;
		this.sex = sex;
		this.money = money;
		this.extendone = extendone;
		this.extendtwo = extendtwo;
		this.isdelete = isdelete;
		this.pic = pic;
		this.content = content;

		this.searchnum = searchnum;
		this.indexnum = indexnum;
		this.ip = ip;
		this.qq = qq;
		this.weixin = weixin;
		this.weibo = weibo;
		this.qqcode = qqcode;
		this.weixincode = weixincode;
		this.weibocode = weibocode;

		this.reviews = reviews;

	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "searchnum")
	public Integer getSearchnum() {
		return searchnum;
	}

	public void setSearchnum(Integer searchnum) {
		this.searchnum = searchnum;
	}

	@Column(name = "indexnum")
	public Integer getIndexnum() {
		return indexnum;
	}

	public void setIndexnum(Integer indexnum) {
		this.indexnum = indexnum;
	}

	@Column(name = "qq")
	public Long getQq() {
		return qq;
	}

	public void setQq(Long qq) {
		this.qq = qq;
	}

	@Column(name = "ip", length = 15)
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Column(name = "weixin", length = 20)
	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	@Column(name = "weibo", length = 20)
	public String getWeibo() {
		return weibo;
	}

	public void setWeibo(String weibo) {
		this.weibo = weibo;
	}

	@Column(name = "qqcode", length = 50)
	public String getQqcode() {
		return qqcode;
	}

	public void setQqcode(String qqcode) {
		this.qqcode = qqcode;
	}

	@Column(name = "weixincode", length = 50)
	public String getWeixincode() {
		return weixincode;
	}

	public void setWeixincode(String weixincode) {
		this.weixincode = weixincode;
	}

	@Column(name = "weibocode", length = 50)
	public String getWeibocode() {
		return weibocode;
	}

	public void setWeibocode(String weibocode) {
		this.weibocode = weibocode;
	}

	@Column(name = "name", length = 30)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "password", length = 200)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "regtime", length = 19)
	public Date getRegtime() {
		return this.regtime;
	}

	public void setRegtime(Date regtime) {
		this.regtime = regtime;
	}

	@Column(name = "rank")
	public Integer getRank() {
		return this.rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	@Column(name = "email", length = 50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "tel")
	public Long getTel() {
		return this.tel;
	}

	public void setTel(Long tel) {
		this.tel = tel;
	}

	@Column(name = "sex", length = 5)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "money", precision = 15, scale = 0)
	public Float getMoney() {
		return this.money;
	}

	public void setMoney(Float money) {
		this.money = money;
	}

	// md5加盐
	@Column(name = "extendone", length = 15)
	public String getExtendone() {
		return this.extendone;
	}

	public void setExtendone(String extendone) {
		this.extendone = extendone;
	}

	@Column(name = "extendtwo", length = 15)
	public String getExtendtwo() {
		return this.extendtwo;
	}

	public void setExtendtwo(String extendtwo) {
		this.extendtwo = extendtwo;
	}

	@Column(name = "isdelete")
	public Integer getIsdelete() {
		return this.isdelete;
	}

	public void setIsdelete(Integer isdelete) {
		this.isdelete = isdelete;
	}

	@Column(name = "pic")
	public String getPic() {
		return this.pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	@Column(name = "content")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
