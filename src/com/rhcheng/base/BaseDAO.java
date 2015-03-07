package com.rhcheng.base;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.rhcheng.common.Pagination;
import com.rhcheng.common.SysConstants;



/*******************************************************************************
 * DAO基类
 * 
 * <pre>
 * 为系统常用的数据库操作提供支持，同时包括分页功能
 * </pre>
 * 
 * @author RhCheng
 * @version 2013-07-01
 ******************************************************************************/
public abstract class BaseDAO extends JdbcDaoSupport {
	
	// 注入数据源
//	@Resource(name = "dataSource")
//	public void setMyDataSource(DataSource dataSource) {
//		super.setDataSource(dataSource);
//	}
	
	// 注入jdbcTemplate
	@Resource(name="jdbcTemplate")
	public void setMyJdbcTemplate(JdbcTemplate jdbcTemplate){
		super.setJdbcTemplate(jdbcTemplate);
	}
	
	
	private static Logger log = Logger.getLogger(BaseDAO.class);
	
	/*private DataFieldMaxValueIncrementer mysqlidGenarater;

	public DataFieldMaxValueIncrementer getMysqlidGenarater() {
		return mysqlidGenarater;
	}

	public void setMysqlidGenarater(DataFieldMaxValueIncrementer mysqlidGenarater) {
		this.mysqlidGenarater = mysqlidGenarater;
	}*/

	/**
	 * 获取序列的值
	 * Oracle sequence 从oracle 序列查找下一个值
	 * 
	 * @param sequenceName
	 * @author RhCheng
	 * @return int
	 * **/
	public long getSequenceId(String sequenceName) {
		StringBuffer sql = new StringBuffer(" SELECT ").append(sequenceName)
				.append(".NEXTVAL FROM DUAL");
		long id = 0;
		try {
			id = getJdbcTemplate().queryForLong(sql.toString());
			log.info("-------->getSequenceId............. " + sequenceName
					+ " The New id:" + id);
		} catch (DataAccessException e) {
			log.error("-------->sequence :" + sequenceName
					+ ".............Exception msg:" + e.getMessage());
			//e.printStackTrace();
			throw e;
		}
		return id;
	}

