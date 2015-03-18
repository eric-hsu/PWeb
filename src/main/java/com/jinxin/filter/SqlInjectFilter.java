package com.jinxin.filter;

    import java.io.IOException;  
    import java.io.UnsupportedEncodingException;  
    import java.util.Enumeration;  
    import java.util.HashMap;  
import java.util.Iterator;
    import java.util.Map;  
    import java.util.StringTokenizer;  
    import javax.servlet.Filter;  
    import javax.servlet.FilterChain;  
    import javax.servlet.FilterConfig;  
    import javax.servlet.ServletException;  
    import javax.servlet.ServletRequest;  
    import javax.servlet.ServletResponse;  
    import javax.servlet.http.HttpServletRequest;  
    import javax.servlet.http.HttpServletResponse;  

import org.apache.commons.lang.StringUtils;
    import org.apache.commons.logging.Log;  
import org.apache.commons.logging.LogFactory;  
import org.apache.log4j.Logger;

import com.jinxin.common.utils.CommonUtil;
import com.jinxin.web.PMController;
      

/**
* 过滤sql关键字的Filter
* @author @author <a href="mailto:jarjava@163.com">Xu Yan Li</a>.
*/
public class SqlInjectFilter implements Filter {
	private final static String errorpage = "/PWeb/jsp/common/sqlInjectError.jsp";
	private static final Logger logger = Logger.getLogger(SqlInjectFilter.class);
	
	private String inj_str = "'|and|exec|insert|select|delete|update|count|*|%|chr|mid|master|truncate|char|declare|; |or|cast|-|+|,";
	protected FilterConfig filterConfig = null;
	protected boolean ignore = true;
	
	public void init(FilterConfig config) throws ServletException {
		this.filterConfig = config;
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
		throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		Iterator values = req.getParameterMap().values().iterator();//获取所有的表单参数  
		while(values.hasNext()){
			String[] value = (String[])values.next();
			for(int i = 0;i < value.length;i++){
				if(StringUtils.isBlank(value[i])){
					continue;
				}
				if(sql_inj(value[i])){
					//TODO这里发现sql注入代码的业务逻辑代码  
					logger.info("sql注入**************************参数值："+value[i]+"***********************来源地址:"+req.getRequestURL().toString()+",ip："+CommonUtil.getIpAddr(req)+"***************************************");
					req.setAttribute("message", "参数值不合法");
					res.sendRedirect(errorpage);
				return;
			}
			}
		}
		chain.doFilter(request, response);
	}
	
	public boolean sql_inj(String str)
		{
		str = str.toLowerCase();
		String[] inj_stra=inj_str.split("\\|");
		for (int i=0 ; i < inj_stra.length ; i++ ){
			if (str.indexOf(" "+inj_stra[i]+" ")>=0){
				return true;
			}
		}
		return false;
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}  
}