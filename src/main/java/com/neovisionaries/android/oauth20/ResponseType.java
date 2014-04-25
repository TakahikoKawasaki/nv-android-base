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
 * Values for {@code response_type}.
 *
 * <blockquote>
 * <p>
 * <a href="http://tools.ietf.org/html/rfc6749">RFC 6749 (OAuth 2.0)</a>,
 * <a href="http://tools.ietf.org/html/rfc6749#section-3.1.1">3.1.1. Response Type</a>
 * </p>
 * <dl>
 * <dt>{@code response_type}</dt>
 * <dd>
 * REQUIRED. The value MUST be one of {@code "code"} for requesting
 * an authorization code as described by
 * <a href="http://tools.ietf.org/html/rfc6749#section-4.1.1"
 * >Section 4.1.1</a>, {@code "token"} for requesting an access token
 * (implicit grant) as described by
 * <a href="http://tools.ietf.org/html/rfc6749#section-4.2.1"
 * >Section 4.2.1</a>, or a registered extension value as described by
 * <a href="http://tools.ietf.org/html/rfc6749#section-8.4"
 * >Section 8.4.</a>
 * </dd>
 * </dl>
 * </blockquote>
 *
 * @see <a href="http://tools.ietf.org/html/rfc6749#section-3.1.1"
 *      >RFC 6749 (OAuth 2.0), 3.1.1. Response Type</a>
 *
 * @since 1.9
 *
 * @author Takahiko Kawasaki
 */
public enum ResponseType
{
    /**
     * {@code response_type} to request an authorization code.
     */
    code,


    /**
     * {@code response_type} to request an access token (implicit grant).
     */
    token
    ;
}
