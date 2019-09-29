package com.max.gyst;

import com.max.gyst.util.FilterUtil;
import org.junit.Test;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class FilterUtilTest {

    @Test
    public void shouldFilter() {

        TestMasterObject testMasterObject1 = new TestMasterObject(new TestObject(1, "max"));
        TestMasterObject testMasterObject2 = new TestMasterObject(new TestObject(2, "vika"));

        List<TestMasterObject> testObjects = Stream.of(testMasterObject1, testMasterObject2).collect(Collectors.toList());

        FilterUtil<TestMasterObject> underTest = new FilterUtil<>(testObjects);
        List<TestMasterObject> result = underTest
                .filterIfNotNull(TestMasterObject::getTestObject, new TestObject(2, "vika"))
                .getSource();

        assertThat(result).containsOnly(testMasterObject2);
    }

    private class TestMasterObject {

        private TestObject testObject;

        TestMasterObject(TestObject testObject) {
            this.testObject = testObject;
        }

        TestObject getTestObject() {
            return testObject;
        }
    }


    public class TestObject {

        private int id;
        private String name;

        TestObject(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TestObject that = (TestObject) o;
            return id == that.id &&
                    Objects.equals(name, that.name);
        }


        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }

}