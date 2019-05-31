package com.example.tung.appmusic.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Baihatduocthich implements Parcelable{

@SerializedName("Idbaihat")
@Expose
private String idbaihat;
@SerializedName("Tenbaihat")
@Expose
private String tenbaihat;
@SerializedName("Hinhbaihat")
@Expose
private String hinhbaihat;
@SerializedName("Casi")
@Expose
private String casi;
@SerializedName("Linhbaihat")
@Expose
private String linhbaihat;
@SerializedName("Luotthich")
@Expose
private String luotthich;
@SerializedName("IdDongnhac")
@Expose
private String idDongnhac;
@SerializedName("lyric")
@Expose
private String lyric;

    protected Baihatduocthich(Parcel in) {
        idbaihat = in.readString();
        tenbaihat = in.readString();
        hinhbaihat = in.readString();
        casi = in.readString();
        linhbaihat = in.readString();
        luotthich = in.readString();
        idDongnhac = in.readString();
        lyric = in.readString();
    }

    public static final Creator<Baihatduocthich> CREATOR = new Creator<Baihatduocthich>() {
        @Override
        public Baihatduocthich createFromParcel(Parcel in) {
            return new Baihatduocthich(in);
        }

        @Override
        public Baihatduocthich[] newArray(int size) {
            return new Baihatduocthich[size];
        }
    };

    public String getIdbaihat() {
return idbaihat;
}

public void setIdbaihat(String idbaihat) {
this.idbaihat = idbaihat;
}

public String getTenbaihat() {
return tenbaihat;
}

public void setTenbaihat(String tenbaihat) {
this.tenbaihat = tenbaihat;
}

public String getHinhbaihat() {
return hinhbaihat;
}

public void setHinhbaihat(String hinhbaihat) {
this.hinhbaihat = hinhbaihat;
}

public String getCasi() {
return casi;
}

public void setCasi(String casi) {
this.casi = casi;
}

public String getLinhbaihat() {
return linhbaihat;
}

public void setLinhbaihat(String linhbaihat) {
this.linhbaihat = linhbaihat;
}

public String getLuotthich() {
return luotthich;
}
public void setLuotthich(String luotthich) {
this.luotthich = luotthich;
}
public String getIdDongnhac() {
    return idDongnhac;
}

public void setIdDongnhac(String idDongnhac) {
    this.idDongnhac = idDongnhac;
}
    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idbaihat);
        dest.writeString(tenbaihat);
        dest.writeString(hinhbaihat);
        dest.writeString(casi);
        dest.writeString(linhbaihat);
        dest.writeString(luotthich);
        dest.writeString(idDongnhac);
        dest.writeString(lyric);
    }
}
