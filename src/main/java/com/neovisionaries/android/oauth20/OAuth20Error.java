package com.neovisionaries.android.oauth20;


/**
 * Values for {@code error} from OAuth 2.0 endpoints.
 *
 * @see <a href="http://tools.ietf.org/html/rfc6749#section-4.1.2.1"
 *      >RFC 6749 (OAuth 2.0), 4.1.2.1. Error Response</a>
 *
 * @see <a href="http://tools.ietf.org/html/rfc6749#section-4.2.2.1"
 *      >RFC 6749 (OAuth 2.0), 4.2.2.1. Error Response</a>
 *
 * @see <a href="http://tools.ietf.org/html/rfc6749#section-5.2"
 *      >RFC 6749 (OAuth 2.0), 5.2. Error Response</a>
 *
 * @author Takahiko Kawasaki
 */
public enum OAuth20Error
{
    /**
     * The resource owner or authorization server denied the
     * request.
     */
    access_denied,


    /**
     * Client authentication failed (e.g., unknown client, no
     * client authentication included, or unsupported
     * authentication method).  The authorization server MAY
     * return an HTTP 401 (Unauthorized) status code to indicate
     * which HTTP authentication schemes are supported.  If the
     * client attempted to authenticate via the "Authorization"
     * request header field, the authorization server MUST
     * respond with an HTTP 401 (Unauthorized) status code and
     * include the "WWW-Authenticate" response header field
     * matching the authentication scheme used by the client.
     */
    invalid_client,


    /**
     * The provided authorization grant (e.g., authorization
     * code, resource owner credentials) or refresh token is
     * invalid, expired, revoked, does not match the redirection
     * URI used in the authorization request, or was issued to
     * another client.
     */
    invalid_grant,


    /**
     * The request is missing a required parameter, includes an
     * invalid parameter value, includes a parameter more than
     * once, or is otherwise malformed.
     */
    invalid_request,


    /**
     * The requested scope is invalid, unknown, or malformed.
     */
    invalid_scope,


    /**
     * The authorization server encountered an unexpected
     * condition that prevented it from fulfilling the request.
     * (This error code is needed because a 500 Internal Server
     * Error HTTP status code cannot be returned to the client
     * via an HTTP redirect.)
     */
    server_error,


    /**
     * The authorization server is currently unable to handle
     * the request due to a temporary overloading or maintenance
     * of the server. (This error code is needed because a 503
     * Service Unavailable HTTP status code cannot be returned
     * to the client via an HTTP redirect.)
     */
    temporarily_unavailable,


    /**
     * The client is not authorized to request an authorization
     * code or an access token using this method.
     */
    unauthorized_client,


    /**
     * The authorization grant type is not supported by the
     * authorization server.
     */
    unsupported_grant_type,


    /**
     * The authorization server does not support obtaining an
     * authorization code or an access token using this method.
     */
    unsupported_response_type
    ;
}
