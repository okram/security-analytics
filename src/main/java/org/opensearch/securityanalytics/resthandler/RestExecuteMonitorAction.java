/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.opensearch.securityanalytics.resthandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.opensearch.client.node.NodeClient;
import org.opensearch.common.unit.TimeValue;
import org.opensearch.rest.BaseRestHandler;
import org.opensearch.rest.RestRequest;
import org.opensearch.rest.action.RestToXContentListener;
import org.opensearch.securityanalytics.SecurityAnalyticsPlugin;
import org.opensearch.securityanalytics.action.ExecuteMonitorAction;
import org.opensearch.securityanalytics.action.ExecuteMonitorRequest;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import static org.opensearch.rest.RestRequest.Method.GET;
import static org.opensearch.securityanalytics.resthandler.Tokens.*;

public class RestExecuteMonitorAction extends BaseRestHandler {

    private static final Logger LOG = LogManager.getLogger(RestExecuteMonitorAction.class);

    @Override
    public String getName() {
        return _EXECUTE;
    }

    @Override
    public List<Route> routes() {
        return List.of(new Route(GET, SecurityAnalyticsPlugin.SAP_MONITORS_BASE_URI + "/" + getName()));
    }

    @Override
    protected RestChannelConsumer prepareRequest(final RestRequest request, final NodeClient client) throws IOException {
        final String monitorId = request.param(MONITOR_ID);
        final TimeValue requestEnd = request.paramAsTime(REQUEST_END, TimeValue.MAX_VALUE);
        final boolean dryRun = request.paramAsBoolean(DRY_RUN, false);
        if (null == monitorId || monitorId.isEmpty()) throw new IllegalArgumentException("missing monitorId");
        LOG.debug("{} {}/{}", request.method(), SecurityAnalyticsPlugin.SAP_MONITORS_BASE_URI + "/" + getName(), monitorId);

        return channel -> client.execute(ExecuteMonitorAction.INSTANCE, new ExecuteMonitorRequest(monitorId, requestEnd, dryRun), new RestToXContentListener(channel));
    }

    @Override
    public Set<String> responseParams() {
        return Set.of(MONITOR_ID, REQUEST_END, DRY_RUN);
    }

}