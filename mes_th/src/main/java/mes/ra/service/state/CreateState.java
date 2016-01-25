package mes.ra.service.state;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import mes.framework.AdapterService;

import mes.framework.ExecuteResult;
import mes.framework.IMessage;
import mes.framework.ServiceException;
import mes.framework.ServiceExceptionType;

import mes.ra.bean.State;

import mes.ra.factory.StateFactory;

	/**
	 * ����״̬
	 * 
	 * @author ���
	 * 
	 */
	public class CreateState extends AdapterService {
		/**
		 * �������
		 */
		private Connection con = null;
		/**
		 * ״̬��
		 */
		private String string_statename = null;
		/**
		 * ��ʽ����
		 */
		private String string_style= null;
		// 
		/**
		 * ��ʽ����
		 */
		private String string_styledesc= null;
		Statement stmt = null;
		ResultSet rs = null;
		StateFactory factory = new StateFactory();
		private final Log log = LogFactory.getLog(CreateState.class);
		@Override
		
		public boolean checkParameter(IMessage message, String processid) {
		
			con = (Connection) message.getOtherParameter("con");
			string_statename = message.getUserParameterValue("string_statename").trim();
			string_style = message.getUserParameterValue("string_style");
			string_styledesc = message.getUserParameterValue("string_styledesc");
			//���log��Ϣ
		    String debug="״̬����string_statename��" + string_statename
			+ " ��ʽ����string_style��"+string_style+ "\n";
		    log.info("���״̬�Ĳ���: " + debug);
			if (string_statename == null || string_style == null ) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.PARAMETERLOST, "�������Ϊ��", this.getId(),
						processid, new java.util.Date(), null));
				return false;
			}
			return true;
		}
		@Override
		public ExecuteResult doAdapterService(IMessage message, String processid)
				throws SQLException, Exception {
			try {
				try {					
					State state = new State();
					state.setStateName(string_statename);
					state.setStyledesc(string_styledesc);
					state.setStyle(string_style);
					state.setDelete(0);
					factory.createState(state, con);
					log.debug( "��Ӵ��������ɹ�!");
				
				}catch (SQLException sqle) {
					message.addServiceException(new ServiceException(
							ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
									.getId(), processid, new Date(), sqle));
					log.error("���״̬����ʱ,���ݿ��쳣"	+ sqle.toString());
					return ExecuteResult.fail;
				} 
			} catch (Exception e) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.UNKNOWN, e.toString(), this.getId(),
						processid, new java.util.Date(), e));
				log.fatal( "���״̬����ʱ,δ֪�쳣" + e.toString());
				return ExecuteResult.fail;
			}
			return ExecuteResult.sucess;
		}
		@Override
		public void relesase() throws SQLException {
			string_statename = null;
			string_style = null;
			con = null;
		}
	}


