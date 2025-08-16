package com.pablodev.shared.infrastructure.event.kafka.customization;

import java.util.function.Consumer;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class KafkaListenerEndpointCustomizerBuilder {

    private Consumer<KafkaListenerEndpointsConfigurer> consumer;

    public KafkaListenerEndpointCustomizerBuilder configureListeners(
            Consumer<KafkaListenerEndpointsConfigurer> consumer) {
        return new KafkaListenerEndpointCustomizerBuilder(consumer);
    }

    public KafkaListenerEndpointCustomizer build() {
        return new KafkaListenerEndpointCustomizer(consumer);
    }

}
