package com.sdu.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.sdu.entity.DataFile;

public class DataFileDaoImpl {
	SessionFactory sessionFactory;
	public DataFileDaoImpl() {
		//System.out.println("DataFileDaoImpl构造函数被调用!");
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		   this.sessionFactory = sessionFactory;
	}
	
	/*public List<DataFile> getByUserId(int userid){
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		//hql没有分号结尾

//		String hql = "from DataFile datafile where datafile.userid = '"+userid+"'";
		List<DataFile> list = null;
		try {
			String hql = "from DataFile datafile ";
			Query query = session.createQuery(hql);
			list = query.list();
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}
		return list;
	}*/
	public int getCountByUserId(int userId,String suffix){
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		int count = 0;
		try{
			String sql =null;
			if("all".equals(suffix)){
				sql="select count(*) from datafile where datafile.admin_dataid = '"+userId+"';";
			}else if("docAll".equals(suffix)){
				sql="select count(*) from datafile where datafile.admin_dataid = '"+userId+
						"' and datafile.d_suffix='doc' or datafile.d_suffix='pptx' or datafile.d_suffix='pdf';";
			}else if("imgAll".equals(suffix)){
				sql="select count(*) from datafile where datafile.admin_dataid = '"+userId+
						"' and datafile.d_suffix='jpg' or datafile.d_suffix='png' or datafile.d_suffix='gif';";				
			}else if("Video".equals(suffix)){
				sql="select count(*) from datafile where datafile.admin_dataid = '"+userId+
						"' and datafile.d_suffix='avi' or datafile.d_suffix='mp4' or datafile.d_suffix='flv';";			
			}else{
				sql="select count(*) from datafile where datafile.admin_dataid = '"+userId+"' and datafile.d_suffix='"+suffix+"';";
			}
			count = ((Number) session.createSQLQuery(sql).uniqueResult()).intValue();
			tx.commit();
		}catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}
		return count;
	}
	public List<Object> getByUserId(int userid,int offset,int pageSize,String suffix){
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		List<Object> list = null;
		try {
			String sql =null;
			if("all".equals(suffix)){
				sql="select d.d_id,d.d_name,p.p_name,d.d_type,d.d_size,d.d_createTime from datafile as d,project as p where d.pro_dataid = p.p_id and d.admin_dataid = '"+userid+"' limit "+offset+","+pageSize+";";
			}else if("docAll".equals(suffix)){
				sql="select d.d_id,d.d_name,p.p_name,d.d_type,d.d_size,d.d_createTime from datafile as d,project as p where d.pro_dataid = p.p_id and d.admin_dataid = '"+userid+
						"' and d.d_suffix='doc' or d.d_suffix='pptx' or d.d_suffix='txt' or d.d_suffix='pdf' limit "+offset+","+pageSize+";";
			}else if("imgall".equals(suffix)){
				sql="select d.d_id,d.d_name,p.p_name,d.d_type,d.d_size,d.d_createTime from datafile as d,project as p where d.pro_dataid = p.p_id and d.admin_dataid = '"+userid+
						"' and d.d_suffix='jpg' or d.d_suffix='png' or d.d_suffix='gif' limit "+offset+","+pageSize+";";
			}else if("Video".equals(suffix)){
				sql="select d.d_id,d.d_name,p.p_name,d.d_type,d.d_size,d.d_createTime from datafile as d,project as p where d.pro_dataid = p.p_id and d.admin_dataid = '"+userid+
						"' and d.d_suffix='avi' or d.d_suffix='mp4' or d.d_suffix='flv' limit "+offset+","+pageSize+";";
			}else{
				sql="select d.d_id,d.d_name,p.p_name,d.d_type,d.d_size,d.d_createTime from datafile as d,project as p where d.pro_dataid = p.p_id and d.admin_dataid = '"+userid+"' and d.d_suffix='"+suffix+"' limit "+offset+","+pageSize+";";
			}
			Query query = session.createSQLQuery(sql);
			list = query.list();
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}
		return list;
	}
	public int saveDateFile(DataFile dataFile){
	//	System.out.println("进入saveDataFile!");
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction =session.beginTransaction();
		try{
			session.saveOrUpdate(dataFile);
			transaction.commit();
		//	System.out.println("保存成功！");
			//System.out.println("dataFileId:"+dataFile.getD_id());
		}catch(Exception e){
			transaction.rollback();
			e.printStackTrace();
		//	System.out.println("保存失败");
			return 0;
		}
		return dataFile.getD_id();
	}
	public boolean removeById(int d_id){
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try{
			String hql = "delete from DataFile datafile where datafile.d_id = '"+d_id+"'";
		//	System.out.println(hql);
/*			session.createQuery(hql);*/
			session.createQuery(hql).executeUpdate();
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			tx.rollback();
			return false;
		}
		return true;
	}
	public boolean updateHashader(int d_id,String hasheader){
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			String hql  = "update DataFile datafile set datafile.d_hasheader = '"+hasheader+"' where datafile.d_id = '"+d_id+"'";
			//System.out.println("hql:"+hql);
			session.createQuery(hql).executeUpdate();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			return false;
		}
		return true;
	}
	//根据id查询数据文件
	public DataFile getDataFileById(int did){
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		//hql没有分号结尾
		DataFile dataFile = null;
		try {
			//根据id查询，返回的是object的对象，需要进行转换，否则无法使用Datafile的方法和属性
			dataFile = (DataFile) session.get(DataFile.class, did);
			tx.commit();
		//	System.out.println("查询成功");
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}
		return dataFile;
	}
	public List<Object> getDataFileByAdminId(int adminId){
		List<Object> list = null;
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try{
			String sql = "select distinct datafile.pro_dataid,datafile.d_type from datafile where datafile.admin_dataid = '"+adminId+"';";
			list = session.createSQLQuery(sql).list();
			//System.out.println("-----------------------sql:"+sql);
			tx.commit();
		}catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}
		return list;
	}
	public List<Object> getDataFilesByProjectId(int adminId, int project_id, int offset, int pageSize,String suffix) {
		List<Object> list = null;
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try{			
			String sql =null;
			if("all".equals(suffix)){
			  sql="select d.d_id,d.d_name,p.p_name,d.d_type,d.d_size,d.d_createTime  from datafile as d,project as p where d.pro_dataid = p.p_id and d.pro_dataid = '"+project_id+"' and d.admin_dataid = '"+adminId+"' limit "+offset+","+pageSize+";";
			}else if("docAll".equals(suffix)){
				sql="select d.d_id,d.d_name,p.p_name,d.d_type,d.d_size,d.d_createTime  from datafile as d,project as p where d.pro_dataid = p.p_id and d.pro_dataid = '"+project_id+"' and d.admin_dataid = '"+adminId+
						"' and d.d_suffix='doc' or d.d_suffix='pptx' or d.d_suffix='pdf' limit "+offset+","+pageSize+";";
			}else if("imgAll".equals(suffix)){
				sql="select d.d_id,d.d_name,p.p_name,d.d_type,d.d_size,d.d_createTime  from datafile as d,project as p where d.pro_dataid = p.p_id and d.pro_dataid = '"+project_id+"' and d.admin_dataid = '"+adminId+
						"' and d.d_suffix='jpg' or d.d_suffix='png' or d.d_suffix='gif' limit "+offset+","+pageSize+";";
			}else if("Video".equals(suffix)){
				sql="select d.d_id,d.d_name,p.p_name,d.d_type,d.d_size,d.d_createTime  from datafile as d,project as p where d.pro_dataid = p.p_id and d.pro_dataid = '"+project_id+"' and d.admin_dataid = '"+adminId+
						"' and d.d_suffix='avi' or d.d_suffix='mp4' or d.d_suffix='flv' limit "+offset+","+pageSize+";";
			}else{
				sql="select d.d_id,d.d_name,p.p_name,d.d_type,d.d_size,d.d_createTime  from datafile as d,project as p where d.pro_dataid = p.p_id and d.pro_dataid = '"+project_id+"' and d.admin_dataid = '"+adminId+"' and d.d_suffix='"+suffix+"' limit "+offset+","+pageSize+";";
			}
			list = session.createSQLQuery(sql).list();
			tx.commit();
		}catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}
		return list;
	}
	public List<Object> getDataFilesByProjectIdAndDataFileType(int adminId,int project_id, String datafile_type, int offset, int pageSize,String suffix) {
		List<Object> list = null;
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try{
			String sql =null;
			if("all".equals(suffix)){
				sql="select d.d_id,d.d_name,p.p_name,d.d_type,d.d_size,d.d_createTime  from datafile as d,project as p where p.p_id = d.pro_dataid and d.pro_dataid = '"+project_id+"' and d.admin_dataid='" +adminId+"' and d.d_type='"+datafile_type+"' limit "+offset+","+pageSize+";";
			}else if("docAll".equals(suffix)){
				sql="select d.d_id,d.d_name,p.p_name,d.d_type,d.d_size,d.d_createTime  from datafile as d,project as p where p.p_id = d.pro_dataid and d.pro_dataid = '"+project_id+"' and d.admin_dataid='" +adminId+"' and d.d_type='"+datafile_type+		
				 "' and d.d_suffix='doc' or d.d_suffix='pptx' or d.d_suffix='pdf' limit "+offset+","+pageSize+";";
			}else if("imgAll".equals(suffix)){
				sql="select d.d_id,d.d_name,p.p_name,d.d_type,d.d_size,d.d_createTime  from datafile as d,project as p where p.p_id = d.pro_dataid and d.pro_dataid = '"+project_id+"' and d.admin_dataid='" +adminId+"' and d.d_type='"+datafile_type+		
				 "' and d.d_suffix='jpg' or d.d_suffix='png' or d.d_suffix='gif' limit "+offset+","+pageSize+";";
			}else if("Video".equals(suffix)){
				sql="select d.d_id,d.d_name,p.p_name,d.d_type,d.d_size,d.d_createTime  from datafile as d,project as p where p.p_id = d.pro_dataid and d.pro_dataid = '"+project_id+"' and d.admin_dataid='" +adminId+"' and d.d_type='"+datafile_type+			
					"' and d.d_suffix='avi' or d.d_suffix='mp4' or d.d_suffix='flv' limit "+offset+","+pageSize+";";
			}else{
				sql="select d.d_id,d.d_name,p.p_name,d.d_type,d.d_size,d.d_createTime  from datafile as d,project as p where p.p_id = d.pro_dataid and d.pro_dataid = '"+project_id+"' and d.admin_dataid='" +adminId+"' and d.d_type='"+datafile_type+"' and d.d_suffix="+suffix+"' limit "+offset+","+pageSize+";";			
			}
	//		System.out.println("sql:"+sql);
			list = session.createSQLQuery(sql).list();
			tx.commit();
		}catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}
		return list;
	}
	public boolean removeByIds(String ids) {
		boolean result= false;
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try{
			String sql = "delete from datafile where datafile.d_id in ("+ids+");";
			session.createSQLQuery(sql).executeUpdate();
			tx.commit();
			result = true;
		}catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}
		return result;
	}
	public int getCountByProjectId(int adminId, int project_Id,String suffix) {
		int count = 0;
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try{
			String sql =null;
			if("all".equals(suffix)){
			   sql="select count(*) from datafile where datafile.admin_dataid = '"+adminId+"' and datafile.pro_dataid ='"+project_Id+"';";
			}else if("docAll".equals(suffix)){
				sql ="select count(*) from datafile where datafile.admin_dataid = '"+adminId+"' and datafile.pro_dataid ='"+project_Id+
						"' and datafile.d_suffix='doc' or datafile.d_suffix='pptx' or datafile.d_suffix='pdf';";
			}else if("imgAll".equals(suffix)){
				sql ="select count(*) from datafile where datafile.admin_dataid = '"+adminId+"' and datafile.pro_dataid ='"+project_Id+
						"' and datafile.d_suffix='jpg' or datafile.d_suffix='png' or datafile.d_suffix='gif';";				
			}else if("Video".equals(suffix)){
				sql ="select count(*) from datafile where datafile.admin_dataid = '"+adminId+"' and datafile.pro_dataid ='"+project_Id+
						"' and datafile.d_suffix='avi' or datafile.d_suffix='mp4' or datafile.d_suffix='flv';";			
			}else{
			  sql ="select count(*) from datafile where datafile.admin_dataid = '"+adminId+"' and datafile.pro_dataid ='"+project_Id+"' and datafile.d_suffix='"+suffix+"';";
			}			
			Query query = session.createSQLQuery(sql);
			count = ((Number)query.uniqueResult()).intValue();
			tx.commit();
		}catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}
		return count;
	}
	public int getCountByProjectIdAndDataFileType(int adminId, int project_Id,String datafile_type,String suffix){
		int count = 0;
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try{
			String sql =null;
			if("all".equals(suffix)){
				sql="select count(*) from datafile where datafile.admin_dataid = '"+adminId+"' and datafile.pro_dataid ='"+project_Id+"' and datafile.d_type='"+datafile_type+"';";
			}else if("docAll".equals(suffix)){
				sql="select count(*) from datafile where datafile.admin_dataid = '"+adminId+"' and datafile.pro_dataid ='"+project_Id+"' and datafile.d_type='"+datafile_type+
						"' and datafile.d_suffix='doc' or datafile.d_suffix='pptx' or datafile.d_suffix='pdf';";
			}else if("imgAll".equals(suffix)){
				sql="select count(*) from datafile where datafile.admin_dataid = '"+adminId+"' and datafile.pro_dataid ='"+project_Id+"' and datafile.d_type='"+datafile_type+
						"' and datafile.d_suffix='jpg' or datafile.d_suffix='png' or datafile.d_suffix='gif';";				
			}else if("Video".equals(suffix)){
				sql="select count(*) from datafile where datafile.admin_dataid = '"+adminId+"' and datafile.pro_dataid ='"+project_Id+"' and datafile.d_type='"+datafile_type+
						"' and datafile.d_suffix='avi' or datafile.d_suffix='mp4' or datafile.d_suffix='flv';";			
			}else{
				sql="select count(*) from datafile where datafile.admin_dataid = '"+adminId+"' and datafile.pro_dataid ='"+project_Id+"' and datafile.d_type='"+datafile_type+"' and datafile.d_suffix='"+suffix+"';";				
			}
			count = ((Number) session.createSQLQuery(sql).uniqueResult()).intValue();
			tx.commit();
		}catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}
		return count;
	}
	public List<Object> getDataFileTypeByProjectId(int projectId) {
		List<Object> list = null;
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try{
			String sql = "select distinct d.d_type from datafile as d,project as p where p.p_id = d.pro_dataid and p.p_id = '"+projectId+"';";
	//		System.out.println("sql"+sql);
			list = session.createSQLQuery(sql).list();
			tx.commit();
		}catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}
		return list;
	}
	public List<Object> getDataFilesByProjectIdAndDataFileType(int adminId,
			int project_id, String datafile_type) {
		List<Object> list = null;
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try{
			String sql = "select d.d_id,d.d_name,d.d_size,d_createTime from datafile as d where d.admin_dataid = '"+adminId+"' and d.pro_dataid='"+project_id+"' and d.d_type = '"+datafile_type+"';";
		//	System.out.println("sql:"+sql);
			list = session.createSQLQuery(sql).list();
			tx.commit();
		}catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}
		return list;
	}
	public boolean projectIsNull(int fileId) {
		boolean result = false;
		List<DataFile> list = null;
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try{
			String sql ="select * from datafile where datafile.d_id = '"+fileId+"' and datafile.pro_dataid is null;";
			list = session.createSQLQuery(sql).list();
			tx.commit();
			if(list.size()>0){
				result = true;
			}
		}catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}
		return result;
	} 
	public int getAdminIdByProjectId(int dataFileId){
		int adminId = 0;
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		try{
			String sql = "select datafile.admin_dataid from datafile where datafile.d_id = '"+dataFileId+"';";
			adminId = ((Number)session.createSQLQuery(sql).uniqueResult()).intValue();
			tx.commit();
		}catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}
		return adminId;
	}
	public List<Object> selectDataFile(String d_suffix) {
		Session session =sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		List list = null;
		try{
			String hql=null;
			if("all".equals(d_suffix)){
			   hql="select * from DataFile";
			}else{
			  hql ="select * from DataFile dataFile where d_suffix='"+d_suffix+"'";
			}
			Query query = session.createSQLQuery(hql);
			list = query.list();
			tx.commit();
		}catch(Exception e){
			tx.rollback();
			e.printStackTrace();
		}
		System.out.println("list::"+list);
		return list;
	}
	public int selectDataFileCount(String d_suffix) {
		 Session session = sessionFactory.getCurrentSession();
		 Transaction tx = session.beginTransaction();
		 int count =0;
		 try{
			 String hql=null;
			 if("all".equals(d_suffix)){
				   hql="select count(*) from DataFile";
			 }else{
				  hql ="select count(*) from DataFile dataFile where d_suffix='"+d_suffix+"'";
			}
			Query query = session.createQuery(hql);
			Object obj=query.uniqueResult();
			Long lobj = (Long) obj;
			count = lobj.intValue();
			 tx.commit();
		 }catch(Exception e){
			 tx.rollback();
			 e.printStackTrace();
		 }
		 System.out.println("count::"+count);
		return count;
	}

}
