package mes.ra.service.instruction;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import mes.framework.AdapterService;
import mes.framework.DataBaseType;
import mes.framework.ExecuteResult;
import mes.framework.IMessage;
import mes.framework.ServiceException;
import mes.framework.ServiceExceptionType;
import mes.ra.dao.DAO_Instruction_cache;
import mes.ra.factory.InstructionCacheFactory;
import mes.system.dao.DAOFactoryAdapter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**���ָ��
 * @author XuJia
 */

public class BreakInstruction extends AdapterService {
	/**
	 *�������
	 */ 
	private Connection con = null;
	/**
	 *�ֵõĵ�һ������
	 */
	private String count1= null;
	/**
	 *����
	 */
	private String count= null;
	/**
	 *����ָ��ı�ʶ��
	 */
	private String Str_produceMarker = null;
	/**
	 *Ŀ��ָ���˳���
	 */
	private String order = null;
	/**
	 *��������
	 */
	private String str_date=null;
	/**
	 *������Ԫ���
	 */
	private String ProduceUnitID=null;
	/**
	 *���
	 */
	private String workOrder=null;

	ResultSet rs=null;
	ResultSet rs1 = null;
	Statement stmt = null;
	int num = 0;
	int i;
	int d;
	int number;

	private final Log log = LogFactory.getLog(BreakInstruction.class);

	@Override
	public boolean checkParameter(IMessage message, String processid) {

		try {
			str_date=message.getUserParameterValue("str_date");
			workOrder=message.getUserParameterValue("workOrder");
			ProduceUnitID=message.getUserParameterValue("ProduceUnitID");
			con = (Connection) message.getOtherParameter("con"); 
			stmt = con.createStatement();   //��ʼ��
			count1 = message.getUserParameterValue("count1"); //�ֵõĵ�һ������
			count = message.getUserParameterValue("count");   //����
			Str_produceMarker = message.getUserParameterValue("Str_produceMarker");  //����ָ��ı�ʶ��
			order = message.getUserParameterValue("order");  //Ŀ��ָ���˳���
			d=(Integer.parseInt(count))-(Integer.parseInt(count1));  //���������ָ�������
			
			// ���log��Ϣ
			String debug = "����1��count1=" + count1 + "\n" + "��ʶ��Str_produceMarker=" + Str_produceMarker + "\n";
			log.debug("������̷���ʱ�û��ύ�Ĳ���: " + debug);
							
			return true;		
		} catch (SQLException e) {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
			e.printStackTrace();
			return true;
		}
	}
	@Override
	public ExecuteResult doAdapterService(IMessage message, String processid)
			throws SQLException, Exception {
		try {
			try {
												
				int max=0;
				stmt = con.createStatement(); 
				DAO_Instruction_cache dao = (DAO_Instruction_cache) DAOFactoryAdapter.getInstance(
				  DataBaseType.getDataBaseType(con), DAO_Instruction_cache.class);		
				// ����ѡ��ָ�������				
				InstructionCacheFactory instructionCacheFactory = new InstructionCacheFactory();
				con = (Connection) message.getOtherParameter("con");
				instructionCacheFactory.updateInstructionCacheNum((Integer.parseInt(count1)),(Integer.parseInt(order)), con,str_date,(Integer.parseInt(ProduceUnitID)),workOrder);
				// �������˳���				
				String sql1=dao.getInstructionMaxOrder((Integer.parseInt(ProduceUnitID)),str_date,workOrder);
				log.debug("����ָ�����˳���SQL��䣺"+sql1);
				rs1=stmt.executeQuery(sql1);
				if(rs1.next()){
					max=rs1.getInt(1);
					log.debug("���˳���Ϊ��"+max);
				}
				// �������ָ��ż�1
				for(i=max;i>=(Integer.parseInt(order)+1);i--){  
					String sql4=dao.selectInstructionCacheid(i,str_date,(Integer.parseInt(ProduceUnitID)),workOrder);
					log.debug("ͨ��˳��š�������Ԫ���������ڡ���β�ѯ��ʱָ��SQL��䣺"+sql4);
					rs=stmt.executeQuery(sql4);
					if (rs.next())
						instructionCacheFactory.updateInstructionCacheOrder(i, con,str_date,(Integer.parseInt(ProduceUnitID)),workOrder);
					log.info("������ʱָ��˳��ųɹ�");
				} 
				
				// ����ֽ���ָ��
				instructionCacheFactory.insertInstructionCache((Integer.parseInt(order)+1),Str_produceMarker,d,(Integer.parseInt(order)), con);
				log.info("������ʱָ��ɹ�");
				return ExecuteResult.sucess;
				
			} catch (SQLException sqle) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.DATABASEERROR, "���ݿ�����쳣", this
								.getId(), processid, new Date(), sqle));
				log.error("���ݿ��쳣");
				return ExecuteResult.fail;
			}
		} catch (Exception e) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.UNKNOWN, e.toString(), this.getId(),
					processid, new java.util.Date(), e));
			log.error("δ֪�쳣");
			return ExecuteResult.fail;
		} finally {
			if (rs1 != null)
				rs1.close();
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		}
		}

	@Override
	public void relesase() throws SQLException {
		order = null;
		count1 = null;
		Str_produceMarker=null;
		con = null;

	}

}
