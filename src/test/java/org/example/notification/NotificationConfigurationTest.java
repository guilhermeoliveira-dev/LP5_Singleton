package org.example.notification;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NotificationConfigurationTest {

    @Test
    void getInstance() {
        NotificationConfiguration configuration = NotificationConfiguration.getInstance();
        NotificationConfiguration configuration2 = NotificationConfiguration.getInstance();
        Assertions.assertSame(configuration, configuration2);
    }
}