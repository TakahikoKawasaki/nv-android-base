/*
 * Copyright (C) 2014 Neo Visionaries Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.neovisionaries.android.oauth20;


/**
 * Listener associated with {@link OAuth20View}.
 *
 * @since 1.9
 *
 * @author Takahiko Kawasaki
 */
public interface OAuth20ViewListener
{
    /**
     * Called when an authorization code was issued by the authorization server.
     *
     * <p>
     * This happens only when the value of {@code response_type} in the
     * original authorization request was {@code "code"}.
     * </p>
     *
     * @param view
     *         The {@link OAuth20View} with which this listener is associated.
     *
     * @param code
     *         An authorization code issued by the authorization server.
     *
     * @param state
     *         The value of {@code state} parameter which was contained
     *         in the original authorization request sent from this client.
     */
    void onCodeIssued(OAuth20View view, String code, String state);


    /**
     * Called when an access token was issued by the authorization server.
     *
     * <p>
     * This happens only when the value of {@code response_type} in the
     * original authorization request was {@code "token"}.
     * </p>
     *
     * @param view
     *         The {@link OAuth20View} with which this listener is associated.
     *
     * @param accessToken
     *         An access token issued by the authorization server.
     *
     * @param state
     *         The value of {@code state} parameter which was contained
     *         in the original authorization request sent from this client.
     */
    void onTokenIssued(OAuth20View view, AccessToken accessToken, String state);


    /**
     * Called when the authorization server notified this client an error.
     *
     * @param view
     *         The {@link OAuth20View} with which this listener is associated.
     *
     * @param error
     *         An error ID.
     *
     * @param description
     *         The description of the error. May be {@code null}.
     *
     * @param uri
     *         The URL where the detail of the error is described.
     *         May be {@code null}.
     *
     * @param state
     *         The value of {@code state} parameter which was contained
     *         in the original authorization request sent from this client.
     */
    void onError(OAuth20View view, OAuth20Error error, String description, String uri, String state);
}
