package io.github.gcdd1993.springcloud.openfeign.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;

/**
 * @author gcdd1993
 * @date 2021/12/7
 * @since 1.0.0
 */
@Slf4j
public class AppAuthInterceptorImpl implements HandlerInterceptor {

    @Autowired
    private AppAuthProvider appAuthProvider;

    /**
     * 在执行请求前，判断权限
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorization = request.getHeader("Authorization");
        try {
            // basic auth
            if (authorization != null && authorization.startsWith("Basic ")) {
                String decoded = new String(Base64.getDecoder().decode(authorization.substring(6)));
                String[] split = decoded.split(":");
                if (split.length == 2) {
                    boolean authenticated = appAuthProvider.authenticate(split[0], split[1]);
                    if (authenticated) {
                        return true;
                    }
                }
            }
        } catch (Exception ex) {
            log.error("unexpected error when preHandle app auth.", ex);
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return false;
    }
}
