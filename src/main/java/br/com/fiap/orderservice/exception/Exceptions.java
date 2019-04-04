package br.com.fiap.orderservice.exception;

import org.apache.commons.lang3.StringUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Exceptions {

    public static String notFound(String entity, Map<String, String> searchParams) {
        return String.format("%s não foi encontrado. %s",
                StringUtils.capitalize(entity), searchParams);
    }

    public static String notUpdated(String entity, Map<String, String> searchParams) {
        return String.format("%s não foi possível atualizar. %s",
                StringUtils.capitalize(entity), searchParams);
    }

    public static <K, V> Map<K, V> toMap(Class<K> keyType, Class<V> valueType, String... entries) {
        if (entries.length % 2 == 1) {
            throw new IllegalArgumentException("Entrada inválida");
        }
        return IntStream.range(0, entries.length / 2).map(i -> i * 2)
                .collect(HashMap::new,
                        (m, i) -> m.put(keyType.cast(entries[i]), valueType.cast(entries[i + 1])),
                        Map::putAll);
    }

}