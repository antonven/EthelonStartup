package myapps.wycoco.com.ethelonstartup.Libraries;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * Created by Acer on 05/12/2016.
 */

public class Travel implements Parcelable {
    String name;
    String image;

    public Travel(String name, String image) {
        this.name = name;
        this.image = image;
    }

    protected Travel(Parcel in) {
        name = in.readString();

    }

    public static final Creator<Travel> CREATOR = new Creator<Travel>() {
        @Override
        public Travel createFromParcel(Parcel in) {
            return new Travel(in);
        }

        @Override
        public Travel[] newArray(int size) {
            return new Travel[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        //est.write
    }
}
