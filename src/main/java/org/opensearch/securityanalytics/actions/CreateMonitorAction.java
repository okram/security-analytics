/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.opensearch.securityanalytics.actions;

import org.opensearch.action.ActionType;
import org.opensearch.common.io.stream.Writeable;

public class CreateMonitorAction extends ActionType<CreateMonitorResponse> {

    public CreateMonitorAction(String name, Writeable.Reader<CreateMonitorResponse> responseReader) {
        super(name, responseReader);
    }
}
