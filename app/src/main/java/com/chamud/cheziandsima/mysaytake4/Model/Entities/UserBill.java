package com.chamud.cheziandsima.mysaytake4.Model.Entities;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Iterator;

/**
 * Created by CheziAndSima on 07/09/2015.
 */
public class UserBill implements Serializable {
    int id;
    String description;
    String subject;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }


    public UserBill(JSONObject billJson) throws JSONException {

        Iterator<?> keys = billJson.keys();
        while (keys.hasNext()) {
            String key = (String) keys.next();

            switch (key) {
                case "id": {
                    this.id = billJson.getInt(key);
                    break;
                }
                case "description": {
                    this.description = billJson.getString(key);
                    break;
                }
                case "title": {
                    this.subject = billJson.getString(key);
                    break;
                }
                default:
                    break;
            }
        }

    }
}
