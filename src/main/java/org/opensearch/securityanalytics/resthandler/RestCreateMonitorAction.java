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
import org.opensearch.search.fetch.subphase.FetchSourceContext;
import org.opensearch.securityanalytics.SecurityAnalyticsPlugin;
import org.opensearch.securityanalytics.action.CreateMonitorAction;
import org.opensearch.securityanalytics.action.CreateMonitorRequest;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import static org.opensearch.rest.RestRequest.Method.GET;

public class RestCreateMonitorAction extends BaseRestHandler {

    private static final Logger LOG = LogManager.getLogger(RestCreateMonitorAction.class);

    @Override
    public String getName() {
        return "create";
    }

    @Override
    public List<Route> routes() {
        return List.of(new Route(GET, SecurityAnalyticsPlugin.SAP_BASE_URI + "/" + getName()));
    }

    @Override
    protected RestChannelConsumer prepareRequest(final RestRequest request, final NodeClient client) throws IOException {
        final String monitorId = request.param("monitorId");
        if (monitorId == null || monitorId.isEmpty())
            throw new IllegalArgumentException("missing monitorId");

        LOG.debug("{} {}/{}", request.method(), SecurityAnalyticsPlugin.SAP_BASE_URI, monitorId);

        final FetchSourceContext srcContext = request.method().equals(RestRequest.Method.HEAD) ?
                FetchSourceContext.DO_NOT_FETCH_SOURCE :
                FetchSourceContext.parseFromRestRequest(request);
        return channel -> client.execute(CreateMonitorAction.INSTANCE, new CreateMonitorRequest(monitorId), new RestToXContentListener(channel));
    }

    @Override
    public Set<String> responseParams() {
        return Set.of("monitorId");
    }

}
