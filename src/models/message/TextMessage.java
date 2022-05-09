package models.message;

import models.user.User;

public class TextMessage extends Message {

    public TextMessage(User sentFrom, String content) {
        super(sentFrom, content);
    }

}
