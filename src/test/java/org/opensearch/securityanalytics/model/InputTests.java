/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.opensearch.securityanalytics.model;

import org.opensearch.common.io.stream.BytesStreamInput;
import org.opensearch.common.io.stream.BytesStreamOutput;
import org.opensearch.securityanalytics.model.util.ModelSerializer;
import org.opensearch.test.OpenSearchTestCase;

import java.util.HashSet;
import java.util.List;

public class InputTests extends OpenSearchTestCase {

    public void testWriterReader() throws Exception {
        final Input in = new Input("description", List.of("index1", "index2", "index3"), List.of(new Query("id", "name", "query", List.of("tag1"))));
        final BytesStreamOutput output = new BytesStreamOutput();
        ModelSerializer.write(output, in);
        final BytesStreamInput input = new BytesStreamInput(output.bytes().toBytesRef().bytes);
        final Input in2 = ModelSerializer.read(input, Input.class);
        assertEquals("description", in2.description);
        assertEquals(List.of("index1", "index2", "index3"), in2.indices);
        assertEquals(List.of(new Query("id", "name", "query", List.of("tag1"))), in2.queries);
    }

    public void testWriterReaderWithEmptyLists() throws Exception {
        final Input in = new Input("description", List.of(), List.of(new Query("id", "name", "query", List.of("tag1"))));
        final BytesStreamOutput output = new BytesStreamOutput();
        ModelSerializer.write(output, in);
        final BytesStreamInput input = new BytesStreamInput(output.bytes().toBytesRef().bytes);
        final Input in2 = ModelSerializer.read(input, Input.class);
        assertEquals("description", in2.description);
        assertEquals(List.of(), in2.indices);
        assertEquals(List.of(new Query("id", "name", "query", List.of("tag1"))), in2.queries);
    }

    public void testHashCodeEquality() throws Exception {
        final Input a = new Input("one", List.of("two", "three"), List.of(new Query("one", "two", "three", List.of("a", "b", "c")), new Query("two", "one", "three", List.of("b", "c"))));
        final Input b = new Input("one", List.of("two", "three"), List.of(new Query("one", "two", "three", List.of("a", "b", "c")), new Query("two", "one", "three", List.of("b", "c"))));
        final Input c = new Input("one", List.of("two"), List.of(new Query("one", "two", "three", List.of("a", "b", "c")), new Query("two", "one", "three", List.of("b", "c"))));
        final Input d = new Input("one", List.of("two", "three"), List.of(new Query("one", "two", "three", List.of("a", "b", "c")), new Query("two", "two", "three", List.of("b", "c"))));
        //
        assertEquals(3, new HashSet<>(List.of(a, b, c, d)).size());
        //
        assertEquals(a, b);
        assertNotEquals(a, c);
        assertNotEquals(a, d);
        assertNotEquals(b, c);
        assertNotEquals(b, d);
        assertNotEquals(c, d);
        //
        assertEquals(a.hashCode(), b.hashCode());
    }
}
