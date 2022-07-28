/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.opensearch.securityanalytics.transport;

import org.opensearch.action.ActionListener;
import org.opensearch.action.support.ActionFilters;
import org.opensearch.action.support.TransportAction;
import org.opensearch.client.Client;
import org.opensearch.client.Request;
import org.opensearch.client.Response;
import org.opensearch.cluster.service.ClusterService;
import org.opensearch.common.inject.Inject;
import org.opensearch.common.settings.Settings;
import org.opensearch.common.xcontent.NamedXContentRegistry;
import org.opensearch.monitor.StatusInfo;
import org.opensearch.rest.RestRequest;
import org.opensearch.securityanalytics.SecurityAnalyticsPlugin;
import org.opensearch.securityanalytics.action.CreateMonitorRequest;
import org.opensearch.securityanalytics.action.CreateMonitorResponse;
import org.opensearch.tasks.Task;
import org.opensearch.transport.TransportService;

import static org.opensearch.securityanalytics.resthandler.Tokens._CREATE;

public class TransportCreateMonitorAction extends TransportAction<CreateMonitorRequest, CreateMonitorResponse> {

    private final Client client;

    @Inject
    public TransportCreateMonitorAction(TransportService transport, final Client client, final ActionFilters actionFilters, NamedXContentRegistry xContentRegistry, final ClusterService clusterService, final Settings settings) {
        super(_CREATE, actionFilters, transport.getTaskManager());
        this.client = client;
    }

    @Override
    protected void doExecute(final Task task, final CreateMonitorRequest request, final ActionListener<CreateMonitorResponse> actionListener) {
        final Request alertRequest = new Request(RestRequest.Method.POST.name(), SecurityAnalyticsPlugin.ALERTING_BASE_URI + "/monitors");
        // alertRequest.setJsonEntity(); // TOOD: add JSON body
        try {
            final Response response = null;//this.client.execute(null, alertRequest);
            if (response.getStatusLine().getStatusCode() >= 200 && response.getStatusLine().getStatusCode() <= 299)
                actionListener.onResponse(new CreateMonitorResponse(request.monitorId(), new StatusInfo(StatusInfo.Status.HEALTHY, "Monitor Created")));
            else
                actionListener.onResponse(new CreateMonitorResponse(request.monitorId(), new StatusInfo(StatusInfo.Status.UNHEALTHY, "Monitor Not Created")));
        } catch (final Exception e) {
            actionListener.onFailure(e);
        }
    }
}
