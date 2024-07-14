package com.cod.dao;

import com.cod.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * UsersDao
 * @author admin
 */
@Repository
@Mapper
public interface UsersDao {
    /**
     * deleteByPrimaryKey
     * @param username username
     * @return data
     */
    int deleteByPrimaryKey(String username);

    /**
     * insert
     * @param record record
     * @return data
     */
    int insert(Users record);

    /**
     * insertSelective
     * @param record record
     * @return data
     */
    int insertSelective(Users record);

    /**
     * selectByPrimaryKey
     * @param username username
     * @return Users
     */
    Users selectByPrimaryKey(String username);

    /**
     * updateByPrimaryKeySelective
     * @param record record
     * @return data
     */
    int updateByPrimaryKeySelective(Users record);

    /**
     * updateByPrimaryKey
     * @param record record
     * @return data
     */
    int updateByPrimaryKey(Users record);
}