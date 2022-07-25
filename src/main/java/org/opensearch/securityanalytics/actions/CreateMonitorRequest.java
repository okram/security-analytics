/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.opensearch.securityanalytics.actions;

import org.opensearch.action.ActionRequest;
import org.opensearch.action.ActionRequestValidationException;
import org.opensearch.common.io.stream.StreamOutput;

import java.io.IOException;

public class CreateMonitorRequest extends ActionRequest {


    @Override
    public ActionRequestValidationException validate() {
        return null;
    }

    @Override
    public void writeTo(StreamOutput out) throws IOException {
        /*out.writeOptionalString(destinationId);
        out.writeLong(version);
        out.writeBoolean(srcContext != null);
        srcContext ?.writeTo(out);
        table.writeTo(out);
        out.writeString(destinationType);*/
    }
}
