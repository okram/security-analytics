/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.opensearch.securityanalytics.alerting;

import org.json.JSONObject;
import org.opensearch.securityanalytics.SecurityAnalyticsIntegTestCase;
import org.opensearch.securityanalytics.TestTools;
import org.opensearch.securityanalytics.resthandler.Tokens;

import java.io.IOException;
import java.util.Map;

public class ExecuteMonitorIT extends SecurityAnalyticsIntegTestCase {

    public void testExecuteMonitor() throws Exception {
        createIndex("accounts");
        assertTrue(indexExists("accounts"));
        index("accounts", "document", "1", Map.of("name", "alice"));
        index("accounts", "document", "2", Map.of("name", "bob"));
        //final Response response = POST("_plugins/_alerting/monitors", ExampleAlertingJSON.CREATE_MONITOR_2);
        //assertEquals(201, response.getStatusLine().getStatusCode());
        final String jsonString = noLog(() -> {
            try {
                return TestTools.prettyString(POST(Tokens.ALERTING_MONITORS_BASE_URI, ExampleAlertingJSON.CREATE_MONITOR_2).getEntity().getContent());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        final JSONObject json = new JSONObject(jsonString);
        logger.info("ENTITY RESPONSE =>\n" + json.toString(4));
        final String id = json.getString("_id");
        logger.info("Monitor id: " + id);
        assertEquals(200, POST(Tokens.executeMonitor(Tokens.ALERTING_MONITORS_BASE_URI, id)).getStatusLine().getStatusCode());
    }

    public void testExecuteMonitorViaSAP() throws Exception {
        createIndex("accounts");
        assertTrue(indexExists("accounts"));
        index("accounts", "document", "1", Map.of("name", "alice"));
        index("accounts", "document", "2", Map.of("name", "bob"));
        final String jsonString = noLog(() -> {
            try {
                return TestTools.prettyString(POST(Tokens.ALERTING_MONITORS_BASE_URI, ExampleAlertingJSON.CREATE_MONITOR_2).getEntity().getContent());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        final JSONObject json = new JSONObject(jsonString);
        logger.info("ENTITY RESPONSE =>\n" + json.toString(4));
        final String id = json.getString("_id");
        logger.info("Monitor id: " + id);
        assertEquals(200, POST(Tokens.executeMonitor(Tokens.SAP_MONITORS_BASE_URI, id)).getStatusLine().getStatusCode());
    }
}
