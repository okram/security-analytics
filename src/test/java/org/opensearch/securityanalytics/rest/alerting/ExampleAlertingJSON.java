/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.opensearch.securityanalytics.rest.alerting;

import org.json.JSONObject;

public class ExampleAlertingJSON {

    private ExampleAlertingJSON() {
        // do nothing (static methods/fields)
    }

    public static String within(final String key, final String json) {
        final JSONObject external = new JSONObject();
        external.put(key, new JSONObject(json));
        return external.toString(4);
    }

    public static final String SETTINGS_FLAT = "{\n" +
            "plugins.security.system_indices.enabled: true,\n" +
            "plugins.security.restapi.roles_enabled: [\"all_access\", \"security_rest_api_access\"],\n" +
            "plugins.security.system_indices.indices: [\".opendistro-alerting-config\", \".opendistro-alerting-alert*\", \".opendistro-anomaly-results*\", \".opendistro-anomaly-detector*\", \".opendistro-anomaly-checkpoints\", \".opendistro-anomaly-detection-state\", \".opendistro-reports-*\", \".opendistro-notifications-*\", \".opendistro-notebooks\", \".opendistro-asynchronous-search-response*\"]\n" +
            "}";

    public static final String SETTINGS = "{\n" +
            "  \"plugins\": {\n" +
            "    \"security\" : {\n" +
            "      \"restapi\" : {\n" +
            "        \"roles_enabled\" : [\"all_access\", \"security_rest_api_access\"]\n" +
            "        },\n" +
            "      \"system_indices\" : {\n" +
            "        \"enabled\" : true,\n" +
            "        \"indices\" : [\".opendistro-alerting-config\", \".opendistro-alerting-alert*\", \".opendistro-notifications-*\"]\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "}";


    public static final String CREATE_MONITOR_1 = "{\n" +
            "  \"type\": \"monitor\",\n" +
            "  \"monitor_type\": \"doc_level_monitor\",\n" +
            "  \"name\": \"monitor-1\",\n" +
            "  \"enabled\": true,\n" +
            "  \"schedule\": {\n" +
            "    \"period\": {\n" +
            "      \"interval\": 1,\n" +
            "      \"unit\": \"MINUTES\"\n" +
            "    }\n" +
            "}\n" +
            "}";

    public static final String SEARCH_MONITOR_1 = "{\n" +
            "  \"query\": {\n" +
            "    \"match\" : {\n" +
            "      \"monitor.name\": \"monitor-1\"\n" +
            "    }\n" +
            "  }\n" +
            "}";


}
