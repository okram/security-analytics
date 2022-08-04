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

public class QueryTests extends OpenSearchTestCase {

    public void testWriterReader() throws Exception {
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

    public void testWriterReaderWithEmptyLists() throws Exception {
        final Query query = new Query("one", "two", "three", List.of());
        final BytesStreamOutput output = new BytesStreamOutput();
        ModelSerializer.write(output, query);
        final BytesStreamInput input = new BytesStreamInput(output.bytes().toBytesRef().bytes);
        final Query query2 = ModelSerializer.read(input, Query.class);
        assertEquals("one", query2.id);
        assertEquals("two", query2.name);
        assertEquals("three", query2.query);
        assertEquals(List.of(), query2.tags);
    }

    public void testHashCodeEquality() throws Exception {
        final Query a = new Query("one", "two", "three", List.of("a", "b", "c"));
        final Query b = new Query("one", "two", "three", List.of("a", "b", "c"));
        final Query c = new Query("two", "two", "three", List.of("a", "b", "c"));
        final Query d = new Query("one", "two", "three", List.of("a", "c"));
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
