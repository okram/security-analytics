/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.opensearch.securityanalytics.transport;

import org.opensearch.action.ActionListener;
import org.opensearch.action.support.ActionFilters;
import org.opensearch.action.support.TransportAction;
import org.opensearch.client.Request;
import org.opensearch.client.Response;
import org.opensearch.client.RestClient;
import org.opensearch.common.inject.Inject;
import org.opensearch.monitor.StatusInfo;
import org.opensearch.rest.RestRequest;
import org.opensearch.securityanalytics.SecurityAnalyticsPlugin;
import org.opensearch.securityanalytics.action.CreateMonitorRequest;
import org.opensearch.securityanalytics.action.CreateMonitorResponse;
import org.opensearch.tasks.Task;
import org.opensearch.transport.TransportService;

public class TransportMonitorAction extends TransportAction<CreateMonitorRequest, CreateMonitorResponse> {

    private final RestClient client;

    @Inject
    public TransportMonitorAction(final String actionName, final TransportService transportService, final ActionFilters actionFilters) {
        super(actionName, actionFilters, transportService.getTaskManager());
        this.client = null;
    }

    @Override
    protected void doExecute(final Task task, final CreateMonitorRequest request, final ActionListener<CreateMonitorResponse> actionListener) {
        final Request alertRequest = new Request(RestRequest.Method.POST.name(), SecurityAnalyticsPlugin.ALERTING_BASE_URI + "/monitors");
        // alertRequest.setJsonEntity(); // TOOD: add JSON body
        try {
            final Response response = this.client.performRequest(alertRequest);
            if (response.getStatusLine().getStatusCode() >= 200 && response.getStatusLine().getStatusCode() <= 299)
                actionListener.onResponse(new CreateMonitorResponse(request.monitorId(), new StatusInfo(StatusInfo.Status.HEALTHY, "Monitor Created")));
            else
                actionListener.onResponse(new CreateMonitorResponse(request.monitorId(), new StatusInfo(StatusInfo.Status.UNHEALTHY, "Monitor Not Created")));
        } catch (final Exception e) {
            actionListener.onFailure(e);
        }
    }
}
