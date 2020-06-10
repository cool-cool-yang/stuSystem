package com.stuSystem.manager.pojo.other.productService;

/**
 * 提供生产者服务
 */
public interface ProductService<T> {
    boolean isEmpty();
    boolean isFull();
    boolean isfinish();
    int totoal();
    T get() throws InterruptedException;
    boolean submit(ProductTask task)throws Exception;


}
