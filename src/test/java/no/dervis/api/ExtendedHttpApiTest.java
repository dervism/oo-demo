package no.dervis.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

public class ExtendedHttpApiTest {

    @Test
    public void testGetJSONArrayAsList() throws IOException, InterruptedException {
        String url = "http://example.com";
        Class<String> clazz = String.class;

        HttpClient clientMock = Mockito.mock(HttpClient.class);
        ExtendedHttpApi ExtendedHttpApi = new ExtendedHttpApi(clientMock);
        ExtendedHttpApi api = Mockito.spy(ExtendedHttpApi);

        HttpResponse responseMock = Mockito.mock(HttpResponse.class);

        Mockito.when(clientMock.send(any(), any())).thenReturn(responseMock);
        Mockito.when(responseMock.body()).thenReturn("[\"value1\", \"value2\", \"value3\"]");

        List<String> list = api.getJSONArrayAsList(clazz, url);

        Assertions.assertNotNull(list);
        Assertions.assertSame(3, list.size());
        Assertions.assertEquals("value1", list.get(0));
        Assertions.assertEquals("value2", list.get(1));
        Assertions.assertEquals("value3", list.get(2));
    }

    @Test
    public void testGetJSONArrayAsListWhenResponseIsEmpty() throws IOException, InterruptedException {
        String url = "http://example2.com";
        Class<String> clazz = String.class;

        HttpClient clientMock = Mockito.mock(HttpClient.class);
        ExtendedHttpApi ExtendedHttpApi = new ExtendedHttpApi(clientMock);
        ExtendedHttpApi api = Mockito.spy(ExtendedHttpApi);

        HttpResponse responseMock = Mockito.mock(HttpResponse.class);

        Mockito.when(clientMock.send(any(), any())).thenReturn(responseMock);
        Mockito.when(responseMock.body()).thenReturn("[]");

        List<String> list = api.getJSONArrayAsList(clazz, url);

        Assertions.assertNotNull(list);
        Assertions.assertTrue(list.isEmpty());
    }

    @Test
    public void testGetJSONArrayAsJavaListWhenResponseIsNotValidJson() throws IOException, InterruptedException {
        String url = "http://example3.com";
        Class<String> clazz = String.class;

        HttpClient clientMock = Mockito.mock(HttpClient.class);
        ExtendedHttpApi ExtendedHttpApi = new ExtendedHttpApi(clientMock);
        ExtendedHttpApi api = Mockito.spy(ExtendedHttpApi);

        HttpResponse responseMock = Mockito.mock(HttpResponse.class);

        Mockito.when(clientMock.send(any(), any())).thenReturn(responseMock);
        Mockito.when(responseMock.body()).thenReturn("not a json response");

        Assertions.assertThrows(RuntimeException.class, () -> api.getJSONArrayAsList(clazz, url));
    }

    @Test
    public void testGetJSONArrayAsJavaListWhenResponseIsInvalidJsonArray() throws IOException, InterruptedException {
        String url = "http://example4.com";
        Class<String> clazz = String.class;

        HttpClient clientMock = Mockito.mock(HttpClient.class);
        ExtendedHttpApi ExtendedHttpApi = new ExtendedHttpApi(clientMock);
        ExtendedHttpApi api = Mockito.spy(ExtendedHttpApi);

        HttpResponse responseMock = Mockito.mock(HttpResponse.class);

        Mockito.when(clientMock.send(any(), any())).thenReturn(responseMock);
        Mockito.when(responseMock.body()).thenReturn("[\"value1\", \"value2\",}");

        Assertions.assertThrows(RuntimeException.class, () -> api.getJSONArrayAsList(clazz, url));
    }

    @Test
    public void testGetJSONArrayAsListWhenResponseContainsInteger() throws IOException, InterruptedException {
        String url = "http://example5.com";
        Class<Integer> clazz = Integer.class;

        HttpClient clientMock = Mockito.mock(HttpClient.class);
        ExtendedHttpApi ExtendedHttpApi = new ExtendedHttpApi(clientMock);
        ExtendedHttpApi api = Mockito.spy(ExtendedHttpApi);

        HttpResponse responseMock = Mockito.mock(HttpResponse.class);

        Mockito.when(clientMock.send(any(), any())).thenReturn(responseMock);
        Mockito.when(responseMock.body()).thenReturn("[1, 2, 3]");

        List<Integer> list = api.getJSONArrayAsList(clazz, url);

        Assertions.assertNotNull(list);
        Assertions.assertSame(3, list.size());
        Assertions.assertEquals(1, list.get(0));
        Assertions.assertEquals(2, list.get(1));
        Assertions.assertEquals(3, list.get(2));
    }

    @Test
    public void testPostJavaObjectToAPI() throws Exception {
        // Prepare data and mocks
        String url = "http://example.com";
        String mockObject = "This is a test";

        HttpClient clientMock = Mockito.mock(HttpClient.class);
        ExtendedHttpApi api = new ExtendedHttpApi(clientMock);

        HttpResponse responseMock = Mockito.mock(HttpResponse.class);

        // Mock behaviors
        Mockito.when(responseMock.statusCode()).thenReturn(200);
        Mockito.when(clientMock.send(any(), any())).thenReturn(responseMock);

        // Call the method under test
        ExtendedHttpApi.HttpPostRespons response = api.postJavaObjectToAPI(mockObject, url);

        // Check result
        Assertions.assertNotNull(response);
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertFalse(response.isError());
    }

