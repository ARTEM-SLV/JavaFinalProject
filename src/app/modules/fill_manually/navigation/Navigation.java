package app.modules.fill_manually.navigation;

import java.util.Map;

public class Navigation<K, V> {

    private final String firstMessage;
    private final Map<K, V> options;
    private final String optionsMessage;

    private Navigation(Builder<K, V> builder) {
        this.firstMessage = builder.firstMessage;
        this.optionsMessage = builder.optionsMessage;
        this.options = builder.options;
    }

    public String getFirstMessage() {
        return this.firstMessage;
    }

    public Map<K, V> getOptions() {
        return this.options;
    }

    public String getOptionsMessage() {
        return this.optionsMessage;
    }

    public static class Builder<K, V> {

        private String firstMessage;
        private Map<K, V> options;
        private String optionsMessage;

        public Builder<K, V> firstMessage(String message) {
            this.firstMessage = message;
            return this;
        }

        public Builder<K, V> optionsMessage(String message) {
            this.optionsMessage = message;
            return this;
        }

        public Builder<K, V> options(Map<K, V> map) {
            this.options = map;
            return this;
        }

        public Navigation<K, V> build() {
            return new Navigation<>(this);
        }
    }
}
