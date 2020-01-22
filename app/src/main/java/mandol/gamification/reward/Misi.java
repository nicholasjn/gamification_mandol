package mandol.gamification.reward;

import java.io.Serializable;

public class Misi implements Serializable {
    private int _id;
    private String _tipe;
    private String _desc;
    private int _fp;

    public Misi(){

    }

    public Misi(int id, String tipe, String desc, int fp){
        this._id = id;
        this._tipe = tipe;
        this._desc = desc;
        this._fp = fp;
    }

    public void setID(int id){this._id = id;}
    public int getID() {return this._id;}
    public void setTipe(String tipe){this._tipe = tipe;}
    public String getTipe(){return this._tipe;}
    public void setDesc(String desc){this._desc = desc;}
    public String getDesc(){return this._desc;}
    public void setFP(int fp){this._fp = fp;}
    public int getFP(){return this._fp;}

}
