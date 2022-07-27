/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.opensearch.securityanalytics.action;

import org.opensearch.common.io.stream.BytesStreamOutput;
import org.opensearch.common.io.stream.StreamInput;
import org.opensearch.test.OpenSearchTestCase;

public class CreateMonitorRequestTests extends OpenSearchTestCase {

    public void testCreateMonitorRequestWithId() throws Exception {
        final CreateMonitorRequest request = new CreateMonitorRequest("10");
        assertNotNull(request);
        final BytesStreamOutput out = new BytesStreamOutput();
        request.writeTo(out);
        final StreamInput sin = StreamInput.wrap(out.bytes().toBytesRef().bytes);
        final CreateMonitorRequest newRequest = new CreateMonitorRequest(sin);
        assertEquals("10", newRequest.monitorId());
        assertEquals(request.monitorId(), newRequest.monitorId());
    }

}
