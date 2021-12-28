package io.github.gcdd1993.security;

public interface ISecurityUserService {

    String validatePasswordResetToken(String token);

}
