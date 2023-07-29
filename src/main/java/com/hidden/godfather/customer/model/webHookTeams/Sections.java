package com.hidden.godfather.customer.model.webHookTeams;
import java.io.Serializable;
import java.util.List;
public class Sections implements Serializable {
    public String activityTitle;
    public String activitySubtitle;
    public List<Facts> facts;
    public boolean markdown;
}