	/**
	 * mysql 获取sequenceid
	 * @return
	 */
	public long getSequenceIdOfMysql(String seqname){
		//String sql1 = " UPDATE tb_adminsystem_mysqlsequence SET sequenceid=LAST_INSERT_ID(sequenceid+1) ";
		String sql1 = " UPDATE sequence SET "+seqname+" = LAST_INSERT_ID( "+seqname+" +1) ";
		String sql2 = " SELECT LAST_INSERT_ID() ";
		int se = 0;
		try{
			this.saveORUpdate(sql1, new Object[]{});
			se = this.getJdbcTemplate().queryForInt(sql2);
			log.info("-------->getSequenceId.............  sequenceid "
					+ " The New id:" + se);
			
		}catch(DataAccessException e){
			log.error("-------->sequenceName : sequenceid.............Exception msg:" + e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return se;
	}
	
	
	
	/**
	 * 查找多条记录,查询结果封装成对象List
	 * 
	 * @param sql
	 *            查询的SQL
	 * @param args
	 *            参数
	 * @param mappedClass
	 *            封装的对象
	 * @author RhCheng
	 * @param <T>
	 * **/
	public <T> List<T> queryForListBean(String sql, Object[] args,
			Class<T> mappedClass) {
		List<T> rs = null;
		try {
			rs = getJdbcTemplate().query(sql, args,new BeanPropertyRowMapper<T>(mappedClass));
			log.info("----->queryForListBean{" + sql + ","
					+ mappedClass.getName() + "}..... Result list size:"
					+ (null != rs ? rs.size() : "null"));
		} catch (DataAccessException e) {
			log.error("-------->queryForListBean{" + sql + ","
					+ mappedClass.getName() + "}.............Exception msg:"
					+ e.getMessage());
			//e.printStackTrace();
			throw e;
		}
		return rs;
	}

	/**
	 * 查找单条记录
	 * 
	 * @param sql
	 *            查询的SQL
	 * @param args
	 *            参数
	 * @param mappedClass
	 *            封装的对象
	 * @author RhCheng
	 * **/
	public <T> T queryForBean(String sql, Object[] args, Class<T> mappedClass) {
		List<T> rs = null;
		try{
			rs = queryForListBean(sql, args, mappedClass);
			if (null != rs && rs.size() > 0) {
				log.info("----->queryForBean{" + sql + ","
						+ mappedClass.getName() + "}..... Result list size:"
						+ (null != rs ? rs.size() : "null"));
				if (rs.size() > 1) {
					log.warn("-------->queryForBean(" + sql + ","
							+ mappedClass.getName()
							+ ").............The search Result is not only one!");
				}
				return rs.get(0);
			}
		}catch(DataAccessException e){
			log.error("-------->queryForBean{" + sql + ","
					+ mappedClass.getName() + "}.............Exception msg:"
					+ e.getMessage());
			//e.printStackTrace();
			throw e;
		}
		
		return null;
	}

	/**
	 * 执行新增/更新/删除
	 * 
	 * @param sql
	 *            执行的SQL
	 * @param args
	 *            参数
	 * @author RhCheng
	 * **/
	public int saveORUpdate(String sql, Object... args) {
		int num = 0;
		try {
			num = getJdbcTemplate().update(sql, args);
			log.info("----->saveORUpdate(" + sql + ")----> effective Num:"
					+ num);
		} catch (DataAccessException e) {
			log.error("-------->saveORUpdate(" + sql
					+ ").............Exception msg:" + e.getMessage());
			//e.printStackTrace();
			throw e;
		}
		return num;
	}

	public int batchUpdate(String sql,List<Object[]> batchArgs){
		int[] num = getJdbcTemplate().batchUpdate(sql, batchArgs);
		return num.length;
	}
	
	
	/**
	 * 查找某一个属性
	 * 
	 * @param sql
	 *            执行的SQL
	 * @param args
	 *            参数
	 * @param elementType
	 *            查询的属性类型
	 * @author RhCheng
	 * **/
	public <T> List<T> findProperty(String sql, Object[] args,
			Class<T> elementType) {
		List<T> rs = null;
		try {
			rs = getJdbcTemplate().queryForList(sql, elementType, args);
			log.info("----->findProperty(" + sql + ")----> Result list size:"
					+ (null != rs ? rs.size() : "null"));
		} catch (DataAccessException e) {
			log.error("-------->findProperty(" + sql
					+ ").............Exception msg:" + e.getMessage());
			//e.printStackTrace();
			throw e;
		}
		return rs;
	}

	/**
	 * 查询结果封装成map,即数据与对应字段以map形式呈现，而非对象类型呈现
	 * 
	 * @param sql
	 *            执行的SQL
	 * @param args
	 *            参数
	 * @author RhCheng
	 * **/
	public List<Map<String, Object>> queryForMapList(String sql, Object... args) {
		List<Map<String, Object>> rs = null;
		try {
			rs = getJdbcTemplate().queryForList(sql, args);
			log.info("----->queryForMapList(" + sql
					+ ")----> Result list size:"
					+ (null != rs ? rs.size() : "null"));
		} catch (DataAccessException e) {
			log.error("-------->queryForMapList(" + sql
					+ ").............Exception msg:" + e.getMessage());
			//e.printStackTrace();
			throw e;
		}
		return rs;
	}

	/**
	 * 查询结果封装成map
	 * 
	 * @param sql
	 *            执行的SQL
	 * @param args
	 *            参数
	 * @author RhCheng
	 * **/
	public Map queryForMap(String sql, Object[] args) {
		Map map = null;
		try {
			map = this.getJdbcTemplate().queryForMap(sql, args);
			log.info("BaseDAO----------->queryForMap-------> sql:" + sql);
		} catch (DataAccessException e) {
			log.error("BaseDAO-------->queryForMap(" + sql
					+ ").............Exception msg:" + e.getMessage());
			//e.printStackTrace();
			throw e;
		}
		return map;
	}

	/**
	 * 查询总行数
	 * 
	 * @param sql
	 *            执行的SQL
	 * @param args
	 *            参数
	 * @author RhCheng
	 * **/
	public int queryCount(String sql, Object[] args) {
		int count = 0;
		try{
			count = this.getJdbcTemplate().queryForInt(sql, args);
			log.info("BaseDAO----------->queryCount-------> sql:( " + sql + " )resultSize:"+count);
		}catch(DataAccessException e){
			log.error("BaseDAO-------->queryCount(" + sql
					+ ").............Exception msg:" + e.getMessage());
			//e.printStackTrace();
			throw e;
		}
		return count;
	}
	
	
	/**
	 * 根据普通sql及相关参数获取分页查询的数据并返回分页实体对象
	 * 利用此方法，分页操作对程序员是透明的
	 * @whichdb 什么数据库
	 * @param pageSize
	 * @param toPage
	 * @param sql
	 * @param mappedClass
	 * @param args
	 * @return
	 * @author RhCheng
	 * 
	 */
	public <T> Pagination<T> queryForPage(int whichdb,int pageSize, int toPage, String sql,
			Class<T> mappedClass,Object... args) {
		String countSql = Pagination.countSql(sql);
		String querySql="";
		if(whichdb == SysConstants.ORCLA_SEARCH){//oracle
			querySql = Pagination.pageSql2_oracle(sql);
		}else if(whichdb == SysConstants.MYSQL_SEARCH ){//mysql
			querySql = Pagination.pageSql2_mysql(sql);
		}
		
		// 得到结果集总行数
		int totalCount =  this.queryCount(countSql, args);
		
		// 构造分页组件
		Pagination<T> page = new Pagination<T>(pageSize,toPage,totalCount);
		
		// 由普通sql根据形参条件生成分页sql
		String lastSql = page.generatePageSql(querySql);
		
		//根据分页sql查询对应数据并返回
		List<T> list = this.queryForListBean(lastSql,args,mappedClass);
		
		page.setObjLists(list);
		
		log.info("------->queryForPage-----pageSize:"+list==null?0:list.size());
		
		return page;

	}
	
	
	
	
}
