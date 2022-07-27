/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.opensearch.securityanalytics.action;

import org.opensearch.action.ActionResponse;
import org.opensearch.common.io.stream.StreamInput;
import org.opensearch.common.io.stream.StreamOutput;

import java.io.IOException;

public class ExecuteMonitorResponse extends ActionResponse {

    private final String monitorId;

    public ExecuteMonitorResponse(final String monitorId) {
        this.monitorId = monitorId;
    }

    public ExecuteMonitorResponse(final StreamInput input) throws IOException {
        this(input.readString());
    }

    @Override
    public void writeTo(final StreamOutput output) throws IOException {
        output.writeString(this.monitorId);
    }

}
