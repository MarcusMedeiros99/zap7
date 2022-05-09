package models.message;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import models.user.User;

public abstract class Message {
    private Integer id;
    private static int latestId = 0;
    private String content;
    private Date sendDate;
    private User sentFrom;
    private Collection<User> viewedBy;

    protected Message(User sentFrom) {
        this.id = getNextId();
        this.sendDate = new Date();
        this.viewedBy = new ArrayList<>();
        this.sentFrom = sentFrom;
    }

    public Message(User sentFrom, String content) {
        this.id = getNextId();
        this.sendDate = new Date();
        this.content = content;
        this.viewedBy = new ArrayList<>();
        this.sentFrom = sentFrom;
    }

    private static int getNextId() {
        return ++latestId;
    }

    public Integer getId() {
        return this.id;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public User getSentFrom() {
        return sentFrom;
    }

    public void setSentFrom(User sentFrom) {
        this.sentFrom = sentFrom;
    }

    public Collection<User> getViewedBy() {
        return viewedBy;
    }

    public void setViewedBy(Collection<User> viewedBy) {
        this.viewedBy = viewedBy;
        
    }

    public void addViewedBy(User user) {
        this.viewedBy.add(user);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
