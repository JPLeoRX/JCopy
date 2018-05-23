package io.github.jpleorx.jcopy.core.listeners;

import io.github.jpleorx.jcopy.core.copy.CopyMultipleStatistics;

/**
 * Statistics listener
 *
 * @author Leo Ertuna
 * @since 23.05.2018 21:16
 */
public interface CopyMultipleStatisticsListener extends CustomListener<CopyMultipleStatistics> {
    @Override
    void action(CopyMultipleStatistics copyMultipleStatistics);
}