package mandol.gamification.reward;

import java.io.Serializable;

public class Achievement implements Serializable {
    private int id_ach;
    private String title, title_desc, tier, tier_desc;

    public Achievement(){

    }

    public Achievement(int id_ach, String title, String title_desc, String tier, String tier_desc){
        this.id_ach = id_ach;
        this.title = title;
        this.title_desc = title_desc;
        this.tier = tier;
        this.tier_desc = tier_desc;
    }

    public void setID(int id_ach){this.id_ach = id_ach;}
    public int getID(){return this.id_ach;}
    public void setTitle(String title){this.title = title;}
    public String getTitle(){return this.title;}
    public void setTitleDesc(String title_desc){this.title_desc = title_desc;}
    public String getTitleDesc(){return this.title_desc;}
    public void setTier(String tier){this.tier = tier;}
    public String getTier(){return this.tier;}
    public void setTierDesc(String tier_desc){this.tier_desc = tier_desc;}
    public String getTierDesc(){return tier_desc;}

}
