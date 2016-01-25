package mes.system.factory;

import java.sql.Connection;
import java.sql.SQLException;

import mes.system.elements.IElement;


public interface IElementFactory<element extends IElement> {

	public abstract element createElement();

	public abstract boolean update(element e, Connection con)
			throws SQLException;

	/**
	 * �洢Ԫ��<br>
	 * ��������Ԫ�ص�ʱ�򣬿��ܳ���ͬʱ�Զ������е����������<br>
	 * ���Դ˷���������Ҫ��ʱ�������ݿ������
	 * 
	 * @param e
	 *            Ŀ��Ԫ��
	 * @param con
	 *            ���ݿ�����
	 * @return 
	 * @throws SQLException
	 */
	public abstract boolean save(element e, Connection con) throws SQLException;

	/**
	 * ɾ��Ŀ����������͡����������ñ�־�á�1��
	 * 
	 * @param id
	 *            ��������id
	 * @param con
	 *            Ŀ�����ݿ�����
	 * @return ���ز����Ƿ�ɹ���trueΪ�ɹ�
	 * @throws SQLException
	 */
	public abstract boolean deleteElement(int id, Connection con)
			throws SQLException;

	/**
	 * ɾ��Ŀ����������͡����������ñ�־�á�1��
	 * 
	 * @param name
	 *            ��������
	 * @param con
	 *            Ŀ�����ݿ�����
	 * @return ���ز����Ƿ�ɹ���trueΪ�ɹ�
	 * @throws SQLException
	 */
	public abstract boolean deleteElement(String name, Connection con)
			throws SQLException;

	public abstract boolean deleteElement(element e, Connection con)
			throws SQLException;

	public abstract element queryElement(int id, Connection con)
			throws SQLException;

	public abstract element queryElement(String name, Connection con)
			throws SQLException;

}