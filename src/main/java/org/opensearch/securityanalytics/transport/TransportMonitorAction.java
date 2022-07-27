/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.opensearch.securityanalytics.transport;

import org.opensearch.action.ActionListener;
import org.opensearch.action.support.ActionFilters;
import org.opensearch.action.support.HandledTransportAction;
import org.opensearch.common.inject.Inject;
import org.opensearch.securityanalytics.action.CreateMonitorRequest;
import org.opensearch.securityanalytics.action.CreateMonitorResponse;
import org.opensearch.tasks.Task;
import org.opensearch.transport.TransportService;

public class TransportMonitorAction extends HandledTransportAction<CreateMonitorRequest, CreateMonitorResponse> {

    @Inject
    public TransportMonitorAction(final String actionName, final TransportService transportService, final ActionFilters actionFilters) {
        super(actionName, transportService, actionFilters, null);
    }

    @Override
    protected void doExecute(final Task task, final CreateMonitorRequest request, final ActionListener<CreateMonitorResponse> actionListener) {

    }
}
