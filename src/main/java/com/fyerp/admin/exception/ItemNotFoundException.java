/*
 * 作者：xuda
 * 创建时间：18-7-5 上午11:05
 * 模块名称：admin
 */

package com.fyerp.admin.exception;

/**
 * com.spring.exception
 * @author jacky
 * @date 2017/12/25
 **/
public class ItemNotFoundException extends GenericException{
    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public ItemNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new runtime exception with the specified detail message and
     * cause.  <p>Note that the detail message associated with
     * {@code cause} is <i>not</i> automatically incorporated in
     * this runtime exception's detail message.
     *
     * @param message the detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method).
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link #getCause()} method).  (A <tt>null</tt> value is
     *                permitted, and indicates that the cause is nonexistent or
     *                unknown.)
     * @since 1.4
     */
    public ItemNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
