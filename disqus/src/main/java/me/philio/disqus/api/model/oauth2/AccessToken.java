package me.philio.disqus.api.model.oauth2;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import me.philio.disqus.AuthorizeActivity;

/**
 * Model representing an access token response
 *
 * This model implements the {@link Parcelable} interface to simplify passing it between activities,
 * for example when using {@link AuthorizeActivity} in an application.
 */
public class AccessToken implements Parcelable {

    /**
     * Parcelable creator implementation
     */
    public static final Creator<AccessToken> CREATOR = new Creator<AccessToken>() {

        @Override
        public AccessToken createFromParcel(Parcel source) {
            return new AccessToken(source);
        }

        @Override
        public AccessToken[] newArray(int size) {
            return new AccessToken[size];
        }

    };

    public String username;

    @SerializedName("user_id")
    public long userId;

    @SerializedName("access_token")
    public String accessToken;

    @SerializedName("expires_in")
    public long expiresIn;

    @SerializedName("token_type")

    public String tokenType;

    public String state;

    public String scope;

    @SerializedName("refresh_token")
    public String refreshToken;

    /**
     * No args constructor
     */
    public AccessToken() {
    }

    /**
     * Constructor with parcel
     *
     * @param source
     */
    public AccessToken(Parcel source) {
        username = source.readString();
        userId = source.readLong();
        accessToken = source.readString();
        expiresIn = source.readLong();
        tokenType = source.readString();
        state = source.readString();
        scope = source.readString();
        refreshToken = source.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeLong(userId);
        dest.writeString(accessToken);
        dest.writeLong(expiresIn);
        dest.writeString(tokenType);
        dest.writeString(state);
        dest.writeString(scope);
        dest.writeString(refreshToken);
    }

}
