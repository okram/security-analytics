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
import org.opensearch.securityanalytics.action.ExecuteMonitorRequest;
import org.opensearch.securityanalytics.action.ExecuteMonitorResponse;
import org.opensearch.tasks.Task;
import org.opensearch.transport.TransportService;

import static org.opensearch.securityanalytics.resthandler.Tokens._EXECUTE;

public class TransportExecuteMonitorAction extends TransportAction<ExecuteMonitorRequest, ExecuteMonitorResponse> {

    private final Client client;

    @Inject
    public TransportExecuteMonitorAction(TransportService transport, final Client client, final ActionFilters actionFilters, NamedXContentRegistry xContentRegistry, final ClusterService clusterService, final Settings settings) {
        super(_EXECUTE, actionFilters, transport.getTaskManager());
        this.client = client;
    }

    @Override
    protected void doExecute(final Task task, final ExecuteMonitorRequest request, final ActionListener<ExecuteMonitorResponse> actionListener) {

    }
}
