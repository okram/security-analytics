/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.opensearch.securityanalytics.resthandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.opensearch.alerting.action.IndexMonitorRequest;
import org.opensearch.client.node.NodeClient;
import org.opensearch.commons.alerting.action.IndexMonitorAction;
import org.opensearch.rest.BaseRestHandler;
import org.opensearch.rest.RestRequest;
import org.opensearch.rest.action.RestToXContentListener;

import java.io.IOException;
import java.util.List;

import static org.opensearch.rest.RestRequest.Method.POST;

public class RestCreateMonitorAction extends BaseRestHandler {

    private static final Logger LOG = LogManager.getLogger(RestCreateMonitorAction.class);

    @Override
    public String getName() {
        return "create_monitor_action";
    }

    @Override
    public List<Route> routes() {
        return List.of(new Route(POST, Tokens.SAP_MONITORS_BASE_URI + "/" + getName()));
    }

    @Override
    protected RestChannelConsumer prepareRequest(final RestRequest request, final NodeClient client) throws IOException {
        final String monitorId = request.param("monitorID");
        LOG.debug("{} {}/{}", request.method(), Tokens.SAP_MONITORS_BASE_URI, monitorId);
        if (monitorId == null || monitorId.isEmpty()) {
            throw new IllegalArgumentException("missing id");
        }
        return channel -> client.execute(IndexMonitorAction.Companion.getINSTANCE(), new IndexMonitorRequest(request.content().streamInput()), new RestToXContentListener<>(channel));
    }
    /*@Override
    public Set<String> responseParams() {
        return Set.of(MONITOR_ID);
    }*/

}
