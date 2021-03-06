package mes.pm.service.assembledoc;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

import mes.framework.AdapterService;
import mes.framework.ExecuteResult;
import mes.framework.IMessage;
import mes.framework.ServiceException;
import mes.framework.ServiceExceptionType;
import mes.pm.bean.AssDocItem;
import mes.pm.bean.AssembleDoc;
import mes.pm.factory.AssembleDocFactory;
import mes.util.SerializeAdapter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 添加装配指示单
 * 
 * @author Ypeng
 * 
 */
public class AddAssembleDoc extends AdapterService {
	/**
	 * 获得连接
	 */
	private Connection con = null;
	/**
	 * 装配指示单号
	 */
	private int int_id ;
	/**
	 * 装配指示单名
	 */
	private String name = null;
	/**
	 * 物料类型标示
	 */
	private String materiel = null;
	/**
	 * 描述信息
	 */
	private String description = null;
	/**
	 * String型装配指示项Map
	 */
	private String str_attr_val = null;
	/**
	 * 装配指示项Map
	 */
	private HashMap<?, ?> attr_val = new HashMap<String, String>();
	/**
	 * 类型转换对象
	 */
	private SerializeAdapter sa = new SerializeAdapter();
	/**
	 * 装配指示项数量
	 */
	private int attr_count = 0;
	/**
	 * 用户ID
	 */
	private int userid = 0;
	/**
	 * 日志
	 */
	private final Log log = LogFactory.getLog(AddAssembleDoc.class);
	/**
	 * 装配指示单名称数量
	 */
	int count=0;
	/**
	 * 装配指示单工厂
	 */
	AssembleDocFactory factory = new AssembleDocFactory();
	@Override
	public boolean checkParameter(IMessage message, String processid) {
		con = (Connection) message.getOtherParameter("con");
		name = message.getUserParameterValue("name");
		materiel = message.getUserParameterValue("materiel");
		description = message.getUserParameterValue("description");
		str_attr_val = message.getUserParameterValue("str_attr_val");
		try {
			attr_val = (HashMap<?,?>)sa.toObject(str_attr_val);
			userid = Integer.parseInt(message.getUserParameterValue("userid"));
			attr_count = Integer.parseInt(message.getUserParameterValue("attr_count"));
			count = factory.getAssembleDocCountByName(name, con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(count>0){
			message.addServiceException(new ServiceException(
				ServiceExceptionType.PARAMETERERROR, "装配指示单名称已经存在，请重新输入！", this.getId(),
				processid, new java.util.Date(), null));
			log.fatal("装配指示单名称已经存在");
			return false;
		}
		//输出log信息
	    String debug="创建用户ID："+userid+"；名称：" + name + "；"+ "物料类型标示："+materiel+ ";"
		+ "描述信息："+description;
	    log.debug("添加装配指示单时用户提交的参数: " + debug);
	    
		return true;
	}

	@Override
	public ExecuteResult doAdapterService(IMessage message, String processid)
			throws SQLException, Exception {
		try {
			try {
				AssembleDoc assembleDoc = new AssembleDoc();
				assembleDoc.setCreateUID(userid);
				assembleDoc.setName(name);
				assembleDoc.setMateriel(materiel);
				assembleDoc.setDescription(description);
				factory.saveAssembleDoc(assembleDoc, con);
				log.info("添加装配指示单服务成功！");
				int_id = factory.getAssembleDocIdByName(name, con);
				for(int i=1;i<=attr_count;i++){
					AssDocItem assDocItem = new AssDocItem();
					log.debug("第"+i+"个装配指示项---名字："+attr_val.get("str_itemname"+i)+
							"；子件标示："+attr_val.get("str_itemcode"+i)+"；描述："+attr_val.get("str_itemdes"+i));
					assDocItem.setAssDocId(int_id);
					assDocItem.setName(attr_val.get("str_itemname"+i).toString());
					assDocItem.setCode(attr_val.get("str_itemcode"+i).toString());
					assDocItem.setDescription(attr_val.get("str_itemdes"+i).toString());
					factory.saveAssDocItem(assDocItem,con);
				}
				log.info("添加装配指示项成功");
			} catch (SQLException sqle) {
				message.addServiceException(new ServiceException(
						ServiceExceptionType.DATABASEERROR, "数据库操作异常", this
								.getId(), processid, new Date(), sqle));
				log.error("数据库异常，中断服务。");
				return ExecuteResult.fail;
			}
		} catch (Exception e) {
			message.addServiceException(new ServiceException(
					ServiceExceptionType.UNKNOWN, e.toString(), this.getId(),
					processid, new java.util.Date(), e));
			log.error("未知异常，中断服务。");
			return ExecuteResult.fail;
		}
		return ExecuteResult.sucess;
	}

	@Override
	public void relesase() throws SQLException {
		con = null;

	}

}
