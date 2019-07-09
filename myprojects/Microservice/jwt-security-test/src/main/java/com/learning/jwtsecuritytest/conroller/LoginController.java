package com.learning.jwtsecuritytest.conroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.learning.jwtsecuritytest.model.JWTUserDetails;
import com.learning.jwtsecuritytest.model.ResponseToken;
import com.learning.jwtsecuritytest.model.UserInputModel;
import com.learning.jwtsecuritytest.service.JWTUserService;
import com.learning.jwtsecuritytest.util.JwtTokenUtil;

@RestController
@RequestMapping("/access/")
public class LoginController {

	@Autowired
	AuthenticationManager authenticateManager;

	@Autowired
	JWTUserService userService;

	@Autowired
	JwtTokenUtil util;

	@RequestMapping(value = "/${jwt.login.uri}", method=RequestMethod.POST)
	public ResponseToken login(@RequestBody UserInputModel userModel) {
		try {
			authenticate(userModel);
			JWTUserDetails userDetails = (JWTUserDetails) userService.loadUserByUsername(userModel.getUserId());

			String token = util.generateToken(userDetails.getUserName());

			return new ResponseToken(token);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	
	@RequestMapping(value = "${jwt.refresh.token.uri}/{token}", method=RequestMethod.GET)
	public ResponseToken refreshToken(@PathVariable("token") String token) {
		String refreshToken=null;
		try {
			if(null!=token && util.IstokenValid(token)) {
				String userName=util.getUserName(token);
				refreshToken=util.generateToken(userName);
			}
			
			return new ResponseToken(refreshToken);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	private void authenticate(UserInputModel userModel) throws Exception {
		if (null != userModel && null != userModel.getUserId() && null != userModel.getPassword()) {
			authenticateManager.authenticate(
					new UsernamePasswordAuthenticationToken(userModel.getUserId(), userModel.getPassword()));
		}
	}

}
