/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.opensearch.securityanalytics.action;

import org.opensearch.action.ActionType;
import org.opensearch.securityanalytics.resthandler.Tokens;

public class ExecuteMonitorAction extends ActionType<ExecuteMonitorResponse> {

    public static final String NAME = "cluster:admin/opendistro/security_analytics/monitor/execute";
    public static final ExecuteMonitorAction INSTANCE = new ExecuteMonitorAction();

    private ExecuteMonitorAction() {
        super(Tokens.SAP_EXECUTE_MONITOR_ACTION, ExecuteMonitorResponse::new);
    }
}
