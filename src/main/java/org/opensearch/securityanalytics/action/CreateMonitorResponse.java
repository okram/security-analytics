/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.opensearch.securityanalytics.action;

import org.opensearch.action.ActionResponse;
import org.opensearch.common.io.stream.StreamInput;
import org.opensearch.common.io.stream.StreamOutput;
import org.opensearch.monitor.StatusInfo;

import java.io.IOException;
import java.util.Objects;

public class CreateMonitorResponse extends ActionResponse {

    private final String id;
    private final StatusInfo info;

    public CreateMonitorResponse(final String id, final StatusInfo info) {
        this.id = id;
        this.info = info;
    }

    public String getMonitorId() {
        return this.id;
    }

    public StatusInfo getStatusInfo() {
        return this.info;
    }

    public CreateMonitorResponse(final StreamInput input) throws IOException {
        this.id = input.readString();
        this.info = new StatusInfo(input.readEnum(StatusInfo.Status.class), input.readString());
    }

    @Override
    public void writeTo(final StreamOutput out) throws IOException {
        out.writeString(this.id);
        out.writeEnum(this.info.getStatus());
        out.writeString(this.info.getInfo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.info);
    }

    @Override
    public boolean equals(final Object other) {
        return other instanceof CreateMonitorResponse && this.id.equals(((CreateMonitorResponse) other).id)
                && this.info.getInfo().equals(((CreateMonitorResponse) other).info.getInfo())
                && this.info.getStatus().equals(((CreateMonitorResponse) other).info.getStatus());
    }


}
