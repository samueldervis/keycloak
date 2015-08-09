package org.keycloak.authentication;

import org.keycloak.provider.Provider;

import javax.ws.rs.core.Response;

/**
 * RequiredAction provider.  Required actions are one-time actions that a user must perform before they are logged in.
 *
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
public interface RequiredActionProvider extends Provider {
    /**
     * Called every time a uesr authenticates.  This checks to see if this required action should be triggered.
     * The implementation of this method is responsible for setting the required action on the UserModel.
     *
     * For example, the UpdatePassword required actions checks the password policies to see if the password has expired.
     *
     * @param context
     */
    void evaluateTriggers(RequiredActionContext context);

    /**
     * If the user has a required action set, this method will be the initial call to obtain what to display to the
     * user's browser.  Return null if no action should be done.
     *
     * @param context
     * @return
     */
    Response requiredActionChallenge(RequiredActionContext context);

    /**
     * This is an optional method.  If the required action has a more complex interaction, you can encapsulate it within
     * a REST service.  This method returns a JAX-RS sub locator object that can be referenced at:
     *
     * /realms/{realm}/login-actions/required-actions/{provider-id}
     *
     * @param context
     * @return
     */
    Object jaxrsService(RequiredActionContext context);

    /**
     * Provider id of this required action.  Must match ProviderFactory.getId().
     *
     * @return
     */
    String getProviderId();
}
