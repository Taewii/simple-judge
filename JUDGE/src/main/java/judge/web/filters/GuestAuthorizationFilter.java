package judge.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({"/problems/*"})
public class GuestAuthorizationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String username = (String) req.getSession().getAttribute("username");

        if (username == null) {
            res.sendRedirect("/");
            return;
        }

        chain.doFilter(request, res);
    }
}
