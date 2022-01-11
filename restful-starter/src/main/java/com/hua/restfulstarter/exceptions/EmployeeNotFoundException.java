package com.hua.restfulstarter.exceptions;

/**
 * 根据id查找employee表格时，差不到数据时抛的异常
 */
public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}
