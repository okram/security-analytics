/*
 * Copyright OpenSearch Contributors
 *  SPDX-License-Identifier: Apache-2.0
 */
package org.opensearch.securityanalytics;

import org.opensearch.action.ActionRequest;
import org.opensearch.action.ActionResponse;
import org.opensearch.cluster.metadata.IndexNameExpressionResolver;
import org.opensearch.cluster.node.DiscoveryNodes;
import org.opensearch.common.settings.ClusterSettings;
import org.opensearch.common.settings.IndexScopedSettings;
import org.opensearch.common.settings.Settings;
import org.opensearch.common.settings.SettingsFilter;
import org.opensearch.plugins.ActionPlugin;
import org.opensearch.plugins.Plugin;
import org.opensearch.rest.RestController;
import org.opensearch.rest.RestHandler;
import org.opensearch.securityanalytics.action.CreateMonitorAction;
import org.opensearch.securityanalytics.resthandler.RestCreateMonitorAction;
import org.opensearch.securityanalytics.transport.TransportMonitorAction;

import java.util.List;
import java.util.function.Supplier;

public class SecurityAnalyticsPlugin extends Plugin implements ActionPlugin {

    public static final String MONITOR_BASE_URI = "_security_analytics";

    @Override
    public List<RestHandler> getRestHandlers(
            final Settings settings,
            final RestController restController,
            final ClusterSettings clusterSettings,
            final IndexScopedSettings indexScopedSettings,
            final SettingsFilter settingsFilter,
            final IndexNameExpressionResolver indexNameExpressionResolver,
            final Supplier<DiscoveryNodes> nodesInCluster) {
        return List.of(new RestCreateMonitorAction());
    }

    @Override
    public List<ActionHandler<? extends ActionRequest, ? extends ActionResponse>> getActions() {
        return List.of(new ActionHandler<>(CreateMonitorAction.INSTANCE, TransportMonitorAction.class));

        /*ScheduledJobsStatsTransportAction:: class.java),
        ActionPlugin.ActionHandler(IndexMonitorAction.INSTANCE, TransportIndexMonitorAction:: class.java),*/

    }


    public SecurityAnalyticsPlugin() {

    }

    public void createMonitor() {

    }

    public void executeMonitor() {

    }
}