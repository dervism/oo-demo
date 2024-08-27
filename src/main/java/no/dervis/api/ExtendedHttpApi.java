package no.dervis.api;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * An extended version of HttpApi with methods to handle date and time properly.
 * This class does not depend on JSR-310 extension to Jackson-DataBind.
 */

public class ExtendedHttpApi {

    private static final String ISO_DATETIME_REGEX = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}";
    private static final String NB_ISO_DATE_TIME_REGEX = "\\d{2}-\\d{2}-\\d{4}T\\d{2}:\\d{2}:\\d{2}";
    private static final String NB_ISO_DATE_TIME_PATTERN = "dd-MM-yyyy'T'HH:mm:ss";
    private static final String NB_DATE_REGEX = "\\d{2}-\\d{2}-\\d{4}";
    private static final String NB_DATE_PATTERN = "dd-MM-yyyy";
    private static final String NB_DATE_TIME_PATTERN = "dd-MM-yyyy HH:mm:ss";
    private static final String EN_DATE_FORMAT_REGEX = "\\d{4}-\\d{2}-\\d{2}";
    private static final String EN_DATE_FORMAT_PATTERN = "yyyy-MM-dd";
    private static final String TIME_REGEX = "\\d{2}:\\d{2}:\\d{2}";
    private static final String TIME_PATTERN = "HH:mm:ss";
    private static final String TIME_SHORT_REGEX = "\\d{2}:\\d{2}";
    private static final String TIME_SHORT_PATTERN = "HH:mm";

    private final HttpClient client;

    public ExtendedHttpApi() {
        this.client = HttpClient.newHttpClient();
    }

    public ExtendedHttpApi(HttpClient client) {
        this.client = client;
    }

    public <T> List<T> getList(Class<T> clazz, String url) {
        try(client) {

            // Create HttpRequest instance
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            // Send a GET request and get HttpResponse
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Get String response body
            String responseBody = response.body();

            // Create ObjectMapper instance
            ObjectMapper objectMapper = getObjectMapperWithDateConverter();

            // Convert JSON string to List of objects
            return objectMapper.readValue(responseBody, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sender et Java-objekt som JSON til den angitte URL-en via en HTTP POST-forespørsel.
     *
     * @param <T> typen på objektet som skal sendes
     * @param t   objektet som skal sendes
     * @param url URL-en som objektet skal sendes til
     * @return true hvis HTTP-responskoden er en 2xx tall (alt var ok), false ellers (en feil har skjedd)
     * @throws RuntimeException hvis det oppstår en feil under operasjonen
     */
    public <T> HttpPostRespons postJavaObjectToAPI(T t, String url) throws RuntimeException {
        try (client) {
            // Create ObjectMapper instance
            ObjectMapper objectMapper = getObjectMapperWithDateConverter();

            // Convert Java object to JSON string
            String requestBody = objectMapper.writeValueAsString(t);

            // Create HttpRequest instance
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            // Send the request and get HttpResponse
            return new HttpPostRespons(client.send(request, HttpResponse.BodyHandlers.ofString()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public record HttpPostRespons(HttpResponse<String> response) {
        public boolean isSuccessful() {
            return response.statusCode() >= 200 && response.statusCode() < 300;
        }

        public boolean isError() {
            return !isSuccessful();
        }
    }

    private ObjectMapper getObjectMapperWithDateConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule customDateModule = new SimpleModule();
        customDateModule.addSerializer(LocalDateTime.class, new JsonSerializer<>() {
            @Override
            public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                gen.writeString(konverterDatoTilJSONString(value));
            }
        });

        customDateModule.addDeserializer(LocalDateTime.class, new JsonDeserializer<>() {
            @Override
            public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
                DateTimeFormatter formatter = switch (p.getText()) {
                    case String text when text.matches(ISO_DATETIME_REGEX) ->
                            DateTimeFormatter.ISO_LOCAL_DATE_TIME;
                    case String text when text.matches(NB_ISO_DATE_TIME_REGEX) ->
                            DateTimeFormatter.ofPattern(NB_ISO_DATE_TIME_PATTERN);
                    default -> throw new IllegalArgumentException("Unsupported date format: " + p.getText());
                };
                return LocalDateTime.parse(p.getText(), formatter);
            }
        });

        customDateModule.addDeserializer(LocalDate.class, new JsonDeserializer<>() {
            @Override
            public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
                DateTimeFormatter formatter = switch (p.getText()) {
                    case String text when text.matches(NB_DATE_REGEX) ->
                            DateTimeFormatter.ofPattern(NB_DATE_PATTERN);
                    case String text when text.matches(EN_DATE_FORMAT_REGEX) ->
                            DateTimeFormatter.ofPattern(EN_DATE_FORMAT_PATTERN);
                    default -> throw new IllegalArgumentException("Unsupported date format: " + p.getText());
                };

                return LocalDate.parse(p.getText(), formatter);
            }
        });

        customDateModule.addDeserializer(LocalTime.class, new JsonDeserializer<>() {
            @Override
            public LocalTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
                DateTimeFormatter formatter = switch (p.getText()) {
                    case String text when text.matches(TIME_REGEX) ->
                            DateTimeFormatter.ofPattern(TIME_PATTERN);
                    case String text when text.matches(TIME_SHORT_REGEX) ->
                            DateTimeFormatter.ofPattern(TIME_SHORT_PATTERN);
                    default -> throw new IllegalArgumentException("Unsupported date format: " + p.getText());
                };
                return LocalTime.parse(p.getText(), formatter);
            }
        });

        objectMapper.registerModule(customDateModule);
        return objectMapper;
    }

    private String konverterDatoTilJSONString(Object dateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(NB_DATE_TIME_PATTERN);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(NB_DATE_PATTERN);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(TIME_PATTERN);

        String formattedString = switch (dateTime) {
            case LocalDateTime localDateTime -> localDateTime.format(dateTimeFormatter);
            case LocalDate localDate -> localDate.format(dateFormatter);
            case LocalTime localTime -> localTime.format(timeFormatter);
            case null, default ->
                    throw new IllegalArgumentException("Dato typen må være enten LocalDateTime, LocalDate, eller LocalTime");
        };

        try {
            return new ObjectMapper().writeValueAsString(formattedString);
        } catch (Exception e) {
            throw new RuntimeException("Error converting date/time to JSON", e);
        }
    }
}
