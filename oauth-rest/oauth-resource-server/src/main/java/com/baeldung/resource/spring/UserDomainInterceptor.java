package com.baeldung.resource.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.baeldung.resource.web.controller.UserInfoController;

/**
 * @author IMRAN
 *
 */
@Component
public class UserDomainInterceptor implements HandlerInterceptor {

	private static final Logger log = LoggerFactory.getLogger(UserDomainInterceptor.class);
	private static final String ALLOWED_DOMAIN = "@test.com";

	@Autowired
	private UserInfoController userInfoController;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		final JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) request.getUserPrincipal();

		final Jwt jwt = (Jwt) jwtAuthenticationToken.getCredentials();

		final String userName = (String) userInfoController.getUserInfo(jwt).get("user_name");

		if (Boolean.FALSE.equals(userName.endsWith(ALLOWED_DOMAIN))) {
			log.error("Invalid user domain! Allowed domain is {}", ALLOWED_DOMAIN);
			return false;
		} 

		return true;
	}
}
