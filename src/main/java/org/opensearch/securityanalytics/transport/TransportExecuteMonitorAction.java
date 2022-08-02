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
import org.opensearch.common.util.concurrent.ThreadContext;
import org.opensearch.common.xcontent.NamedXContentRegistry;
import org.opensearch.securityanalytics.action.ExecuteMonitorAction;
import org.opensearch.securityanalytics.action.ExecuteMonitorRequest;
import org.opensearch.securityanalytics.action.ExecuteMonitorResponse;
import org.opensearch.securityanalytics.resthandler.Tokens;
import org.opensearch.tasks.Task;
import org.opensearch.transport.TransportService;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class TransportExecuteMonitorAction extends TransportAction<ExecuteMonitorRequest, ExecuteMonitorResponse> {

    private final Client client;

    @Inject
    public TransportExecuteMonitorAction(TransportService transport, final Client client, final ActionFilters actionFilters, NamedXContentRegistry xContentRegistry, final ClusterService clusterService, final Settings settings) {
        super(ExecuteMonitorAction.NAME, actionFilters, transport.getTaskManager());
        logger.info("Transport with settings " + settings + " and client " + client);
        this.client = client;
    }

    @Override
    protected void doExecute(final Task task, final ExecuteMonitorRequest request, final ActionListener<ExecuteMonitorResponse> actionListener) {
        logger.info("Executing task" + task);
        logger.info("Request: " + request);
        try (final ThreadContext.StoredContext context = this.client.threadPool().getThreadContext().stashContext()) {
            final String reply = HttpClient.newHttpClient()
                    .send(HttpRequest.newBuilder()
                                    .timeout(Duration.ofSeconds(3000))
                                    .uri(URI.create(Tokens.executeMonitor(Tokens.ALERTING_MONITORS_BASE_URI, request.getMonitorId())))
                                    .POST(HttpRequest.BodyPublishers.noBody())
                                    .build(),
                            HttpResponse.BodyHandlers.ofString()).body();
            actionListener.onResponse(new ExecuteMonitorResponse(reply));
        } catch (IOException | InterruptedException e) {
            actionListener.onFailure(e);
        }
    }
}
