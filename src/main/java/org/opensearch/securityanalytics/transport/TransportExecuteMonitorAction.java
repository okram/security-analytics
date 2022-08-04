/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.opensearch.securityanalytics.transport;

import org.opensearch.action.ActionListener;
import org.opensearch.action.support.ActionFilters;
import org.opensearch.action.support.TransportAction;
import org.opensearch.client.Client;
import org.opensearch.cluster.service.ClusterService;
import org.opensearch.common.inject.Inject;
import org.opensearch.common.settings.Settings;
import org.opensearch.common.xcontent.NamedXContentRegistry;
import org.opensearch.securityanalytics.action.ExecuteMonitorAction;
import org.opensearch.securityanalytics.action.ExecuteMonitorRequest;
import org.opensearch.securityanalytics.action.ExecuteMonitorResponse;
import org.opensearch.tasks.Task;
import org.opensearch.transport.TransportService;

public class TransportExecuteMonitorAction extends TransportAction<ExecuteMonitorRequest, ExecuteMonitorResponse> {

    private final Client client;
    private final ClusterService cluster;

    @Inject
    public TransportExecuteMonitorAction(TransportService transport, final Client client, final ActionFilters actionFilters, NamedXContentRegistry xContentRegistry, final ClusterService clusterService, final Settings settings) {
        super(ExecuteMonitorAction.NAME, actionFilters, transport.getTaskManager());
        this.client = client;
        this.cluster = clusterService;

    }

    @Override
    protected void doExecute(final Task task, final ExecuteMonitorRequest request, final ActionListener<ExecuteMonitorResponse> actionListener) {
        try {
            //final Response response = tools.POST(Tokens.executeMonitor(Tokens.ALERTING_MONITORS_BASE_URI, request.getMonitorId()));
            //actionListener.onResponse(new ExecuteMonitorResponse(request.id()));
        } catch (final Exception e) {
            actionListener.onFailure(e);
        }
    }
}
