/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.opensearch.securityanalytics.action;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.opensearch.action.ActionResponse;
import org.opensearch.common.io.stream.StreamInput;
import org.opensearch.common.io.stream.StreamOutput;
import org.opensearch.common.xcontent.ToXContent;
import org.opensearch.common.xcontent.ToXContentObject;
import org.opensearch.common.xcontent.XContentBuilder;
import org.opensearch.securityanalytics.resthandler.Tokens;

import java.io.IOException;

public class ExecuteMonitorResponse extends ActionResponse implements ToXContentObject {

    private static final Logger LOG = LogManager.getLogger(ExecuteMonitorResponse.class);

    private final String monitorId;

    public ExecuteMonitorResponse(final String monitorId) {
        this.monitorId = monitorId;
    }

    public ExecuteMonitorResponse(final StreamInput input) throws IOException {
        this(input.readString());
        if (input.available() != -1)
            input.readAllBytes();
    }

    public String getMonitorId() {
        return this.monitorId;
    }

    @Override
    public void writeTo(final StreamOutput output) throws IOException {
        output.writeString(this.monitorId);
    }

    @Override
    public XContentBuilder toXContent(XContentBuilder xContentBuilder, ToXContent.Params params) throws IOException {
        return xContentBuilder.startObject().field(Tokens.MONITOR_ID, this.monitorId).endObject();
    }

    @Override
    public boolean isFragment() {
        return false;
    }
}
