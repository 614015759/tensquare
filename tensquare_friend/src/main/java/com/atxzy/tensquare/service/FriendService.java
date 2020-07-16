package com.atxzy.tensquare.service;

import com.atxzy.tensquare.client.UserClient;
import com.atxzy.tensquare.dao.FriendDao;
import com.atxzy.tensquare.dao.NoFriendDao;
import com.atxzy.tensquare.pojo.Friend;
import com.atxzy.tensquare.pojo.NoFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FriendService {
    @Autowired
    private FriendDao friendDao;

    @Autowired
    private NoFriendDao noFriendDao;

    @Autowired
    private UserClient userClient;

    @Transactional
    public int addFriend(String userid,String friendid){
        //判断如果用户已经添加了这个好友，则不进行任何操作，返回0
        if(friendDao.selectCount(userid,friendid) >0){
            return 0;
        }
        userClient.incFollows(userid,1);
        userClient.incFans(friendid,1);


        //向喜欢列表中添加记录
        Friend friend = new Friend();
        friend.setUserId(userid);
        friend.setFriendId(friendid);
        friend.setIslike("0");
        friendDao.save(friend);
        if (friendDao.selectCount(friendid,userid) > 0){
            friendDao.Like(userid,friendid,"1");
            friendDao.Like(friendid,userid,"1");
        }
        return 1;
    }



    public void addNoFriend(String userid,String friendid){
        NoFriend noFriend = new NoFriend();
        noFriend.setUserid(userid);
        noFriend.setFriendid(friendid);
        noFriendDao.save(noFriend);
    }

    public void deleteNoFriend(String userid,String friendid){
        NoFriend noFriend = new NoFriend();
        noFriend.setUserid(userid);
        noFriend.setFriendid(friendid);
        noFriendDao.delete(noFriend);
    }

    public void deleteFriend(String userid,String friendid){
        userClient.incFans(friendid,-1);
        userClient.incFollows(userid,-1);
        friendDao.deleteFriend(userid,friendid);
        friendDao.Like(userid,friendid,"0");
        addNoFriend(userid,friendid);

    }

}
