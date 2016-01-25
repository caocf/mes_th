package mes.ra.service.state;

import java.sql.Connection;
import java.sql.SQLException;

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
 * �޸�ָ��״̬��Ϣ
 * 
 * @author xujia
 * 
 */
public class UpdateState extends AdapterService {
	/**
	 * �������
	 */
	private Connection con = null;
	/**
	 * ״̬��
	 */
	private String string_statename = null;
	/**
	 * ��ʽ
	 */
	private String string_style = null;	 
	/**
	 * ��ʽ����
	 */
	private String string_styledesc= null;
	private String int_id=null;
	StateFactory factory = new StateFactory();
	private final Log log = LogFactory.getLog(UpdateState.class);
	@Override
	public boolean checkParameter(IMessage message, String processid) {
		
		con = (Connection) message.getOtherParameter("con");
		string_statename = message.getUserParameterValue("string_statename").trim();
		string_styledesc = message.getUserParameterValue("string_styledesc");
		int_id=message.getUserParameterValue("int_id");
		//���log��Ϣ
	    String debug="״̬����string_statename:" + string_statename 
		+ "  ��ʽ���룺string_style:"+string_styledesc+ "\n";
	    log.info("����״̬���Ĳ���Ϊ: " + debug);
		string_style = message.getUserParameterValue("string_style");
		if (string_statename == null || string_style == null) {
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
				State state = new State();
				state.setId(Integer.parseInt(int_id));
				state.setStateName(string_statename);
				state.setStyledesc(string_styledesc);
				state.setStyle(string_style);
				state.setDelete(0);
				factory.updateState(state, con);
				log.debug( "���������ɹ�");
				
		} catch (Exception e) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.UNKNOWN, e.toString(), this.getId(),
					processid, new java.util.Date(), e));
			log.error( "���״̬����ʱ,δ֪�쳣" + e.toString());
			
			
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
