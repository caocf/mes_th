package com.qm.mes.util.tree;

import java.sql.*;

import com.qm.th.security.MD5;

public class AccessCtrl
{
	public String message="";
	private DataServer_UserManage ds=null;
	
	public AccessCtrl(Connection con)throws Exception
	{
		ds=new DataServer_UserManage(con);
	}
	/*
	 * �û�������֤
	 */
	public boolean userProof(String username,String password)
	{
		MD5 m = new MD5();
		String password_32=m.getMD5ofStr(password);
		try{
			if(!ds.getExistUser(username))
			{
				this.message="�û��������ڣ�";
				return false;
			}
			String userid=ds.getUserID(username);
			String state=ds.getUserState(userid);
			if(state.trim().equals("0"))
			{
				this.message="���û��ѱ�ͣ�ã�";
				return false;
			}
			if(!ds.userProof(username,password_32))
			{
				this.message="���벻��ȷ��";
				return false;
			}
			return true;
		}catch(Exception e)
		{
			System.out.println("AccessCtrl��userProof(String userid,String username,String password)�����׳��쳣��"+e);
			this.message="�����쳣:"+e.getMessage();
			return false;
		}
	}
	public String getMessage()
	{
		return this.message;
	}
}
