package fr.upmc.dar2.filters;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * Filtre d'injection de CSS dans les pages (expérimental)
 * 
 * @author Daniel
 *
 */

//@WebFilter(urlPatterns="/*")
public class CSSFilter implements Filter {
	@SuppressWarnings("unused")
    private FilterConfig filterConfig = null;
	
	/**
	 * Liste de CSS à injecter dans les pages
	 */
	
	
	private String[] includes = {
		    "<link rel='stylesheet' href='/DAR/assets/css/header.css'>",
			"<link rel='stylesheet' href='/DAR/assets/css/footer.css'>",
			"<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css' integrity='sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u' crossorigin='anonymous'>",
			"<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css'>",
			"<link href='http://fonts.googleapis.com/css?family=Cookie' rel='stylesheet' type='text/css'>"
	};

	class CharResponseWrapper extends HttpServletResponseWrapper {
		  protected CharArrayWriter charWriter;

		  protected PrintWriter writer;

		  protected boolean getOutputStreamCalled;

		  protected boolean getWriterCalled;

		  public CharResponseWrapper(HttpServletResponse response) {
		    super(response);

		    charWriter = new CharArrayWriter();
		  }

		  public ServletOutputStream getOutputStream() throws IOException {
		    if (getWriterCalled) {
		      throw new IllegalStateException("getWriter already called");
		    }

		    getOutputStreamCalled = true;
		    return super.getOutputStream();
		  }

		  public PrintWriter getWriter() throws IOException {
		    if (writer != null) {
		      return writer;
		    }
		    if (getOutputStreamCalled) {
		      throw new IllegalStateException("getOutputStream already called");
		    }
		    getWriterCalled = true;
		    writer = new PrintWriter(charWriter);
		    return writer;
		  }

		  public String toString() {
		    String s = null;

		    if (writer != null) {
		      s = charWriter.toString();
		    }
		    return s;
		  }
		}

    public void init(FilterConfig filterConfig) throws ServletException
    {
        this.filterConfig = filterConfig;
    }

    public void destroy()
    {
        filterConfig = null;
    }

    public void doFilter(
            ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException
    {    	
        CharResponseWrapper responseWrapper = new CharResponseWrapper(
                (HttpServletResponse)response);
                
        chain.doFilter(request, responseWrapper);
        
        if (responseWrapper.getContentType() != null) {
	        if (responseWrapper.getContentType().contains("text/html")) {
	        	
	            String responseModified = new String(responseWrapper.toString());
	            String headEnd = "</head>";
       	            
	            for (String css : includes) {
	            	responseModified = responseModified.replace(headEnd, css + '\n' + headEnd);
	            }
	            	            
	            response.getWriter().write(responseModified);
	        }
	        else {
	        	if (responseWrapper.toString() != null)
	        		response.getWriter().write(responseWrapper.toString());
	        }
        } else {
        	if (responseWrapper.toString() != null)
        		response.getWriter().write(responseWrapper.toString());
        }
    }
}
