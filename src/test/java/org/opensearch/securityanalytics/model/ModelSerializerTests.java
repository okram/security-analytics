/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.opensearch.securityanalytics.model;

import org.opensearch.common.io.stream.BytesStreamInput;
import org.opensearch.common.io.stream.BytesStreamOutput;
import org.opensearch.common.xcontent.XContentBuilder;
import org.opensearch.common.xcontent.XContentFactory;
import org.opensearch.common.xcontent.XContentType;
import org.opensearch.securityanalytics.model.util.ModelSerializer;
import org.opensearch.test.OpenSearchTestCase;

import java.util.List;

public class ModelSerializerTests extends OpenSearchTestCase {

    public void testWriter() throws Exception {
        final Query query = new Query("one", "two", "three", List.of("a", "b", "c"));
        final BytesStreamOutput output = new BytesStreamOutput();
        ModelSerializer.write(output, query);
        final BytesStreamInput input = new BytesStreamInput(output.bytes().toBytesRef().bytes);
        final Query query2 = ModelSerializer.read(input, Query.class);
        assertEquals("one", query2.id);
        assertEquals("two", query2.name);
        assertEquals("three", query2.query);
        assertEquals(List.of("a", "b", "c"), query2.tags);
    }

    public void testXBuilder() throws Exception {
        Query query = new Query("one", "two", "three", List.of("a", "b", "c"));
        final BytesStreamOutput output = new BytesStreamOutput();
        final XContentBuilder builder = XContentFactory.contentBuilder(XContentType.JSON, output);
        final BytesStreamInput input = new BytesStreamInput(output.bytes().toBytesRef().bytes);
        //
    }
}
