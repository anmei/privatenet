package com.rhcheng.taobaoassi.model;

import java.util.Date;

import com.rhcheng.util.date.DateUtils;

public class TaobaoModel extends ProductInfo{
	public String title;// 宝贝名称 需要过滤处理
	public Long cid;//宝贝在淘宝中所属类目，需要在导入淘宝助理后手动设置
	public Long seller_cids;// 商品在店铺中所属类目，需要在导入淘宝助理后手动设置
	public Integer stuff_status = 1;// 商品新旧程度,1：全新
	public String location_state = "广东"; // 省
	public String location_city = "东莞"; // 市
	public Integer item_type = 0;//出售方式
	public Float price;//宝贝价格
	public Float auction_increment;// 加价幅度
	public Integer sum;// 宝贝数量
	public Integer valid_thru;// 有效期
	public Integer freight_payer = 2;// 运费承担，默认使用快递
	public Integer post_fee = 0; // 平邮
	public Integer ems_fee = 0;//EMS
	public Integer express_fee = 0;// 快递
	public Integer has_invoice = 0;// 发票
	public Integer has_warranty;// 保修
	public Integer approve_status = 1;// 放入仓库
	public Integer has_showcase = 0; // 橱窗推荐
	public String list_time;//开始时间
	public String description;// 宝贝描述
	public String cateProps;// 宝贝属性
	public String postage_id;// 现有店铺中的邮费模板id
	public Float has_discount = 0f;//会员打折
	public String modified = DateUtils.formatDate(new Date(),"yyyy/MM/dd HH:mm");// 修改时间
	public Integer upload_fail_msg = 200;//主图片、销售属性图片上传状态
	public String picture_status;//图片状态
	public Float auction_point = 0f;// 返点比例
	public String picture;// 主图、销售属性图
	public String video;// 视频
	public String skuProps;// 销售属性组合
	public Integer inputPids;// 代表类目关键属性：品牌值
	public String inputValues;// 类目关键属性：货号
	public String outer_id;// 商家编码
	public String propAlias;// 销售属性别名
	public Integer auto_fill=0;// 代充类型
	public Integer num_id = 0;// 数字id
	public Integer local_cid = -1;// 本地id
	public Integer navigation_type = 1;// 宝贝分类
	public String user_name;// 用户名称
	public Integer syncStatus;//宝贝状态
	public Integer is_lighting_consigment;// 闪电发货
	public Integer is_xinpin;// 新品
	public String foodparame;// 食品专项
	public String features;// 尺码库
	public Integer buyareatype = 0;// 采购地
	public Integer global_stock_type = -1;// 库存类型
	public Integer global_stock_country;//国家地区
	public Integer sub_stock_type = 0;// 库存计数
	public String item_size;// 物流体积
	public Integer item_weight = 0;// 物流重量
	public Integer sell_promise = 1;// 退换货承偌
	public Integer custom_design_flag;// 定制工具
	public String wireless_desc;// 无线详情
	public String barcode;//商品条码
	public String sku_barcode;// SKU条码
	public String newprepay;//7天退货
	public String subtitle;// 宝贝卖点
	public String cpv_memo;//属性值备注
	public String input_custom_cpv;//自定义属性值
	public String qualification;//商品资质
	public String add_qualification;//增加商品资质
	public String o2o_bind_service;//关联线下服务
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getCid() {
		return cid;
	}
	public void setCid(Long cid) {
		this.cid = cid;
	}
	public Long getSeller_cids() {
		return seller_cids;
	}
	public void setSeller_cids(Long seller_cids) {
		this.seller_cids = seller_cids;
	}
	public Integer getStuff_status() {
		return stuff_status;
	}
	public void setStuff_status(Integer stuff_status) {
		this.stuff_status = stuff_status;
	}
	public String getLocation_state() {
		return location_state;
	}
	public void setLocation_state(String location_state) {
		this.location_state = location_state;
	}
	public String getLocation_city() {
		return location_city;
	}
	public void setLocation_city(String location_city) {
		this.location_city = location_city;
	}
	public Integer getItem_type() {
		return item_type;
	}
	public void setItem_type(Integer item_type) {
		this.item_type = item_type;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Float getAuction_increment() {
		return auction_increment;
	}
	public void setAuction_increment(Float auction_increment) {
		this.auction_increment = auction_increment;
	}
	public Integer getSum() {
		return sum;
	}
	public void setSum(Integer sum) {
		this.sum = sum;
	}
	public Integer getValid_thru() {
		return valid_thru;
	}
	public void setValid_thru(Integer valid_thru) {
		this.valid_thru = valid_thru;
	}
	public Integer getFreight_payer() {
		return freight_payer;
	}
	public void setFreight_payer(Integer freight_payer) {
		this.freight_payer = freight_payer;
	}
	public Integer getPost_fee() {
		return post_fee;
	}
	public void setPost_fee(Integer post_fee) {
		this.post_fee = post_fee;
	}
	public Integer getEms_fee() {
		return ems_fee;
	}
	public void setEms_fee(Integer ems_fee) {
		this.ems_fee = ems_fee;
	}
	public Integer getExpress_fee() {
		return express_fee;
	}
	public void setExpress_fee(Integer express_fee) {
		this.express_fee = express_fee;
	}
	public Integer getHas_invoice() {
		return has_invoice;
	}
	public void setHas_invoice(Integer has_invoice) {
		this.has_invoice = has_invoice;
	}
	public Integer getHas_warranty() {
		return has_warranty;
	}
	public void setHas_warranty(Integer has_warranty) {
		this.has_warranty = has_warranty;
	}
	public Integer getApprove_status() {
		return approve_status;
	}
	public void setApprove_status(Integer approve_status) {
		this.approve_status = approve_status;
	}
	public Integer getHas_showcase() {
		return has_showcase;
	}
	public void setHas_showcase(Integer has_showcase) {
		this.has_showcase = has_showcase;
	}
	public String getList_time() {
		return list_time;
	}
	public void setList_time(String list_time) {
		this.list_time = list_time;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCateProps() {
		return cateProps;
	}
	public void setCateProps(String cateProps) {
		this.cateProps = cateProps;
	}
	public String getPostage_id() {
		return postage_id;
	}
	public void setPostage_id(String postage_id) {
		this.postage_id = postage_id;
	}
	public Float getHas_discount() {
		return has_discount;
	}
	public void setHas_discount(Float has_discount) {
		this.has_discount = has_discount;
	}
	public String getModified() {
		return modified;
	}
	public void setModified(String modified) {
		this.modified = modified;
	}
	public Integer getUpload_fail_msg() {
		return upload_fail_msg;
	}
	public void setUpload_fail_msg(Integer upload_fail_msg) {
		this.upload_fail_msg = upload_fail_msg;
	}
	public String getPicture_status() {
		return picture_status;
	}
	public void setPicture_status(String picture_status) {
		this.picture_status = picture_status;
	}
	public Float getAuction_point() {
		return auction_point;
	}
	public void setAuction_point(Float auction_point) {
		this.auction_point = auction_point;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	public String getSkuProps() {
		return skuProps;
	}
	public void setSkuProps(String skuProps) {
		this.skuProps = skuProps;
	}
	public Integer getInputPids() {
		return inputPids;
	}
	public void setInputPids(Integer inputPids) {
		this.inputPids = inputPids;
	}
	public String getInputValues() {
		return inputValues;
	}
	public void setInputValues(String inputValues) {
		this.inputValues = inputValues;
	}
	public String getOuter_id() {
		return outer_id;
	}
	public void setOuter_id(String outer_id) {
		this.outer_id = outer_id;
	}
	public String getPropAlias() {
		return propAlias;
	}
	public void setPropAlias(String propAlias) {
		this.propAlias = propAlias;
	}
	public Integer getAuto_fill() {
		return auto_fill;
	}
	public void setAuto_fill(Integer auto_fill) {
		this.auto_fill = auto_fill;
	}
	public Integer getNum_id() {
		return num_id;
	}
	public void setNum_id(Integer num_id) {
		this.num_id = num_id;
	}
	public Integer getLocal_cid() {
		return local_cid;
	}
	public void setLocal_cid(Integer local_cid) {
		this.local_cid = local_cid;
	}
	public Integer getNavigation_type() {
		return navigation_type;
	}
	public void setNavigation_type(Integer navigation_type) {
		this.navigation_type = navigation_type;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public Integer getSyncStatus() {
		return syncStatus;
	}
	public void setSyncStatus(Integer syncStatus) {
		this.syncStatus = syncStatus;
	}
	public Integer getIs_lighting_consigment() {
		return is_lighting_consigment;
	}
	public void setIs_lighting_consigment(Integer is_lighting_consigment) {
		this.is_lighting_consigment = is_lighting_consigment;
	}
	public Integer getIs_xinpin() {
		return is_xinpin;
	}
	public void setIs_xinpin(Integer is_xinpin) {
		this.is_xinpin = is_xinpin;
	}
	public String getFoodparame() {
		return foodparame;
	}
	public void setFoodparame(String foodparame) {
		this.foodparame = foodparame;
	}
	public String getFeatures() {
		return features;
	}
	public void setFeatures(String features) {
		this.features = features;
	}
	public Integer getBuyareatype() {
		return buyareatype;
	}
	public void setBuyareatype(Integer buyareatype) {
		this.buyareatype = buyareatype;
	}
	public Integer getGlobal_stock_type() {
		return global_stock_type;
	}
	public void setGlobal_stock_type(Integer global_stock_type) {
		this.global_stock_type = global_stock_type;
	}
	public Integer getGlobal_stock_country() {
		return global_stock_country;
	}
	public void setGlobal_stock_country(Integer global_stock_country) {
		this.global_stock_country = global_stock_country;
	}
	public Integer getSub_stock_type() {
		return sub_stock_type;
	}
	public void setSub_stock_type(Integer sub_stock_type) {
		this.sub_stock_type = sub_stock_type;
	}
	public String getItem_size() {
		return item_size;
	}
	public void setItem_size(String item_size) {
		this.item_size = item_size;
	}
	public Integer getItem_weight() {
		return item_weight;
	}
	public void setItem_weight(Integer item_weight) {
		this.item_weight = item_weight;
	}
	public Integer getSell_promise() {
		return sell_promise;
	}
	public void setSell_promise(Integer sell_promise) {
		this.sell_promise = sell_promise;
	}
	public Integer getCustom_design_flag() {
		return custom_design_flag;
	}
	public void setCustom_design_flag(Integer custom_design_flag) {
		this.custom_design_flag = custom_design_flag;
	}
	public String getWireless_desc() {
		return wireless_desc;
	}
	public void setWireless_desc(String wireless_desc) {
		this.wireless_desc = wireless_desc;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getSku_barcode() {
		return sku_barcode;
	}
	public void setSku_barcode(String sku_barcode) {
		this.sku_barcode = sku_barcode;
	}
	public String getNewprepay() {
		return newprepay;
	}
	public void setNewprepay(String newprepay) {
		this.newprepay = newprepay;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public String getCpv_memo() {
		return cpv_memo;
	}
	public void setCpv_memo(String cpv_memo) {
		this.cpv_memo = cpv_memo;
	}
	public String getInput_custom_cpv() {
		return input_custom_cpv;
	}
	public void setInput_custom_cpv(String input_custom_cpv) {
		this.input_custom_cpv = input_custom_cpv;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getAdd_qualification() {
		return add_qualification;
	}
	public void setAdd_qualification(String add_qualification) {
		this.add_qualification = add_qualification;
	}
	public String getO2o_bind_service() {
		return o2o_bind_service;
	}
	public void setO2o_bind_service(String o2o_bind_service) {
		this.o2o_bind_service = o2o_bind_service;
	}
	
	
	
	
}