    @Test
    public void testPostJavaObjectToAPIWhenServerReturnsError() throws Exception {
        // Prepare data and mocks
        String url = "http://example.com";
        String mockObject = "This is a test";

        HttpClient clientMock = Mockito.mock(HttpClient.class);
        ExtendedHttpApi api = new ExtendedHttpApi(clientMock);

        HttpResponse responseMock = Mockito.mock(HttpResponse.class);

        // Mock behaviors
        Mockito.when(responseMock.statusCode()).thenReturn(500);
        Mockito.when(clientMock.send(any(), any())).thenReturn(responseMock);

        // Call the method under test
        ExtendedHttpApi.HttpPostRespons response = api.postJavaObjectToAPI(mockObject, url);

        // Check result
        Assertions.assertNotNull(response);
        Assertions.assertFalse(response.isSuccessful());
        Assertions.assertTrue(response.isError());
    }

    @Test
    public void testGetJSONArrayAsListWithDateResponse() throws IOException, InterruptedException {
        String url = "http://example6.com";

        HttpClient clientMock = Mockito.mock(HttpClient.class);
        ExtendedHttpApi ExtendedHttpApi = new ExtendedHttpApi(clientMock);
        ExtendedHttpApi api = Mockito.spy(ExtendedHttpApi);

        HttpResponse responseMock = Mockito.mock(HttpResponse.class);

        Mockito.when(clientMock.send(any(), any())).thenReturn(responseMock);
        Mockito.when(responseMock.body()).thenReturn("[\"2024-05-01\", \"2024-02-14\",\"2024-07-05\"]");

        List<LocalDate> list = api.getJSONArrayAsList(LocalDate.class, url);

        Assertions.assertNotNull(list);
        Assertions.assertEquals(3, list.size());
        LocalDate actual = list.get(0);

        Assertions.assertEquals(LocalDate.parse("2024-05-01"), actual);
        Assertions.assertEquals(LocalDate.parse("2024-02-14"), list.get(1));
        Assertions.assertEquals(LocalDate.parse("2024-07-05"), list.get(2));
    }

    @Test
    public void testGetJSONArrayAsListWithDateTimeResponse() throws IOException, InterruptedException {
        String url = "http://example6.com";

        HttpClient clientMock = Mockito.mock(HttpClient.class);
        ExtendedHttpApi ExtendedHttpApi = new ExtendedHttpApi(clientMock);
        ExtendedHttpApi api = Mockito.spy(ExtendedHttpApi);

        HttpResponse responseMock = Mockito.mock(HttpResponse.class);

        Mockito.when(clientMock.send(any(), any())).thenReturn(responseMock);
        Mockito.when(responseMock.body()).thenReturn("[\"2024-05-01T12:07:02\", \"2024-02-14T14:40:00\",\"2024-07-05T19:30:00\"]");

        List<LocalDateTime> list = api.getJSONArrayAsList(LocalDateTime.class, url);

        Assertions.assertNotNull(list);
        Assertions.assertEquals(3, list.size());
        LocalDateTime actual = list.get(0);

        Assertions.assertEquals(LocalDateTime.parse("2024-05-01T12:07:02"), actual);
        Assertions.assertEquals(LocalDateTime.parse("2024-02-14T14:40:00"), list.get(1));
        Assertions.assertEquals(LocalDateTime.parse("2024-07-05T19:30:00"), list.get(2));
    }

    record Meeting(LocalDateTime datetime, String name) {}

    @Test
    public void testPostMeetingObjectToAPI() throws Exception {
        // Prepare data and mocks
        String url = "http://example.com";
        Meeting mockObject = new Meeting(LocalDateTime.now(), "Test Meeting");

        HttpClient clientMock = Mockito.mock(HttpClient.class);
        ExtendedHttpApi api = new ExtendedHttpApi(clientMock);

        HttpResponse responseMock = Mockito.mock(HttpResponse.class);

        // Mock behaviors
        Mockito.when(responseMock.statusCode()).thenReturn(200);
        Mockito.when(clientMock.send(any(), any())).thenReturn(responseMock);

        // Call the method under test
        ExtendedHttpApi.HttpPostRespons response = api.postJavaObjectToAPI(mockObject, url);

        // Check result
        Assertions.assertNotNull(response);
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertFalse(response.isError());
    }

    @Test
    public void testGetMeetingListFromAPI() throws IOException, InterruptedException {
    String url = "http://example.com";

    HttpClient clientMock = Mockito.mock(HttpClient.class);
        ExtendedHttpApi ExtendedHttpApi = new ExtendedHttpApi(clientMock);
        ExtendedHttpApi api = Mockito.spy(ExtendedHttpApi);

        HttpResponse responseMock = Mockito.mock(HttpResponse.class);

        Mockito.when(clientMock.send(any(), any())).thenReturn(responseMock);
        Mockito.when(responseMock.body()).thenReturn("[{\"datetime\":\"2024-05-01T12:07:02\", \"name\":\"Meeting1\"}, {\"datetime\":\"2024-02-14T14:40:00\", \"name\":\"Meeting2\"},{\"datetime\":\"2024-07-05T19:30:00\", \"name\":\"Meeting3\"}]");

        List<Meeting> list = api.getJSONArrayAsList(Meeting.class, url);

        Assertions.assertNotNull(list);
        Assertions.assertEquals(3, list.size());
        Meeting actual = list.get(0);
        Assertions.assertEquals(LocalDateTime.parse("2024-05-01T12:07:02"), actual.datetime());
        Assertions.assertEquals("Meeting1", actual.name());
        Assertions.assertEquals(LocalDateTime.parse("2024-02-14T14:40:00"), list.get(1).datetime());
        Assertions.assertEquals("Meeting2", list.get(1).name());
        Assertions.assertEquals(LocalDateTime.parse("2024-07-05T19:30:00"), list.get(2).datetime());
        Assertions.assertEquals("Meeting3", list.get(2).name());
    }


}

