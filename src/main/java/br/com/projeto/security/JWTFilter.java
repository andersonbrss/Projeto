package br.com.projeto.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.SignatureException;

@WebFilter( "/*" )
public class JWTFilter implements Filter{

	private static final String AUTH_HEADER_KEY = "Authorization";
    private static final String AUTH_HEADER_VALUE_PREFIX = "Bearer ";

    private static final int STATUS_CODE_UNAUTHORIZED = 401;
	
	@Override
    public void init(FilterConfig filterConfig) throws ServletException {}
	
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
	    HttpServletRequest req = (HttpServletRequest) servletRequest;
	    HttpServletResponse res = (HttpServletResponse) servletResponse;

	    String token = getBearerToken( req );
	    
	    if( token == null && (req.getRequestURI().startsWith("/projeto/") 
	    		||req.getRequestURI().startsWith("/favicon.ico") 
	    		|| req.getRequestURI().startsWith("/recursos/login")) ) {
	    	
	        filterChain.doFilter(servletRequest, servletResponse);
	        return;
	    }

	    if(token == null || token.trim().isEmpty()) {
	        res.setStatus( STATUS_CODE_UNAUTHORIZED );
	        return;
	    }
	    
	    try {
            Jws<Claims> parser = JWTUtil.decode(token);
            System.out.println("User request: "+ parser.getBody().getSubject());
            
            if( !parser.getBody().get("role").equals("ADM") && req.getRequestURI().startsWith("/recursos/operador") ) {
            	res.setStatus( STATUS_CODE_UNAUTHORIZED );
    	        return;
    	    }
            
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (SignatureException e) {
            res.setStatus( STATUS_CODE_UNAUTHORIZED );
        }

	}
	
    private String getBearerToken( HttpServletRequest request ) {
        String authHeader = request.getHeader( AUTH_HEADER_KEY );
        if ( authHeader != null && authHeader.startsWith( AUTH_HEADER_VALUE_PREFIX ) ) {
            return authHeader.substring( AUTH_HEADER_VALUE_PREFIX.length() );
        }
        return null;
    }
}
