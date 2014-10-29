package me.philio.disqus.api.model.blacklists;

import me.philio.disqus.api.model.users.User;

/**
 * A blacklist value, behaves like a {@link String} unless the {@link BlacklistEntry.Type} is
 * user, in which case should contain a valid {@link me.philio.disqus.api.model.users.User} instance as well
 */
public class BlacklistValue {

    /**
     * String value
     */
    private String mValue;

    /**
     * User details
     */
    private User mUser;

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
     * @param user
     */
    public BlacklistValue(String value, User user) {
        this(value);
        mUser = user;
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
    public User getUserDetails() {
        return mUser;
    }

}
