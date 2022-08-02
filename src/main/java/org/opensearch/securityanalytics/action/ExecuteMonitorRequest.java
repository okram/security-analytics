/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.opensearch.securityanalytics.action;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.opensearch.action.ActionRequest;
import org.opensearch.action.ActionRequestValidationException;
import org.opensearch.common.io.stream.StreamInput;
import org.opensearch.common.io.stream.StreamOutput;
import org.opensearch.common.unit.TimeValue;

import java.io.IOException;

public class ExecuteMonitorRequest extends ActionRequest {

    private static final Logger LOG = LogManager.getLogger(ExecuteMonitorRequest.class);

    final boolean dryRun;
    final TimeValue requestEnd;
    final String monitorId;

    public ExecuteMonitorRequest(final String monitorId, final TimeValue requestEnd, final boolean dryRun) {
        this.monitorId = monitorId;
        this.requestEnd = requestEnd;
        this.dryRun = dryRun;
    }

    public ExecuteMonitorRequest(final StreamInput input) throws IOException {
        LOG.info("HERE" + input);
        this.monitorId = input.readString();
        this.requestEnd = input.readTimeValue();
        this.dryRun = input.readBoolean();

        //this(input.readString(), input.readTimeValue(), input.readBoolean());
    }


    public String getMonitorId() {
        return this.monitorId;
    }

    public TimeValue getRequestEnd() {
        return this.requestEnd;
    }

    public boolean getDryRun() {
        return this.dryRun;
    }

    @Override
    public ActionRequestValidationException validate() {
        return null == this.monitorId ? new ActionRequestValidationException() : null;
    }

    @Override
    public void writeTo(final StreamOutput out) throws IOException {
        out.writeBoolean(this.dryRun);
        out.writeTimeValue(this.requestEnd);
        out.writeOptionalString(this.monitorId);
    }
}