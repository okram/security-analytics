/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.opensearch.securityanalytics.action;

import org.opensearch.test.OpenSearchTestCase;

public class CreateMonitorActionTests extends OpenSearchTestCase {

    public void testCreateMonitorActionName() {
        assertNotNull(CreateMonitorAction.INSTANCE.name());
        assertEquals(CreateMonitorAction.INSTANCE.name(), CreateMonitorAction.NAME);
    }
}
