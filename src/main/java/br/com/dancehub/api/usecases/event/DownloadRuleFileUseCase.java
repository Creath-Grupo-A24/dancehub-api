package br.com.dancehub.api.usecases.event;

import br.com.dancehub.api.event.EventEntity;
import br.com.dancehub.api.event.EventRepository;
import br.com.dancehub.api.exceptions.NotFoundEntityException;
import br.com.dancehub.api.utils.UUIDUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DownloadRuleFileUseCase {

    final private EventRepository eventRepository;

    public byte[] execute(String id) {
        final EventEntity event = eventRepository.findById(UUIDUtils.getFromString(id)).orElseThrow(() -> new NotFoundEntityException(EventEntity.class, id));
        if (event.getFileName() != null && !event.getFileName().isBlank()) {
            try {
                return Files.readAllBytes(Path.of("C:\\uploads\\" + event.getFileName() + ".pdf"));
            } catch (IOException ignored) {
            }
        }
        return null;
    }

}
