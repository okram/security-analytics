/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.opensearch.securityanalytics.resthandler;

public final class Tokens {

    private Tokens() {
        // do nothing
    }

    public static final String MONITOR_ID = "monitorId";
    public static final String REQUEST_END = "requestEnd";
    public static final String DRY_RUN = "dryRun";

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String QUERY = "query";
    public static final String TAGS = "tags";

    public static final String DESCRIPTION = "description";
    public static final String INDICES = "indices";
    public static final String QUERIES = "queries";

    public static final String TYPE = "type";
    public static final String MONITOR_TYPE = "monitor_type";
    public static final String MONITOR = "monitor";
    public static final String ENABLED = "enabled";
    public static final String SCHEDULE = "schedule";
    public static final String INTERVAL = "interval";
    public static final String PERIOD = "period";
    public static final String UNIT = "unit";
    public static final String INPUTS = "inputs";
    public static final String VERSION = "version";

    public static final String _CREATE = "_create";
    public static final String _EXECUTE = "_execute";
}
