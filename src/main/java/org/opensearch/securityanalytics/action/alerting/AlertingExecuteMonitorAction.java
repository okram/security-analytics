/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.opensearch.securityanalytics.action.alerting;

import org.opensearch.action.ActionType;
import org.opensearch.commons.alerting.action.ExecuteMonitorResponse;

public class AlertingExecuteMonitorAction extends ActionType<ExecuteMonitorResponse> {

    public static final String NAME = "cluster:admin/opendistro/sap/monitor/execute";
    public static final AlertingExecuteMonitorAction INSTANCE = new AlertingExecuteMonitorAction();

    private AlertingExecuteMonitorAction() {
        super(NAME, ExecuteMonitorResponse::new);
    }
}
