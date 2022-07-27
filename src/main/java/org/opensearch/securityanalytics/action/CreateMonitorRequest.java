/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.opensearch.securityanalytics.action;

import org.opensearch.action.ActionRequest;
import org.opensearch.action.ActionRequestValidationException;
import org.opensearch.common.io.stream.StreamInput;
import org.opensearch.common.io.stream.StreamOutput;

import java.io.IOException;

public class CreateMonitorRequest extends ActionRequest {

    private String id;

    public CreateMonitorRequest(final StreamInput input) throws IOException {
        this.id = input.readString();
    }

    public CreateMonitorRequest(final String id) {
        this.id = id;
    }

    public String monitorId() {
        return this.id;
    }

    @Override
    public ActionRequestValidationException validate() {
        return null == this.id ? new ActionRequestValidationException() : null;
    }

    @Override
    public void writeTo(StreamOutput out) throws IOException {
        out.writeString(this.id);
    }
}
