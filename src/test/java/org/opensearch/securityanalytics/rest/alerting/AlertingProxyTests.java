/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.opensearch.securityanalytics.rest.alerting;

import org.json.JSONObject;
import org.opensearch.test.OpenSearchTestCase;

public class AlertingProxyTests extends OpenSearchTestCase {

    public void testAlertingProxyObjects() {
        final JSONObject json = new JSONObject(ExampleAlertingJSON.CREATE_MONITOR_1);
        assertEquals("monitor", json.get("type"));
        assertTrue(json.getBoolean("enabled"));
        assertEquals("monitor-1", json.getString("name"));
    }

    public void testAlertingMonitorObjects() {
        logger.info(new JSONObject(ExampleAlertingJSON.CREATE_MONITOR_1).toString(4));
    }
}
