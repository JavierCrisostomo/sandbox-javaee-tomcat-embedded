/**
@file
    SystemSetting.java
@author
    William Chang
@version
    0.1
@date
    - Created: 2017-02-05
    - Modified: 2017-02-16
    .
@note
    References:
    - General:
        - Nothing.
        .
    .
*/

package data.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@SuppressWarnings("serial")
public class SystemSetting implements Serializable {
    private UUID id;
    private String applicationName;
    private String name;
    private String value;
    private Date dateModified;

    public SystemSetting() {
        this.id = UUID.randomUUID();
        this.applicationName = "";
        this.name = "";
        this.value = "";
        this.dateModified = new Date();
    }

    public SystemSetting(String name, String value) {
        this.id = UUID.randomUUID();
        this.applicationName = "";
        this.name = name;
        this.value = value;
        this.dateModified = new Date();
    }

    public String getApplicationName() {
        return applicationName;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

