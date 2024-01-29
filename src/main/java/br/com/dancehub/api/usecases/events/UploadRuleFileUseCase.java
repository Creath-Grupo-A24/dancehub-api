package br.com.dancehub.api.usecases.events;

import br.com.dancehub.api.event.EventEntity;
import br.com.dancehub.api.event.EventRepository;
import br.com.dancehub.api.exceptions.NotFoundEntityException;
import br.com.dancehub.api.utils.UUIDUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UploadRuleFileUseCase {

    private final EventRepository eventRepository;

    public boolean execute(MultipartFile multipartFile, String id) {
        if (multipartFile == null || multipartFile.isEmpty()) return false;
        final EventEntity event = eventRepository.findById(UUIDUtils.getFromString(id)).orElseThrow(() -> new NotFoundEntityException(EventEntity.class, id));
        if (event.getFileName() != null && !event.getFileName().isBlank()){
            new File("C:\\uploads\\" + event.getFileName() + ".pdf").delete();
        }
        final String fileName = Objects.requireNonNull(multipartFile.getOriginalFilename()).replace(".pdf", "") + new Date().getTime();
        final File file = new File("C:\\uploads\\" + fileName + ".pdf");
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            return false;
        }
        event.setFileName(fileName);
        eventRepository.save(event);
        return true;
    }

}
