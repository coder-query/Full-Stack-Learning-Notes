package com.mdk.springsecurity6study.common.exception;

import com.mdk.springsecurity6study.common.vo.Result;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // ==================== JWT 相关异常 ====================

    /**
     * JWT 过期异常
     */
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<Result<Void>> handleExpiredJwtException(ExpiredJwtException e) {
        log.warn("JWT 已过期: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Result.error(401, "Token 已过期，请重新登录"));
    }

    /**
     * JWT 无效异常
     */
    @ExceptionHandler(JwtException.class)
    public ResponseEntity<Result<Void>> handleJwtException(JwtException e) {
        log.warn("JWT 无效: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Result.error(401, "Token 无效"));
    }

    // ==================== 认证相关异常 ====================

    /**
     * 用户名不存在
     */
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Result<Void>> handleUsernameNotFoundException(UsernameNotFoundException e) {
        log.warn("用户不存在: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Result.error(401, "用户名或密码错误"));
    }

    /**
     * 密码错误
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Result<Void>> handleBadCredentialsException(BadCredentialsException e) {
        log.warn("密码错误: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Result.error(401, "用户名或密码错误"));
    }

    /**
     * 账户被锁定
     */
    @ExceptionHandler(LockedException.class)
    public ResponseEntity<Result<Void>> handleLockedException(LockedException e) {
        log.warn("账户被锁定: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Result.error(401, "账户已被锁定，请联系管理员"));
    }

    /**
     * 账户被禁用
     */
    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<Result<Void>> handleDisabledException(DisabledException e) {
        log.warn("账户被禁用: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Result.error(401, "账户已被禁用"));
    }

    /**
     * 账户已过期
     */
    @ExceptionHandler(AccountExpiredException.class)
    public ResponseEntity<Result<Void>> handleAccountExpiredException(AccountExpiredException e) {
        log.warn("账户已过期: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Result.error(401, "账户已过期，请联系管理员"));
    }

    /**
     * 凭证（密码）已过期
     */
    @ExceptionHandler(CredentialsExpiredException.class)
    public ResponseEntity<Result<Void>> handleCredentialsExpiredException(CredentialsExpiredException e) {
        log.warn("密码已过期: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Result.error(401, "密码已过期，请修改密码"));
    }

    /**
     * 其他认证异常
     */
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Result<Void>> handleAuthenticationException(AuthenticationException e) {
        log.error("认证异常: ", e);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Result.error(401, "认证失败: " + e.getMessage()));
    }

    /**
     * 访问被拒绝（无权限）
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Result<Void>> handleAccessDeniedException(AccessDeniedException e) {
        log.warn("访问拒绝: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(Result.error(403, "无权访问该资源"));
    }

    // ==================== 参数校验异常 ====================

    /**
     * 参数校验异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Result<Void>> handleValidException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldError() != null
                ? e.getBindingResult().getFieldError().getDefaultMessage()
                : "参数校验失败";
        log.warn("参数校验失败: {}", message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Result.error(400, message));
    }

    /**
     * 绑定异常
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity<Result<Void>> handleBindException(BindException e) {
        String message = e.getFieldError() != null
                ? e.getFieldError().getDefaultMessage()
                : "参数绑定失败";
        log.warn("参数绑定失败: {}", message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Result.error(400, message));
    }

    // ==================== 业务异常 ====================

    /**
     * 非法参数异常
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Result<Void>> handleIllegalArgumentException(IllegalArgumentException e) {
        log.warn("非法参数: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Result.error(400, e.getMessage()));
    }

    // ==================== 系统异常 ====================

    /**
     * 系统其他异常
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Result<Void>> handleException(Exception e) {
        log.error("系统异常: ", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Result.error(500, "服务器内部错误"));
    }
}
