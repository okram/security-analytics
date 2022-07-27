/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.opensearch.securityanalytics.action;

import org.opensearch.action.ActionType;
import org.opensearch.common.io.stream.Writeable;

public class ExecuteMonitorAction extends ActionType<ExecuteMonitorResponse> {

    private static final String NAME = "execute";
    public static final ExecuteMonitorAction INSTANCE = new ExecuteMonitorAction(NAME, null);

    public ExecuteMonitorAction(final String name, final Writeable.Reader<ExecuteMonitorResponse> responseReader) {
        super(name, responseReader);
    }
}
