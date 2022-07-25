/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.opensearch.securityanalytics.rest.alerting;

import org.opensearch.test.OpenSearchIntegTestCase;

public class AlertingProxyRestIT extends OpenSearchIntegTestCase {

    public void testCreateMonitorViaAlerting() throws Exception {
        assertTrue(getRestClient().isRunning());
    }

}
