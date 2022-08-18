/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.opensearch.securityanalytics.action.alerting;

import org.opensearch.action.ActionType;
import org.opensearch.commons.alerting.action.IndexMonitorResponse;

public class AlertingCreateMonitorAction extends ActionType<IndexMonitorResponse> {

    public static final String NAME = "cluster:admin/opendistro/sap/monitor/execute";
    public static final AlertingCreateMonitorAction INSTANCE = new AlertingCreateMonitorAction();

    private AlertingCreateMonitorAction() {
        super(NAME, IndexMonitorResponse::new);
    }
}
