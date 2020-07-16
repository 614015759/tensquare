package com.atxzy.tensquare.dao;

import com.atxzy.tensquare.pojo.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FriendDao extends JpaRepository<Friend,String> {

    /**
     * 根据用户id与被关注用户id查询记录个数
     */
    @Query("select count(f) from Friend  f where f.userid=?1 and f.friendid =?2")
    public int selectCount(String userid,String friendid);

    /**
     * 更新为互相喜欢
     */
    @Modifying
    @Query("update Friend  f set f.islike=?3 where f.userid=?1 and f.friendid=?2")
    public void Like(String userid,String friendid,String islike);


    /**
     * 删除好友
     */
    @Modifying
    @Query("Delete from  Friend f where f.userid = ?1 and f.friendid = ?2")
    public void deleteFriend(String userid,String friendid);


}
