/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.opensearch.securityanalytics.action;

import org.opensearch.common.io.stream.BytesStreamOutput;
import org.opensearch.common.io.stream.StreamInput;
import org.opensearch.monitor.StatusInfo;
import org.opensearch.test.OpenSearchTestCase;

public class CreateMonitorResponseTests extends OpenSearchTestCase {

    public void testCreateMonitorResponse() throws Exception {
        final CreateMonitorResponse response = new CreateMonitorResponse("10", new StatusInfo(StatusInfo.Status.HEALTHY, "a-ok"));
        assertNotNull(response);
        final BytesStreamOutput out = new BytesStreamOutput();
        response.writeTo(out);
        final StreamInput sin = StreamInput.wrap(out.bytes().toBytesRef().bytes);
        final CreateMonitorResponse newResponse = new CreateMonitorResponse(sin);
        assertNotNull(newResponse);
        assertEquals("a-ok", newResponse.getStatusInfo().getInfo());
        assertEquals(StatusInfo.Status.HEALTHY, newResponse.getStatusInfo().getStatus());
        assertEquals("10", newResponse.getMonitorId());
        assertEquals(response, newResponse);

    }
}
