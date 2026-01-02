package com.mdk.springsecurity6study.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 用户名不存在
     */
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> handleUsernameNotFoundException(UsernameNotFoundException e) {
        log.warn("用户不存在: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("用户名或密码错误");
    }

    /**
     * 密码错误
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentialsException(BadCredentialsException e) {
        log.warn("密码错误: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("用户名或密码错误");
    }

    /**
     * 账户被锁定
     */
    @ExceptionHandler(LockedException.class)
    public ResponseEntity<String> handleLockedException(LockedException e) {
        log.warn("账户被锁定: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("账户已被锁定，请联系管理员");
    }

    /**
     * 账户被禁用
     */
    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<String> handleDisabledException(DisabledException e) {
        log.warn("账户被禁用: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("账户已被禁用");
    }

    /**
     * 账户已过期
     */
    @ExceptionHandler(AccountExpiredException.class)
    public ResponseEntity<String> handleAccountExpiredException(AccountExpiredException e) {
        log.warn("账户已过期: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("账户已过期，请联系管理员");
    }

    /**
     * 凭证（密码）已过期
     */
    @ExceptionHandler(CredentialsExpiredException.class)
    public ResponseEntity<String> handleCredentialsExpiredException(CredentialsExpiredException e) {
        log.warn("密码已过期: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("密码已过期，请修改密码");
    }

    /**
     * 其他认证异常
     */
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
        log.error("认证异常: ", e);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("认证失败: " + e.getMessage());
    }

    /**
     * 访问被拒绝（无权限）
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handleAccessDeniedException(AccessDeniedException e) {
        log.warn("访问拒绝: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("无权访问该资源");
    }

    /**
     * 系统其他异常
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        log.error("系统异常: ", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("服务器错误: " + e.getMessage());
    }
}
