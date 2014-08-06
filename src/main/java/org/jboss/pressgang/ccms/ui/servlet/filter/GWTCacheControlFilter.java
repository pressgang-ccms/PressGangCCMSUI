/*
 * Copyright 2011-2014 Red Hat, Inc.
 *
 * This file is part of PressGang CCMS.
 *
 * PressGang CCMS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PressGang CCMS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with PressGang CCMS. If not, see <http://www.gnu.org/licenses/>.
 */

package org.jboss.pressgang.ccms.ui.servlet.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * {@link Filter} to add cache control headers for GWT generated files to ensure
 * that the correct files get cached.
 * <p>
 * From http://seewah.blogspot.com.au/2009/02/gwt-tips-2-nocachejs-getting-cached-in.html
 * <p>
 * Modified to follow GWT recommended headers more closely:
 * https://developers.google.com/web-toolkit/doc/latest/DevGuideCompilingAndDebugging#perfect_caching
 * See also http://palizine.plynt.com/issues/2008Jul/cache-control-attributes/
 * @author See Wah Cheng
 * @author Sean Flanigan <sflaniga@redhat.com>
 */
public class GWTCacheControlFilter implements Filter {
    private static final Pattern JS_AND_IMAGES_RE = Pattern.compile(".*?\\.(js|png|jpg|jpeg|gif|svg|css|ico|otf|ttf|woff|eot)$");
    private static final long ONE_MIN_MS = 60 * 1000L;
    private static final long ONE_HOUR_MS = ONE_MIN_MS * 60;
    private static final long ONE_DAY_MS = ONE_HOUR_MS * 24;
    private static final long ONE_YEAR_MS = ONE_DAY_MS * 365;

    public void destroy() {
    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException,
            ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String requestURI = httpRequest.getRequestURI();

        if (requestURI.contains(".nocache.")) {
            long now = System.currentTimeMillis();
            httpResponse.setDateHeader("Date", now);
            httpResponse.setDateHeader("Expires", now - ONE_DAY_MS);
            httpResponse.setHeader("Cache-control", "public, max-age=0, must-revalidate");
        } else if (requestURI.contains(".cache.")) {
            long now = System.currentTimeMillis();
            httpResponse.setDateHeader("Date", now);
            httpResponse.setDateHeader("Expires", now + ONE_YEAR_MS);
        } else if (requestURI.endsWith(".dic") || requestURI.endsWith(".aff")) {
            // Cache dictionaries for a day
            long now = System.currentTimeMillis();
            httpResponse.setDateHeader("Date", now);
            httpResponse.setDateHeader("Expires", now + ONE_DAY_MS);
        } else if (JS_AND_IMAGES_RE.matcher(requestURI).matches()) {
            // Cache images, css and javascript files for 10 minutes
            long now = System.currentTimeMillis();
            httpResponse.setDateHeader("Date", now);
            httpResponse.setDateHeader("Expires", now + (10 * ONE_MIN_MS));
        }

        filterChain.doFilter(request, response);
    }
}