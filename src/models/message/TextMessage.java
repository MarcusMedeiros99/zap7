package models.message;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import models.user.User;

public class TextMessage implements Message {
    private Integer id;
    private static int latestId = 0;
    private String content;
    private Date sendDate;
    private User sentFrom;
    private Collection<User> viewedBy;

    private int getNextId() {
        return ++latestId;
    }

    public TextMessage(User sentFrom, String content) {
        this.id = getNextId();
        this.sendDate = new Date();
        this.content = content;
        this.viewedBy = new ArrayList<>();
        this.sentFrom = sentFrom;
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

    @Override
    public User getSentFrom() {
        return sentFrom;
    }

    public void setSentFrom(User sentFrom) {
        this.sentFrom = sentFrom;
    }

    public Collection<User> getViewedBy() {
        return viewedBy;
    }

    @Override
    public void setViewedBy(Collection<User> viewedBy) {
        this.viewedBy = viewedBy;
        
    }

    @Override
    public void addViewedBy(User user) {
        this.viewedBy.add(user);
    }

    @Override
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
