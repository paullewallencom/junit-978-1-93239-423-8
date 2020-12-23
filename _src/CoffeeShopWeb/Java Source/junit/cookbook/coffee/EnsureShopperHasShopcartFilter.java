package junit.cookbook.coffee;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import junit.cookbook.coffee.model.ShopcartModel;

public class EnsureShopperHasShopcartFilter implements Filter {
    public void doFilter(
        ServletRequest request,
        ServletResponse response,
        FilterChain filterChain)
        throws ServletException, IOException {

        addShopcartIfNeeded(request);
        filterChain.doFilter(request, response);
    }

    public void addShopcartIfNeeded(ServletRequest request) {
        HttpSession session =
            ((HttpServletRequest) request).getSession(true);

        ShopcartModel shopcartModel =
            (ShopcartModel) session.getAttribute("shopcartModel");

        if (shopcartModel == null) {
            session.setAttribute("shopcartModel", new ShopcartModel());
        }
    }

    public void init(FilterConfig config) {
    }

    public void destroy() {
    }
}
