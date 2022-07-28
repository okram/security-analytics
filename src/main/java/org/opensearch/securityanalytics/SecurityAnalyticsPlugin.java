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
import org.opensearch.common.xcontent.NamedXContentRegistry;
import org.opensearch.plugins.ActionPlugin;
import org.opensearch.plugins.Plugin;
import org.opensearch.rest.RestController;
import org.opensearch.rest.RestHandler;
import org.opensearch.securityanalytics.action.CreateMonitorAction;
import org.opensearch.securityanalytics.action.ExecuteMonitorAction;
import org.opensearch.securityanalytics.model.Input;
import org.opensearch.securityanalytics.model.Monitor;
import org.opensearch.securityanalytics.model.Query;
import org.opensearch.securityanalytics.resthandler.RestCreateMonitorAction;
import org.opensearch.securityanalytics.resthandler.RestExecuteMonitorAction;
import org.opensearch.securityanalytics.transport.TransportCreateMonitorAction;
import org.opensearch.securityanalytics.transport.TransportExecuteMonitorAction;

import java.util.List;
import java.util.function.Supplier;

public class SecurityAnalyticsPlugin extends Plugin implements ActionPlugin {

    public static final String SAP_BASE_URI = "/_plugins/_security_analytics";
    public static final String SAP_MONITORS_BASE_URI = "/_plugins/_security_analytics/monitors";
    public static final String ALERTING_BASE_URI = "/_plugins/_alerting";

    @Override
    public List<RestHandler> getRestHandlers(
            final Settings settings,
            final RestController restController,
            final ClusterSettings clusterSettings,
            final IndexScopedSettings indexScopedSettings,
            final SettingsFilter settingsFilter,
            final IndexNameExpressionResolver indexNameExpressionResolver,
            final Supplier<DiscoveryNodes> nodesInCluster) {
        return List.of(new RestCreateMonitorAction(), new RestExecuteMonitorAction());
    }

    public SecurityAnalyticsPlugin() {

    }

    @Override
    public List<ActionHandler<? extends ActionRequest, ? extends ActionResponse>> getActions() {
        return List.of(
                new ActionHandler<>(CreateMonitorAction.INSTANCE, TransportCreateMonitorAction.class),
                new ActionHandler<>(ExecuteMonitorAction.INSTANCE, TransportExecuteMonitorAction.class));
    }

    @Override
    public List<NamedXContentRegistry.Entry> getNamedXContent() {
        return List.of(
                Monitor.XCONTENT_REGISTRY,
                Input.XCONTENT_REGISTRY,
                Query.XCONTENT_REGISTRY);
    }
}