package com.atxzy.tensquare.pojo;



import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "tb_friend")
@IdClass(Friend.class)
@Proxy(lazy = false)
public class Friend implements Serializable {
    @Id
    private String userid;

    @Id
    private String friendid;

    public String getIslike() {
        return islike;
    }

    public void setIslike(String islike) {
        this.islike = islike;
    }

    private String islike;

    public Friend() {
    }

    public String getUserId() {
        return userid;
    }

    public void setUserId(String userId) {
        this.userid = userId;
    }

    public String getFriendId() {
        return friendid;
    }

    public void setFriendId(String friendId) {
        this.friendid = friendId;
    }
}
