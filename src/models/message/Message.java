package models.message;

import java.util.Collection;
import java.util.Date;

import models.user.User;

public interface Message {

    public Integer getId();

    public Date getSendDate();
    public void setSendDate(Date date);

    public User getSentFrom();
    public void setSentFrom(User user);

    public Collection<User> getViewedBy();
    public void setViewedBy(Collection<User> viewedBy);
    public void addViewedBy(User user);
    
    public String getContent();
}
