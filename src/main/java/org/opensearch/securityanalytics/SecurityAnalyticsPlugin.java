/*
 * Copyright OpenSearch Contributors
 *  SPDX-License-Identifier: Apache-2.0
 */
package org.opensearch.securityanalytics;

import org.opensearch.action.ActionRequest;
import org.opensearch.action.ActionResponse;
import org.opensearch.action.admin.cluster.node.info.NodeInfo;
import org.opensearch.action.admin.cluster.node.info.NodesInfoRequest;
import org.opensearch.action.admin.cluster.node.info.NodesInfoResponse;
import org.opensearch.action.admin.cluster.node.info.PluginsAndModules;
import org.opensearch.client.Client;
import org.opensearch.cluster.metadata.IndexNameExpressionResolver;
import org.opensearch.cluster.node.DiscoveryNodes;
import org.opensearch.cluster.service.ClusterService;
import org.opensearch.common.io.stream.NamedWriteableRegistry;
import org.opensearch.common.settings.ClusterSettings;
import org.opensearch.common.settings.IndexScopedSettings;
import org.opensearch.common.settings.Settings;
import org.opensearch.common.settings.SettingsFilter;
import org.opensearch.common.xcontent.NamedXContentRegistry;
import org.opensearch.env.Environment;
import org.opensearch.env.NodeEnvironment;
import org.opensearch.plugins.ActionPlugin;
import org.opensearch.plugins.Plugin;
import org.opensearch.plugins.PluginInfo;
import org.opensearch.repositories.RepositoriesService;
import org.opensearch.rest.RestController;
import org.opensearch.rest.RestHandler;
import org.opensearch.script.ScriptContext;
import org.opensearch.script.ScriptService;
import org.opensearch.securityanalytics.action.CreateMonitorAction;
import org.opensearch.securityanalytics.action.ExecuteMonitorAction;
import org.opensearch.securityanalytics.model.Input;
import org.opensearch.securityanalytics.model.Monitor;
import org.opensearch.securityanalytics.model.Query;
import org.opensearch.securityanalytics.resthandler.RestCreateMonitorAction;
import org.opensearch.securityanalytics.resthandler.RestExecuteMonitorAction;
import org.opensearch.securityanalytics.transport.TransportCreateMonitorAction;
import org.opensearch.securityanalytics.transport.TransportExecuteMonitorAction;
import org.opensearch.threadpool.ThreadPool;
import org.opensearch.watcher.ResourceWatcherService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.opensearch.securityanalytics.resthandler.Tokens.OPENSEARCH_ALERTING;

public class SecurityAnalyticsPlugin extends Plugin implements ActionPlugin {

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

        /*@Override
    public Map<ScriptContext<?>, List<Whitelist>> getContextWhitelists() {
        Whitelist whitelist = WhitelistLoader.loadFromResourceFiles(SecurityAnalyticsPlugin.class, "org.opensearch.alerting.txt")
        return Map.of();//TriggerScript.CONTEXT to listOf(whitelist))
    }*/

   /* public void getAlertingPlugin(final Client client) {
        final NodesInfoRequest nodesInfoRequest = new NodesInfoRequest();
        nodesInfoRequest.addMetric(NodesInfoRequest.Metric.PLUGINS.metricName());
        final NodesInfoResponse nodesInfoResponse = client.admin().cluster().nodesInfo(nodesInfoRequest)
                .actionGet();
        final List<PluginInfo> pluginInfos = nodesInfoResponse.getNodes().stream()
                .flatMap((Function<NodeInfo, Stream<PluginInfo>>) nodeInfo -> nodeInfo.getInfo(PluginsAndModules.class)
                        .getPluginInfos().stream()).collect(Collectors.toList());
        final PluginInfo alertingInfo = pluginInfos.stream().filter(pluginInfo -> pluginInfo.getName().equals(OPENSEARCH_ALERTING)).findAny().orElseThrow();
        //alertingInfo.
    }*/

   /*@Override
    public Collection createComponents(Client client, ClusterService clusterService, ThreadPool threadPool, ResourceWatcherService resourceWatcherService,
                                       ScriptService scriptService, NamedXContentRegistry xContentRegistry, Environment environment, NodeEnvironment nodeEnvironment,
                                       NamedWriteableRegistry namedWriteableRegistry, IndexNameExpressionResolver indexNameExpressionResolver,
                                       Supplier<RepositoriesService> repositoriesServiceSupplier) {
        // Need to figure out how to use the OpenSearch DI classes rather than handwiring things here.
        Settings settings = environment.settings();
        return Collections.emptyList();
        alertIndices = AlertIndices(settings, client, threadPool, clusterService);
       Monitor runner = new MonitorRunnerService
                .registerClusterService(clusterService)
                .registerClient(client)
                .registerNamedXContentRegistry(xContentRegistry)
                .registerScriptService(scriptService)
                .registerSettings(settings)
                .registerThreadPool(threadPool)
                .registerAlertIndices(alertIndices)
                .registerInputService(new InputService(client, scriptService, namedWriteableRegistry, xContentRegistry))
                .registerTriggerService(new TriggerService(scriptService))
                .registerAlertService(bew AlertService(client, xContentRegistry, alertIndices))
                .registerDocLevelMonitorQueries(DocLevelMonitorQueries(client, clusterService))
                .registerConsumers()
                .registerDestinationSettings()
        scheduledJobIndices = ScheduledJobIndices(client.admin(), clusterService)
        docLevelMonitorQueries = DocLevelMonitorQueries(client, clusterService)
        scheduler = JobScheduler(threadPool, runner)
        sweeper = JobSweeper(environment.settings(), client, clusterService, threadPool, xContentRegistry, scheduler, ALERTING_JOB_TYPES)
        destinationMigrationCoordinator = DestinationMigrationCoordinator(client, clusterService, threadPool, scheduledJobIndices)
        this.threadPool = threadPool
        this.clusterService = clusterService
        return listOf(sweeper, scheduler, runner, scheduledJobIndices, docLevelMonitorQueries, destinationMigrationCoordinator)
    }*/
}