package code.service;

import code.dao.UsersDao;
import code.entity.Users;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService {
	private final UsersDao usersDao;

	public MyUserDetailsService(UsersDao usersDao) {
		this.usersDao = usersDao;
	}

	/**
	 * loadUserByUsername
	 *
	 * @param username username
	 *
	 * @return UserDetails
	 *
	 * @throws UsernameNotFoundException UsernameNotFoundException
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = usersDao.selectByPrimaryKey(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return User.withUsername(user.getUsername()).password(user.getPassword()).roles("USER").build();
	}

	/**
	 * deleteByPrimaryKey
	 *
	 * @param username username
	 *
	 * @return data
	 */
	public int deleteByPrimaryKey(String username) {
		return usersDao.deleteByPrimaryKey(username);
	}

	/**
	 * insert
	 *
	 * @param record record
	 *
	 * @return data
	 */
	public int insert(Users record) {
		return usersDao.insert(record);
	}

	/**
	 * insertSelective
	 *
	 * @param record record
	 *
	 * @return data
	 */
	public int insertSelective(Users record) {
		return usersDao.insertSelective(record);
	}

	/**
	 * selectByPrimaryKey
	 *
	 * @param username username
	 *
	 * @return Users
	 */
	public Users selectByPrimaryKey(String username) {
		return usersDao.selectByPrimaryKey(username);
	}

	/**
	 * updateByPrimaryKeySelective
	 *
	 * @param record record
	 *
	 * @return data
	 */
	public int updateByPrimaryKeySelective(Users record) {
		return usersDao.updateByPrimaryKeySelective(record);
	}

	/**
	 * updateByPrimaryKey
	 *
	 * @param record record
	 *
	 * @return data
	 */
	public int updateByPrimaryKey(Users record) {
		return usersDao.updateByPrimaryKey(record);
	}
}