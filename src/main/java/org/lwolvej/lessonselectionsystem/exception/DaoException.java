package org.lwolvej.lessonselectionsystem.exception;

/**
 * dao层的统一异常处理
 * 避免dao层处理改异常
 *
 * @author LwolveJ
 */
public class DaoException extends RuntimeException {

    private static final long serialVersionUID = -9215326641559843399L;

    public DaoException() {
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
