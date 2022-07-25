/*
 * Copyright OpenSearch Contributors
 *  SPDX-License-Identifier: Apache-2.0
 */
package org.opensearch.securityanalytics;

import org.opensearch.rest.RestHandler;
import org.opensearch.rest.RestRequest;

public class AlertingProxy {

    public static final String ALERTING_URI = "_plugins/_alerting";
    public static final String ALERTING_MONITORS_URI = ALERTING_URI + "/monitors";

    private AlertingProxy() {

    }

    public static RestHandler.Route monitorURI(final String monitorId) {
        return new RestHandler.Route(RestRequest.Method.GET, ALERTING_MONITORS_URI + "/" + monitorId);
    }

    public static RestHandler.Route createMonitorRoute(final String monitorId) {
        return new RestHandler.Route(RestRequest.Method.GET, ALERTING_MONITORS_URI + "/" + monitorId);
    }
    public static RestHandler.Route postMonitor() {
        return new RestHandler.Route(RestRequest.Method.POST, ALERTING_MONITORS_URI);
    }

    public static RestHandler.Route monitorExecuteRoute(final String monitorId) {
        return new RestHandler.Route(RestRequest.Method.PUT, ALERTING_MONITORS_URI + "/" + monitorId + "/_execute");
    }


    public static void createMonitor() {

    }
}
