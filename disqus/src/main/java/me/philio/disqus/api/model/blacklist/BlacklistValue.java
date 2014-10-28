package me.philio.disqus.api.model.blacklist;

import me.philio.disqus.api.model.user.UserDetails;

/**
 * A blacklist value, behaves like a {@link String} unless the {@link BlacklistEntry.Type} is
 * user, in which case should contain a valid {@link UserDetails} instance as well
 */
public class BlacklistValue {

    /**
     * String value
     */
    private String mValue;

    /**
     * User details
     */
    private UserDetails mUserDetails;

    /**
     * Set value
     *
     * @param value
     */
    public BlacklistValue(String value) {
        mValue = value;
    }

    /**
     * Set value and user details
     *
     * @param value
     * @param userDetails
     */
    public BlacklistValue(String value, UserDetails userDetails) {
        this(value);
        mUserDetails = userDetails;
    }

    @Override
    public String toString() {
        return mValue;
    }

    /**
     * Get user details
     *
     * @return
     */
    public UserDetails getUserDetails() {
        return mUserDetails;
    }

}
