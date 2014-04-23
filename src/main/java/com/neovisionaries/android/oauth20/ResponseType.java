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
