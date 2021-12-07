package io.github.gcdd1993.springcloud.openfeign.auth;

/**
 * @author gcdd1993
 * @date 2021/12/7
 * @since 1.0.0
 */
public interface AppAuthProvider {

    /**
     * 按照appId 和 appKey进行授权，成功返回True
     */
    boolean authenticate(String appId, String appKey);

    class AlwaysPassAppAuthProviderImpl implements AppAuthProvider {

        @Override
        public boolean authenticate(String appId, String appKey) {
            return true;
        }
    }
}
