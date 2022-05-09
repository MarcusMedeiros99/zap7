package models.message;

import models.user.User;

public class MediaMessage extends Message {
    private MediaType mediaType;
    private String mediaPath;

    public MediaMessage(User sentFrom, MediaType mediaType, String mediaPath) {
        super(sentFrom);
        this.mediaType = mediaType;
        this.mediaPath = mediaPath;
    }

    public MediaMessage(User sentFrom, String mediaType, String mediaPath) {
        super(sentFrom);
        this.mediaType = MediaType.valueOf(mediaType);
        this.mediaPath = mediaPath;
    }

    @Override
    public String getContent() {
        return this.mediaType.toString();
    }

    @Override
    public void setContent(String content) {
        this.mediaType = MediaType.valueOf(content);
    }

    public String getMediaPath() {
        return mediaPath;
    }

    public void setMediaPath(String mediaPath) {
        this.mediaPath = mediaPath;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

}
