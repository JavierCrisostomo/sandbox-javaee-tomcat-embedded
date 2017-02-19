/**
@file
    SystemSession.java
@author
    William Chang
@version
    0.1
@date
    - Created: 2017-02-16
    - Modified: 2017-02-16
    .
@note
    References:
    - General:
        - http://www.speakingcs.com/2014/11/date-and-time-in-java-8-introduction.html
        .
    .
*/

package data.entities;

import java.util.Date;

public class SystemSession {
    private String id;
    private String applicationName;
    private Date dateCreated;
    private Date dateAccessed;
    private Date dateExpire;
    private byte value;
    private String metadata;

    public SystemSession() {
        this.id = "";
        this.applicationName = "";
        this.dateCreated = new Date();
        this.dateAccessed = new Date();
        this.dateExpire = new Date(Long.MAX_VALUE); // toString() : Sun Aug 17 12:42:55 IST 292278994
        this.value =  new Byte("");
        this.metadata = "";
    }

    public String getApplicationName() {
        return applicationName;
    }

    public Date getDateAccessed() {
        return dateAccessed;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public Date getDateExpire() {
        return dateExpire;
    }

    public String getId() {
        return id;
    }

    public String getMetadata() {
        return metadata;
    }

    public byte getValue() {
        return value;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public void setDateAccessed(Date dateAccessed) {
        this.dateAccessed = dateAccessed;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setDateExpire(Date dateExpire) {
        this.dateExpire = dateExpire;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public void setValue(byte value) {
        this.value = value;
    }
}
