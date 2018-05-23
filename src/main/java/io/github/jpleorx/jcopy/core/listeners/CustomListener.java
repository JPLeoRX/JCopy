package io.github.jpleorx.jcopy.core.listeners;

/**
 * A simple custom listener that we will use to sync with cache and with copying procedure progress
 *
 * @author Leo Ertuna
 * @since 23.05.2018 21:14
 */
public interface CustomListener<E> {
    /**
     * Call this when we need to fire the listener
     */
    void action(E e);
}