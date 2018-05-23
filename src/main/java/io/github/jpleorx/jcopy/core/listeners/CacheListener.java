package io.github.jpleorx.jcopy.core.listeners;

import io.github.jpleorx.jcopy.core.Cache;

/**
 * Cache listener
 *
 * @author Leo Ertuna
 * @since 23.05.2018 21:23
 */
public interface CacheListener extends CustomListener<Cache> {
    @Override
    void action(Cache cache);
}