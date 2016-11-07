package demo;

import java.util.List;

public interface IUserDAO {
	public abstract User create(User user) throws Exception; //��Ӽ�¼
	public abstract void remove(User user) throws Exception; //ɾ����¼
	public abstract User find(User user) throws Exception;  //��ѯ��¼
	public abstract List<User> findAll() throws Exception;  //��ѯ���м�¼
	public abstract void update(User user) throws Exception; //�޸ļ�¼
}
