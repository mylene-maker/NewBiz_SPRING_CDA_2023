package com.humanbooster.Newbiz.config;

import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class UploadConfig {

    private String location = "upload";
    private final List<String> uploadImageTypes = new ArrayList<String>();

    public UploadConfig(){
        this.uploadImageTypes.add("image/jpeg");
        this.uploadImageTypes.add("image/png");
    }

    public String getLocation(){
        return location;
    }

    public List<String> getUploadImageTypes(){
        return this.uploadImageTypes;
    }

    public void setLocation(String location){
        this.location = location;
    }

}
