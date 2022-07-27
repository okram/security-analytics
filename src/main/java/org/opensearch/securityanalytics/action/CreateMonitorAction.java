/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.opensearch.securityanalytics.action;

import org.opensearch.action.ActionType;
import org.opensearch.common.io.stream.Writeable;

public class CreateMonitorAction extends ActionType<CreateMonitorResponse> {

    public static final String NAME = "create_monitor";

    public static final CreateMonitorAction INSTANCE = new CreateMonitorAction(NAME, null);

    private CreateMonitorAction(final String name, final Writeable.Reader<CreateMonitorResponse> responseReader) {
        super(name, responseReader);
    }
}
