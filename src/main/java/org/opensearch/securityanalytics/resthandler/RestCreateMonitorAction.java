/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.opensearch.securityanalytics.resthandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.opensearch.client.node.NodeClient;
import org.opensearch.rest.BaseRestHandler;
import org.opensearch.rest.RestRequest;
import org.opensearch.rest.action.RestToXContentListener;
import org.opensearch.securityanalytics.action.CreateMonitorAction;
import org.opensearch.securityanalytics.action.CreateMonitorRequest;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import static org.opensearch.rest.RestRequest.Method.POST;
import static org.opensearch.securityanalytics.resthandler.Tokens.MONITOR_ID;
import static org.opensearch.securityanalytics.resthandler.Tokens._CREATE;

public class RestCreateMonitorAction extends BaseRestHandler {

    private static final Logger LOG = LogManager.getLogger(RestCreateMonitorAction.class);

    @Override
    public String getName() {
        return _CREATE;
    }

    @Override
    public List<Route> routes() {
        return List.of(new Route(POST, Tokens.SAP_MONITORS_BASE_URI + "/" + getName()));
    }

    @Override
    protected RestChannelConsumer prepareRequest(final RestRequest request, final NodeClient client) throws IOException {
        final String monitorId = request.param(MONITOR_ID);
        if (null == monitorId || monitorId.isEmpty())
            throw new IllegalArgumentException("missing monitorId");
        LOG.debug("{} {}/{}", request.method(), Tokens.SAP_MONITORS_BASE_URI, monitorId);
        return channel -> client.execute(CreateMonitorAction.INSTANCE, new CreateMonitorRequest(monitorId), new RestToXContentListener(channel));
    }



    @Override
    public Set<String> responseParams() {
        return Set.of(MONITOR_ID);
    }

}
